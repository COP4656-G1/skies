package com.skies.ajayioluwatobi.skies;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Throw_Phone extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor gyroSensor;
    private double rate = 0;
    private int score = 0;
    //private boolean ready = false;
    private HashMap<Double,Float> accToTime = new HashMap<Double,Float>();
    private long mLastTimestamp = System.nanoTime();
    private double secondsElapsed = 0;
    enum States {Start,Finish};
    private States state = States.Start;
    private static final int MORE_MS = 25000000;

    private static final String TAG = "PlayActivity";

    private DatabaseReference mDatabase;
    private DatabaseReference fDatabase;
    private Button mSubmitButton;
    public double spinA;
    public int mScore;
    public int tScore;
    public int attempt;
    public int nAttempt;
    public String timeOf;
    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_throw__phone);
        final ImageView hand= findViewById(R.id.hand_image);
        final ImageView phone= findViewById(R.id.phone_image);
        final TextView spin_text= findViewById(R.id.spin_text);
        final TextView your_score= findViewById(R.id.your_score_text);
        final TextView score_text= findViewById(R.id.score_text);

        your_score.setVisibility(View.INVISIBLE);
        score_text.setVisibility(View.INVISIBLE);

        mSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        gyroSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(Throw_Phone.this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
        tScore = 0;
        nAttempt = 0;
        String uid = getUid();
        fDatabase = FirebaseDatabase.getInstance().getReference().child("/user-turns/").child(uid);
        fDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Turn testT = dataSnapshot.getValue(Turn.class);
                if (testT != null) {
                    tScore = testT.mScore;
                    nAttempt = testT.attempts;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Timer t = new Timer();
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        secondsElapsed += 0.25;
                        if((state == States.Finish) && (counter ==0)){
                            counter = 1;
                            hand.setVisibility(View.INVISIBLE);
                            phone.setVisibility(View.INVISIBLE);
                            your_score.setVisibility(View.VISIBLE);
                            score = (int)rate *1000;
                            score_text.setText(String.valueOf(score));
                            score_text.setVisibility(View.VISIBLE);

                            submit(score);

                        }
                    }
                });
            }

        }, 0, 250);//0.25 seconds

    }
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(Throw_Phone.this);
    }
    protected void onResume(){
        super.onResume();
    }
    protected void onStop(){
        super.onStop();
        mSensorManager.unregisterListener(Throw_Phone.this);
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public void onSensorChanged(SensorEvent event) {
        if(event.timestamp - mLastTimestamp < MORE_MS) return;
            TextView1(Math.abs(event.values[2]));
            if(event.values[2] > rate) {
                rate = Math.abs(event.values[2]);
                TextView2(rate);
            }
            if(secondsElapsed > 7) {
                mSensorManager.unregisterListener(Throw_Phone.this);
                state = States.Finish;
            }


        mLastTimestamp = event.timestamp;
    }

    public void TextView1(final double a) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
    public void TextView2(final double a) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
    public void submit(final int mScore){

        Toast.makeText(this, "Posting...", Toast.LENGTH_SHORT).show();

        final String useriD = getUid();
        mDatabase.child("users").child(useriD).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);

                        if(user == null){
                            Toast.makeText(Throw_Phone.this,
                                    "Error: could not fetch user.",
                                    Toast.LENGTH_LONG).show();
                        }else{
                            int a = 0;
                            if(mScore < tScore) a = tScore;
                            else a = mScore;
                            attempt = nAttempt + 1;

                            Date date = new Date();
                            timeOf = date.toString();

                            writeTurn(useriD, user.name, a, attempt, timeOf);
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Throw_Phone.this,
                                "getUser: cancelled.",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void writeTurn(String userID, String username,
                          int mScore, int attempt, String timeOf){

        String key = mDatabase.child("user-turns").push().getKey();
        Turn turn = new Turn(userID, mScore, attempt, timeOf);
        Map<String, Object> turnValues = turn.toMap();
        Turn turn1 = new Turn(username, mScore,0," ");
        Map<String, Object> turnValues1 = turn1.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        //childUpdates.put("/turns/" + key, turnValues);
        childUpdates.put("/user-turns/" + userID, turnValues);
        childUpdates.put("/user-scores/"+ userID ,turnValues1 );
        mDatabase.updateChildren(childUpdates);
    }
    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


}
