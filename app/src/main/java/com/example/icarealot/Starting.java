package com.example.icarealot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Starting extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_starting);
    Button permissions_btn = findViewById(R.id.permissions_btn);
    permissions_btn.setOnClickListener(this);
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