package com.example.icarealot.activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.icarealot.services.MapsActivity;
import com.example.icarealot.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import maes.tech.intentanim.CustomIntent;

public class Perfil extends AppCompatActivity {

  private FirebaseAuth mAuth;
  private FirebaseDatabase mDatabase;
  private ValueEventListener valueEventListener;
  private TextView nome_TelaPerfil;
  private TextView cidade_TelaPerfil;
  private Button dadosPessoais;
  private Button configuracoes;
  private Button salvos;
  private CircleImageView imagemPerfil;
  public Uri imageUri;
  private FirebaseStorage storage;
  private StorageReference storageReference;
  private DatabaseReference databaseReference;
  ;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_perfil);
    mDatabase = FirebaseDatabase.getInstance();
    nome_TelaPerfil = findViewById(R.id.nome_TelaPerfil);
    cidade_TelaPerfil = findViewById(R.id.cidade_TelaPerfil);
    dadosPessoais = findViewById(R.id.btn_dados_TelaPerfil);
    imagemPerfil = findViewById(R.id.foto_TelaPerfil);
    configuracoes = findViewById(R.id.btn_configuracoes_TelaPerfil);
    salvos = findViewById(R.id.btn_salvos_TelaPerfil);
    storage = FirebaseStorage.getInstance();
    storageReference = storage.getReference();


    dadosPessoais.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(Perfil.this, DadosPessoaisPerfil.class);
        startActivity(i);
        CustomIntent.customType(Perfil.this, "left-to-right");
      }
    });

    configuracoes.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(Perfil.this, ConfiguracoesPerfil.class);
        startActivity(i);
        CustomIntent.customType(Perfil.this, "left-to-right");
      }
    });

    salvos.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(Perfil.this, SalvosPerfil.class);
        startActivity(i);
        CustomIntent.customType(Perfil.this, "left-to-right");
      }
    });
    imagemPerfil.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        choosePicture();
      }
    });

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
            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            break;
          case R.id.navPerfil:
            break;
        }
        return false;
      }
    });

  }

  private void choosePicture() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(intent,1);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode==1 && data!=null && data.getData()!= null){
      imageUri = data.getData();
      imagemPerfil.setImageURI(imageUri);
      uploadPicture();
    }
  }

  private  void getDounloadUrl(StorageReference reference){
    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
      @Override
      public void onSuccess(Uri uri) {
        setUserProfileUrl(uri);
      }
    });
  }

  private void setUserProfileUrl(Uri uri) {
    FirebaseUser user2 = FirebaseAuth.getInstance().getCurrentUser();
    UserProfileChangeRequest request = new UserProfileChangeRequest.Builder().setPhotoUri(uri).build();
    user2.updateProfile(request).addOnSuccessListener(new OnSuccessListener<Void>() {
      @Override
      public void onSuccess(Void unused) {
        Toast.makeText(Perfil.this, "Update Sucesfully", Toast.LENGTH_SHORT).show();
      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull @NotNull Exception e) {
        Toast.makeText(Perfil.this, "Profile Image Failed", Toast.LENGTH_SHORT).show();
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
    if(user.getPhotoUrl()!= null){
      Glide.with(this).load(user.getPhotoUrl()).into(imagemPerfil);
    }

  }
  private void uploadPicture(){
    final ProgressDialog pd = new ProgressDialog(this);
    pd.setTitle("Uploading Image...");
    pd.show();
    final String randomKey = UUID.randomUUID().toString();
    StorageReference mountainsRef = storageReference.child("images/" + randomKey);


    mountainsRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
      @Override
      public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        pd.dismiss();
        getDounloadUrl(mountainsRef);

      }
    }).addOnFailureListener(new OnFailureListener() {
      @Override
      public void onFailure(@NonNull @NotNull Exception e) {
        pd.dismiss();
        Toast.makeText(getApplicationContext(), "Failed To Upload", Toast.LENGTH_LONG);
      }
    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
      @Override
      public void onProgress(@NonNull @NotNull UploadTask.TaskSnapshot snapshot) {
        double progressPercent = (100.00* snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
        pd.setMessage("In Progress");
      }
    });
  }
}
