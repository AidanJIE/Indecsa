package com.example.indecsa.network;

import com.example.indecsa.models.LoginRequest;
import com.example.indecsa.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // Capital Humano login
    @POST("capitalhumano/login")
    Call<LoginResponse> loginCapitalHumano(@Body LoginRequest request);


    // Admin login
    @POST("admin/login")
    Call<LoginResponse> loginAdmin(@Body LoginRequest request);
}
