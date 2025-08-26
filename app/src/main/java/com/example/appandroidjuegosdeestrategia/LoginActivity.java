package com.example.appandroidjuegosdeestrategia;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appandroidjuegosdeestrategia.db.DBHelper;
import com.example.appandroidjuegosdeestrategia.util.PasswordUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin, btnGoRegister;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ Revisar si ya hay sesión activa
        String loggedUser = getSharedPreferences("MyPrefs", MODE_PRIVATE)
                .getString("loggedUser", null);

        if (loggedUser != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoRegister = findViewById(R.id.btnGoRegister);
        dbHelper = new DBHelper(this);

        btnLogin.setOnClickListener(v -> loginUser());
        btnGoRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    private void loginUser() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString();

        Cursor cursor = dbHelper.getUser(username);
        if (cursor.moveToFirst()) {
            String storedHash = cursor.getString(cursor.getColumnIndexOrThrow("password_hash"));
            String salt = cursor.getString(cursor.getColumnIndexOrThrow("salt"));

            String inputHash = PasswordUtils.hashPassword(password, salt);

            if (storedHash.equals(inputHash)) {
                // ✅ Guardar sesión
                getSharedPreferences("MyPrefs", MODE_PRIVATE)
                        .edit()
                        .putString("loggedUser", username)
                        .apply();

                Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}