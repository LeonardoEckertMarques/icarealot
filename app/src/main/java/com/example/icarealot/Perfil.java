package com.example.icarealot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Perfil extends AppCompatActivity {

  private FirebaseAuth mAuth;
  private FirebaseDatabase mDatabase;
  private ValueEventListener valueEventListener;
  private TextView nome_TelaPerfil;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_perfil);
    //Instancia do BD
    mDatabase = FirebaseDatabase.getInstance();
    nome_TelaPerfil = findViewById(R.id.nome_TelaPerfil);

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
    Menu menu = bottomNavigationView.getMenu();
    MenuItem menuItem = menu.getItem(3);
    menuItem.setChecked(true);
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.navBuscar:
            startActivity(new Intent(getApplicationContext(), TelaInicial.class));
            break;
          case R.id.navSalvos:
            startActivity(new Intent(getApplicationContext(), Salvos.class));
            break;
          case R.id.navMap:
            startActivity(new Intent(getApplicationContext(), Mapa.class));
            break;
          case R.id.navPerfil:
            break;
        }
        return false;
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
          String usuario = datasnapshot.child("usuario").getValue(String.class);
          nome_TelaPerfil.setText(usuario);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
      });
    }
  }

}