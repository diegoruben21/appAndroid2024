package com.example.appandroidjuegosdeestrategia.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GameResponse {

    @SerializedName("results")
    private List<GameInfo> results;

    public List<GameInfo> getResults() {
        return results;
    }
}
