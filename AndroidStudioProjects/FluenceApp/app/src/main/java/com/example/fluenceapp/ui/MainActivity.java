package com.example.fluenceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.fluenceapp.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView btnIniciarSesion = findViewById(R.id.iniciarSesion);
        TextView btnContacto = findViewById(R.id.contacto);
        Button btnEmpresa = findViewById(R.id.btnEmpresa);
        Button btnInfluencer = findViewById(R.id.btnInfluencer);
        Button btnIrHome = findViewById(R.id.btn_ir_home);

        btnIniciarSesion.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, LoginActivity.class)));

        btnContacto.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ContactActivity.class)));

        btnEmpresa.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            intent.putExtra("tipo_usuario", "Empresa");
            startActivity(intent);
        });

        btnInfluencer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            intent.putExtra("tipo_usuario", "Influencer");
            startActivity(intent);
        });

        btnIrHome.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}