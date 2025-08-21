package com.example.appandroidjuegosdeestrategia;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.appandroidjuegosdeestrategia.modelo.AndroidGame;

public class EditGameActivity extends AppCompatActivity {

    private EditText edtNombre, edtGenero, edtCompatibilidad, edtVersion;
    private ImageView imgGame;
    private Button btnGuardar, btnSeleccionarImagen;

    private Uri selectedImageUri;
    private int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_game);

        edtNombre = findViewById(R.id.edtNombre);
        edtGenero = findViewById(R.id.edtGenero);
        edtCompatibilidad = findViewById(R.id.edtCompatibilidad);
        edtVersion = findViewById(R.id.edtVersion);
        imgGame = findViewById(R.id.imgGame);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);

        // Ver si venimos de editar un juego existente
        if (getIntent().hasExtra("game")) {
            AndroidGame game = (AndroidGame) getIntent().getSerializableExtra("game");
            index = getIntent().getIntExtra("index", -1);

            edtNombre.setText(game.getNombre());
            edtGenero.setText(game.getGenero());
            edtCompatibilidad.setText(game.getCompatibilidad());
            edtVersion.setText(game.getVersionAndroid());

            if (game.getImageUri() != null) {
                selectedImageUri = Uri.parse(game.getImageUri());
                imgGame.setImageURI(selectedImageUri);
            }
        }

        // Seleccionar imagen
        btnSeleccionarImagen.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                imagePickerLauncher.launch("image/*");
            } else {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                } else {
                    openImagePicker();
                }
            }
        });

        // Guardar juego
        btnGuardar.setOnClickListener(v -> guardarJuego());
    }

    private void guardarJuego() {
        String nombre = edtNombre.getText().toString().trim();
        String genero = edtGenero.getText().toString().trim();
        String compatibilidad = edtCompatibilidad.getText().toString().trim();
        String version = edtVersion.getText().toString().trim();

        if (nombre.isEmpty() || genero.isEmpty() || compatibilidad.isEmpty() || version.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear objeto AndroidGame
        AndroidGame nuevoJuego = new AndroidGame(
                nombre,
                genero,
                compatibilidad,
                version,
                selectedImageUri != null ? selectedImageUri.toString() : null
        );

        Intent intent = new Intent();
        intent.putExtra("game", nuevoJuego);
        intent.putExtra("index", index);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerIntentLauncher.launch(intent);
    }

    // Para Android 13+
    private final ActivityResultLauncher<String> imagePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    imgGame.setImageURI(uri);
                }
            });

    // Para Android <13
    private final ActivityResultLauncher<Intent> imagePickerIntentLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    imgGame.setImageURI(selectedImageUri);
                }
            });
}