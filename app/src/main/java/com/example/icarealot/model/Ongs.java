package com.example.icarealot.model;

public class Ongs {

  private String id;
  private String nome;
  private String urlFoto;
  private String descricao;
  private String telefone;

  public Ongs() {}

  public Ongs(String id, String nome, String urlFoto, String descricao, String telefone) {
    this.id = id;
    this.nome = nome;
    this.urlFoto = urlFoto;
    this.descricao = descricao;
    this.telefone = telefone;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() { return nome; }

  public void setNome(String nome) { this.nome = nome; }

  public String getUrlFoto() {
    return urlFoto;
  }

  public void setUrlFoto(String urlFoto) {
    this.urlFoto = urlFoto;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }
}
