package com.example.fluenceapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fluenceapp.data.entities.EmpresaEntity;

import java.util.List;

@Dao
public interface EmpresaDao {

    @Insert
    void insert(EmpresaEntity empresa);

    @Query("SELECT * FROM empresas")
    List<EmpresaEntity> getAll();

    @Query("SELECT * FROM empresas WHERE nombre LIKE '%' || :nombre || '%'")
    List<EmpresaEntity> buscarPorNombre(String nombre);

    @Query("DELETE FROM empresas")
    void deleteAll();
    @Query("SELECT * FROM empresas WHERE seguidores >= :minSeguidores AND engagement >= :minEngagement")
    List<EmpresaEntity> buscarPorFiltros(int minSeguidores, float minEngagement);
}
