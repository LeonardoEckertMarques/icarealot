package com.example.icarealot.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class Usuario {

  private String id;
  private String usuario;
  private String email;
  private String senha;
  private String cpfCnpj;
  private Boolean tipoOng;

  public Usuario() {

  }

  public Usuario(String id, String usuario, String email, String senha, String cpfCnpj, Boolean tipoOng) {
    this.id = id;
    this.usuario = usuario;
    this.email = email;
    this.senha = senha;
    this.cpfCnpj = cpfCnpj;
    this.tipoOng = tipoOng;
  }

  @Exclude
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsuario() {
    return usuario;
  }

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

  public Boolean getTipoOng() {
    return tipoOng;
  }

  public void setTipoOng(Boolean tipoOng) {
    this.tipoOng = tipoOng;
  }

  public void salvaUsuario() {
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    reference.child("usuarios").child(getId()).setValue(this);
  }

}
