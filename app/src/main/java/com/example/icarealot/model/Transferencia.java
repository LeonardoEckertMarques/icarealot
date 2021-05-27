package com.example.icarealot.model;

public class Transferencia {
  private String agencia;
  private String banco;
  private String conta;
  private String identificador;
  private String pix;

  public Transferencia() {
  }

  public Transferencia(String agencia, String banco, String conta, String identificador, String pix) {
    this.agencia = agencia;
    this.banco = banco;
    this.conta = conta;
    this.identificador = identificador;
    this.pix = pix;
  }

  public String getAgencia() {
    return agencia;
  }

  public void setAgencia(String agencia) {
    this.agencia = agencia;
  }

  public String getBanco() {
    return banco;
  }

  public void setBanco(String banco) {
    this.banco = banco;
  }

  public String getConta() {
    return conta;
  }

  public void setConta(String conta) {
    this.conta = conta;
  }

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  public String getPix() {
    return pix;
  }

  public void setPix(String pix) {
    this.pix = pix;
  }
}
