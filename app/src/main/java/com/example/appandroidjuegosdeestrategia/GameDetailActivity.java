package com.example.appandroidjuegosdeestrategia;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appandroidjuegosdeestrategia.api.ApiClient;
import com.example.appandroidjuegosdeestrategia.api.ApiService;
import com.example.appandroidjuegosdeestrategia.api.GameInfo;
import com.example.appandroidjuegosdeestrategia.modelo.AndroidGame;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameDetailActivity extends AppCompatActivity {

    private TextView txtNombre, txtGenero, txtCompatibilidad, txtVersion, txtPuntuacion, txtApiDescripcion, txtApiRelease;
    private ImageView imgJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        // Enlazar vistas
        txtNombre = findViewById(R.id.txtNombreDetail);
        txtGenero = findViewById(R.id.txtGeneroDetail);
        txtCompatibilidad = findViewById(R.id.txtCompatibilidadDetail);
        txtVersion = findViewById(R.id.txtVersionDetail);
        txtPuntuacion = findViewById(R.id.txtPuntuacionDetail);
        imgJuego = findViewById(R.id.imgJuegoDetail);

        // TextViews para datos de la API
        txtApiDescripcion = findViewById(R.id.txtApiDescripcion);
        txtApiRelease = findViewById(R.id.txtApiRelease);

        // Recibir el juego desde MainActivity
        AndroidGame juego = (AndroidGame) getIntent().getSerializableExtra("juego");

        if (juego != null) {
            txtNombre.setText("Nombre: " + juego.getNombre());
            txtGenero.setText("Género: " + juego.getGenero());
            txtCompatibilidad.setText("Compatibilidad: " + juego.getCompatibilidad());
            txtVersion.setText("Android: " + juego.getVersionAndroid());
            txtPuntuacion.setText("⭐ Puntuación: " + juego.getPuntuacion());

            if (juego.getImageUri() != null) {
                imgJuego.setImageURI(Uri.parse(juego.getImageUri()));
            }

            //  Llamada a la API usando el nombre del juego
            buscarJuegoEnApi(juego.getNombre());
        }
    }

    private void buscarJuegoEnApi(String nombreJuego) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<GameInfo> call = apiService.getGameDetails(nombreJuego, "889c97c7d1134fa3a79262042a5cd6ce");
        call.enqueue(new Callback<GameInfo>() {
            @Override
            public void onResponse(Call<GameInfo> call, Response<GameInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GameInfo info = response.body();

                    txtApiDescripcion.setText("Descripción: " + info.getDescription());
                    txtApiRelease.setText("Lanzamiento: " + info.getReleased());
                } else {
                    Toast.makeText(GameDetailActivity.this, "No se encontró información extra", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GameInfo> call, Throwable t) {
                Toast.makeText(GameDetailActivity.this, "Error al conectar con API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}