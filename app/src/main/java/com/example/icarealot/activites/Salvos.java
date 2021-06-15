package com.example.icarealot.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.icarealot.services.FirebaseServices;
import com.example.icarealot.services.MapsActivity;
import com.example.icarealot.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Salvos extends AppCompatActivity  {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_salvos);

    //Menu de itens importante anexar o INDEX para navegação do usuário!
    //Caso contrário o menu não funciona corretamente, referente ao return!
    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
    Menu menu = bottomNavigationView.getMenu();
    MenuItem menuItem = menu.getItem(1);
    menuItem.setChecked(true);
    //!important
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.navBuscar:
            startActivity(new Intent(getApplicationContext(), TelaInicial.class));
            break;
          case R.id.navSalvos:
            break;
          case R.id.navMap:
            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
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