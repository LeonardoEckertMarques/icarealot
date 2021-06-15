package com.example.icarealot.activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.icarealot.R;
import com.example.icarealot.model.Address;
import com.example.icarealot.model.Geo;
import com.example.icarealot.model.Ongs;
import com.example.icarealot.model.Transferencia;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;
import maes.tech.intentanim.CustomIntent;

public class DadosOng extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private TextView nomeOng;
    private EditText descricao;
    private EditText rua;
    private EditText numero;
    private EditText complemento;
    private EditText cidade;
    private EditText cep;
    private EditText latitude;
    private EditText longitude;
    private EditText telefone;
    private EditText banco;
    private EditText agencia;
    private EditText conta;
    private EditText identificador;
    private EditText pix;
    private ImageButton voltar;
    private static String nomeUser;
    private Button salvarDados;
    private String fotoPerfilOng;
    private String ongNome;
    private Button alterarOng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_ong);
        mDatabase = FirebaseDatabase.getInstance();
        voltar = findViewById(R.id.btn_voltar_DadosOng);
        nomeOng = findViewById(R.id.nome_da_ong);
        descricao = findViewById(R.id.descricao_ong);
        rua = findViewById(R.id.rua_ong);
        numero = findViewById(R.id.numero_ong);
        complemento = findViewById(R.id.complemento_ong);
        cidade = findViewById(R.id.cidade_ong);
        cep = findViewById(R.id.cep_ong);
        latitude = findViewById(R.id.latitude_ong);
        longitude = findViewById(R.id.longitude_ong);
        telefone = findViewById(R.id.telefone_ong);
        banco = findViewById(R.id.banco_ong);
        agencia = findViewById(R.id.agencia_ong);
        conta = findViewById(R.id.conta_ong);
        identificador = findViewById(R.id.identificador_ong);
        pix = findViewById(R.id.pix_ong);
        salvarDados = findViewById(R.id.btn_salvar_ong);
        alterarOng = findViewById(R.id.btn_alterarOng);
        telefone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));


        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DadosOng.this, Perfil.class);
                startActivity(i);
                CustomIntent.customType(DadosOng.this, "right-to-left");
            }
        });

        salvarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCadastrarOng();
            }
        });
        alterarOng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarOng();
            }
        });
        MaskEditTextChangedListener maskCEP = new MaskEditTextChangedListener("#####-###", cep);
        cep.addTextChangedListener(maskCEP);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getUserDados();
        getOngDados();

    }

    public void getUserDados() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DatabaseReference reference = mDatabase.getReference().child("usuarios").child(user.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                    nomeUser = datasnapshot.child("usuario").getValue(String.class);
                    nomeOng.setText(nomeUser);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void getOngDados() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ongs");
        myRef.orderByChild("ongs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String nameOng = childDataSnapshot.child("nome").getValue().toString();

                    if(nameOng.equals(nomeUser)){
                        alterarOng.setVisibility(View.VISIBLE);
                        salvarDados.setVisibility(View.GONE);

                        descricao.setText(childDataSnapshot.child("descricao").getValue().toString());
                        telefone.setText(childDataSnapshot.child("telefone").getValue().toString());
                        rua.setText(childDataSnapshot.child("address").child("rua").getValue().toString());
                        numero.setText(childDataSnapshot.child("address").child("numero").getValue().toString());
                        complemento.setText(childDataSnapshot.child("address").child("complemento").getValue().toString());
                        cep.setText(childDataSnapshot.child("address").child("cep").getValue().toString());
                        cidade.setText(childDataSnapshot.child("address").child("cidade").getValue().toString());
                        latitude.setText(childDataSnapshot.child("geo").child("lat").getValue().toString());
                        longitude.setText(childDataSnapshot.child("geo").child("lng").getValue().toString());
                        banco.setText(childDataSnapshot.child("transferencia").child("banco").getValue().toString());
                        agencia.setText(childDataSnapshot.child("transferencia").child("agencia").getValue().toString());
                        conta.setText(childDataSnapshot.child("transferencia").child("conta").getValue().toString());
                        identificador.setText(childDataSnapshot.child("transferencia").child("identificador").getValue().toString());
                        pix.setText(childDataSnapshot.child("transferencia").child("pix").getValue().toString());
                    }else{
                        alterarOng.setVisibility(View.GONE);
                        salvarDados.setVisibility(View.VISIBLE);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(DadosOng.this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void onCadastrarOng() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ongs");
        myRef.orderByChild("nome").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> key =  new ArrayList<String>();

                long lista = dataSnapshot.getChildrenCount();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    ongNome = childDataSnapshot.child("nome").getValue().toString();
                }
                if (ongNome != nomeUser) {

                    if (user.getPhotoUrl() != null) {
                        fotoPerfilOng = user.getPhotoUrl().toString();
                    }

                    Ongs ongs = new Ongs();
                    ongs.setDescricao(descricao.getText().toString());
                    ongs.setNome(nomeUser);
                    ongs.setFoto(fotoPerfilOng);
                    ongs.setTelefone(telefone.getText().toString());


                    Address address = new Address();
                    address.setRua(rua.getText().toString());
                    address.setNumero(numero.getText().toString());
                    address.setComplemento(complemento.getText().toString());
                    address.setCidade((cidade.getText().toString()));
                    address.setCep((cep.getText().toString()));
                    ongs.setAddress(address);

                    Geo geo = new Geo();

                    Log.i("onteste555", "onresponse5555"+ latitude.getText().toString());

                    if(latitude.getText().toString().equals("")){
                        Double latidudeDouble = Double.valueOf(0);
                        geo.setLat(latidudeDouble);
                    }else{
                        Double latidudeDouble = Double.parseDouble(String.valueOf(latitude.getText()));
                        geo.setLat(latidudeDouble);
                    }

                    if(longitude.getText().toString().equals("")) {
                        Double longitudeDouble = Double.valueOf(0);
                        geo.setLng(longitudeDouble);
                    }else{
                        Double longitudeDouble = Double.parseDouble(String.valueOf(longitude.getText()));
                        geo.setLng(longitudeDouble);
                    }
                    ongs.setGeo(geo);

                    Transferencia transferencia = new Transferencia();
                    transferencia.setBanco(banco.getText().toString());
                    transferencia.setAgencia(agencia.getText().toString());
                    transferencia.setConta(conta.getText().toString());
                    transferencia.setIdentificador(identificador.getText().toString());
                    transferencia.setPix(pix.getText().toString());
                    ongs.setTransferencia(transferencia);

                    DatabaseReference reference = mDatabase.getReference().child("ongs").child(String.valueOf(lista));
                    reference.setValue(ongs);

                }
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DadosOng.this);
                builder.setTitle("Inclusão de dados Ong");
                builder.setMessage("Dados adicionados com sucesso!");
                builder.setCancelable(true);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });
                builder.show();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(DadosOng.this, "Erro!", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void alterarOng(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getPhotoUrl() != null) {
            fotoPerfilOng = user.getPhotoUrl().toString();
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ongs");
        myRef.orderByChild("nome").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    String nameOng = childDataSnapshot.child("nome").getValue().toString();
                    String id = childDataSnapshot.getKey();

                    if (nameOng.equals(nomeUser)) {

                        myRef.child(id).child("descricao").setValue(descricao.getText().toString());
                        myRef.child(id).child("telefone").setValue(telefone.getText().toString());
                        myRef.child(id).child("address").child("cep").setValue(cep.getText().toString());
                        myRef.child(id).child("address").child("cidade").setValue(cidade.getText().toString());
                        myRef.child(id).child("address").child("complemento").setValue(complemento.getText().toString());
                        myRef.child(id).child("address").child("numero").setValue(numero.getText().toString());
                        myRef.child(id).child("address").child("rua").setValue(rua.getText().toString());
                        myRef.child(id).child("gio").child("lat").setValue(latitude.getText().toString());
                        myRef.child(id).child("gio").child("lng").setValue(longitude.getText().toString());
                        myRef.child(id).child("transferencia").child("banco").setValue(banco.getText().toString());
                        myRef.child(id).child("transferencia").child("agencia").setValue(agencia.getText().toString());
                        myRef.child(id).child("transferencia").child("conta").setValue(conta.getText().toString());
                        myRef.child(id).child("transferencia").child("identificador").setValue(identificador.getText().toString());
                        myRef.child(id).child("transferencia").child("pix").setValue(pix.getText().toString());
                        Log.i("foto", "foto"+ fotoPerfilOng);
                        myRef.child(id).child("foto").setValue(fotoPerfilOng);
                    }

                }
                Toast.makeText(DadosOng.this, "Alteração de dados concluída", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(DadosOng.this, "Alteração não concluida", Toast.LENGTH_SHORT).show();

            }
        });
    }
}