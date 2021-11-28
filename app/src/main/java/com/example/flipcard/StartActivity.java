package com.example.flipcard;

import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        Button TimeAttack = findViewById(R.id.TimeAttack);
        TimeAttack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), difficultyActivity.class);
                startActivity(intent);
            }

        });

        Button MyPage = (Button)findViewById(R.id.MyPage);
        MyPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyActivity.class);
                startActivity(intent);
            }

        });


    }

    public void onClick11(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
//        Toast.makeText(this, "askdjao", Toast.LENGTH_SHORT).show();
    }






}
