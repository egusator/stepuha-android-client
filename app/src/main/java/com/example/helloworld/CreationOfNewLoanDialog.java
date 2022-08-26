package com.example.helloworld;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.helloworld.dto.ErrorDTO;
import com.example.helloworld.dto.PersonDTO;
import com.example.helloworld.model.PersonEntity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.math.BigDecimal;

import okhttp3.*;

public class CreationOfNewLoanDialog extends AppCompatDialogFragment {
    PersonEntity person;
    private final OkHttpClient httpClient = new OkHttpClient();
    CreationOfNewLoanDialog(PersonEntity person) {
        this.person = person;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setTitle("Input money");
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BigDecimal money = new BigDecimal(input.getText().toString());
                Long id = person.getId();

                RequestBody formBody = new FormBody.Builder()
                        .add("borrowerId", String.valueOf(person.getId()))
                        .add("money", money.toString())
                        .build();

                Request request = new Request.Builder()
                        .url("https://chups.xyz/stepuha/api/loan/create")
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
                            Toast.makeText(getActivity(), errorDTO.getError().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
