package com.example.quizappjava.JavaClasses;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizappjava.Beans.User;
import com.example.quizappjava.R;

import java.util.ArrayList;

public class RecyclerController extends RecyclerView.Adapter<RecyclerController.ViewHolder> {

    private ArrayList<User> userArraList = new ArrayList<User>();



    public RecyclerController(Cursor queryRequest) {
        ParseData(queryRequest);
    }

    private void ParseData(Cursor queryRequest) {
        for (queryRequest.moveToFirst(); !queryRequest.isAfterLast(); queryRequest.moveToNext()) {
            userArraList.add(
                    new User(
                            queryRequest.getString(queryRequest.getColumnIndex("name")),
                            queryRequest.getInt(queryRequest.getColumnIndex("score")),
                            queryRequest.getInt(queryRequest.getColumnIndex("level"))
                    ));
        }

    }

    @NonNull
    @Override
    public RecyclerController.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerController.ViewHolder holder, int position) {

        holder.user=userArraList.get(position);

        holder.lbName.setText(userArraList.get(position).getName());
        holder.lbScore.setText(userArraList.get(position).getScore()+"");
        holder.lbLevel.setText(userArraList.get(position).getLevel()+"");
    }

    @Override
    public int getItemCount() {
        return userArraList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lbName;
        private TextView lbScore;
        private TextView lbLevel;
        private User user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lbName=itemView.findViewById(R.id.lbName);
            lbLevel=itemView.findViewById(R.id.lbLevel);
            lbScore=itemView.findViewById(R.id.lbScore);

        }


    }
}
