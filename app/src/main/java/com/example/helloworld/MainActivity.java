package com.example.helloworld;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworld.dto.ErrorDTO;
import com.example.helloworld.dto.PersonDTO;
import okhttp3.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private final OkHttpClient httpClient = new OkHttpClient();
    public static final String PERSON = "com.example.helloworld.PERSON";
    public void authorize(View view) throws IOException {
        EditText editText = (EditText) findViewById(R.id.personLogin);
        String message = editText.getText().toString();

        message = message.replaceAll("[^A-Za-z0-9]", "");

        Request request = new Request.Builder()
                .url("https://chups.xyz/stepuha/api/person/get-by-login?login="+message)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);
                    String jsonString = responseBody.string();

                    if (jsonString.contains("ERROR")) {
                        ErrorDTO errorDTO = new Gson().fromJson(jsonString, ErrorDTO.class);
                        Toast.makeText(MainActivity.this, errorDTO.getError().getMessage(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    PersonDTO personDTO = new Gson().fromJson(jsonString, PersonDTO.class);
                    Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
                    intent.putExtra("person", personDTO.getPersonList().get(0));
                    startActivity(intent);

                }

            }

        });
}
}