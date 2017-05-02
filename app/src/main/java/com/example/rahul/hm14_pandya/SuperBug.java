package com.example.rahul.hm14_pandya;

// Rahul Pandya
// L20355202

import android.graphics.Canvas;

import com.example.rahul.hm14_pandya.Assets;
import com.example.rahul.hm14_pandya.SuperBug.SuperBugState;

import java.util.Random;

public class SuperBug {

    // States of a Bug
    enum SuperBugState {
        Dead,
        ComingBackToLife,
        Alive,            // in the game
        DrawDead,            // draw dead body on screen
    }

    ;

    Random rand = new Random();
    SuperBugState state;            // current state of bug
    int x, y, abc;                // location on screen (in screen coordinates)
    int tox, toy;           // location bug is moving to
    float dirx, diry;       // direction the bug is moving

    double speed;            // speed of bug (in pixels per second)
    // All times are in seconds
    float timeToBirth;        // # seconds till birth
    float startBirthTimer;    // starting timestamp when decide to be born
    float deathTime;        // time of death
    float animateTimer;        // used to move and animate the bug

    // Bug starts not alive
    public SuperBug() {
        state = SuperBugState.Dead;
    }

    // Bug birth processing
    public void birth(Canvas canvas) {
        // Bring a bug to life?
        if (state == SuperBugState.Dead) {
            // Set it to coming alive
            state = SuperBugState.ComingBackToLife;
            // Set a random number of seconds before it comes to life
            //timeToBirth = (float)Math.random () * 5;
            timeToBirth = 21;
            // Note the current time
            startBirthTimer = System.nanoTime() / 1000000000f;
        }
        // Check if bug is alive yet
        else if (state == SuperBugState.ComingBackToLife) {
            float curTime = System.nanoTime() / 1000000000f;
            // Has birth timer expired?
            if (curTime - startBirthTimer >= timeToBirth) {
                // If so, then bring bug to life
                state = SuperBugState.Alive;
                // Set bug starting location at top of screen
                x = (int) (Math.random() * canvas.getWidth());
                // Keep entire bug on screen
                if (x < Assets.superbug1.getWidth() / 2)
                    x = Assets.superbug1.getWidth() / 2;
                else if (x > canvas.getWidth() - Assets.superbug1.getWidth() / 2)
                    x = canvas.getWidth() - Assets.superbug1.getWidth() / 2;
                y = 0;
                abc = rand.nextInt((20 - 0) + 1) + 0;
                if (abc % 10 == 0) {
                    // Ccmpute a to location
                    tox = (int) (Math.random() * canvas.getWidth());
                    toy = (int) (Math.random() * (canvas.getHeight() - y - 1) + y - 1);
                    // Compute the direction the bug is moving
                    dirx = tox - x;
                    diry = toy - y;
                    // Compute the length of this direction vector
                    float len = (float) (Math.sqrt(dirx * dirx + diry * diry));
                    // Normalize this direction vector
                    dirx /= len;
                    diry /= len;
                }

                // Set speed of this bug
                speed = canvas.getHeight() / 6; // no faster than 1/4 a screen per second
                // subtract a random amount off of this so some bugs are a little slower
                // ADD CODE HERE
                // Record timestamp of this bug being born
                animateTimer = curTime;
            }
        }
    }

    // Bug movement processing
    public void move(Canvas canvas) {
        // Make sure this bug is alive
        if (state == SuperBugState.Alive) {
            // Get elapsed time since last call here
            float curTime = System.nanoTime() / 1000000000f;
            float elapsedTime = curTime - animateTimer;
            animateTimer = curTime;
            // Compute the amount of pixels to move (vertically down the screen)
            if (abc % 10 == 0) {
                x += (dirx * speed * elapsedTime);
                y += (diry * speed * elapsedTime);
            } else {

                y += (speed * elapsedTime);
            }
            // Draw bug on screen
            int theTime = (int) (System.nanoTime() / 100000000) % 10;
            if (theTime % 2 == 0)
                canvas.drawBitmap(Assets.superbug1, x, y, null);
            else
                canvas.drawBitmap(Assets.superbug2, x, y, null);

            // Has it reached the bottom of the screen?
            if (abc % 10 == 0) {
                if (y >= canvas.getHeight()) {
                    // Kill the bug
                    state = SuperBugState.Dead;
                    // Subtract 1 life
                    Assets.livesLeft--;
                }
                // Has it reached the to location (or passed it)?
                else if (y >= toy) {
                    // Ccmpute a to location
                    tox = (int) (Math.random() * canvas.getWidth());
                    toy = (int) (Math.random() * (canvas.getHeight() - y - 1) + y - 1);
                    // Compute the direction the bug is moving
                    dirx = tox - x;
                    diry = toy - y;
                    // Compute the length of this direction vector
                    float len = (float) (Math.sqrt(dirx * dirx + diry * diry));
                    // Normalize this direction vector
                    dirx /= len;
                    diry /= len;
                }


            } else {
                // Has it reached the bottom of the screen?
                if (y >= canvas.getHeight()) {
                    // Kill the bug
                    state = SuperBugState.Dead;
                    // Subtract 1 life
                    Assets.livesLeft--;
                }

            }

        }
    }

    // Process touch to see if kills bug - return true if bug killed
    public boolean touched(Canvas canvas, int touchx, int touchy) {
        boolean touched = false;
        // Make sure this bug is alive
        if (state == SuperBugState.Alive) {


            // Compute distance between touch and center of bug
            float dis = (float) (Math.sqrt((touchx - x) * (touchx - x) + (touchy - y) * (touchy - y)));
            // Is this close enough for a kill?
            if (dis <= Assets.superbug1.getWidth() * 0.75f) {

                Assets.touch_count++;
                if (Assets.touch_count < 4) {
                    return false;
                } else {
                    state = SuperBugState.DrawDead;    // need to draw dead body on screen for a while
                    touched = true;
                    // Record time of death
                    deathTime = System.nanoTime() / 1000000000f;
                    Assets.touch_count = 0;
                }

            }
        }
        return (touched);
    }

    // Draw dead bug body on screen, if needed
    public void drawDead(Canvas canvas) {
        if (state == SuperBugState.DrawDead) {
            canvas.drawBitmap(Assets.deadbigbug, x, y, null);
            // Get time since death
            float curTime = System.nanoTime() / 1000000000f;
            float timeSinceDeath = curTime - deathTime;
            // Drawn dead body long enough (4 seconds) ?
            if (timeSinceDeath > 4)
                state = SuperBugState.Dead;
        }
    }

}
