package com.example.appandroidjuegosdeestrategia.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Service para conectarse al API de juegos (RAWG por ejemplo)
public interface GameService {

    // Ejemplo: https://api.rawg.io/api/games?key=TU_API_KEY&search=minecraft
    @GET("games")
    Call<GameResponse> searchGames(
            @Query("key") String apiKey,
            @Query("search") String query
    );
}
