package com.example.quizappjava.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizappjava.Beans.User;


public class DaoSQLite extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Game.db";
    private static final String TABLE_NAME_SCORES = "puntuaciones";
    private SQLiteDatabase sqLiteDatabase = getWritableDatabase();

    public DaoSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME_SCORES +" (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT (15) NOT NULL,score INTEGER NOT NULL, level INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public Cursor getSortScores(){
        return getReadableDatabase().query(TABLE_NAME_SCORES,null,null,null,null,null,"score DESC");
    }
    public void insertNewScore(User user){
        sqLiteDatabase.insert(TABLE_NAME_SCORES,null,user.toContentValues());
    }

}
