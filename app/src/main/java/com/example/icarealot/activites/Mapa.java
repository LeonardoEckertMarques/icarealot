package com.example.icarealot.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.icarealot.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Mapa extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mapa);

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
    Menu menu = bottomNavigationView.getMenu();
    MenuItem menuItem = menu.getItem(2);
    menuItem.setChecked(true);

    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.navBuscar:
            startActivity(new Intent(getApplicationContext(), TelaInicial.class));
            break;
          case R.id.navSalvos:
            startActivity(new Intent(getApplicationContext(), Salvos.class));
            break;
          case R.id.navMap:
            break;
          case R.id.navPerfil:
            startActivity(new Intent(getApplicationContext(), Perfil.class));
            break;
        }
        return false;
      }
    });

  }
}