package com.example.icarealot;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.icarealot.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Cadastro extends AppCompatActivity implements View.OnClickListener {

  private FirebaseAuth mAuth;
  private Button btnCadastro;
  private EditText email;
  private EditText senha;
  private Button btnLogar;
  private Button btnPular;
  private CheckBox cb_mostrar_senha_cadastro;
  private ProgressBar cadastro_progessbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cadastro);

    mAuth = FirebaseAuth.getInstance();
    email = findViewById(R.id.email);
    senha = findViewById(R.id.senha);
    cb_mostrar_senha_cadastro = findViewById(R.id.cb_mostrar_senha_cadastro);
    cadastro_progessbar = findViewById(R.id.cadastro_progessbar);
    btnCadastro = findViewById(R.id.btn_cadastro);
    btnLogar = findViewById(R.id.btnLogar);
    btnPular = findViewById(R.id.btnPular);
    btnCadastro.setOnClickListener(this);
    btnLogar.setOnClickListener(this);
    btnPular.setOnClickListener(this);

    cb_mostrar_senha_cadastro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
          senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
      }
    });

  }

  public void onClick(View view) {
    Intent intent;
    switch (view.getId()) {
      case R.id.btnLogar:
        intent = new Intent(Cadastro.this, Login.class);
        startActivity(intent);
        break;
      case R.id.btnPular:
        intent = new Intent(Cadastro.this, TelaInicial.class);
        startActivity(intent);
        break;
      case R.id.btn_cadastro:
        String registroEmail = email.getText().toString();
        String registroSenha = senha.getText().toString();

        if(!TextUtils.isEmpty(registroEmail) || !TextUtils.isEmpty(registroSenha)) {
          cadastro_progessbar.setVisibility(View.VISIBLE);
          mAuth.createUserWithEmailAndPassword(registroEmail, registroSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()) {
                telaLogin();
              } else {
                String erro = task.getException().getMessage();
                Toast.makeText(Cadastro.this, "Erro:"+ erro, Toast.LENGTH_SHORT).show();
              }
              cadastro_progessbar.setVisibility(View.INVISIBLE);
            }
          });
        } else {
          Toast.makeText(Cadastro.this, "Erro: E-mail e/ou senha em branco!.", Toast.LENGTH_SHORT).show();
        }
        break;
      default:
      break;
    }
  }

  private void telaLogin() {
    Intent i = new Intent(Cadastro.this, Login.class);
    startActivity(i);
    finish();
  }

}