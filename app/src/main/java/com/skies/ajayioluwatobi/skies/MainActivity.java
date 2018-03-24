package com.skies.ajayioluwatobi.skies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private TranslateAnimation mTranslateAnim = new TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, 0.0f,
            Animation.RELATIVE_TO_PARENT, 1.0f,
            Animation.RELATIVE_TO_PARENT, 0.0f,
            Animation.RELATIVE_TO_PARENT, 1.0f);

    private RotateAnimation mRotateAnim = new RotateAnimation(0, 720 * 2);

    private static final int ANIM_DURATION = 3000; // milliseconds

    public void doRotate(View v) {
        v.startAnimation(mRotateAnim);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton play_button = findViewById(R.id.play_button);
        final ImageButton options_button = findViewById(R.id.options_button);
        final ImageButton exit_button = findViewById(R.id.exit_button);

        mRotateAnim.setDuration(ANIM_DURATION);

        Animation rotateAnim = AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.full_rotate);
        play_button.setAnimation(rotateAnim);
        options_button.setAnimation(rotateAnim);
        exit_button.setAnimation(rotateAnim);

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, play_options.class);

               startActivity(intent);

                Animation rotateAnim = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.full_rotate);

                play_button.startAnimation(rotateAnim);


            }
        });
        options_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation rotateAnim = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.full_rotate);
                options_button.startAnimation(rotateAnim);
            }
        });
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation rotateAnim = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.full_rotate);
                exit_button.startAnimation(rotateAnim);
                finish();
            }
        });

    }





}
