package com.example.fluenceapp.ui;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.fluenceapp.R;
import java.util.Locale;

public class SettingsActivity extends BaseActivity {

    private Switch switchDarkMode, switchNotifications;
    private Spinner spinnerLanguage;
    private SharedPreferences prefs;

    // Códigos de idioma y nombres visibles (ajusta según tus recursos)
    private final String[] languageCodes = {"es", "en"};
    private final String[] languageNames = {"Español", "English"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Aplica modo oscuro antes de setContentView
        prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean darkMode = prefs.getBoolean("dark_mode", false);
        AppCompatDelegate.setDefaultNightMode(
                darkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchDarkMode = findViewById(R.id.switch_dark_mode);
        switchNotifications = findViewById(R.id.switch_notifications);
        spinnerLanguage = findViewById(R.id.spinner_language);

        // Configura el spinner de idioma
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languageNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);

        // Selecciona el idioma guardado
        String savedLang = prefs.getString("language", "es");
        int langIndex = 0;
        for (int i = 0; i < languageCodes.length; i++) {
            if (languageCodes[i].equals(savedLang)) {
                langIndex = i;
                break;
            }
        }
        spinnerLanguage.setSelection(langIndex);

        // Restaurar estado de los switches
        switchDarkMode.setChecked(darkMode);
        switchNotifications.setChecked(prefs.getBoolean("notifications", true));

        // Listeners
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("dark_mode", isChecked).apply();
            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );
        });

        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("notifications", isChecked).apply();
        });

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private boolean firstCall = true;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Evita recrear la activity en la primera llamada (al iniciar)
                if (firstCall) {
                    firstCall = false;
                    return;
                }
                String selectedLang = languageCodes[position];
                if (!selectedLang.equals(prefs.getString("language", "es"))) {
                    prefs.edit().putString("language", selectedLang).apply();
                    setLocale(selectedLang);
                    recreate();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setLocale(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}