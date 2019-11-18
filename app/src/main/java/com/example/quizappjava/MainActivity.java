package com.example.quizappjava;

import android.content.Context;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private Button btJugar;
    private Button btCreditos;
    private EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.txtNombre);
        btCreditos = findViewById(R.id.btCreditos);
        btJugar = findViewById(R.id.btJugar);

        MediaPlayer.create(this, R.raw.adamssong).start();
        setRandomImage();
    }

    private void setRandomImage() {

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btJugar:
                if (txtName.getText().toString().trim().length() > 0 && txtName.getText().toString().trim().length() <= 15) {
                    User user=new User();
                    user.setLifes(3);
                    user.setName(txtName.getText().toString().trim());
                    user.setNivel(1);
                    user.setScore(0);
                    Intent intent=new Intent(this,GameController.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                } else {
                    Snackbar.make(view, "Introduce un nombre menor de 15 caracteres", Snackbar.LENGTH_SHORT).show();


                    //forzarTeclado();
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


}
