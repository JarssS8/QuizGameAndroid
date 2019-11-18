package com.example.quizappjava;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Random;

public class GameController extends AppCompatActivity {
    private User user;
    private ImageView imgLifes;
    private ImageView imgPregunta;
    private EditText txtRespuesta;
    private TextView lbName;
    private TextView lbScore;
    private Button btComprobar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_controller);

        imgLifes = findViewById(R.id.imageLifes);
        imgPregunta = findViewById(R.id.imageResolve);
        txtRespuesta = findViewById(R.id.txtRespuesta);
        lbName = findViewById(R.id.lbUsername);
        // lbScore=findViewById(R.id.);
        btComprobar = findViewById(R.id.btComprobar);

        MediaPlayer.create(this, R.raw.heroesdelsabado).start();
        user = (User) getIntent().getSerializableExtra("user");
        cargarLayoutDatos();
        cargarPregunta();
    }

    public void controller(View view) {

    }

    public void cargarLayoutDatos() {
        lbName.setText(user.getName());
        lbScore.setText(user.getScore());
        switch (user.getLifes()) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;

        }
    }

    public void cargarPregunta() {
        ImageView[] imagenesDelNivel = new ImageView[3];
        Random rand = new Random();
        String imagenNombre = "nivel" + user.getNivel() + "_" + rand.nextInt(3);
        switch (user.getNivel()) {
            case 1:
                imagenesDelNivel[0].setImageDrawable();
                imagenesDelNivel[1].setImageDrawable();
                imagenesDelNivel[2].setImageDrawable();
                break;
            case 2:
                imagenesDelNivel[0].setImageDrawable();
                imagenesDelNivel[1].setImageDrawable();
                imagenesDelNivel[2].setImageDrawable();
                break;
            case 3:
                imagenesDelNivel[0].setImageDrawable();
                imagenesDelNivel[1].setImageDrawable();
                imagenesDelNivel[2].setImageDrawable();
                break;
            case 4:
                imagenesDelNivel[0].setImageDrawable();
                imagenesDelNivel[1].setImageDrawable();
                imagenesDelNivel[2].setImageDrawable();
                break;
            case 5:
                imagenesDelNivel[0].setImageDrawable();
                imagenesDelNivel[1].setImageDrawable();
                imagenesDelNivel[2].setImageDrawable();
                break;
            case 6:
                imagenesDelNivel[0].setImageDrawable();
                imagenesDelNivel[1].setImageDrawable();
                imagenesDelNivel[2].setImageDrawable();
                break;

        }
        imgPregunta = imagenesDelNivel[rand.nextInt(3)];
    }
}
