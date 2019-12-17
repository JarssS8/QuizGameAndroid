package com.example.quizappjava.Activities;

import android.content.Context;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizappjava.R;
import com.example.quizappjava.Beans.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView btJugar;
    private Button btCreditos;
    private ImageView btLeader;
    private EditText txtName;
    private ImageView imageMain;
    static  MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.txtNombre);
        btJugar = findViewById(R.id.btJugar);
        btJugar.setClickable(true);
        btJugar.setOnClickListener(this);
        imageMain=findViewById(R.id.imageMain);
        btLeader= findViewById(R.id.btLeaderBorad);
        btLeader.setClickable(true);
        btLeader.setOnClickListener(this);

        mediaPlayer=MediaPlayer.create(this, R.raw.appmaintheme);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume((float) 0.5,(float) 0.5);
        mediaPlayer.start();
        setRandomImage();
    }

    private void setRandomImage() {
        imageMain.setImageResource(R.drawable.logo_final_kappa_2);

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btJugar:
                if (txtName.getText().toString().trim().length() > 0 && txtName.getText().toString().trim().length() <= 15) {
                    User user=new User();
                    user.setLifes(3);
                    user.setName(txtName.getText().toString().trim());
                    user.setLevel(1);
                    user.setScore(0);
                    Intent intent=new Intent(this,GameController.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Introduce un nombre de hasta 15 caracteres", Toast.LENGTH_LONG).show();
                    forzarTeclado();
                }
                break;
            case R.id.btCreditos:
                Snackbar.make(view, "Coming soon", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.btLeaderBorad:
                Intent intent =new Intent(this,LeaderBoard.class);
                startActivity(intent);
                break;
        }
    }


    private void forzarTeclado() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }

}
