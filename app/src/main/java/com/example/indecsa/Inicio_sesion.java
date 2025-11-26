package com.example.indecsa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.indecsa.models.LoginRequest;
import com.example.indecsa.models.LoginResponse;
import com.example.indecsa.network.ApiService;
import com.example.indecsa.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inicio_sesion extends Fragment {

    private EditText etCorreo, etPassword;

    public Inicio_sesion() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio_sesion, container, false);

        etCorreo = view.findViewById(R.id.etCorreo);
        etPassword = view.findViewById(R.id.etPassword);

        // Botón login
        view.findViewById(R.id.btnInicio_sesion).setOnClickListener(v -> intentarLogin());

        // Botón crear cuenta
        view.findViewById(R.id.btnCrear_cuenta).setOnClickListener(v -> {
            Registrarse r = new Registrarse();
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorfragmentos, r)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    private void intentarLogin() {

        String correo = etCorreo.getText().toString().trim();
        String contra = etPassword.getText().toString().trim();

        if (correo.isEmpty() || contra.isEmpty()) {
            Toast.makeText(requireContext(), "Llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService api = RetrofitClient.getClient().create(ApiService.class);
        LoginRequest request = new LoginRequest(correo, contra);

        api.loginCapitalHumano(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(requireContext(),
                            "Error en el servidor (" + response.code() + ")",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                LoginResponse res = response.body();

                if (res == null) {
                    Toast.makeText(requireContext(),
                            "Respuesta vacía del servidor",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (res.isSuccess()) {

                    Toast.makeText(requireContext(),
                            "Bienvenido " + res.getCapitalHumano().getCorreoCapHum(),
                            Toast.LENGTH_SHORT).show();

                    // Cambiar al menú
                    Administrador menu = new Administrador();
                    requireActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contenedorfragmentos, menu)
                            .commit();

                } else {
                    Toast.makeText(requireContext(), res.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(requireContext(),
                        "Error de conexión: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

                Log.e("LOGIN_ERROR", "Fallo de conexión", t);
            }
        });
    }
}
