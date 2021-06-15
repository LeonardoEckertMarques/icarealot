package com.example.icarealot.activites;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.icarealot.R;
import com.example.icarealot.services.FirebaseServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Login extends AppCompatActivity
        implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

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

    edit_email = findViewById(R.id.edit_email);
    edit_senha = findViewById(R.id.edit_senha);
    btn_login = findViewById(R.id.btn_login);
    btn_cadastro_login = findViewById(R.id.btn_cadastro_login);
   // cb_mostrar_senha = findViewById(R.id.cb_mostrar_senha);
    login_progessbar = findViewById(R.id.login_progessbar);

    btn_login.setOnClickListener(this);
    btn_cadastro_login.setOnClickListener(this);
  }

  public void onLogarUsuario() {
    String email = edit_email.getText().toString();
    String senha = edit_senha.getText().toString();
    if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(senha)) {
      login_progessbar.setVisibility(View.VISIBLE);
      FirebaseServices.getFirebaseAuthInstance().signInWithEmailAndPassword(email, senha)
              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()) {
                    startActivity(new Intent(Login.this, TelaInicial.class));
                    finish();
                  } else {
                    String erro = task.getException().getMessage();
                    Toast.makeText(Login.this, "Erro: " + erro, Toast.LENGTH_SHORT).show();
                    login_progessbar.setVisibility(View.INVISIBLE);
                  }
                }
              });
    } else {
      Toast.makeText(Login.this, "E-mail e/ou senha inválidos!.", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btn_login:
        onLogarUsuario();
        break;
      case R.id.btn_cadastro_login:
        startActivity(new Intent(Login.this, Cadastro.class));
        finish();
        break;
    }
  }

  @Override
  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    if (isChecked) {
      edit_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    } else {
      edit_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
  }

}