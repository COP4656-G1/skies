package com.skies.ajayioluwatobi.skies;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class play_options extends AppCompatActivity implements InstructionFragment.OnFragmentInteractionListener {
    private TranslateAnimation mTranslateAnim = new TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, 0.0f,
            Animation.RELATIVE_TO_PARENT, 1.0f,
            Animation.RELATIVE_TO_PARENT, 0.0f,
            Animation.RELATIVE_TO_PARENT, 1.0f);

    private RotateAnimation mRotateAnim = new RotateAnimation(0, 720 * 2);
    public MediaPlayer mp;
    private static final int ANIM_DURATION = 3000; // milliseconds
    static final int Throw_phone_request = 1;  // The request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_options);

        final ImageButton single_player = findViewById(R.id.single_player_button);
        final ImageButton multiplayer = findViewById(R.id.multiplayer_button);
        final ImageButton main_menu = findViewById(R.id.main_menu_button);

        mRotateAnim.setDuration(ANIM_DURATION);

        Animation rotateAnim = AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.full_rotate);
        single_player.setAnimation(rotateAnim);
        multiplayer.setAnimation(rotateAnim);
        main_menu.setAnimation(rotateAnim);

        single_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(getApplicationContext(),R.raw.soho );
                mp.start();
                //Intent i = new Intent(play_options.this, Throw_Phone.class);
                //startActivityForResult(i,Throw_phone_request) ;

                Intent intent = new Intent(play_options.this, InstructionActivity.class);
                startActivity(intent);

                Animation rotateAnim = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.full_rotate);

                single_player.startAnimation(rotateAnim);
            }
        });


        main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(getApplicationContext(),R.raw.soho );
                mp.start();

                Animation rotateAnim = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.full_rotate);

                main_menu.startAnimation(rotateAnim);
                finish();
            }
        });








    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Throw_phone_request) {
            if(resultCode == Activity.RESULT_OK){
                double rate =data.getDoubleExtra("rate",0.0);
                //  onScorereturn();
                Toast.makeText(play_options.this,
                        String.valueOf(rate), Toast.LENGTH_LONG).show();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri){

    }
}
