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

// SI SE USA UN TOOLBAR PROPIO, HAY QUE MODIFICAR LOS ARCHIVOS "THEMES" EN VALUES.

public class MainActivity extends AppCompatActivity {

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

    private void enlazarComponentes() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
    }

    private void remplazarFragmentos(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragLayout, f);
        ft.commit();
    }

    private void menu() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        menuListeners();
    }

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