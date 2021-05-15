package com.example.icarealot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

  private FirebaseAuth mAuth;
  private EditText edit_email;
  private EditText edit_senha;
  private Button btn_login;
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
    cb_mostrar_senha = findViewById(R.id.cb_mostrar_senha);
    login_progessbar = findViewById(R.id.login_progessbar);

    btn_login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        if(!email.isEmpty() || !senha.isEmpty()) {
          login_progessbar.setVisibility(View.VISIBLE);
          mAuth.signInWithEmailAndPassword(email, senha)
                  .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()) {
                        telaPrincipal();
                      } else {
                        String erro = task.getException().getMessage();
                        Toast.makeText(Login.this, "Erro:"+ erro, Toast.LENGTH_SHORT).show();
                        login_progessbar.setVisibility(View.INVISIBLE);
                      }
                    }
                  });
        }

      }
    });

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

  private void telaPrincipal() {
    Intent i = new Intent(Login.this, TelaInicial.class);
    startActivity(i);
    finish();
  }
}