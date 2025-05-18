package com.example.fluenceapp.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {
    public UserEntity(String nombre, String email, String password, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }
    public UserEntity() {
    }
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String nombre;

    @NonNull
    public String email;

    @NonNull
    public String password;

    @NonNull
    public String rol; // "empresa" o "influencer"
}
