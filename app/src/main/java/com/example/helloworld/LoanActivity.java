package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.dto.ErrorDTO;
import com.example.helloworld.dto.LoanAndPersonDTO;
import com.example.helloworld.model.LoanAndPersonEntity;
import com.example.helloworld.model.PersonEntity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoanActivity extends AppCompatActivity {
    private Button lendingButton;
    private LoanAndPersonEntity loanAndPersonEntity;
    private final OkHttpClient httpClient = new OkHttpClient();
    private PersonEntity person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        Intent intent = getIntent();
        person = (PersonEntity) intent.getSerializableExtra("person");
        loanAndPersonEntity = (LoanAndPersonEntity) intent.getSerializableExtra("selected");

        TextView loginTextView = (TextView) findViewById(R.id.loginTextView);
        loginTextView.setText(loanAndPersonEntity.getPerson().getLogin());

        TextView moneyAmountTextView = (TextView) findViewById(R.id.moneyAmountTextView);
        moneyAmountTextView.setText(loanAndPersonEntity.getLoan().getMoney().toString());

        TextView dateTextView = (TextView) findViewById(R.id.dateTextView);
        Date date = new Date((long) loanAndPersonEntity.getLoan().getCreationTime()*1000);
        dateTextView.setText(date.toString());

        lendingButton = (Button) findViewById(R.id.lendingButton);
        lendingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lendMoney();
            }
        });
    }
    public void lendMoney() {
        Long loanId = loanAndPersonEntity.getLoan().getId();
        RequestBody formBody = new FormBody.Builder()
                .add("lenderId", String.valueOf(person.getId()))
                .add("loanId", String.valueOf(loanId))
                .build();

        Request request = new Request.Builder()
                .url("https://chups.xyz/stepuha/api/loan/lend")
                .post(formBody)
                .build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonString = response.body().string();
                if (jsonString.contains("ERROR")) {
                    ErrorDTO errorDTO = new Gson().fromJson(jsonString, ErrorDTO.class);
                }
                Intent intent = new Intent(LoanActivity.this, DisplayMessageActivity.class);
                intent.putExtra("person",person);
                startActivity(intent);
            }
        });
    }
}