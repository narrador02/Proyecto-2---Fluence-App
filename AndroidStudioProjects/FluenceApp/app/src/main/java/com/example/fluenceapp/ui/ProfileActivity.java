package com.example.fluenceapp.ui;

import android.os.Bundle;
import android.widget.TextView;
import com.example.fluenceapp.R;
import com.example.fluenceapp.utils.SessionManager;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SessionManager session = new SessionManager(this);
        String nombre = session.getNombre();
        String email = session.getEmail();
        String rol = session.getRol();

        TextView nombreText = findViewById(R.id.text_user_name);
        TextView emailText = findViewById(R.id.text_user_email);
        TextView rolText = findViewById(R.id.text_user_type);

        nombreText.setText(nombre != null ? nombre : "Nombre no disponible");
        emailText.setText("Correo: " + (email != null ? email : "Sin correo"));
        rolText.setText("Tipo de usuario: " + (rol != null ? rol : "desconocido"));
    }
}