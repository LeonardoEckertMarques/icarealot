package com.example.icarealot.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.icarealot.R;

import maes.tech.intentanim.CustomIntent;

public class ConfiguracoesPerfil extends AppCompatActivity {
    private ImageButton voltar;
    private Button alteraSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes_perfil);

        alteraSenha = findViewById(R.id.btn_alterarsenha);
        voltar = findViewById(R.id.btn_voltar_config);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfiguracoesPerfil.this, Perfil.class);
                startActivity(i);
                CustomIntent.customType(ConfiguracoesPerfil.this, "right-to-left");
            }
        });

        alteraSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfiguracoesPerfil.this, AlterarSenhaPerfil.class);
                startActivity(i);
                CustomIntent.customType(ConfiguracoesPerfil.this, "left-to-right");
            }
        });

    }
}