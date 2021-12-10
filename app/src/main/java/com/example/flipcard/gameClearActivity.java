package com.example.flipcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class gameClearActivity extends AppCompatActivity {

    Button gameOver;
    Button retry;
    TextView time;
    String remainTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamecleardialog);

        Intent intent=getIntent();
        remainTime=intent.getStringExtra("remainTime");
        gameOver = findViewById(R.id.gameOver);
        retry = findViewById(R.id.retry);
        time = findViewById(R.id.time);
        time.setText("남은 시간: "+remainTime);

        gameOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StartActivity.class);
                startActivity(intent);
            }
        });
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
