package com.example.bali_ratn_island;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tv;
    ProgressBar prb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("BALI RATN ISLAND");

        startService(new Intent(this,music_service.class));
        prb=findViewById(R.id.prb);
        imageView=findViewById(R.id.applogoiv);
        tv=findViewById(R.id.wl_tv);

        tv.setVisibility(View.GONE);
        prb.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                TranslateAnimation animation1=new TranslateAnimation(4000.0f,0.0f,0.0f,0.0f);
                animation1.setDuration(3000);
                animation1.setRepeatMode(1);
                animation1.setFillAfter(true);
                imageView.setAnimation(animation1);

                tv.setVisibility(View.GONE);
            }
        },0);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TranslateAnimation animation2 = new TranslateAnimation(0.0f, 0.0f, 4000.0f, 0.0f);
                animation2.setDuration(3000);
                // if you want infinite
                // animation.setRepeatCount(Animation.INFINITE);
                animation2.setRepeatMode(1);
                animation2.setFillAfter(true);
                tv.startAnimation(animation2);

                tv.setVisibility(View.VISIBLE);

            }
        }, 3000);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                prb.setVisibility(View.VISIBLE);

            }
        }, 7000);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,home.class));

            }
        },9000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}