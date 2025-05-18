package com.example.fluenceapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fluenceapp.data.entities.ColaboracionEntity;

import java.util.List;

@Dao
public interface ColaboracionDao {

    @Insert
    void insert(ColaboracionEntity colaboracion);

    @Query("SELECT * FROM colaboraciones WHERE influencerId = :influencerId")
    List<ColaboracionEntity> getPorInfluencer(int influencerId);

    @Query("SELECT * FROM colaboraciones WHERE empresaId = :empresaId")
    List<ColaboracionEntity> getPorEmpresa(int empresaId);

    @Query("DELETE FROM colaboraciones")
    void deleteAll();
}