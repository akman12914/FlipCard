package com.example.flipcard;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageButton;
import android.widget.TableRow;

public class CardButton extends ImageButton {
    int x;
    int y;
    public int cardNumber; //각 버튼이 가질 카드의 번호
    public CardButton(Context context, int x, int y){
        super(context);
        this.x=x;
        this.y=y;
    }

//    public void printnum(){
//        System.out.println(cardNumber);
//    }

    public void flipCard(){
        Resources res = getResources();
        BitmapDrawable bitmapDrawable;

        if(cardNumber==1)
            bitmapDrawable = (BitmapDrawable) res.getDrawable(R.drawable.ace);
        else if(cardNumber==2)
            bitmapDrawable = (BitmapDrawable) res.getDrawable(R.drawable.two);
        else if(cardNumber==3)
            bitmapDrawable = (BitmapDrawable) res.getDrawable(R.drawable.three);
        else if(cardNumber==4)
            bitmapDrawable = (BitmapDrawable) res.getDrawable(R.drawable.four);
        else if(cardNumber==5)
            bitmapDrawable = (BitmapDrawable) res.getDrawable(R.drawable.five);
        else if(cardNumber==6)
            bitmapDrawable = (BitmapDrawable) res.getDrawable(R.drawable.six);
        else if(cardNumber==7)
            bitmapDrawable = (BitmapDrawable) res.getDrawable(R.drawable.seven);
        else
            bitmapDrawable = (BitmapDrawable) res.getDrawable(R.drawable.eight);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        bitmap = Bitmap.createScaledBitmap(bitmap, 100, 110, true);
        this.setImageBitmap(bitmap);
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setLayoutParams
                (new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));

    }
}

