package com.skies.ajayioluwatobi.skies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class DisplayActivity extends BaseActivity {

    private Turn our;
    private ArrayList<String> a = new ArrayList<String>();
    private ArrayList<Integer> b = new ArrayList<Integer>();
    private DatabaseReference mDataBase;
    private DatabaseReference fDatabase;
    private int counter = 0;
    private ListView mUserList;;
    private TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18;
    private ArrayList<Turn> list = new ArrayList<Turn>();
    private HashMap<String,Integer> map = new HashMap<String, Integer>() {};

    public class TurnComparator implements Comparator<Turn> {
        public int compare(Turn p1, Turn p2) {
            if (p1.mScore < p2.mScore) return 1 ;
            else if (p1.mScore > p2.mScore) return -1;
            return 0;
        }
    }
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        t1 = findViewById(R.id.textView26);
        t2 = findViewById(R.id.textView27);
        t3 = findViewById(R.id.textView28);
        t4 = findViewById(R.id.textView29);
        t5 = findViewById(R.id.textView30);
        t6 = findViewById(R.id.textView31);
        t7 = findViewById(R.id.textView32);
        t8 = findViewById(R.id.textView33);
        t9 = findViewById(R.id.textView34);
        t10 = findViewById(R.id.textView35);
        t11 = findViewById(R.id.textView36);
        t12 = findViewById(R.id.textView37);
        t13 = findViewById(R.id.textView38);
        t14 = findViewById(R.id.textView39);
        t15 = findViewById(R.id.textView40);
        t16 = findViewById(R.id.textView41);
        //b2 = findViewById(R.id.button2);
        mDataBase = FirebaseDatabase.getInstance().getReference().child("/user-scores");

        //  mDataBase.orderByValue("")''

        String uid = getUid();
        //Query topPlayers = mDataBase.child("user-scores").orderByKey().limitToLast(10);

       //topPlayers.addChildEventListener(new ChildEventListener() {
        mDataBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final Turn turn = dataSnapshot.getValue(Turn.class);
                ++counter;
                //list.add(turn);
                //Collections.sort(list,new TurnComparator());


                switch (counter){
                    case 1:
                        t1.setText(turn.uid);t2.setText(String.valueOf(turn.mScore));
                        break;
                    case 2:
                        t3.setText(turn.uid);t4.setText(String.valueOf(turn.mScore));
                        break;
                    case 3:
                        t5.setText(turn.uid);t6.setText(String.valueOf(turn.mScore));
                        break;
                    case 4:
                        t7.setText(turn.uid);t8.setText(String.valueOf(turn.mScore));
                        break;
                    case 5:
                        t9.setText(turn.uid);t10.setText(String.valueOf(turn.mScore));
                        break;
                    case 6:
                        t11.setText(turn.uid);t12.setText(String.valueOf(turn.mScore));
                        break;
                    case 7:
                        t13.setText(turn.uid);t14.setText(String.valueOf(turn.mScore));
                        break;
                    case 8:
                        t15.setText(turn.uid);t16.setText(String.valueOf(turn.mScore));
                        break;
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //t1.setText(list.get(0).uid);t2.setText(String.valueOf(list.get(0).mScore));
        //t3.setText(list.get(1).uid);t4.setText(list.get(1).mScore);
        //t5.setText(list.get(2).uid);t6.setText(list.get(2).mScore);
        //t7.setText(list.get(3).uid);t8.setText(list.get(3).mScore);
        //t9.setText(list.get(4).uid);t10.setText(list.get(4).mScore);
        //t11.setText(list.get(5).uid);t12.setText(list.get(5).mScore);
        //t13.setText(list.get(6).uid);t14.setText(list.get(6).mScore);
        //t15.setText(list.get(7).uid);t16.setText(list.get(7).mScore);

    }
    @Override
    protected void onResume(){
        super.onResume();

    }



}
