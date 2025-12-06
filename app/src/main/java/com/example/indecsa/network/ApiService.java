package com.example.indecsa.network;

import com.example.indecsa.models.AdminDto;
import com.example.indecsa.models.CapHumDto;
import com.example.indecsa.models.Contratista;
import com.example.indecsa.models.LoginRequestAdmin;
import com.example.indecsa.models.LoginRequestCapHum;
import com.example.indecsa.models.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @POST("/indecsa/api/contratistas")
    Call<Contratista> crearContratista(@Body Contratista contratista);

    @GET("/indecsa/api/contratistas")
    Call<List<Contratista>> obtenerContratistas();

    @GET("indecsa/api/contratistas")
    Call<List<Contratista>> obtenerContratistasPorEstadoYEspecialidad(@Query("estado") String estado, @Query("especialidad") String especialidad);

    @PUT("/indecsa/api/contratistas/{id}")
    Call<Contratista> actualizarContratista(@Path("id") int id, @Body Contratista contratista);



}
