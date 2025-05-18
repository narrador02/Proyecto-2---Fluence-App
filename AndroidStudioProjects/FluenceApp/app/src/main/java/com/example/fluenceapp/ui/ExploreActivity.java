package com.example.fluenceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fluenceapp.R;
import com.example.fluenceapp.data.AppDatabase;
import com.example.fluenceapp.data.entities.EmpresaEntity;
import com.example.fluenceapp.utils.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends AppCompatActivity {

    private String rolUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        Log.d("ExploreActivity", "ExploreActivity loaded");

        SessionManager session = new SessionManager(this);
        if (!session.haySesion()) {
            Toast.makeText(this, "Inicia sesión primero", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        rolUsuario = session.getRol(); // "influencer" o "empresa"

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_explore);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
                return true;
            } else if (itemId == R.id.nav_explore) {
                return true;
            } else if (itemId == R.id.nav_dashboard) {
                startActivity(new Intent(this, DashboardActivity.class));
                return true;
            }
            return false;
        });

        // Título dinámico
        TextView titulo = findViewById(R.id.explore_title);
        if ("influencer".equalsIgnoreCase(rolUsuario)) {
            titulo.setText("Explora todas las empresas disponibles");
        } else {
            titulo.setText("Descubre ofertas de otras empresas");
        }

        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<ExploreAdapter.Company> companies = getEmpresasFromRoom();
        ExploreAdapter adapter = new ExploreAdapter(companies, rolUsuario);
        recyclerView.setAdapter(adapter);
    }

    private List<ExploreAdapter.Company> getEmpresasFromRoom() {
        AppDatabase db = AppDatabase.getInstance(this);
        List<EmpresaEntity> empresas = db.empresaDao().getAll();
        List<ExploreAdapter.Company> result = new ArrayList<>();
        for (EmpresaEntity empresa : empresas) {
            result.add(new ExploreAdapter.Company(empresa.nombre, empresa.tieneOfertasActivas));
        }
        return result;
    }
}