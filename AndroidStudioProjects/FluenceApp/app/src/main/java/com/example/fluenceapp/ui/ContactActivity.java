package com.example.fluenceapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.fluenceapp.R;

public class ContactActivity extends BaseActivity {

    EditText emailMensaje, tituloMensaje, cuerpoMensaje;
    Button btnEnviarMensaje;
    TextView mensajeConfirmacion;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        emailMensaje = findViewById(R.id.emailMensaje);
        tituloMensaje = findViewById(R.id.tituloMensaje);
        cuerpoMensaje = findViewById(R.id.cuerpoMensaje);
        btnEnviarMensaje = findViewById(R.id.btnEnviarMensaje);
        mensajeConfirmacion = findViewById(R.id.mensajeConfirmacion);

        mensajeConfirmacion.setVisibility(View.GONE);

        btnEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailMensaje.getText().toString().trim();
                String titulo = tituloMensaje.getText().toString().trim();
                String mensaje = cuerpoMensaje.getText().toString().trim();

                if (email.isEmpty() || titulo.isEmpty() || mensaje.isEmpty()) {
                    showToast("Rellena todos los campos");
                    return;
                }

                if (!email.contains("@")) {
                    showToast("Introduce un correo válido");
                    return;
                }

                if (titulo.length() > 40) {
                    showToast("El título no puede tener más de 40 caracteres");
                    return;
                }

                if (mensaje.length() > 500) {
                    showToast("El mensaje no puede superar los 500 caracteres");
                    return;
                }

                showToast("Mensaje enviado correctamente");
                clearFields();
                mensajeConfirmacion.setVisibility(View.VISIBLE);
            }
        });
    }

    private void showToast(String msg) {
        Toast.makeText(ContactActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        emailMensaje.setText("");
        tituloMensaje.setText("");
        cuerpoMensaje.setText("");
    }
}