package com.example.icarealot.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class UsuarioModel implements Parcelable {

  private String id;
  private String usuario;
  private String email;
  private String senha;
  private String cpfCnpj;

  public UsuarioModel() { }

  public UsuarioModel(String id, String usuario, String email, String senha, String cpfCnpj) {
    this.id = id;
    this.usuario = usuario;
    this.email = email;
    this.senha = senha;
    this.cpfCnpj = cpfCnpj;
  }

  private UsuarioModel(Parcel p) {
    this.id = p.readString();
    this.usuario = p.readString();
    this.email = p.readString();
    this.senha = p.readString();
    this.cpfCnpj = p.readString();
  }

  public static final Creator<UsuarioModel> CREATOR = new Creator<UsuarioModel>() {
    @Override
    public UsuarioModel createFromParcel(Parcel p) {
      return new UsuarioModel(p);
    }

    @Override
    public UsuarioModel[] newArray(int size) {
      return new UsuarioModel[size];
    }
  };

  @Exclude
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public String getUsuario() { return usuario; }
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }
  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String getCpfCnpj() {
    return cpfCnpj;
  }
  public void setCpfCnpj(String cpfCnpj) {
    this.cpfCnpj = cpfCnpj;
  }

  public void salvaUsuario() {
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    reference.child("usuarios").child(getId()).setValue(this);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(this.id);
    parcel.writeString(this.usuario);
    parcel.writeString(this.email);
    parcel.writeString(this.senha);
    parcel.writeString(this.cpfCnpj);
  }

}
