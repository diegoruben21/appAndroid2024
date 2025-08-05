package com.example.appandroidjuegosdeestrategia.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.reflect.TypeToken;
import com.example.appandroidjuegosdeestrategia.modelo.AndroidGame;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PrefUtil {

    private static final String PREF_NAME = "game_data";
    private static final String KEY_LIST = "game_list";

    public static void guardarLista(Context context, ArrayList<AndroidGame> lista) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(lista);
        editor.putString(KEY_LIST, json);
        editor.apply();
    }

    public static ArrayList<AndroidGame> cargarLista(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_LIST, null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<AndroidGame>>(){}.getType();
        return json != null ? gson.fromJson(json, type) : new ArrayList<>();
    }
}
