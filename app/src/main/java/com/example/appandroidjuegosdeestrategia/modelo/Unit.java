package com.example.appandroidjuegosdeestrategia.modelo;

import java.io.Serializable;

public class Unit implements Serializable {
    private String name;
    private String type;
    private int power;

    public Unit(String name, int power, String type) {
        this.name = name;
        this.power = power;
        this.type = type;
    }

    public Unit(String name, String type, int power) {
        this.name = name;
        this.type = type;
        this.power = power;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPower(int power) {
        this.power = power;
    }
}

