package com.example.icarealot.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.example.icarealot.R;

import maes.tech.intentanim.CustomIntent;

public class ConfiguracoesPerfil extends AppCompatActivity {
    private ImageButton voltar;
    private Button alteraSenha;
    private SwitchCompat darkMode;
    private static String checked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes_perfil);

        alteraSenha = findViewById(R.id.btn_alterarsenha);
        voltar = findViewById(R.id.btn_voltar_config);
        darkMode = findViewById(R.id.switch_darkMode);

        if(checked == "true"){
            darkMode.setChecked(true);
        }else{
            darkMode.setChecked(false);
        }

        darkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    darkMode.setChecked(true);
                    checked = "true";
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    darkMode.setChecked(false);
                    checked = "false";
                }
            }
        });

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