package com.example.quizappjava.Beans;

import android.content.ContentValues;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int lifes;
    private int level;
    private int score;

    public  User(){

    }

    public User(String name, int score, int level) {
        this.name=name;
        this.score=score;
        this.level=level;
    }

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
        values.put("name",name);
        values.put("score",score);
        values.put("level",level);
        return values;
    }
}
