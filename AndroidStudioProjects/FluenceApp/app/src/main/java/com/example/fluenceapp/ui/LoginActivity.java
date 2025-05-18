package com.example.fluenceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fluenceapp.R;
import com.example.fluenceapp.data.AppDatabase;
import com.example.fluenceapp.data.entities.UserEntity;
import com.example.fluenceapp.utils.SessionManager;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText emailField, passwordField;
    Button btnIniciarSesion;
    Spinner spinnerTipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppDatabase db = AppDatabase.getInstance(this);
        if (db.userDao().getAllUsers().isEmpty()) {
            db.userDao().insert(new UserEntity("Laura", "laura@email.com", "123456", "influencer"));
        }

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        spinnerTipoUsuario = findViewById(R.id.spinnerTipoUsuario);

        List<String> tipos = Arrays.asList("Empresa", "Influencer");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoUsuario.setAdapter(adapter);

        btnIniciarSesion.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String tipoUsuario = spinnerTipoUsuario.getSelectedItem().toString().toLowerCase();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            UserEntity user = db.userDao().getByEmail(email);

            if (user != null && user.password.equals(password) && user.rol.equalsIgnoreCase(tipoUsuario)) {
                SessionManager session = new SessionManager(this);
                session.guardarSesion(user.nombre, user.email, user.rol);

                startActivity(new Intent(this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });

        TextView forgotPassword = findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(v ->
                startActivity(new Intent(this, ForgotPwdActivity.class)));

        TextView registerLink = findViewById(R.id.registerLink);
        registerLink.setOnClickListener(v -> {
            String tipoUsuario = spinnerTipoUsuario.getSelectedItem().toString();
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.putExtra("tipo_usuario", tipoUsuario);
            startActivity(intent);
        });
    }
}