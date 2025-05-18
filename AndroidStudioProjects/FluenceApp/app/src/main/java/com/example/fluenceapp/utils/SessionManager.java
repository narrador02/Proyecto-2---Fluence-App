package com.example.fluenceapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "fluence_session";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ROL = "rol";

    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void guardarSesion(String nombre, String email, String rol) {
        editor.putString(KEY_NOMBRE, nombre);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_ROL, rol);
        editor.apply();
    }

    public String getNombre() {
        return prefs.getString(KEY_NOMBRE, null);
    }

    public String getEmail() {
        return prefs.getString(KEY_EMAIL, null);
    }

    public String getRol() {
        return prefs.getString(KEY_ROL, null);
    }

    public boolean haySesion() {
        return getEmail() != null && getRol() != null;
    }

    public void cerrarSesion() {
        editor.clear();
        editor.apply();
    }
}