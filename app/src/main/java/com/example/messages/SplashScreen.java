package com.example.messages;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 1200;
    Animation zoomInAnimation, leftAnimation;
    ScrollView scrollView;
    ImageView zoom_In;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //Animations
        leftAnimation = AnimationUtils.loadAnimation(this, R.anim.left_animation);
        zoomInAnimation =  AnimationUtils.loadAnimation(this, R.anim.zoomin_animation);

        //Hooks
//        scrollView = findViewById(R.id.messageLayout);
        zoom_In = findViewById(R.id.zoomInIcon);

        //Setting Animations
//        scrollView.setAnimation(leftAnimation);
        zoom_In.setAnimation(zoomInAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MessageMenu.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
}
