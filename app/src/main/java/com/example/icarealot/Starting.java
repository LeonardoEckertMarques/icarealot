package com.example.icarealot;

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

import java.util.ArrayList;
import java.util.List;

public class Starting extends AppCompatActivity implements View.OnClickListener {

  TextView txtPermissoes;

  String[] permissoes = {
          Manifest.permission.INTERNET,
          Manifest.permission.ACCESS_COARSE_LOCATION,
          Manifest.permission.ACCESS_FINE_LOCATION,
          Manifest.permission.READ_EXTERNAL_STORAGE,
          Manifest.permission.RECORD_AUDIO
  };

  public static final int CODIGO_PERMISSOES_REQUERIDAS = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_starting);
    Button permissions_btn = findViewById(R.id.permissions_btn);
    permissions_btn.setOnClickListener(this);
    txtPermissoes = findViewById(R.id.txtPermissioes);

    if (verficarPermissoes()){


    } else {

         txtPermissoes.setText("Nem todas as permissões necessárias estão ativadas");
    }
  }

  public boolean verficarPermissoes(){

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

    txtPermissoes.setText("Todas as permissões estão ativas");

    return true;

  }

  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.permissions_btn:
        Intent intent = new Intent(Starting.this, Cadastro.class);
        startActivity(intent);
        finish();
        break;
      default:
        break;
    }
  }
}