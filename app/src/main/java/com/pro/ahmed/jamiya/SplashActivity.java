package com.pro.ahmed.jamiya;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.pro.ahmed.jamiya.help_classes.HelpConstants.CHECK_LOGIN;

import com.pro.ahmed.jamiya.data.DataProcessor;
import com.pro.ahmed.jamiya.help_classes.HelpClass;

public class SplashActivity extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                //go to home if user is login
                if (DataProcessor.getInstance(SplashActivity.this).getBool(CHECK_LOGIN)) {
                    HelpClass.startNewActivity(SplashActivity.this, Create_Join_GroupActivity.class);
                } else {
                    HelpClass.startNewActivity(SplashActivity.this, LoginActivity.class);
                }
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
