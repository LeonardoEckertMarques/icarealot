package com.example.icarealot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icarealot.model.UsuarioModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class TelaInicial extends AppCompatActivity {
// implements View.OnClickListener
  private FirebaseAuth mAuth;
  private FirebaseDatabase mDatabase;
  private ValueEventListener valueEventListener;
  private Button btnLogout;
  private TextView nome_TelaInicial;
  private TextView cpfCnpj_TelaIncial;
  private TextView email_TelaInicial;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tela_inicial);
    mDatabase = FirebaseDatabase.getInstance();
    //btnLogout = findViewById(R.id.btnLogout);
    //nome_TelaInicial = findViewById(R.id.nome_TelaInicial);
    //cpfCnpj_TelaIncial = findViewById(R.id.cpfCnpj_TelaInicial);
    //email_TelaInicial = findViewById(R.id.email_telaInicial);
    //btnLogout.setOnClickListener(this);
    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
    //bottomNavigationView.setSelectedItemId(R.id.navBuscar);
    Menu menu = bottomNavigationView.getMenu();
    MenuItem menuItem = menu.getItem(0);
    menuItem.setChecked(true);

    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
          case R.id.navBuscar:
            break;
          case R.id.navSalvos:
            startActivity(new Intent(getApplicationContext(), Salvos.class));
            break;
          case R.id.navMap:
            startActivity(new Intent(getApplicationContext(), Mapa.class));
            break;
          case R.id.navPerfil:
            startActivity(new Intent(getApplicationContext(), Perfil.class));
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
      //comunicaDatabase();
    }
  }

  /*DatabaseReference reference = mDatabase.getReference().child("usuarios").child(user.getUid());
    reference.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot datasnapshot) {
        String usuario = datasnapshot.child("usuario").getValue(String.class);
        nome_TelaInicial.setText(usuario);
        String cpfCnpj = datasnapshot.child("cpfCnpj").getValue(String.class);
        cpfCnpj_TelaIncial.setText(cpfCnpj);
        String email = datasnapshot.child("email").getValue(String.class);
        email_TelaInicial.setText(email);
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.btnLogout:
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(TelaInicial.this, Cadastro.class);
        startActivity(intent);
        finish();
        break;
    }
  }
  */

}