package com.example.icarealot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Permissions extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_permissions);
    //Deixar por aqui por enquanto
    //Depois da pra mepar dentro do Manifest
    getSupportActionBar().hide();
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    Button permissions_btn = findViewById(R.id.permissions_btn);
    //Trocar por um botão!!!!
    //TextView permissions_tv = findViewById(R.id.permissoes_top);
    permissions_btn.setOnClickListener(this);
    //permissions_tv.setOnClickListener(this);
  }

  public void onClick(View view) {
    Intent i;
    switch (view.getId()) {
      case R.id.permissions_btn:
        i = new Intent(Permissions.this, Login.class);
        startActivity(i);
        break;
      //case R.id.permissoes_top:
        //i = new Intent(Permissions.this, TelaInicial.class);
      default:
        break;
    }
  }
}