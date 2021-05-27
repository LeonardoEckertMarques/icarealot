package com.example.icarealot.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.icarealot.activites.Starting;

import java.util.ArrayList;
import java.util.List;

public class StartingController extends AppCompatActivity {

  public static final int CODIGO_PERMISSOES_REQUERIDAS = 1;

  String[] permissoes = {
          Manifest.permission.INTERNET,
          Manifest.permission.ACCESS_COARSE_LOCATION,
          Manifest.permission.ACCESS_FINE_LOCATION,
          Manifest.permission.READ_EXTERNAL_STORAGE,
          Manifest.permission.RECORD_AUDIO
  };

  //Verificar Permissões por Controller ;)

  /*
  public boolean verficarPermissoes() {
    List<String> permissoesRequeridas = new ArrayList<>();
    for (String permissoes : permissoes){
      if (ContextCompat.checkSelfPermission(StartingController.this, permissoes) != PackageManager.PERMISSION_GRANTED){
        permissoesRequeridas.add(permissoes);
      }
    }

    if (!permissoesRequeridas.isEmpty()){
      ActivityCompat.requestPermissions(StartingController.this, permissoesRequeridas.toArray(new String[permissoesRequeridas.size()]), CODIGO_PERMISSOES_REQUERIDAS);
      return false;
    }

    return true;
  }*/

}
