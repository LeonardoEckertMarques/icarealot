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
import com.example.icarealot.model.Usuario;
import com.example.icarealot.services.FirebaseServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity
        implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

  private Button btnCadastro;
  private EditText nome;
  private EditText email;
  private EditText cpfCnpj;
  private EditText senha;
  private Button btnLogar;
  private Button btnPular;
  private CheckBox cb_mostrar_senha_cadastro;
  private CheckBox cb_ong_cadastro;
  private ProgressBar cadastro_progessbar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cadastro);

    nome = findViewById(R.id.nome);
    email = findViewById(R.id.email);
    cpfCnpj = findViewById(R.id.cpfCnpj);
    senha = findViewById(R.id.senha);
    cb_mostrar_senha_cadastro = findViewById(R.id.cb_mostrar_senha_cadastro);
    cb_ong_cadastro = findViewById(R.id.cb_ong_cadastro);
    cadastro_progessbar = findViewById(R.id.cadastro_progessbar);
    btnCadastro = findViewById(R.id.btn_cadastro);
    btnLogar = findViewById(R.id.btnLogar);
    btnPular = findViewById(R.id.btnPular);

    btnCadastro.setOnClickListener(this);
    btnLogar.setOnClickListener(this);
    btnPular.setOnClickListener(this);
  }

  public void onCadastrarUsuario() {
    Usuario usuario = new Usuario();
    usuario.setUsuario(nome.getText().toString());
    usuario.setEmail(email.getText().toString());
    usuario.setCpfCnpj(cpfCnpj.getText().toString());
    usuario.setSenha(senha.getText().toString());
    usuario.setTipoOng(cb_ong_cadastro.isChecked());

    if(!TextUtils.isEmpty(usuario.getUsuario()) || !TextUtils.isEmpty(usuario.getCpfCnpj()) || !TextUtils.isEmpty(usuario.getEmail()) || !TextUtils.isEmpty(usuario.getSenha())) {
      cadastro_progessbar.setVisibility(View.VISIBLE);
      FirebaseServices.getFirebaseAuthInstance().createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
          if(task.isSuccessful()) {
            Toast.makeText(Cadastro.this, "Sucesso! Usuário cadastrato!", Toast.LENGTH_SHORT).show();
            usuario.setId(FirebaseServices.getFirebaseAuthInstance().getUid());
            usuario.salvaUsuario();
            startActivity(new Intent(Cadastro.this, Login.class));
            finish();
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
  }

  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btnLogar:
        startActivity(new Intent(Cadastro.this, Login.class));
        break;
      case R.id.btnPular:
        startActivity(new Intent(Cadastro.this, TelaInicial.class));
        break;
      case R.id.btn_cadastro:
        onCadastrarUsuario();
        break;
    }
  }

  @Override
  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    if (isChecked) {
      senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    } else {
      senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
  }
}