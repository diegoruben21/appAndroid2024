package com.example.appandroidjuegosdeestrategia.modelo;

import java.io.Serializable;

public class AndroidGame implements Serializable {
    private String nombre;
    private String genero;
    private String compatibilidad;
    private String versionAndroid;
    private String imageUri; // para guardar la imagen seleccionada
    private String Puntuacion;

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCompatibilidad(String compatibilidad) {
        this.compatibilidad = compatibilidad;
    }

    public void setVersionAndroid(String versionAndroid) {
        this.versionAndroid = versionAndroid;
    }

    public String getPuntuacion() {
        return Puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        Puntuacion = puntuacion;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public AndroidGame(String nombre, String puntuacion, String imageUri, String versionAndroid, String compatibilidad, String genero) {
        this.nombre = nombre;
        Puntuacion = puntuacion;
        this.imageUri = imageUri;
        this.versionAndroid = versionAndroid;
        this.compatibilidad = compatibilidad;
        this.genero = genero;
    }

    public AndroidGame(String nombre, String genero, String compatibilidad, String versionAndroid, String imageUri) {
        this.nombre = nombre;
        this.genero = genero;
        this.compatibilidad = compatibilidad;
        this.versionAndroid = versionAndroid;
        this.imageUri = imageUri;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getCompatibilidad() {
        return compatibilidad;
    }

    public String getVersionAndroid() {
        return versionAndroid;
    }

    public String getImageUri() {
        return imageUri;
    }

}