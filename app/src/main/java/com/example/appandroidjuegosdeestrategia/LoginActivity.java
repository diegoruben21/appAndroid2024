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
    private Button btnLogin;

    // Nombre del archivo de preferencias
    private static final String PREFS_NAME = "UserPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Si ya hay sesión iniciada, saltar al MainActivity directamente
        if (prefs.getBoolean("isLoggedIn", false)) {
            irAlMain();
        }

        btnLogin.setOnClickListener(v -> {
            String usuario = edtUsuario.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // 🔑 Validación simple (puedes mejorarlo con base de datos o API)
            if (usuario.equals("admin") && password.equals("1234")) {
                // Guardar sesión
                prefs.edit()
                        .putBoolean("isLoggedIn", true)
                        .putString("usuario", usuario)
                        .apply();

                Toast.makeText(this, "Bienvenido " + usuario, Toast.LENGTH_SHORT).show();
                irAlMain();
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void irAlMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // para que no pueda regresar con "back"
    }
}