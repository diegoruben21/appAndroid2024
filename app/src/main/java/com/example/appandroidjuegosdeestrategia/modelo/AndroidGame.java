package com.example.appandroidjuegosdeestrategia.modelo;

import java.io.Serializable;

public class AndroidGame implements Serializable {

    private String nombre;
    private String genero;
    private String compatibilidad;
    private String versionAndroid;
    private String imageUri;   // Para guardar la URI de la imagen
    private int puntuacion;    // ⭐ Campo nuevo para calificación

    public AndroidGame(String nombre, String genero, String compatibilidad,
                       String versionAndroid, String imageUri, int puntuacion) {
        this.nombre = nombre;
        this.genero = genero;
        this.compatibilidad = compatibilidad;
        this.versionAndroid = versionAndroid;
        this.imageUri = imageUri;
        this.puntuacion = puntuacion;
    }

    // 👉 Constructor alternativo sin puntuación (por compatibilidad)
    public AndroidGame(String nombre, String genero, String compatibilidad,
                       String versionAndroid, String imageUri) {
        this(nombre, genero, compatibilidad, versionAndroid, imageUri, 0);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCompatibilidad() {
        return compatibilidad;
    }

    public void setCompatibilidad(String compatibilidad) {
        this.compatibilidad = compatibilidad;
    }

    public String getVersionAndroid() {
        return versionAndroid;
    }

    public void setVersionAndroid(String versionAndroid) {
        this.versionAndroid = versionAndroid;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}