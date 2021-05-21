package com.example.icarealot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.icarealot.adapter.CommentsAdapter;
import com.example.icarealot.model.Comments;
import com.example.icarealot.model.UsuarioModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends AppCompatActivity
        implements Response.Listener<JSONArray>,
        Response.ErrorListener {

  List<Comments> comments = new ArrayList<>();

  //implements View.OnClickListener
  private FirebaseAuth mAuth;
  private FirebaseDatabase mDatabase;
  private ValueEventListener valueEventListener;
  //private Button btnLogout;
  //private TextView nome_TelaInicial;
  //private TextView cpfCnpj_TelaIncial;
  //private TextView email_TelaInicial;
  //private TextView testes;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tela_inicial);
    //Instancia do BD
    mDatabase = FirebaseDatabase.getInstance();
    //btnLogout = findViewById(R.id.btnLogout);
    //nome_TelaInicial = findViewById(R.id.nome_TelaInicial);
    //cpfCnpj_TelaIncial = findViewById(R.id.cpfCnpj_TelaInicial);
    //email_TelaInicial = findViewById(R.id.email_telaInicial);
    //btnLogout.setOnClickListener(this);

    RequestQueue queue = Volley.newRequestQueue(this);
    String url = "https://jsonplaceholder.typicode.com/comments";
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
            this, this);
    queue.add(jsonArrayRequest);

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
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
  public void onResponse(JSONArray response) {

    try {
      for(int i = 0; i < response.length(); i++) {
        JSONObject json = response.getJSONObject(i);
        Comments obj = new Comments(
                json.getInt("postId"),
                json.getInt("id"),
                json.getString("name"),
                json.getString("email"),
                json.getString("body"));
        comments.add(obj);
      }

      Toast.makeText(this,"Recebido: " + comments.size() + " comments",Toast.LENGTH_LONG).show();

      RecyclerView recycler = findViewById(R.id.recycler);
      GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
      recycler.setLayoutManager(gridLayoutManager);

      CommentsAdapter commentsAdapter = new CommentsAdapter(comments, R.layout.layout_lista);
      recycler.setAdapter(commentsAdapter);

    } catch (JSONException e) {
      Log.e("JSONException",e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void onErrorResponse(VolleyError error) {
    String msg = error.getMessage();
    Toast.makeText(TelaInicial.this,"onErrorResponse: "+msg,Toast.LENGTH_LONG).show();
  }

  /*
  @Override
  protected void onStart() {
    super.onStart();
    //getUserDados();
  }

  public void getUserDados() {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    if (user != null) {
      DatabaseReference reference = mDatabase.getReference().child("usuarios").child(user.getUid());
      reference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot datasnapshot) {
          String usuario = datasnapshot.child("usuario").getValue(String.class);
          testes.setText(usuario);
          String cpfCnpj = datasnapshot.child("cpfCnpj").getValue(String.class);
          cpfCnpj_TelaIncial.setText(cpfCnpj);
          String email = datasnapshot.child("email").getValue(String.class);
          email_TelaInicial.setText(email);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
      });
    }
  }


  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.home:
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(TelaInicial.this, Cadastro.class);
        startActivity(intent);
        finish();
        break;
    }
  }
   */

}