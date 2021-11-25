package com.example.flipcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    }
    public void onClick11(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
//        Toast.makeText(this, "askdjao", Toast.LENGTH_SHORT).show();
    }
}
