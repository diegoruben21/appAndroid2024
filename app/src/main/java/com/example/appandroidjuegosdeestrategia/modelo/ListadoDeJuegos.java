package com.example.appandroidjuegosdeestrategia.modelo;
import java.util.ArrayList;
public class ListadoDeJuegos {
    private ArrayList<AndroidGame> juegos;

    public ListadoDeJuegos(ArrayList<AndroidGame> juegos) {
        this.juegos = juegos;
    }
    public void agregarJuego(AndroidGame juego) {
        juegos.add(juego);
    }

    public ArrayList<AndroidGame> getJuegos() {
        return juegos;
    }

    public void eliminarJuego(int index) {
        if (index >= 0 && index < juegos.size()) {
            juegos.remove(index);
        }
    }

    public AndroidGame obtenerJuego(int index) {
        if (index >= 0 && index < juegos.size()) {
            return juegos.get(index);
        }
        return null;
    }
}