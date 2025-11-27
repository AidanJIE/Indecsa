package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.indecsa.models.AdminDto;
import com.example.indecsa.models.CapHumDto;
import com.example.indecsa.models.LoginRequestAdmin;
import com.example.indecsa.models.LoginRequestCapHum;
import com.example.indecsa.network.ApiService;
import com.example.indecsa.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registrarse extends Fragment {

    private EditText ediCorreo, ediPassword;
    private Spinner spinnerRol;
    private Button btnRegistrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registrarse, container, false);

        ediCorreo = view.findViewById(R.id.ediCorreo);
        ediPassword = view.findViewById(R.id.ediPass);
        spinnerRol = view.findViewById(R.id.spinnerRol);
        btnRegistrar = view.findViewById(R.id.btnRegistrar);

        // SPINNER DE ROLES
        String[] roles = {"Administrador", "Capital Humano"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                roles
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRol.setAdapter(adapter);

        btnRegistrar.setOnClickListener(v -> registrarUsuario());

        return view;
    }

    private void registrarUsuario() {

        String correo = ediCorreo.getText().toString().trim();
        String password = ediPassword.getText().toString().trim();
        String rol = spinnerRol.getSelectedItem().toString();

        if (correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Llena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService api = RetrofitClient.getClient().create(ApiService.class);

        // ============================================
        // 🔥 REGISTRO ADMIN
        // ============================================
        if (rol.equalsIgnoreCase("Administrador")) {

            LoginRequestAdmin req = new LoginRequestAdmin(correo, password);

            api.registrarAdmin(req).enqueue(new Callback<AdminDto>() {
                @Override
                public void onResponse(Call<AdminDto> call, Response<AdminDto> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(requireContext(),
                                "Administrador registrado exitosamente",
                                Toast.LENGTH_SHORT).show();
                        volverALogin();
                    } else {
                        Toast.makeText(requireContext(),
                                "Error al registrar administrador. Código: " + response.code(),
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<AdminDto> call, Throwable t) {
                    Toast.makeText(requireContext(),
                            "Error de conexión: " + t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });

        }
        // ============================================
        // 🔥 REGISTRO CAPITAL HUMANO
        // ============================================
        else {

            LoginRequestCapHum req = new LoginRequestCapHum(correo, password);

            api.registrarCapHumano(req).enqueue(new Callback<CapHumDto>() {
                @Override
                public void onResponse(Call<CapHumDto> call, Response<CapHumDto> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(requireContext(),
                                "Capital Humano registrado exitosamente",
                                Toast.LENGTH_SHORT).show();
                        volverALogin();
                    } else {
                        Toast.makeText(requireContext(),
                                "Error al registrar Capital Humano. Código: " + response.code(),
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<CapHumDto> call, Throwable t) {
                    Toast.makeText(requireContext(),
                            "Error de conexión: " + t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void volverALogin() {
        Inicio_sesion loginFragment = new Inicio_sesion();

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorfragmentos, loginFragment)
                .addToBackStack(null)
                .commit();
    }
}
