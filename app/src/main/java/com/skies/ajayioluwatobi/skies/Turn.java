package com.skies.ajayioluwatobi.skies;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Turn {
    public String uid;
    public int mScore = 0;
    public int attempts = 0;
    public String timeOf;

    public Turn(){

    }

    public Turn(String uid, int mScore, int attempts, String timeOf){
        this.uid = uid;
        this.mScore = mScore;
        this.attempts = attempts;
        this.timeOf = timeOf;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("mScore", mScore);
        result.put("attempts", attempts);
        result.put("timeOf", timeOf);

        return result;
    }

}