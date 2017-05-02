package com.example.rahul.hm14_pandya;

// Rahul Pandya
// L20355202


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TitleActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    boolean quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        quit = false;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(! quit) {
                    toMainActivity();
                }
            }
        }, 2000);
    }

    private void toMainActivity() {
        Intent intent = new Intent(TitleActivity.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        quit = true;
        super.onBackPressed();
    }
}
