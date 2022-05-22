package com.example.menudesplegableandroid.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menudesplegableandroid.R;

public class FragmentoDatos extends Fragment {

    EditText editTextNombre, editTextEdad;
    Button btnEnviar;

    public FragmentoDatos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View main = inflater.inflate(R.layout.fragment_fragmento_datos, container, false);

        editTextNombre = main.findViewById(R.id.editTextNombre);
        editTextEdad = main.findViewById(R.id.editTextEdad);
        btnEnviar = main.findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextNombre.getText().toString().trim().length() > 0 && editTextEdad.getText().toString().trim().length() > 0) {
                    Bundle result = new Bundle();
                    result.putString("nombre", editTextNombre.getText().toString());
                    result.putString("edad", editTextEdad.getText().toString());
                    getParentFragmentManager().setFragmentResult("datos", result);
                    Toast.makeText(main.getContext(), "Datos Enviados", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(main.getContext(), "Algún campo esta vacio", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return main;


    }

}