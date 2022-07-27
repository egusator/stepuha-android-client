package com.example.helloworld;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.helloworld.dto.ErrorDTO;
import com.example.helloworld.dto.PersonDTO;
import com.example.helloworld.model.AppError;
import com.example.helloworld.model.PersonEntity;
import com.example.helloworld.retrofit.PersonApi;
import com.example.helloworld.retrofit.RetrofitService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public static final String PERSON = "com.example.helloworld.PERSON";
    public void authorize(View view) throws IOException {
        EditText editText = (EditText) findViewById(R.id.personLogin);
        String message = editText.getText().toString();

        message = message.replaceAll("[^A-Za-z0-9]", ""); // удалится все кроме букв и цифр
        RetrofitService retrofitService = new RetrofitService();
        PersonApi personApi = retrofitService.getRetrofit().create(PersonApi.class);
        Call<JsonObject> call = personApi.getPersonByLogin(message);
        call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        String jsonString = response.body().toString();
                        if (jsonString.contains("ERROR")) {
                            ErrorDTO errorDTO = new Gson().fromJson(jsonString, ErrorDTO.class);
                            Toast.makeText(MainActivity.this, errorDTO.getError().getMessage(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        PersonDTO personDTO = new Gson().fromJson(jsonString, PersonDTO.class);
                        Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
                        intent.putExtra(PERSON,personDTO.getPersonList().get(0));
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Something happened", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}