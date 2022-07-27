package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.helloworld.model.PersonEntity;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
         Intent intent = getIntent();
        PersonEntity person = (PersonEntity) intent.getSerializableExtra(MainActivity.PERSON);
        TextView personNameView = (TextView) findViewById(R.id.personNameView);
        TextView moneyTextView = (TextView) findViewById(R.id.moneyTextView);
        personNameView.setText(person.getFirstName());
        moneyTextView.setText(person.getMoney().toString());

    }
}