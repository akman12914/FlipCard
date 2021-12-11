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
    boolean choose; // 타임어택과 챌린지 구분용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamecleardialog);

        Intent intent=getIntent();
        remainTime=intent.getStringExtra("remainTime");
        choose = intent.getBooleanExtra("choose",true);
        gameOver = findViewById(R.id.gameOver);
        retry = findViewById(R.id.retry);
        time = findViewById(R.id.time);
        if(choose){
        time.setText("남은 시간: "+remainTime);}
        else{
            time.setText("걸린 시간: "+remainTime);
        }

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

                if(choose){
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);}
                else{
                    Intent intent = new Intent(getApplicationContext(),ChallengeActivity.class);
                    startActivity(intent);
                }


            }
        });

    }
}
