package com.example.icarealot.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.icarealot.R;
import maes.tech.intentanim.CustomIntent;

public class NotificacoesPerfil extends AppCompatActivity {
    private ImageButton voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes_perfil);
        voltar = findViewById(R.id.btn_voltarNotifica);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NotificacoesPerfil.this, Perfil.class);
                startActivity(i);
                CustomIntent.customType(NotificacoesPerfil.this, "right-to-left");
            }
        });
    }
}