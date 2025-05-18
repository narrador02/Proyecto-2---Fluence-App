package com.example.fluenceapp.data.entities;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "empresas")
public class EmpresaEntity {

    public EmpresaEntity(String nombre, String sector, String ubicacion, String logoUrl,
                         boolean tieneOfertasActivas, int seguidores, float engagement) {
        this.nombre = nombre;
        this.sector = sector;
        this.ubicacion = ubicacion;
        this.logoUrl = logoUrl;
        this.tieneOfertasActivas = tieneOfertasActivas;
        this.seguidores = seguidores;
        this.engagement = engagement;
    }

    public EmpresaEntity() {
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Nullable
    public String nombre;

    public String sector;

    public String ubicacion;

    public String logoUrl;

    public boolean tieneOfertasActivas;

    public int seguidores;

    public float engagement;
}