package com.example.quizappjava.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.quizappjava.DataBase.DaoSQLite;
import com.example.quizappjava.R;
import com.example.quizappjava.Beans.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class GameController extends AppCompatActivity {
    private User user;
    private ImageView imgLifes;
    private ImageView imgPregunta;
    private EditText txtRespuesta;
    private TextView lbName;
    private TextView lbScoreUsuario;
    private TextView lbScoreUser;
    private Button btComprobar;
    private int idRespuesta;
    private String[] respuestas;
    private MediaPlayer mediaPlayer;
    private int vidasIniciales;
    private Long horaEmpiezaNivel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_controller);
        imgLifes = findViewById(R.id.imageLifes);
        imgPregunta = findViewById(R.id.imageResolve);
        txtRespuesta = findViewById(R.id.txtRespuesta);
        lbName = findViewById(R.id.lbNombreUsuario);
        lbScoreUser=findViewById(R.id.lbScoreUsuario);
        btComprobar = findViewById(R.id.btComprobar);
        user = (User) getIntent().getSerializableExtra("user");
        vidasIniciales=user.getLifes();
        horaEmpiezaNivel = System.currentTimeMillis();
        cargarLayoutDatos();
        cargarPregunta();
    }

    public void controller(View view) {
        MediaPlayer.create(this,R.raw.descarga).start();
        if (txtRespuesta.getText().toString().trim().length() == 0) {
            Snackbar.make(view, "Intenta responder algo", Snackbar.LENGTH_SHORT).show();
        } else {
            if (respuestas[idRespuesta].equalsIgnoreCase(txtRespuesta.getText().toString().trim())) {
                MediaPlayer.create(this,R.raw.correctanswer).start();
                if (user.getLevel() == 7) {
                    asignarPuntuacion();
                    lbScoreUser.setText(""+user.getScore());
                    mediaPlayer=MediaPlayer.create(this,R.raw.wingame);
                    mediaPlayer.start();
                    popUpLevelComplete(view, "win");
                } else {
                    asignarPuntuacion();
                    user.setLevel(user.getLevel() + 1);
                    if (user.getLifes()<3) {
                        user.setLifes(user.getLifes() + 1);
                    }
                    popUpLevelComplete(view, "nextLevel");
                }
            } else {
                user.setLifes(user.getLifes() - 1);
                if (user.getLifes() == 0) {
                    mediaPlayer=MediaPlayer.create(this,R.raw.lose);
                    mediaPlayer.start();
                    imgLifes.setVisibility(View.INVISIBLE);
                    popUpLevelComplete(view, "lose");
                }else{
                    mediaPlayer=MediaPlayer.create(this,R.raw.no);
                    mediaPlayer.start();
                    actualizarVidas();
                }
            }

        }


    }

    private void asignarPuntuacion() {
        user.setScore(user.getScore()+100);//Pasarse el nivel
        if (vidasIniciales==user.getLifes()) {//Pasarse el nivel a la primera
            user.setScore(user.getScore()+50);
        }
        if(user.getLevel()==7){//Puntos extra por cada vida que tengas
            user.setScore(user.getScore()+(user.getLifes()*50));
        }
        horaEmpiezaNivel=System.currentTimeMillis()-horaEmpiezaNivel;
        if (horaEmpiezaNivel<=10000) {
            horaEmpiezaNivel=((horaEmpiezaNivel/100)*400)/100;
            user.setScore(user.getScore()+horaEmpiezaNivel.intValue());
        }

    }

    public void popUpLevelComplete(View view, final String accion) {
        LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popUpView = layoutInflater.inflate(R.layout.popup_signup, null);
        final PopupWindow popupWindow = new PopupWindow(popUpView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView lbPopUp = popUpView.findViewById(R.id.lbPopUp);
        Button btGetIt = popUpView.findViewById(R.id.btGetIt);
        if (accion.equalsIgnoreCase("win")) {
            lbPopUp.setText("FELICIDADES HAS GANADO.\nTu puntuacion ha sido de: " + user.getScore() + " puntos\"");
            btGetIt.setText("Menu Principal");
        } else if (accion.equalsIgnoreCase("lose")) {
            lbPopUp.setText("Lo siento has perdido.\nTu puntuacion ha sido de: " + user.getScore() + " puntos");
            btGetIt.setText("Menu Principal");
        }
        btGetIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (accion) {
                    case "nextLevel":
                        popupWindow.dismiss();
                        Bundle extras = new Bundle();
                        extras.putSerializable("user", user);
                        finish();
                        startActivity(getIntent().replaceExtras(extras));
                        break;
                    default:
                        newScore(user);
                        popupWindow.dismiss();
                        finish();
                        break;
                }

            }
        });
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    private void newScore(User user) {
        DaoSQLite manager=new DaoSQLite(this);
        SQLiteDatabase db=manager.getReadableDatabase();
        manager.insertNewScore(user);
    }

    public void cargarLayoutDatos() {
        lbName.setText(user.getName());
        lbScoreUser.setText(""+user.getScore());
        actualizarVidas();

    }

    public void actualizarVidas(){
        switch (user.getLifes()) {
            case 1:
                imgLifes.setImageResource(R.drawable.unavida);
                break;
            case 2:
                imgLifes.setImageResource(R.drawable.dosvidas);
                break;
            case 3:
                imgLifes.setImageResource(R.drawable.tresvidas);
                break;

        }
    }

    public void cargarPregunta() {
        int[] idImagen = new int[3];

        Random rand = new Random();
        switch (user.getLevel()) {
            case 1:
                idImagen[0] = R.drawable.volcanes;
                idImagen[1] = R.drawable.iceberg;
                idImagen[2] = R.drawable.img_lbernaus_20170516_140138_imagenes_lv_terceros_istock_451925737_kecb_u422627825675ite_992x558_lavanguardia_web;
                respuestas = new String[]{"volcan", "iceberg", "oasis"};
                break;

            case 2:
                idImagen[0] = R.drawable.steve_jobs;
                idImagen[1] = R.drawable.elon_musk;
                idImagen[2] = R.drawable.bill_gates;
                respuestas = new String[]{"steve jobs", "elon musk", "bill gates"};
                break;
            case 3:
                idImagen[0] = R.drawable.napoleon;
                idImagen[1] = R.drawable.hitler_fuhrer;
                idImagen[2] = R.drawable.stalin;
                respuestas = new String[]{"napoleon", "hitler", "stalin"};
                break;
            case 4:
                idImagen[0] = R.drawable.pio_baroja;
                idImagen[1] = R.drawable.federico_garcia_lorca;
                idImagen[2] = R.drawable.cervantes;
                respuestas = new String[]{"pio baroja", "federico garcia lorca", "cervantes"};
                break;
            case 5:
                idImagen[0] = R.drawable.atomo;
                idImagen[1] = R.drawable.mole_cula;
                idImagen[2] = R.drawable.neurona;
                respuestas = new String[]{"atomo", "molecula", "neurona"};
                break;
            case 6:
                idImagen[0] = R.drawable.madame_curie_2;
                idImagen[1] = R.drawable.edison_340a;
                idImagen[2] = R.drawable.einstein_lengua_getty;
                respuestas = new String[]{"marie curie", "thomas edison", "einstein"};
                break;
            case 7:
                idImagen[0] = R.drawable.anotaci_n_2019_12_16_202231;
                idImagen[1] = R.drawable.anotaci_n_2019_12_16_202931;
                idImagen[2] = R.drawable.anotaci_n_2019_12_16_203327;
                respuestas = new String[]{"35", "150", "12"};
                break;

        }
        idRespuesta = rand.nextInt(3);
        imgPregunta.setImageResource(idImagen[idRespuesta]);
    }
}
