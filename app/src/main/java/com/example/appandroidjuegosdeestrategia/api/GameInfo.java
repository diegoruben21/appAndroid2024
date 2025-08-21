package com.example.appandroidjuegosdeestrategia.api;



public class GameInfo {
    private String name;
    private String description;
    private String released;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public GameInfo() {
    }

    public GameInfo(String name, String released, String description) {
        this.name = name;
        this.released = released;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleased() {
        return released;
    }
}