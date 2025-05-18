package com.example.fluenceapp.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "colaboraciones")
public class ColaboracionEntity {

    public ColaboracionEntity(int influencerId, int empresaId, String estado, String descripcion, String fechaInicio, String fechaFin) {
        this.influencerId = influencerId;
        this.empresaId = empresaId;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public ColaboracionEntity() {
    }
    @PrimaryKey(autoGenerate = true)
    public int id;

    public int influencerId;

    public int empresaId;

    public String estado; // "pendiente", "activa", "finalizada"

    public String descripcion;

    public String fechaInicio;

    public String fechaFin;
}
