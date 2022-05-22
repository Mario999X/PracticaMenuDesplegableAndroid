package com.example.menudesplegableandroid.fragmentos;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.menudesplegableandroid.R;

public class FragmentoResultado extends Fragment {

    TextView textViewNombreResultado, textViewEdadResultado;

    public FragmentoResultado() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("datos", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String nombreResultado = result.getString("nombre");
                String edadResultado = result.getString("edad");

                textViewNombreResultado.setText("Nombre: " + nombreResultado);
                textViewEdadResultado.setText("Edad : " + edadResultado);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View main = inflater.inflate(R.layout.fragment_fragmento_resultado, container, false);
        textViewNombreResultado = main.findViewById(R.id.textViewNombreResultado);
        textViewEdadResultado = main.findViewById(R.id.textViewEdadResultado);

        return main;
    }


}