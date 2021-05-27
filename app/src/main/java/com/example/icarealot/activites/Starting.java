package com.example.icarealot.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.icarealot.R;
import com.example.icarealot.controller.StartingController;
import com.example.icarealot.services.FirebaseServices;

import java.util.ArrayList;
import java.util.List;

public class Starting extends AppCompatActivity implements View.OnClickListener {

  private TextView txtPermissoes;

  public static final int CODIGO_PERMISSOES_REQUERIDAS = 1;

  String[] permissoes = {
          Manifest.permission.INTERNET,
          Manifest.permission.ACCESS_COARSE_LOCATION,
          Manifest.permission.ACCESS_FINE_LOCATION,
          Manifest.permission.READ_EXTERNAL_STORAGE,
          Manifest.permission.RECORD_AUDIO
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_starting);
    txtPermissoes = findViewById(R.id.txtPermissioes);
    Button permissions_btn = findViewById(R.id.permissions_btn);
    permissions_btn.setOnClickListener(this);
    //StartingController startingController = new StartingController();
    if (!verficarPermissoes()){
      txtPermissoes.setText("Permissões necessárias não estão ativadas!");
    }
  }

  public boolean verficarPermissoes() {
    List<String> permissoesRequeridas = new ArrayList<>();
    for (String permissoes : permissoes){
        if (ContextCompat.checkSelfPermission(this, permissoes) != PackageManager.PERMISSION_GRANTED){
            permissoesRequeridas.add(permissoes);
        }
    }
    if (!permissoesRequeridas.isEmpty()){
      ActivityCompat.requestPermissions(this, permissoesRequeridas.toArray(new String[permissoesRequeridas.size()]), CODIGO_PERMISSOES_REQUERIDAS);
      return false;
    }
    txtPermissoes.setText("Começando a cuidar da comunidade...");
    return true;
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (FirebaseServices.getFirebaseUserCurrentUserInstance() != null) {
      Intent intent = new Intent(Starting.this, TelaInicial.class);
      startActivity(intent);
      finish();
    }
  }

  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.permissions_btn:
        Intent intent = new Intent(Starting.this, Cadastro.class);
        startActivity(intent);
        finishAffinity();
        break;
    }
  }

}