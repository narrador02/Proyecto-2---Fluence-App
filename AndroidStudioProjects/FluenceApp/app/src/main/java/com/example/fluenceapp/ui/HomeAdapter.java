package com.example.fluenceapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fluenceapp.R;
import com.example.fluenceapp.data.entities.EmpresaEntity;
import com.example.fluenceapp.data.entities.InfluencerEntity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    public enum TipoItem { EMPRESA, INFLUENCER }

    private final List<?> lista;
    private final TipoItem tipo;

    public HomeAdapter(List<?> lista, TipoItem tipo) {
        this.lista = lista;
        this.tipo = tipo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (tipo == TipoItem.EMPRESA) {
            EmpresaEntity empresa = (EmpresaEntity) lista.get(position);
            holder.nombre.setText(empresa.nombre);
            holder.descripcion.setText(empresa.sector);
        } else {
            InfluencerEntity influencer = (InfluencerEntity) lista.get(position);
            holder.nombre.setText(influencer.nombre);
            holder.descripcion.setText(influencer.categoria + " | " + influencer.ubicacion);
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, descripcion;
        ImageView icono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.item_title);
            descripcion = itemView.findViewById(R.id.item_subtitle);
            icono = itemView.findViewById(R.id.item_icon);
        }
    }
}