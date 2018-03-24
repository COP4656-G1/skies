package com.skies.ajayioluwatobi.skies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class play_options extends AppCompatActivity {
    static final int Throw_phone_request = 1;  // The request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_options);

        final Button single_player = findViewById(R.id.single_player_button);
        final Button multiplayer = findViewById(R.id.MultiPlayer_button);
        final Button main_menu = findViewById(R.id.Main_menu_button);

        single_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(play_options.this, Throw_Phone.class);
                startActivityForResult(i,Throw_phone_request) ;
            }
        });


        main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
