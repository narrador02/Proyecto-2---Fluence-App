package com.example.fluenceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fluenceapp.R;
import com.example.fluenceapp.data.AppDatabase;
import com.example.fluenceapp.data.entities.EmpresaEntity;
import com.example.fluenceapp.data.entities.InfluencerEntity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Configurar BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                // Ya estamos en HomeActivity
                return true;
            } else if (itemId == R.id.nav_explore) {
                // Navegar a ExploreActivity
                startActivity(new Intent(this, ExploreActivity.class));
                return true;
            }
            return false;
        });

        // Mostrar barra de búsqueda y filtros
        findViewById(R.id.search_bar).setVisibility(View.VISIBLE);
        findViewById(R.id.btn_filter).setVisibility(View.VISIBLE);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ImageButton btnMenu = findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(this);

        ImageButton filterButton = findViewById(R.id.btn_filter);
        filterButton.setOnClickListener(v -> {
            FiltersBottomSheet.UserType type = "INFLUENCER".equalsIgnoreCase(userType)
                    ? FiltersBottomSheet.UserType.INFLUENCER
                    : FiltersBottomSheet.UserType.EMPRESA;

            FiltersBottomSheet bottomSheet = new FiltersBottomSheet(type, result -> {
                // Aquí puedes manejar el resultado de los filtros aplicados
            });
            bottomSheet.show(getSupportFragmentManager(), "filters");
        });

        // Recibe el tipo de usuario del intent
        userType = getIntent().getStringExtra("tipo_usuario");
        AppDatabase db = AppDatabase.getInstance(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if ("influencer".equalsIgnoreCase(userType)) {
            List<EmpresaEntity> empresas = db.empresaDao().getAll();
            HomeAdapter adapter = new HomeAdapter(empresas, HomeAdapter.TipoItem.EMPRESA);
            recyclerView.setAdapter(adapter);
        } else if ("empresa".equalsIgnoreCase(userType)) {
            List<InfluencerEntity> influencers = db.influencerDao().getAll();
            HomeAdapter adapter = new HomeAdapter(influencers, HomeAdapter.TipoItem.INFLUENCER);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_profile) {
            // Al abrir el perfil, pasa el tipo de usuario
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("tipo_usuario", userType);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_logout) {
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}