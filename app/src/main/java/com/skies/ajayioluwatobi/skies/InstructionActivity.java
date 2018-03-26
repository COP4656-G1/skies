package com.skies.ajayioluwatobi.skies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);


        final TextView spin = findViewById(R.id.spin_button);
        final TextView exit = findViewById(R.id.exit_instruction_button);
        final CheckBox terms = findViewById(R.id.terms_checkBox);




        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(terms.isChecked()){
                    terms.setChecked(false);
                    Intent i = new Intent(InstructionActivity.this, Throw_Phone.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(InstructionActivity.this,
                            "Please agree to terms", Toast.LENGTH_LONG).show();
                }

            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });


    }
}
