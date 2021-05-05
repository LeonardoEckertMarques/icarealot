package com.example.icarealot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PermissoesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissoes);
    }

    //Apenas um onClick para várias opções de botões!
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home:
                Intent i = new Intent();
                startActivity(i);
                break;
            default:
                break;
        }
    }

}
