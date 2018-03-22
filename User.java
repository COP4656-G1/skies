package edu.fsu.cs.mobile.cop4656_project1;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wjl13 on 3/22/2018.
 */


@IgnoreExtraProperties
public class User {
    public String uid;
    public double spinA;
    public int mScore;
    public int attempts;
    public String timeOf;

    public User(){

    }

    public User(String uid, double spinA, int mScore, int attempts, String timeOf){
        this.uid = uid;
        this.spinA = spinA;
        this.mScore = mScore;
        this.attempts = attempts;
        this.timeOf = timeOf;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("spinA", spinA);
        result.put("mScore", mScore);
        result.put("attempts", attempts);
        result.put("timeOf", timeOf);

        return result;
    }

}
