package com.example.appandroidjuegosdeestrategia;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appandroidjuegosdeestrategia.adapter.AndroidGameAdapter;
import com.example.appandroidjuegosdeestrategia.modelo.AndroidGame;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int STORAGE_PERMISSION_CODE = 100;
    private static final String PREFS = "prefs";
    private static final String DARK_MODE = "dark_mode";

    private ArrayList<AndroidGame> gameList;
    private AndroidGameAdapter adapter;
    private RecyclerView recyclerView;

    private final ActivityResultLauncher<Intent> editGameLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    AndroidGame game = (AndroidGame) result.getData().getSerializableExtra("game");
                    int index = result.getData().getIntExtra("index", -1);
                    if (index == -1) {
                        // Nuevo juego
                        gameList.add(game);
                    } else {
                        // Editar juego existente
                        gameList.set(index, game);
                    }
                    adapter.notifyDataSetChanged();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Aplicar modo oscuro si estaba activado antes
        SharedPreferences preferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        boolean isDarkMode = preferences.getBoolean(DARK_MODE, false);
        AppCompatDelegate.setDefaultNightMode(isDarkMode ?
                AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pedir permiso de almacenamiento (solo necesario en Android 9 o inferior)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    STORAGE_PERMISSION_CODE);
        }

        // Inicializar lista y RecyclerView
        gameList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new AndroidGameAdapter(this, gameList, this::editGame, this::deleteGame);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Botón agregar juego
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditGameActivity.class);
            editGameLauncher.launch(intent);
        });

        // Botón cerrar sesión
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // Switch modo oscuro
        Switch switchDarkMode = findViewById(R.id.switchDarkMode);
        switchDarkMode.setChecked(isDarkMode);
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(DARK_MODE, isChecked);
            editor.apply();
            AppCompatDelegate.setDefaultNightMode(isChecked ?
                    AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        });
    }

    private void editGame(int index) {
        Intent intent = new Intent(this, EditGameActivity.class);
        intent.putExtra("game", gameList.get(index));
        intent.putExtra("index", index);
        editGameLauncher.launch(intent);
    }

    private void deleteGame(int index) {
        gameList.remove(index);
        adapter.notifyDataSetChanged();
    }

    // Respuesta del permiso de almacenamiento
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso de almacenamiento concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso de almacenamiento denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}