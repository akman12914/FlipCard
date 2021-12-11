package com.example.flipcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyActivity extends AppCompatActivity {

    Button home;
    EditText time;
    TimeCount hanyeol = new TimeCount();
    String record = hanyeol.getRecord();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        time = findViewById(R.id.editTextTextPersonName2);
        time.setText(record);

        home = findViewById(R.id.button3);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }

        });
    }

}
