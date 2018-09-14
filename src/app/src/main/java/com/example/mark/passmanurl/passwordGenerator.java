package com.example.mark.passmanurl;

import java.util.Random;

public class passwordGenerator {
    private String URL, userName;
    private int pin;
    private int minSpecial = 0;
    private int minCapital = 0;
    private int minLower = 0;
    private int minNumbers = 0;
    private Random generator;
    passwordGenerator( String url, String name, int usrNum) {
        URL = url;
        userName = name;
        pin = usrNum;
        generator = new Random(generateSeed());
    }
    private long generateSeed() {
        int x = (int) URL.charAt(URL.length() - 1);
        int y = (int) userName.charAt(userName.length() / 2 );
        int z = pin << 2;
        return (z / (x + y))^ URL.length();
    }



    public String GeneratePassword(int length) {
        char[] passwordArray = new char[length];
        for(int i = 0; i < length; i++) {
            passwordArray[i] = (char) (generator.nextInt(92)+34);
        }
        return passwordArray.toString();
    }
}
