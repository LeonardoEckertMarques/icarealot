package com.example.icarealot.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.icarealot.R;
import com.example.icarealot.services.FirebaseServices;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

  private FirebaseAuth mAuth;
  private EditText edit_email;
  private EditText edit_senha;
  private Button btn_login;
  private Button btn_cadastro_login;
  private CheckBox cb_mostrar_senha;
  private ProgressBar login_progessbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    mAuth = FirebaseAuth.getInstance();
    edit_email = findViewById(R.id.edit_email);
    edit_senha = findViewById(R.id.edit_senha);
    btn_login = findViewById(R.id.btn_login);
    btn_cadastro_login = findViewById(R.id.btn_cadastro_login);
    cb_mostrar_senha = findViewById(R.id.cb_mostrar_senha);
    login_progessbar = findViewById(R.id.login_progessbar);
    btn_login.setOnClickListener(this);
    btn_cadastro_login.setOnClickListener(this);

    cb_mostrar_senha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          edit_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
          edit_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
      }
    });

  }

  @Override
  public void onClick(View view) {
    Intent intent;
    switch (view.getId()) {
      case R.id.btn_login:
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();
        if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(senha)) {
          login_progessbar.setVisibility(View.VISIBLE);
          if (FirebaseServices.getFirebaseAuthEmailSenha(email, senha)) {
            telaPrincipal();
          }
        } else {
          Toast.makeText(Login.this, "E-mail e/ou senha inválidos!.", Toast.LENGTH_SHORT).show();
        }
        break;
      case R.id.btn_cadastro_login:
        intent = new Intent(Login.this, Cadastro.class);
        startActivity(intent);
        finish();
        break;
    }
  }

  private void telaPrincipal() {
    Intent i = new Intent(Login.this, TelaInicial.class);
    startActivity(i);
    finish();
  }

}