package com.example.fluenceapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fluenceapp.R;
import com.example.fluenceapp.data.AppDatabase;
import com.example.fluenceapp.data.entities.UserEntity;
import com.example.fluenceapp.utils.SessionManager;

public class RegisterActivity extends BaseActivity {

    EditText usernameField, emailField, telefonoField, passwordField, confirmarPasswordField;
    Button btnRegistrarse;
    private String tipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView subtituloTipoUsuario = findViewById(R.id.subtituloTipoUsuario);
        tipoUsuario = getIntent().getStringExtra("tipo_usuario");

        if (tipoUsuario != null) {
            subtituloTipoUsuario.setText("Regístrate como " + tipoUsuario.toLowerCase());
        }

        usernameField = findViewById(R.id.usernameField);
        emailField = findViewById(R.id.emailField);
        telefonoField = findViewById(R.id.phoneField);
        passwordField = findViewById(R.id.passwordField);
        confirmarPasswordField = findViewById(R.id.confirmPasswordField);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(v -> {
            String username = usernameField.getText().toString().trim();
            String email = emailField.getText().toString().trim();
            String telefono = telefonoField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String confirmarPassword = confirmarPasswordField.getText().toString().trim();
            tipoUsuario = getIntent().getStringExtra("tipo_usuario").toLowerCase();

            if (username.isEmpty() || email.isEmpty() || telefono.isEmpty()
                    || password.isEmpty() || confirmarPassword.isEmpty()) {
                showToast("Rellena todos los campos");
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showToast("Email no válido");
                return;
            }

            if (telefono.length() != 9 || !telefono.matches("\\d+")) {
                showToast("Introduce un teléfono válido de 9 dígitos");
                return;
            }

            if (password.length() < 8) {
                showToast("La contraseña debe tener mínimo 8 caracteres");
                return;
            }

            if (!password.equals(confirmarPassword)) {
                showToast("Las contraseñas no coinciden");
                return;
            }

            AppDatabase db = AppDatabase.getInstance(RegisterActivity.this);
            UserEntity existente = db.userDao().getByEmail(email);
            if (existente != null) {
                showToast("Este email ya está registrado");
                return;
            }

            // Crear usuario y guardarlo
            UserEntity nuevo = new UserEntity(username, email, password, tipoUsuario);
            db.userDao().insert(nuevo);

            // Guardar sesión directamente
            SessionManager session = new SessionManager(RegisterActivity.this);
            session.guardarSesion(username, email, tipoUsuario);

            // Ir al Home
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            finish();
        });

        TextView irALogin = findViewById(R.id.irALogin);
        irALogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }

    private void showToast(String msg) {
        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}