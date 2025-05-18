package com.example.fluenceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.example.fluenceapp.R;
import com.example.fluenceapp.data.AppDatabase;
import com.example.fluenceapp.data.dao.UserDao;
import com.example.fluenceapp.data.entities.EmpresaEntity;
import com.example.fluenceapp.data.entities.InfluencerEntity;
import com.example.fluenceapp.data.entities.UserEntity;

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logoFluence);
        Animation slideIn = AnimationUtils.loadAnimation(this, R.anim.splash_slide);
        logo.startAnimation(slideIn);

        AppDatabase db = AppDatabase.getInstance(this);
        UserDao userDao = db.userDao();

        // BORRAR TODO, SOLO PARA PRUEBAS
        userDao.deleteAll();
        db.empresaDao().deleteAll();

        // INSERTAR EMPRESAS
        db.empresaDao().insert(new EmpresaEntity("Coca-Cola", "Bebidas", "Madrid", "Refrescos", true, 12000, 4.8f));
        db.empresaDao().insert(new EmpresaEntity("Nike", "Deporte", "Barcelona", "Ropa", true, 9800, 4.7f));
        db.empresaDao().insert(new EmpresaEntity("Zara", "Moda", "Coruña", "Tienda", false, 7600, 4.3f));
        db.empresaDao().insert(new EmpresaEntity("IKEA", "Muebles", "Valencia", "Hogar", true, 5000, 4.6f));
        db.empresaDao().insert(new EmpresaEntity("Amazon", "Tecnologia", "Madrid", "Compras", true, 30000, 4.9f));
        db.empresaDao().insert(new EmpresaEntity("Tesla", "Motor", "Valencia", "Coches", true, 3500, 4.5f));
        db.empresaDao().insert(new EmpresaEntity("Google", "Tecnologia", "Málaga", "Buscador", false, 19000, 4.9f));
        db.empresaDao().insert(new EmpresaEntity("H&M", "Moda", "Sevilla", "Ropa", false, 8500, 4.1f));
        db.empresaDao().insert(new EmpresaEntity("Apple", "Tecnologia", "Barcelona", "Moviles", true, 22000, 4.8f));
        db.empresaDao().insert(new EmpresaEntity("Netflix", "Media", "Madrid", "Series", true, 15000, 4.6f));
        db.empresaDao().insert(new EmpresaEntity("Airbnb", "Turismo", "Granada", "Viajes", false, 5400, 4.3f));
        db.empresaDao().insert(new EmpresaEntity("Spotify", "Musica", "Barcelona", "Música", false, 8700, 4.4f));
        db.empresaDao().insert(new EmpresaEntity("Samsung", "Tecnologia", "Madrid", "Moviles", true, 19000, 4.7f));
        db.empresaDao().insert(new EmpresaEntity("Repsol", "Energia", "Madrid", "Gasolina", false, 6700, 4.2f));
        db.empresaDao().insert(new EmpresaEntity("Decathlon", "Deporte", "Murcia", "Tienda", true, 4500, 4.5f));

        // INSERTAR INFLUENCERS
        db.influencerDao().insert(new InfluencerEntity("Laura", "moda", 24000, 72.4f, "Barcelona", ""));
        db.influencerDao().insert(new InfluencerEntity("Carlos", "fitness", 31000, 38.7f, "Madrid", ""));
        db.influencerDao().insert(new InfluencerEntity("Marta", "viajes", 17000, 91.2f, "Valencia", ""));
        db.influencerDao().insert(new InfluencerEntity("Sergio", "motor", 29000, 25.3f, "Sevilla", ""));
        db.influencerDao().insert(new InfluencerEntity("Lucía", "belleza", 45000, 84.9f, "Bilbao", ""));
        db.influencerDao().insert(new InfluencerEntity("Álvaro", "deporte", 15000, 17.1f, "Zaragoza", ""));
        db.influencerDao().insert(new InfluencerEntity("Ana", "gastronomia", 27000, 66.0f, "Granada", ""));
        db.influencerDao().insert(new InfluencerEntity("David", "cultura", 12000, 12.6f, "Toledo", ""));
        db.influencerDao().insert(new InfluencerEntity("Irene", "naturaleza", 20000, 55.5f, "La Coruña", ""));
        db.influencerDao().insert(new InfluencerEntity("Javier", "moda", 22000, 43.8f, "Barcelona", ""));
        db.influencerDao().insert(new InfluencerEntity("Paula", "gastronomia", 26000, 79.3f, "Málaga", ""));
        db.influencerDao().insert(new InfluencerEntity("Raúl", "motor", 18000, 30.9f, "Valencia", ""));
        db.influencerDao().insert(new InfluencerEntity("Elena", "belleza", 39000, 94.5f, "Sevilla", ""));
        db.influencerDao().insert(new InfluencerEntity("Víctor", "fitness", 25000, 46.2f, "Madrid", ""));
        db.influencerDao().insert(new InfluencerEntity("Carmen", "viajes", 21000, 62.0f, "Bilbao", ""));



        // Usuarios influencers conocidos
        userDao.insert(new UserEntity("Laura", "laura@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Carlos", "carlos@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Marta", "marta@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Sergio", "sergio@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Lucía", "lucia@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Álvaro", "alvaro@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Ana", "ana@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("David", "david@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Irene", "irene@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Javier", "javier@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Paula", "paula@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Raúl", "raul@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Elena", "elena@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Víctor", "victor@email.com", "123456", "influencer"));
        userDao.insert(new UserEntity("Carmen", "carmen@email.com", "123456", "influencer"));

        // Usuarios empresas
        userDao.insert(new UserEntity("Coca-Cola", "coca@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Nike", "nike@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Zara", "zara@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("IKEA", "ikea@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Amazon", "amazon@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Tesla", "tesla@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Google", "google@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("H&M", "hm@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Apple", "apple@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Netflix", "netflix@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Airbnb", "airbnb@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Spotify", "spotify@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Samsung", "samsung@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Repsol", "repsol@email.com", "123456", "empresa"));
        userDao.insert(new UserEntity("Decathlon", "decathlon@email.com", "123456", "empresa"));

        // Lanzar Main después del delay
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, SPLASH_DURATION);
    }
}