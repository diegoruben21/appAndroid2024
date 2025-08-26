package com.example.appandroidjuegosdeestrategia;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appandroidjuegosdeestrategia.modelo.AndroidGame;

public class GameDetailActivity extends AppCompatActivity {

    private TextView txtNombre, txtGenero, txtCompatibilidad, txtVersion;
    private ImageView imgJuego;
    private RatingBar ratingBarDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        txtNombre = findViewById(R.id.txtNombreDetail);
        txtGenero = findViewById(R.id.txtGeneroDetail);
        txtCompatibilidad = findViewById(R.id.txtCompatibilidadDetail);
        txtVersion = findViewById(R.id.txtVersionDetail);
        imgJuego = findViewById(R.id.imgJuegoDetail);
        ratingBarDetail = findViewById(R.id.ratingBarDetail);

        AndroidGame juego = (AndroidGame) getIntent().getSerializableExtra("juego");

        if (juego != null) {
            txtNombre.setText("Nombre: " + juego.getNombre());
            txtGenero.setText("Género: " + juego.getGenero());
            txtCompatibilidad.setText("Compatibilidad: " + juego.getCompatibilidad());
            txtVersion.setText("Android: " + juego.getVersionAndroid());

            if (juego.getImageUri() != null) {
                imgJuego.setImageURI(Uri.parse(juego.getImageUri()));
            }

            ratingBarDetail.setRating(juego.getPuntuacion());
        }
    }
}