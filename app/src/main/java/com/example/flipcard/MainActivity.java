package com.example.flipcard;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TimeCount hanyeol = new TimeCount();

    //타이머변수설정
    private static final long START_TIME_IN_MILLIS = 300000; // 300000, 180000, 60000
    private long Minus_START_TIME_IN_MILLIS=hanyeol.getcount();

    private TextView mTextViewCountDown;
    private Button mButtonStartPause;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS-Minus_START_TIME_IN_MILLIS;

    private CardButton selected_card = null;
    private CardButton current_card = null;
    private boolean card_mismatch = false;

    private CardButton[][] buttons;

    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected_card = null;
        current_card = null;
        card_mismatch = false;

        //activity_main에서 00:00 부분과 시작버튼 가져옴
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        //시작버튼에 리스너지정
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });
        //시간띄워주는 함수
        updateCountDownText();

        Button gameoverbutton = findViewById(R.id.button);
        gameoverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), gameOverActivity.class);
                startActivity(intent);
            }

        });
        final TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        tableLayout.setShrinkAllColumns(true);
        buttons = new CardButton[4][4]; //나중에 새로운 클래스 만들어서 바꿔야할듯
        // -> CardButton이라는 BlockButton과 같은 역할을 하는 클래스 생성
        for (int i = 0; i < 4; i++) {
            final TableRow tableRow = new TableRow(this);
            for (int j = 0; j < 4; j++) {
//                buttons[i][j]= new ImageButton(this,i,j);
                buttons[i][j] = new CardButton(this, i, j);
                Resources res = getResources();
                BitmapDrawable bitmapDrawable = (BitmapDrawable) res.getDrawable(R.drawable.card_back);
                Bitmap bitmap = bitmapDrawable.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, 100, 110, true);
                buttons[i][j].setImageBitmap(bitmap);
                buttons[i][j].setBackgroundColor(Color.TRANSPARENT);
                buttons[i][j].setLayoutParams
                        (new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
                tableRow.addView(buttons[i][j]);

            }
            tableLayout.addView(tableRow);
        }

        int x = 0;
        int y = 0; //버튼에 순차적으로 접근하기 위한 인덱스값 (세로부터 접근 0,0->1,0->2,0)
        Random random = new Random();
        Integer[] random_card = new Integer[16]; //지금까지 나왔던 숫자가 뭐였는지 기록
        Integer[] check_overlap = new Integer[8];//1~8까지 어느 숫자가 몇번이나 나왔는지 기록
        Arrays.fill(check_overlap, 0);
        for (int i = 0; i < 16; i++) { //16개의 숫자를 뽑아야하므로 16번
//            int count=0;
            int randomNumber = random.nextInt(8) + 1;
            random_card[i] = randomNumber;
            if (i == 0) { // 0인 경우에는 처리하기 힘들어서 따로 빼놨습니다.
                check_overlap[randomNumber - 1]++;
                buttons[x][y].cardNumber = randomNumber;
                x++;
            }
            if (check_overlap[randomNumber - 1] == 0) { //처음 나오는 숫자들을 처리하는 부분
                check_overlap[randomNumber - 1]++;
                if (x > 3) { //x인덱스가 3까지라서 초기화를 한번 해줍니다
                    x = 0;
                    y += 1;
                }
                buttons[x][y].cardNumber = randomNumber;
                x++;
            }
            for (int j = 0; j < i; j++) { // 중복제거 기존 지뢰찾기 코드랑 구성은 똑같습니다.
                if (random_card[i] == random_card[j]) {
                    if (check_overlap[randomNumber - 1] == 2) { // 같은 카드는 2개여야 하기에 2번까지만 중복되도록 숫자 생성
                        i--;
                        break;
                    }
                    check_overlap[randomNumber - 1]++;
                    if (x > 3) {
                        x = 0;
                        y += 1;
                    }
                    buttons[x][y].cardNumber = randomNumber;
                    x++;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CardButton card = (CardButton)v;
                        if ( card_mismatch ) {
                            if ( current_card != null ) {
                                current_card.coverCard();
                            }
                            if ( selected_card != null ) {
                                selected_card.coverCard();
                            }
                            card_mismatch = false;
                            current_card = null;
                            selected_card = null;
                            return;
                        }
                        if ( !card.isFlipped() ) {
                            card.flipCard();
                            current_card = card;
                            if ( selected_card != null ) {
                                if ( selected_card.cardNumber == card.cardNumber ) {
                                    card.setMatched(true);
                                    selected_card.setMatched((true));
                                    selected_card = null;
                                    current_card = null;
                                    boolean all_matched = true;
                                    for (int i = 0; i < 4; i++) {
                                        for (int j = 0; j < 4; j++) {
                                            if (!buttons[i][j].isMatched()) {
                                                all_matched = false;
                                                break;
                                            }
                                        }
                                        if (!all_matched) {
                                            break;
                                        }
                                    }
                                    if ( all_matched ) {
                                        // setup the alert builder
                                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                        builder.setTitle("카드게임");
                                        builder.setMessage("성공!!!");

                                        // add a button
                                        builder.setPositiveButton("OK", null);

                                        // create and show the alert dialog
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                                else {
                                    card_mismatch = true;
//                                    try {
//                                        Thread.sleep(1000);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
//
//                                    card.coverCard();
//                                    selected_card.coverCard();
//                                    selected_card = null;
                                }
                            }
                            else {
                                selected_card = card;
                            }
                        }
                    }
                });

            }
        }
    }
    //타이머 함수
    //타이머시작
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("시작하기");
                mButtonStartPause.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("멈춤");
    }
    //타이머 멈춤
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("시작하기");
    }
    //시간표시
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }


}
