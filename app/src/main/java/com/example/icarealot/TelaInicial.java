package com.example.icarealot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class TelaInicial extends AppCompatActivity implements View.OnClickListener {

  private FirebaseAuth mAuth;
  private Button btnLogout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tela_inicial);
    mAuth = FirebaseAuth.getInstance();
    btnLogout = findViewById(R.id.btnLogout);
    btnLogout.setOnClickListener(this);
  }

  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btnLogout:
        mAuth.signOut();
        Intent intent = new Intent(TelaInicial.this, Cadastro.class);
        startActivity(intent);
        finish();
        break;
      default:
        break;
    }
  }

}