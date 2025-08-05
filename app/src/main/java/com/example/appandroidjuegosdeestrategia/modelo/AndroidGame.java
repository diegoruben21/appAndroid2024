package com.example.appandroidjuegosdeestrategia.modelo;

import java.io.Serializable;

public class AndroidGame implements Serializable {
    private String nombre;
    private String genero;
    private String compatibilidad;
    private String versionAndroid;
    private String imageUri;
    private int puntuacion; // ¡Aquí está la puntuación!

    public AndroidGame(String nombre, String genero, String compatibilidad, String versionAndroid, String imageUri, int puntuacion) {
        this.nombre = nombre;
        this.genero = genero;
        this.compatibilidad = compatibilidad;
        this.versionAndroid = versionAndroid;
        this.imageUri = imageUri;
        this.puntuacion = puntuacion;
    }

    // Getters y setters
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
