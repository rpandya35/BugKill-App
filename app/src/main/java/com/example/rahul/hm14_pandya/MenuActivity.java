package com.example.rahul.hm14_pandya;

// Rahul Pandya
// L20355202
// Homework 14

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuActivity extends Activity implements View.OnClickListener{
    MainView v;
    ImageView v1;
    ImageButton b1,b2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_activity);

        if (Assets.mp != null) {
            Assets.mp.stop();
            Assets.mp.release();
            Assets.mp = null;

        }
       v1 = (ImageView) findViewById(R.id.imageView3);       // Background
        b1 = (ImageButton)findViewById(R.id.play);
        b1.setOnClickListener(this);

        b2 = (ImageButton)findViewById(R.id.score);
        b2.setOnClickListener(this);

        v = new MainView(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.play :
                //setContentView(v);

                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.score:
                startActivity(new Intent(this, PrefsActivity.class));
                break;
            default:

                break;

        }




    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Application")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }





}
