package com.example.appandroidjuegosdeestrategia.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appandroidjuegosdeestrategia.GameDetailActivity;
import com.example.appandroidjuegosdeestrategia.R;
import com.example.appandroidjuegosdeestrategia.modelo.AndroidGame;

import java.util.ArrayList;

public class AndroidGameAdapter extends RecyclerView.Adapter<AndroidGameAdapter.GameViewHolder> {

    private Context context;
    private ArrayList<AndroidGame> gameList;
    private final OnGameClickListener editListener;
    private final OnGameClickListener deleteListener;

    public interface OnGameClickListener {
        void onClick(int position);
    }

    public AndroidGameAdapter(Context context, ArrayList<AndroidGame> gameList,
                              OnGameClickListener editListener, OnGameClickListener deleteListener) {
        this.context = context;
        this.gameList = gameList;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_android_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        AndroidGame game = gameList.get(position);

        holder.txtNombre.setText(game.getNombre());
        holder.txtGenero.setText("Género: " + game.getGenero());
        holder.txtCompatibilidad.setText("Compatibilidad: " + game.getCompatibilidad());
        holder.ratingBarItem.setRating(game.getPuntuacion());

        if (game.getImageUri() != null) {
            holder.imgJuego.setImageURI(Uri.parse(game.getImageUri()));
        } else {
            holder.imgJuego.setImageResource(R.mipmap.ic_launcher); // fallback
        }

        // Abrir detalles
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GameDetailActivity.class);
            intent.putExtra("juego", game);
            context.startActivity(intent);
        });

        // Mantener pulsado para editar o borrar
        holder.itemView.setOnLongClickListener(v -> {
            editListener.onClick(position);
            return true;
        });


    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtGenero, txtCompatibilidad;
        ImageView imgJuego;
        RatingBar ratingBarItem;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtGenero = itemView.findViewById(R.id.txtGenero);
            txtCompatibilidad = itemView.findViewById(R.id.txtCompatibilidad);
            imgJuego = itemView.findViewById(R.id.imgJuego);
            ratingBarItem = itemView.findViewById(R.id.ratingBarItem);
        }
    }
}