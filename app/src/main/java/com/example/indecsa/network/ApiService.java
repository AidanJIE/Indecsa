package com.example.indecsa.network;

import com.example.indecsa.models.AdminDto;
import com.example.indecsa.models.CapHumDto;
import com.example.indecsa.models.LoginRequestAdmin;
import com.example.indecsa.models.LoginRequestCapHum;
import com.example.indecsa.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    // LOGIN ADMIN
    @POST("admin/login")
    Call<LoginResponse> loginAdmin(@Body LoginRequestAdmin request);

    // LOGIN CAPITAL HUMANO
    @POST("capitalhumano/login")
    Call<LoginResponse> loginCapHum(@Body LoginRequestCapHum request);

    // REGISTRO ADMIN
    @POST("admin")
    Call<AdminDto> registrarAdmin(@Body LoginRequestAdmin request);

    @POST("capitalhumano")
    Call<CapHumDto> registrarCapHumano(@Body LoginRequestCapHum request);


}
