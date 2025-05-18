package com.example.fluenceapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fluenceapp.R;
import com.example.fluenceapp.data.AppDatabase;
import com.example.fluenceapp.data.entities.UserEntity;

public class ForgotPwdActivity extends BaseActivity {

    EditText emailField;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);

        emailField = findViewById(R.id.emailField);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();

            if (email.isEmpty()) {
                showToast("Introduce tu correo electrónico");
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showToast("Email no válido");
            } else {
                AppDatabase db = AppDatabase.getInstance(ForgotPwdActivity.this);
                UserEntity user = db.userDao().getByEmail(email);

                if (user == null) {
                    showToast("No hay ninguna cuenta registrada con ese email");
                } else {
                    showToast("Simulando envío de correo a " + email);
                    // Aquí podrías abrir una pantalla para cambiar la contraseña
                }
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}