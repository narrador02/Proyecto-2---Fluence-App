package com.example.fluenceapp.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fluenceapp.data.entities.InfluencerEntity;

import java.util.List;

@Dao
public interface InfluencerDao {

    @Insert
    void insert(InfluencerEntity influencer);

    @Query("SELECT * FROM influencers")
    List<InfluencerEntity> getAll();

    @Query("SELECT * FROM influencers WHERE seguidores >= :minSeguidores AND engagement >= :minEngagement")
    List<InfluencerEntity> filtrarPorSeguidoresYEngagement(int minSeguidores, float minEngagement);

    @Query("DELETE FROM influencers")
    void deleteAll();
}
