package com.example.icarealot.model;

public class Address {
  private String cep;
  private String cidade;
  private String complemento;
  private String numero;
  private String rua;

  public Address() {
  }

  public Address(String cep, String cidade, String complemento, String numero, String rua) {
    this.cep = cep;
    this.cidade = cidade;
    this.complemento = complemento;
    this.numero = numero;
    this.rua = rua;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getRua() {
    return rua;
  }

  public void setRua(String rua) {
    this.rua = rua;
  }
}