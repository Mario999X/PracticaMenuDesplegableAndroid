package com.example.menudesplegableandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.menudesplegableandroid.fragmentos.FragmentoDatos;
import com.example.menudesplegableandroid.fragmentos.FragmentoResultado;
import com.google.android.material.navigation.NavigationView;

 /*
    1. Modificamos el layout de main activity.
        - DrawerLayout como base para la actividad. (Importante el ID)
            - Yo añado un linearLayout para que me sea mas sencillo organizar los elementos.
        - ToolBar (+ modificacion de values->themes-> ambos.xml) (Importante el ID)
        - FrameLayout (donde se veran los fragmentos) (Importante el ID)
        - NavigationView (Importante el ID)
            - LayoutGravity = Start
    2. Creacion de un "Android Resource Directory" en res
        - Resource Type: Menu
        - Nuevo "Menu Resource File"
        - Ahi se colocaran los elementos que veremos en el menu lateral, así como sus iconos.
            - Para agregar iconos propios Android:
                - drawable -> New -> Vector Asset
        - Importante dar un ID a cada elemento.
    3. De vuelta al layout de main activity, escribimos lo siguiente dentro del NavigationView:
        -   app:menu="@menu/menu_layout"  | Siendo eso ultimo el nombre layout propio
            o simplemente buscamos en las opciones "menu", no es necesario meterse en el codigo del elemento
            en el layout.
        - Opcional el crear una cabecera para el menu, se crea en layout y se agrega dentro del navigationView:
            -   app:headerLayout="@layout/cabecera_layout"
    4. Programacion de Main Activity
    */

public class MainActivity extends AppCompatActivity {

    // Elementos de la vista
    private DrawerLayout drawerLayout;
    private Toolbar toolbar; //androidx
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enlazarComponentes();
        remplazarFragmentos(new FragmentoDatos());
        menu();
    }

    // Metodo que enlaza los componentes de la vista
    private void enlazarComponentes() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
    }

    // Metodo que reemplaza los fragmentos en el FrameLayout creado
    private void remplazarFragmentos(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragLayout, f);
        ft.commit();
    }

    // Elemento + Animacion para el menu en el ToolBar
    private void menu() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        menuListeners();
    }

    // Listeners para los elementos del menu
    private void menuListeners() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_fragmento_datos:
                        remplazarFragmentos(new FragmentoDatos());
                        break;
                    case R.id.menu_fragmento_resultado:
                        remplazarFragmentos(new FragmentoResultado());
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }
}