package com.example.indecsa.network;

import com.example.indecsa.models.AdminDto;
import com.example.indecsa.models.CapHumDto;
import com.example.indecsa.models.Contratista;
import com.example.indecsa.models.LoginRequestAdmin;
import com.example.indecsa.models.LoginRequestCapHum;
import com.example.indecsa.models.LoginResponse;
import com.example.indecsa.models.TrabajadorDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    // ==================== LOGIN ====================
    @POST("admin/login")
    Call<LoginResponse> loginAdmin(@Body LoginRequestAdmin request);

    @POST("capitalhumano/login")
    Call<LoginResponse> loginCapHum(@Body LoginRequestCapHum request);

    @POST("admin")
    Call<AdminDto> registrarAdmin(@Body LoginRequestAdmin request);

    @POST("capitalhumano")
    Call<CapHumDto> registrarCapHumano(@Body LoginRequestCapHum request);

    // ==================== TRABAJADORES ====================
    @GET("trabajadores")
    Call<List<TrabajadorDto>> getAllTrabajadores();

    @GET("trabajadores/filtros")
    Call<List<TrabajadorDto>> getTrabajadoresFiltrados(
            @Query("estado") String estado,
            @Query("especialidad") String especialidad
    );

    @GET("trabajadores/{id}")
    Call<TrabajadorDto> getTrabajadorById(@Path("id") Integer id);

    @POST("trabajadores")
    Call<TrabajadorDto> createTrabajador(@Body TrabajadorDto trabajador);

    @PUT("trabajadores/{id}")
    Call<TrabajadorDto> updateTrabajador(@Path("id") Integer id, @Body TrabajadorDto trabajador);

    @DELETE("trabajadores/{id}")
    Call<Void> deleteTrabajador(@Path("id") Integer id);

    // ==================== FICHAS ====================
    @GET("fichas/filtros")
    Call<List<Object>> getFichasFiltradas(
            @Query("estado") String estado,
            @Query("especialidad") String especialidad
    );

    // ==================== CONTRATISTAS ====================
    @POST("contratistas")
    Call<Contratista> crearContratista(@Body Contratista contratista);

    @GET("contratistas")
    Call<List<Contratista>> obtenerContratistas();


    @GET("indecsa/api/contratistas")
    Call<List<Contratista>> obtenerContratistasPorEstadoYEspecialidad(@Query("estado") String estado, @Query("especialidad") String especialidad);

    @PUT("/indecsa/api/contratistas/{id}")
    Call<Contratista> actualizarContratista(@Path("id") int id, @Body Contratista contratista);
    
}
