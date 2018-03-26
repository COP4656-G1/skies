package com.skies.ajayioluwatobi.skies;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wjl13 on 3/22/2018.
 */


@IgnoreExtraProperties
public class User {
    public String name;
    public String email;

    public User(){

    }

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

}