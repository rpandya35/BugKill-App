package com.example.rahul.hm14_pandya;

// Rahul Pandya
// L20355202

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Assets {
    static Bitmap background;
    static Bitmap foodbar;
    static Bitmap roach1;
    static Bitmap roach2;
    static Bitmap roach3;
    static Bitmap scorebar;
    static Bitmap ladybug;
    static Bitmap superbug1;
    static Bitmap superbug2;
    static Bitmap deadbigbug;

    // States of the Game Screen
    enum GameState {
        GettingReady,	// play "get ready" sound and start timer, goto next state
        Starting,		// when 3 seconds have elapsed, goto next state
        Running, 		// play the game, when livesLeft == 0 goto next state
        GameEnding,	    // show game over message
        GameOver,
        // game is over, wait for any Touch and go back to title activity screen
    };
    static GameState state;		// current state of the game
    static float gameTimer;// in seconds
    static float notifyCallTime;
    static float waitCallTime;

    static int livesLeft;		// 0-3

    static SoundPool soundPool;
    static MediaPlayer mp;
    static int sound_getready;
    static int sound_squish1,sound_squish2,sound_squish3;
    static int sound_thump;
    static int score;
    static int sound_highscore;
    static int touch_count;
    static int sound_ladybug;
    static int sound_superbug;
    static String scorenow = "Score: 0 ";

    static Bug bug1,bug2,bug3;// try using an array of bugs instead of only 1 bug (so you can have more than 1 on screen at a time)
    static LadyBug ladybug1;
    static SuperBug superBug1;
}
