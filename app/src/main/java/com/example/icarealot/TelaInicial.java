package com.example.icarealot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaInicial extends AppCompatActivity implements View.OnClickListener {

  private FirebaseAuth mAuth;
  private Button btnLogout;
  private TextView id_TelaInicial;
  private TextView email_TelaInicial;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tela_inicial);
    btnLogout = findViewById(R.id.btnLogout);
    id_TelaInicial = findViewById(R.id.id_TelaInicial);
    email_TelaInicial = findViewById(R.id.email_telaInicial);
    btnLogout.setOnClickListener(this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    getUserDados();
  }

  public void getUserDados() {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    if (user != null) {
      String id = user.getUid();
      id_TelaInicial.setText(id);
      String email = user.getEmail();
      email_TelaInicial.setText(email);
    }
  }

  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btnLogout:
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(TelaInicial.this, Cadastro.class);
        startActivity(intent);
        finish();
        break;
    }
  }

}