package com.example.appandroidjuegosdeestrategia.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    // BASE URL -> cámbiala por la de tu API o usa RAWG: https://api.rawg.io/api/
    private static final String BASE_URL = "https://api.rawg.io/api/";

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}