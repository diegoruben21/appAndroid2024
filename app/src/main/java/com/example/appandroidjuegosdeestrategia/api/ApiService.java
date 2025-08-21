package com.example.appandroidjuegosdeestrategia.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    // Endpoint para obtener detalles de un juego
    // RAWG API: https://api.rawg.io/api/games?key=TU_API_KEY&search=nombreJuego
    @GET("games")
    Call<GameInfo> getGameDetails(
            @Query("search") String nombreJuego,
            @Query("key") String apiKey
    );
}