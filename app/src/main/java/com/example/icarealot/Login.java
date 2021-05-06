package com.example.icarealot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icarealot.model.Usuario;

public class Login extends AppCompatActivity {

  private Button btn;
  private EditText usuario;
  private EditText senha;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    //Deixar por aqui por enquanto
    //Depois da pra mepar dentro do Manifest
    getSupportActionBar().hide();
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    btn = findViewById(R.id.button);
    usuario = findViewById(R.id.usuario);
    senha = findViewById(R.id.senha);

    btn.setOnClickListener(new View.OnClickListener(){
      public void onClick(View v) {
        String u = usuario.getText().toString();
        String s = senha.getText().toString();
        if (u.equals(s) && !u.isEmpty()) {
          Intent i = new Intent(Login.this, TelaInicial.class);
          Usuario user = new Usuario(u, s);
          i.putExtra("obj_usuario", user);
          startActivity(i);
        } else {
          Toast.makeText(getApplicationContext(), "Erro! Usuário / senha são diferentes ou em branco!", Toast.LENGTH_SHORT).show();
        }
      }
    });

  }
}