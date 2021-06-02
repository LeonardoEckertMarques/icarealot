package com.example.icarealot.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icarealot.R;

import maes.tech.intentanim.CustomIntent;

public class SalvosPerfil extends AppCompatActivity {
    private ImageButton voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvos_perfil);

        voltar = findViewById(R.id.btn_voltar_salvos);


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SalvosPerfil.this, Perfil.class);
                startActivity(i);
                CustomIntent.customType(SalvosPerfil.this, "right-to-left");
            }
        });
    }
}