package com.example.appandroidjuegosdeestrategia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appandroidjuegosdeestrategia.GameDetailActivity;
import com.example.appandroidjuegosdeestrategia.R;
import com.example.appandroidjuegosdeestrategia.modelo.AndroidGame;

import java.text.BreakIterator;
import java.util.List;

public class AndroidGameAdapter extends RecyclerView.Adapter<AndroidGameAdapter.GameViewHolder> {

    private final Context context;
    private final List<AndroidGame> games;
    private final OnEditClickListener onEditClickListener;
    private final OnDeleteClickListener onDeleteClickListener;

    public interface OnEditClickListener {
        void onEdit(int index);
    }

    public interface OnDeleteClickListener {
        void onDelete(int index);
    }

    public AndroidGameAdapter(Context context, List<AndroidGame> games, OnEditClickListener editListener, OnDeleteClickListener deleteListener) {
        this.context = context;
        this.games = games;
        this.onEditClickListener = editListener;
        this.onDeleteClickListener = deleteListener;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_android_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        AndroidGame game = games.get(position);

        holder.txtNombre.setText("Nombre: " + game.getNombre());
        holder.txtGenero.setText("Género: " + game.getGenero());
        holder.txtCompatibilidad.setText("Compatibilidad: " + game.getCompatibilidad());
        holder.txtVersion.setText("Android: " + game.getVersionAndroid());

        holder.btnEdit.setOnClickListener(v -> onEditClickListener.onEdit(position));
        holder.btnDelete.setOnClickListener(v -> onDeleteClickListener.onDelete(position));
        holder.txtPuntuacion.setText("⭐ " + game.getPuntuacion());


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, GameDetailActivity.class);
            intent.putExtra("juego", game);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {

        public BreakIterator txtPuntuacion;
        TextView txtNombre, txtGenero, txtCompatibilidad, txtVersion;
        Button btnEdit, btnDelete;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtGenero = itemView.findViewById(R.id.txtGenero);
            txtCompatibilidad = itemView.findViewById(R.id.txtCompatibilidad);
            txtVersion = itemView.findViewById(R.id.txtVersion);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
