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
    Button permissions_btn = findViewById(R.id.permissions_btn);
    permissions_btn.setOnClickListener(this);
  }

  public void onClick(View view) {
    Intent i;
    switch (view.getId()) {
      case R.id.permissions_btn:
        i = new Intent(Permissions.this, Cadastro.class);
        startActivity(i);
        break;
      default:
        break;
    }
  }
}