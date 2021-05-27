package com.example.icarealot.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.icarealot.services.MapsActivity;
import com.example.icarealot.R;
import com.example.icarealot.adapter.OngsAdapter;
import com.example.icarealot.model.Ongs;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TelaInicial extends AppCompatActivity {

  List<Ongs> ongsList;
  RecyclerView recyclerView;
  OngsAdapter ongsAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.layout_testes);

    recyclerView = findViewById(R.id.ongLists);
    ongsList = new ArrayList<>();
    extractOngs();

    //Menu de itens importante anexar o INDEX para navegação do usuário!
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
            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            break;
          case R.id.navPerfil:
            startActivity(new Intent(getApplicationContext(), Perfil.class));
            break;
        }
        return false;
      }
    });
  }

  private void extractOngs() {
    String url = "https://icarealot-47aeb-default-rtdb.firebaseio.com/ongs.json";
    RequestQueue queue = Volley.newRequestQueue(this);
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
      @Override
      public void onResponse(JSONArray response) {
        for (int i = 0; i < response.length(); i++) {
          try {
            JSONObject jsonObject = response.getJSONObject(i);
            Ongs ongs = new Ongs();
            //ongs.setId(jsonObject.getString("id").toString());
            ongs.setNome(jsonObject.getString("nome").toString());
            //ongs.setDescricao(jsonObject.getString("descricao").toString());
            //ongs.setTelefone(jsonObject.getString("telefone").toString());
            ongs.setUrlFoto(jsonObject.getString("foto").toString());
            ongsList.add(ongs);
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        //recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ongsAdapter = new OngsAdapter(getApplicationContext(), ongsList);
        recyclerView.setAdapter(ongsAdapter);

      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Log.d("Log Salvos", "onErrorResponse" + error.getMessage());
      }
    });

    queue.add(jsonArrayRequest);

  }


}