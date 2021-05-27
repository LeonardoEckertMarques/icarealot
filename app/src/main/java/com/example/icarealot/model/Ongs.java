package com.example.icarealot.model;

public class Ongs {

  private Address address;
  private String descricao;
  private String urlFoto;
  private Geo geo;
  private String id;
  private String nome;
  private String telefone;
  private Transferencia transferencia;

  public Ongs() {}

  public Ongs(Address address, String descricao, String urlFoto, Geo geo, String id, String nome, String telefone, Transferencia transferencia) {
    this.address = address;
    this.descricao = descricao;
    this.urlFoto = urlFoto;
    this.geo = geo;
    this.id = id;
    this.nome = nome;
    this.telefone = telefone;
    this.transferencia = transferencia;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getUrlFoto() {
    return urlFoto;
  }

  public void setUrlFoto(String urlFoto) {
    this.urlFoto = urlFoto;
  }

  public Geo getGeo() {
    return geo;
  }

  public void setGeo(Geo geo) {
    this.geo = geo;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public Transferencia getTransferencia() {
    return transferencia;
  }

  public void setTransferencia(Transferencia transferencia) {
    this.transferencia = transferencia;
  }
}
