package com.example.icarealot.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.icarealot.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import maes.tech.intentanim.CustomIntent;

public class AlterarSenhaPerfil extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private TextView senhaAtual;
    private EditText senhaNova;
    private ImageButton voltar;
    private String password;
    private String atual;
    private Button alterarsenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_senha_perfil);
        mDatabase = FirebaseDatabase.getInstance();
        senhaAtual = findViewById(R.id.senha_atual);
        senhaNova = findViewById(R.id.senha_nova);
        alterarsenha = findViewById(R.id.alterar_senha);

        voltar = findViewById(R.id.btn_voltar_AlteraSenha);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlterarSenhaPerfil.this, ConfiguracoesPerfil.class);
                startActivity(i);
                CustomIntent.customType(AlterarSenhaPerfil.this, "right-to-left");
            }
        });

        alterarsenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = senhaNova.getText().toString();
                updateSenha();

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        getUserDados();
    }
    public void getUserDados() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference reference = mDatabase.getReference().child("usuarios").child(user.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    String senha = datasnapshot.child("senha").getValue(String.class);
                    senhaAtual.setText(senha);
                    atual = senha;
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }

    private void updateSenha(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(AlterarSenhaPerfil.this);
        builder.setTitle("Alteração de Senha");

        if(password.length() < 6){
            builder.setMessage("A senha deve conter no mínimo 6 caracteres");
            builder.setCancelable(true);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        }else {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference reference = mDatabase.getReference().child("usuarios").child(user.getUid());
            String teste = null;

            if(password.toString().equals(atual)){
                builder.setMessage("A nova senha, não pode ser igual a senha anterior!");
                builder.setCancelable(true);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }else {
                reference.child("senha").setValue(password);
                user.updatePassword(password);
                builder.setMessage("Senha Alterada com sucesso!");
                builder.setCancelable(true);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                        startActivity(getIntent());
                    }
                });
                builder.show();
            }
        }
    }
}