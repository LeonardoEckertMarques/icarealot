package com.example.icarealot.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.icarealot.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import maes.tech.intentanim.CustomIntent;

public class DadosPessoaisPerfil extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private TextView nome;
    private TextView cpfCnpj;
    private TextView senha;
    private TextView email;
    private ImageButton voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais_perfil);
        mDatabase = FirebaseDatabase.getInstance();
        nome = findViewById(R.id.nome_DadosPessoais);
        cpfCnpj = findViewById(R.id.cpfCnpj_DadosPessoais);
        senha = findViewById(R.id.senha_DadosPessoais);
        email = findViewById(R.id.email_DadosPessoais);
        voltar = findViewById(R.id.voltar_DadosPessoais);


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DadosPessoaisPerfil.this, Perfil.class);
                startActivity(i);
                CustomIntent.customType(DadosPessoaisPerfil.this, "right-to-left");
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
                    String user = datasnapshot.child("usuario").getValue(String.class);
                    String cpfCnpjUser = datasnapshot.child("cpfCnpj").getValue(String.class);
                    String senhaUser = datasnapshot.child("senha").getValue(String.class);
                    String emailUser = datasnapshot.child("email").getValue(String.class);
                    Boolean tipo = datasnapshot.child("tipoOng").getValue(Boolean.class);
                    if( tipo == false){
                        String cpf = cpfCnpjUser.substring(0,3)+"."+cpfCnpjUser.substring(3,6)+"."+cpfCnpjUser.substring(6,9)+"-"+ cpfCnpjUser.substring(9,11);
                        cpfCnpj.setText(cpf);
                    }else{
                        String cnpj = cpfCnpjUser.substring(0,2)+"."+cpfCnpjUser.substring(2,5)+"."+cpfCnpjUser.substring(5,8)+"/"+ cpfCnpjUser.substring(8,12)+"-"+ cpfCnpjUser.substring(12,14);
                        cpfCnpj.setText(cnpj);
                    }

                    nome.setText(user);
                    senha.setText(senhaUser);
                    email.setText(emailUser);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


}