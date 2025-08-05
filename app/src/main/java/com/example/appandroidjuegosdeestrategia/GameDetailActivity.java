package com.example.appandroidjuegosdeestrategia;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appandroidjuegosdeestrategia.modelo.AndroidGame;

public class GameDetailActivity extends AppCompatActivity {

    private TextView txtNombre, txtGenero, txtCompatibilidad, txtVersion, txtPuntuacion;
    private ImageView imgJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        txtNombre = findViewById(R.id.txtNombreDetail);
        txtGenero = findViewById(R.id.txtGeneroDetail);
        txtCompatibilidad = findViewById(R.id.txtCompatibilidadDetail);
        txtVersion = findViewById(R.id.txtVersionDetail);
        txtPuntuacion = findViewById(R.id.txtPuntuacionDetail);
        imgJuego = findViewById(R.id.imgJuegoDetail);

        AndroidGame juego = (AndroidGame) getIntent().getSerializableExtra("juego");

        if (juego != null) {
            txtNombre.setText("Nombre: " + juego.getNombre());
            txtGenero.setText("Género: " + juego.getGenero());
            txtCompatibilidad.setText("Compatibilidad: " + juego.getCompatibilidad());
            txtVersion.setText("Android: " + juego.getVersionAndroid());
            txtPuntuacion.setText("⭐ " + juego.getPuntuacion());

            if (juego.getImageUri() != null) {
                imgJuego.setImageURI(Uri.parse(juego.getImageUri()));
            }
        }
    }
}
