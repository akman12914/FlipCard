package com.example.flipcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class gameOverActivity extends AppCompatActivity {

    Button gameOver;
    Button retry;
    TextView card;
    String remainCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameoverdialog);

        Intent intent=getIntent();
        remainCard=intent.getStringExtra("remainCard");
        gameOver = findViewById(R.id.gameOver);
        retry = findViewById(R.id.retry);
        card = findViewById(R.id.time);
        card.setText("남은 카드: "+remainCard);

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
