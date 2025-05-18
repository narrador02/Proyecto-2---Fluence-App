package com.example.fluenceapp.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fluenceapp.data.dao.UserDao;
import com.example.fluenceapp.data.dao.EmpresaDao;
import com.example.fluenceapp.data.dao.InfluencerDao;
import com.example.fluenceapp.data.dao.ColaboracionDao;
import com.example.fluenceapp.data.entities.UserEntity;
import com.example.fluenceapp.data.entities.EmpresaEntity;
import com.example.fluenceapp.data.entities.InfluencerEntity;
import com.example.fluenceapp.data.entities.ColaboracionEntity;

import java.util.concurrent.Executors;

@Database(
        entities = {
                UserEntity.class,
                EmpresaEntity.class,
                InfluencerEntity.class,
                ColaboracionEntity.class
        },
        version = 2 // Incrementa la versión de la base de datos
)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao userDao();
    public abstract EmpresaDao empresaDao();
    public abstract InfluencerDao influencerDao();
    public abstract ColaboracionDao colaboracionDao();

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE empresas ADD COLUMN seguidores INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE empresas ADD COLUMN engagement REAL NOT NULL DEFAULT 0");
        }
    };

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "fluence.db"
                    )
                    .fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2) // Registra la migración
                    .allowMainThreadQueries()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadExecutor().execute(() -> {
                                UserDao userDao = instance.userDao();
                                EmpresaDao empresaDao = instance.empresaDao();
                                InfluencerDao influencerDao = instance.influencerDao();

                                // Usuarios
                                userDao.insert(new UserEntity("Laura", "laura@email.com", "123456", "influencer"));
                                userDao.insert(new UserEntity("Coca-Cola", "coca@cola.com", "123456", "empresa"));

                                // Empresas
                                empresaDao.insert(new EmpresaEntity("Coca-Cola", "Bebidas", "Barcelona", "", true, 1000, 4.5f));
                                empresaDao.insert(new EmpresaEntity("Nike", "Moda deportiva", "Madrid", "", false, 2000, 3.8f));

                                // Influencers
                                influencerDao.insert(new InfluencerEntity("Laura G", "Fitness", 120000, 4.2f, "Barcelona", ""));
                                influencerDao.insert(new InfluencerEntity("Pedro V", "Gaming", 80000, 3.7f, "Valencia", ""));
                            });
                        }
                    })
                    .build();
        }
        return instance;
    }
}