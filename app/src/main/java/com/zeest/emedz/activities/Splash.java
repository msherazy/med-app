package com.zeest.emedz.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zeest.emedz.R;

public class Splash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        /* New Handler to start the Mobile Number Sign Up Activity
         * and close this Splash-Screen after some seconds.*/
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, HomeActivity.class));
//                overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
                finish();
            }
        }, secondsDelayed * 1000);

    }
}
