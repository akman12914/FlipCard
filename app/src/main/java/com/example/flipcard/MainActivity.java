package com.example.flipcard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button gameoverbutton = findViewById(R.id.button);
        gameoverbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), gameOverActivity.class);
                startActivity(intent);
            }

        });
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        tableLayout.setShrinkAllColumns(true);
        ImageButton[][] buttons = new ImageButton[4][4]; //나중에 새로운 클래스 만들어서 바꿔야할듯
        for (int i = 0; i < 4; i++) {
            final TableRow tableRow = new TableRow(this);
            for (int j = 0; j < 4; j++) {
//                buttons[i][j]= new ImageButton(this,i,j);
                buttons[i][j]= new ImageButton(this);
                Resources res = getResources();
                BitmapDrawable bitmapDrawable = (BitmapDrawable)res.getDrawable( R.drawable.card_back);
                Bitmap bitmap = bitmapDrawable.getBitmap();
                bitmap=Bitmap.createScaledBitmap(bitmap, 100,110,true);
                buttons[i][j].setImageBitmap( bitmap );
                buttons[i][j].setBackgroundColor( Color.TRANSPARENT );
//                    @Override
//                    public void onClick(View v) {
//                    }
//                });
                buttons[i][j].setLayoutParams
                        (new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
                tableRow.addView(buttons[i][j]);

            }
            tableLayout.addView(tableRow);
        }
    }
}