package com.skies.ajayioluwatobi.skies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class SignUpActivity extends BaseActivity {

    private static final String TAG = "SignUpActivity";
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private EditText mEmailField;
    private EditText mPasswordField;
    private Button mSignInButton;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmailField = findViewById(R.id.editText5);
        mPasswordField = findViewById(R.id.editText6);
        mSignInButton = findViewById(R.id.button3);
        mSignUpButton = findViewById(R.id.button4);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        mSignInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                signIn();
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();

    }

    private void signUp(){
        if(!validateForm())
            return;

        showProgressDialog();

        String email = mEmailField.getText().toString();


        String password = mPasswordField.getText().toString();
        if(password.length() < 8) {
            mPasswordField.setError("Password must have at least 8 characters.");
            hideProgressDialog();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressDialog();

                        if(task.isSuccessful()){
                            OnSuccess(task.getResult().getUser());
                        }
                        else{
                            mEmailField.setError("Registration failed");
                        }

                    }
                });
    }

    private void signIn(){
        if(!validateForm())
            return;

        showProgressDialog();

        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideProgressDialog();

                        if(task.isSuccessful()){
                            OnSuccess(task.getResult().getUser());
                        }
                        else{
                            mEmailField.setError("Email does not exist.");
                        }


                    }
                });
    }

    private boolean validateForm(){
        boolean res = true;

        if(TextUtils.isEmpty(mEmailField.getText().toString())){
            mEmailField.setError("Email is empty.");
            res = false;
        }

        if(TextUtils.isEmpty(mPasswordField.getText().toString())){
            mEmailField.setError("Password Required.");
            res = false;
        }

        return res;

    }

    private void newUser(String userID, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userID).setValue(user);
    }

    private String getUsername(String email){
        if(email.contains("@")){
            return email.split("@")[0];
        }
        else
            return email;
    }

    private void OnSuccess(FirebaseUser user){
        String username = getUsername(user.getEmail());

        newUser(user.getUid(), username, user.getEmail());

        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        finish();
    }


}