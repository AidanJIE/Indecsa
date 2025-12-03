package com.example.indecsa;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // activity_main.xml debe tener el FrameLayout

        cargarLoginFragment();
    }

    private void cargarLoginFragment() {
        Inicio_sesion loginFragment = new Inicio_sesion();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedorfragmentos, loginFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}