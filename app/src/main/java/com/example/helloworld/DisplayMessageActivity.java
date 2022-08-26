package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.dto.ErrorDTO;
import com.example.helloworld.dto.LoanAndPersonDTO;
import com.example.helloworld.dto.LoanDTO;
import com.example.helloworld.dto.PersonDTO;
import com.example.helloworld.model.LoanEntity;
import com.example.helloworld.model.PersonEntity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import okhttp3.*;


public class DisplayMessageActivity extends AppCompatActivity {

    private PersonEntity person;
    private Button createNewLoanButton, watchingFreeRequestsButton;
    public static final String LOANS = "com.example.helloworld.LOANS";
    private final OkHttpClient httpClient = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Intent intent = getIntent();
        person = (PersonEntity) intent.getSerializableExtra(MainActivity.PERSON);
        TextView personNameView = (TextView) findViewById(R.id.personNameView);
        TextView moneyTextView = (TextView) findViewById(R.id.moneyTextView);
        personNameView.setText(person.getFirstName());
        moneyTextView.setText(person.getMoney().toString());
        createNewLoanButton = (Button) findViewById(R.id.createNewLoanButton);
        createNewLoanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogForCreationOfNewLoan();
            }
        });
        watchingFreeRequestsButton = (Button) findViewById(R.id.watchingFreeRequestsButton);
        watchingFreeRequestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListOfLoansAndLendMoney();
            }
        });
    }

    public void openDialogForCreationOfNewLoan() {
        CreationOfNewLoanDialog creationOfNewLoanDialog = new CreationOfNewLoanDialog(person);
        creationOfNewLoanDialog.show(getSupportFragmentManager(), "Info");
    }
    public void getListOfLoansAndLendMoney() {


            Request request = new Request.Builder()
                    .url("https://chups.xyz/stepuha/api/loan/free-requests?personId="+person.getId())
                    .build();
            httpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    String jsonString = response.body().string();
                    if (jsonString.contains("ERROR")) {
                        ErrorDTO errorDTO = new Gson().fromJson(jsonString, ErrorDTO.class);
                        Toast.makeText(DisplayMessageActivity.this, errorDTO.getError().getMessage(), Toast.LENGTH_LONG).show();
                        return;
                    }
                    LoanAndPersonDTO loanAndPersonDTO = new Gson().fromJson(jsonString, LoanAndPersonDTO.class);
                    Intent intent = new Intent(DisplayMessageActivity.this, LoanListActivity.class);
                    intent.putExtra(LOANS, loanAndPersonDTO);
                    startActivity(intent);
                }
            });

    }
}
