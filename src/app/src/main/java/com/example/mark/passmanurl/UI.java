package com.example.mark.passmanurl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.util.Random;
import android.content.ClipboardManager;


public class UI extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.mark.passmanurl.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
    }


    public void sendMessage(View view) {


        Intent intent = new Intent(this, PassDisplay.class);
        EditText editText = (EditText) findViewById(R.id.usrURL);
        String userURL = editText.getText().toString();
        editText = (EditText) findViewById(R.id.usrUserName);
        String userName = editText.getText().toString();
        editText = (EditText) findViewById(R.id.usrNumber);
        int userNumber = Integer.parseInt(editText.getText().toString());
        passwordGenerator gen = new passwordGenerator();
        long seed = gen.generateSeed(userName, userURL, userNumber);
        gen.setSeed(seed);
        String password = gen.generatePassword();
        intent.putExtra(EXTRA_MESSAGE, password);
        startActivity(intent);
    }
}
