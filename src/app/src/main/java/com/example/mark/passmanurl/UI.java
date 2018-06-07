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
    public long generateSeed(int urlLength, int nameLength, int magicNumber) {
        return (urlLength * magicNumber * nameLength) / (magicNumber + urlLength - nameLength);
    }
    public String generatePassword(String URL, String userName, String userNumber) {

        String[] password = "                ".split("");
        String[] urlArray = URL.split("");
        String[] nameArray = userName.split("");
        String[] numberArray = userNumber.split("");
        int ASCII = 0;
        int number = Integer.parseInt(userNumber);
        int choice = 0;
        int index = 0;
        char ch = ' ';
        Random generator = new Random(generateSeed(urlArray.length, nameArray.length, number));

        for (int i = 0; i < 16; i++) {
            choice = generator.nextInt(4);


            if (choice == 1) {
                index = generator.nextInt(URL.length());
                password[i] = urlArray[index];
            }
            else if (choice == 2) {
                index = generator.nextInt(userName.length());
                password[i] = nameArray[index];
            }
            else if (choice == 3) {
                index = generator.nextInt(userNumber.length());
                password[i] = numberArray[index];
            }
            else {
                ASCII = generator.nextInt(14) + 33;
                ch = (char) ASCII;
                password[i] += ch;
                password[i] = password[i].trim();
            }
        }

        String temp = "";
        for(int i = 0; i < 16; i++) {
            temp += password[i];
        }

        return temp;

    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, PassDisplay.class);
        EditText editText = (EditText) findViewById(R.id.usrURL);
        String userURL = editText.getText().toString();
        editText = (EditText) findViewById(R.id.usrUserName);
        String userName = editText.getText().toString();
        editText = (EditText) findViewById(R.id.usrNumber);
        String userNumber = editText.getText().toString();
        String password = generatePassword(userURL, userName, userNumber);
        intent.putExtra(EXTRA_MESSAGE, password);
        startActivity(intent);
    }
}
