package com.example.appandroidjuegosdeestrategia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuario, edtPassword;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnIngresar = findViewById(R.id.btnIngresar);

        SharedPreferences preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        boolean sesionIniciada = preferences.getBoolean("logueado", false);

        if (sesionIniciada) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        btnIngresar.setOnClickListener(v -> {
            String usuario = edtUsuario.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (usuario.equals("admin") && password.equals("1234")) {
                preferences.edit().putBoolean("logueado", true).apply();

                Toast.makeText(this, "Bienvenido " + usuario, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
