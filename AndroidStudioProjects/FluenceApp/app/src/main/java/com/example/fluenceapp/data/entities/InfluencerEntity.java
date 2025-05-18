package com.example.fluenceapp.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "influencers")
public class InfluencerEntity {

    public InfluencerEntity(String nombre, String categoria, int seguidores, float engagement, String ubicacion, String fotoUrl) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.seguidores = seguidores;
        this.engagement = engagement;
        this.ubicacion = ubicacion;
        this.fotoUrl = fotoUrl;
    }
    public InfluencerEntity() {
    }
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String nombre;

    public String categoria;

    public int seguidores;

    public float engagement;

    public String ubicacion;

    public String fotoUrl;
}
