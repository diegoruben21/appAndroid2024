package com.example.appandroidjuegosdeestrategia.modelo;

import java.io.Serializable;

public class AndroidGame implements Serializable {
    private String nombre;
    private String genero;
    private String compatibilidad;
    private String versionAndroid;
    private String imageUri;
    private int puntuacion; // ⭐ Nueva propiedad

    public AndroidGame(String nombre, String genero, String compatibilidad, String versionAndroid, String imageUri, int puntuacion) {
        this.nombre = nombre;
        this.genero = genero;
        this.compatibilidad = compatibilidad;
        this.versionAndroid = versionAndroid;
        this.imageUri = imageUri;
        this.puntuacion = puntuacion;
    }

    public String getNombre() { return nombre; }
    public String getGenero() { return genero; }
    public String getCompatibilidad() { return compatibilidad; }
    public String getVersionAndroid() { return versionAndroid; }
    public String getImageUri() { return imageUri; }
    public int getPuntuacion() { return puntuacion; }

    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }
}