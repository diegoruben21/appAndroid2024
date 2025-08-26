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

public class RegisterActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnRegister;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        dbHelper = new DBHelper(this);

        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si usuario existe
        Cursor cursor = dbHelper.getUser(username);
        if (cursor != null && cursor.moveToFirst()) {
            Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear usuario
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(password, salt);

        boolean inserted = dbHelper.insertUser(username, hashedPassword, salt, System.currentTimeMillis());
        if (inserted) {
            Toast.makeText(this, "Registro exitoso, inicia sesión", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish(); // ⬅️ vuelve a Login y cierra Register
        } else {
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show();
        }
    }
}