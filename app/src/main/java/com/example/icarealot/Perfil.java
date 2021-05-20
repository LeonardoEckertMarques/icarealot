package com.example.icarealot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Perfil extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_perfil);

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
    //bottomNavigationView.setSelectedItemId(R.id.navPerfil);
    Menu menu = bottomNavigationView.getMenu();
    MenuItem menuItem = menu.getItem(3);
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
            startActivity(new Intent(getApplicationContext(), Mapa.class));
            break;
          case R.id.navPerfil:
            break;
        }
        return false;
      }
    });
  }
}