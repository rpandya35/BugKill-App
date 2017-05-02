package com.example.rahul.hm14_pandya;


// Rahul Pandya
// L20355202

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    MainView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Disable the title
        //requestWindowFeature (Window.FEATURE_NO_TITLE);  // use the styles.xml file to set no title bar
        // Make full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Start the view
        v = new MainView(this);
        setContentView(v);
    }

    @Override
    protected void onPause () {
        super.onPause();
        v.pause();
        if (Assets.mp != null) {
            Assets.mp.stop();
            Assets.mp.release();
            Assets.mp = null;

        }
    }

    @Override
    protected void onResume () {
        super.onResume();
        v.resume();
    }
}
