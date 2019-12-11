package com.example.quizappjava;

import android.content.Context;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btJugar;
    private Button btCreditos;
    private EditText txtName;
    private ImageView imageMain;
    private  MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.txtNombre);
        btCreditos = findViewById(R.id.btCreditos);
        btJugar = findViewById(R.id.btJugar);
        imageMain=findViewById(R.id.imageMain);

        mediaPlayer=MediaPlayer.create(this, R.raw.adamssong);
        mediaPlayer.start();
        setRandomImage();
    }

    private void setRandomImage() {
        Random rand=new Random();
        int idImagen[]=new int[]{R.drawable.main_1,R.drawable.main_2,R.drawable.main_3,R.drawable.main_4};
        imageMain.setImageResource(idImagen[rand.nextInt(4)]);

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
                    mediaPlayer.stop();
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Introduce un nombre de hasta 15 caracteres", Toast.LENGTH_LONG).show();
                    forzarTeclado();
                }
                break;
            case R.id.btCreditos:
                Snackbar.make(view, "Coming soon", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }


    private void forzarTeclado() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

    }

    public void openLeaderBoard(){
        Intent intent =new Intent();
        startActivity(intent);
    }

}
