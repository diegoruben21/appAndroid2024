package com.example.appandroidjuegosdeestrategia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Pantalla splash puede usar un layout sencillo con logo
        setContentView(R.layout.activity_splash);

        // Retraso de 2 segundos antes de decidir a dónde ir
        new Handler().postDelayed(() -> {
            SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            String loggedUser = prefs.getString("loggedUser", null);

            if (loggedUser != null) {
                // Usuario logueado → ir a MainActivity
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                // No logueado → ir a LoginActivity
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish();
        }, 2000);
    }
}