package com.example.quizappjava.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.quizappjava.DataBase.DaoSQLite;
import com.example.quizappjava.JavaClasses.RecyclerController;
import com.example.quizappjava.R;

public class LeaderBoard extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerController mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        DaoSQLite manager=new DaoSQLite(this);
        SQLiteDatabase db=manager.getReadableDatabase();

        layoutManager=new LinearLayoutManager(this);

        recyclerView=findViewById(R.id.recyclerView);

       // recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter=new RecyclerController(manager.getSortScores());
        recyclerView.setAdapter(mAdapter);
    }
}
