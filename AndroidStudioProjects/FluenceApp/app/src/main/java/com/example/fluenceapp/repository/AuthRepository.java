package com.example.fluenceapp.repository;

import com.example.fluenceapp.api.ApiClient;
import com.example.fluenceapp.api.AuthService;
import com.example.fluenceapp.models.LoginRequest;
import com.example.fluenceapp.models.LoginResponse;
import com.example.fluenceapp.models.RegisterRequest;
import com.example.fluenceapp.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private final AuthService authService = ApiClient.getClient().create(AuthService.class);

    public void register(RegisterRequest request, RegisterCallback callback) {
        authService.register(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Error en el servidor");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callback.onError("Error de red: " + t.getMessage());
            }
        });
    }

    public interface RegisterCallback {
        void onSuccess(RegisterResponse response);
        void onError(String message);
    }

    public void login(LoginRequest request, LoginCallback callback) {
        authService.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Credenciales incorrectas o error del servidor");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                callback.onError("Error de red: " + t.getMessage());
            }
        });
    }

    public interface LoginCallback {
        void onSuccess(LoginResponse response);
        void onError(String message);
    }
}