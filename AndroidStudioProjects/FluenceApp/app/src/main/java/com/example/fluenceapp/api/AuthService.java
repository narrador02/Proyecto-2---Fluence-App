package com.example.fluenceapp.api;

import com.example.fluenceapp.models.ForgotPwdRequest;
import com.example.fluenceapp.models.ForgotPwdResponse;
import com.example.fluenceapp.models.LoginRequest;
import com.example.fluenceapp.models.LoginResponse;
import com.example.fluenceapp.models.RegisterRequest;
import com.example.fluenceapp.models.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

    @POST("/api/register")
    Call<RegisterResponse> register(@Body RegisterRequest request);

    @POST("/api/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("/auth/forgot-password")
    Call<ForgotPwdResponse> forgotPassword(@Body ForgotPwdRequest request);
}
