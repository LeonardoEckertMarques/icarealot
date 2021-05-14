package com.example.icarealot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icarealot.model.Usuario;

public class Cadastro extends AppCompatActivity implements View.OnClickListener {

  private Button btn;
  private Button btnLogar;
  private Button btnPular;
  private EditText usuario;
  private EditText senha;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cadastro);
    btn = findViewById(R.id.btn_cadastro);
    usuario = findViewById(R.id.usuario);
    senha = findViewById(R.id.senha);
    btnLogar = findViewById(R.id.btnLogar);
    btnPular = findViewById(R.id.btnPular);
    btnLogar.setOnClickListener(this);
    btnPular.setOnClickListener(this);
  }

  public void onClick(View view) {
    Intent i;
    switch (view.getId()) {
      case R.id.btnLogar:
        i = new Intent(Cadastro.this, Login.class);
        startActivity(i);
        break;
      case R.id.btnPular:
        i = new Intent(Cadastro.this, TelaInicial.class);
        startActivity(i);
      default:
        break;
    }
  }

}