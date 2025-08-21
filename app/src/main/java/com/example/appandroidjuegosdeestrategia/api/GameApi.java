package com.example.appandroidjuegosdeestrategia.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GameApi {
    // Cambia {id} por el ID real que uses en la API
    @GET("games/{id}")
    Call<GameInfo> getGameInfo(@Path("id") int gameId);
}