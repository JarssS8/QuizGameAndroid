package com.example.quizappjava;

import android.content.ContentValues;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int lifes;
    private int level;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public ContentValues toContentValues(){
        ContentValues values= new ContentValues();
        values.put("username",name);
        values.put("score",score);
        values.put("level",level);
        return values;
    }
}
