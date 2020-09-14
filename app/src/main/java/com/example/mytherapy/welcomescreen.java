package com.example.mytherapy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class welcomescreen extends AppCompatActivity {
private static  int splash_time_out=6000;
    Intent intent;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcomescreen);
        imageView=(ImageView)findViewById(R.id.splashlogo);
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.splashanimation);
        Animation animlogo=AnimationUtils.loadAnimation(this,R.anim.logoanim);
        imageView.startAnimation(anim);
        imageView.startAnimation(animlogo);
        animlogo.setRepeatCount(15);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    intent = new Intent(welcomescreen.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

                else
                {
                    intent=new Intent(welcomescreen.this,MainActivity.class);
                    startActivity(intent);
                }

                 finish();
            }
        },splash_time_out);


    }
}
