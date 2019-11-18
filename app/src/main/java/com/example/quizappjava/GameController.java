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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

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
    private int idRespuesta;
    private String[] respuestas;


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
        if (txtRespuesta.getText().toString().trim().length() == 0) {
            Snackbar.make(view, "Intenta responder algo", Snackbar.LENGTH_SHORT).show();
        } else {
            if (respuestas[idRespuesta].equalsIgnoreCase(txtRespuesta.getText().toString().trim())) {
                Snackbar.make(view, "Bien", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(view, "Mal", Snackbar.LENGTH_SHORT).show();
                user.setLifes(user.getLifes()-1);
                if (user.getLifes()==0) {
                    //Hacer pantalla de perder
                }
            }

        }


}

    public void cargarLayoutDatos() {
        lbName.setText(user.getName());
        // lbScore.setText(user.getScore());
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
        int[] idImagen = new int[3];

        Random rand = new Random();
        switch (user.getNivel()) {
            case 1:
                idImagen[0] = R.drawable.nivel_1_1;
                idImagen[1] = R.drawable.nivel_1_2;
                idImagen[2] = R.drawable.nivel_1_3;
                respuestas = new String[]{"superman", "batman", "capitan america"};
                break;

            case 2:
                idImagen[0] = R.drawable.nivel_2_1;
                idImagen[1] = R.drawable.nivel_2_2;
                idImagen[2] = R.drawable.nivel_2_3;
                respuestas = new String[]{"gmail", "facebook", "instagram"};
                break;
            case 3:
                idImagen[0] = R.drawable.nivel_3_1;
                idImagen[1] = R.drawable.nivel_3_2;
                idImagen[2] = R.drawable.nivel_3_3;
                respuestas = new String[]{"gitlab", "git", "github"};
                break;
            case 4:
                idImagen[0] = R.drawable.nivel_4_1;
                idImagen[1] = R.drawable.nivel_4_2;
                idImagen[2] = R.drawable.nivel_4_3;
                respuestas = new String[]{"ferrari", "honda", "mustang"};
                break;
            case 5:
                idImagen[0] = R.drawable.nivel_5_1;
                idImagen[1] = R.drawable.nivel_5_2;
                idImagen[2] = R.drawable.nivel_5_3;
                respuestas = new String[]{"patriots", "eagles", "ravens"};
                break;
            case 6:
                idImagen[0] = R.drawable.nivel_1_1;
                idImagen[1] = R.drawable.nivel_1_2;
                idImagen[2] = R.drawable.nivel_1_3;
                respuestas = new String[]{"superman", "batman", "capitan america"};
                break;

        }
        idRespuesta = rand.nextInt(3);
        imgPregunta.setImageResource(idImagen[idRespuesta]);
    }
}
