package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.helloworld.dto.ErrorDTO;
import com.example.helloworld.dto.LoanAndPersonDTO;
import com.example.helloworld.dto.LoanDTO;
import com.example.helloworld.dto.PersonDTO;
import com.example.helloworld.model.LoanAndPersonEntity;
import com.example.helloworld.model.LoanEntity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;

public class LoanListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_list);
        Intent intent = getIntent();
        LoanAndPersonDTO loanAndPersonDTO = (LoanAndPersonDTO) intent.getSerializableExtra(DisplayMessageActivity.LOANS);

        ListView listview = (ListView) findViewById(R.id.loanListView);
        ArrayList<String> loanListStringArray = new ArrayList<>();



        for(LoanAndPersonEntity loanAndPerson: loanAndPersonDTO.getLoanAndPersonList()) {
            Date date = new Date((long) loanAndPerson.getLoan().getCreationTime()*1000);
            loanListStringArray.add(loanAndPerson.getPerson().getLogin()+ ", " + loanAndPerson.getLoan().getMoney().toString() + ", " + date.toString());
        }
        ArrayAdapter arrayAdapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_1, loanListStringArray);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.i("HelloListView", "You clicked Item: " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        intent.setClass(this, LoanActivity.class);
        intent.putExtra("position", position);
        // Or / And
        intent.putExtra("id", id);
        startActivity(intent);
    }
}