package com.reactapp.core.models;

import java.util.List;

public class NotaFiscal {
    private int numero;
    private int idproduto;
    private int idcliente;
    private double valor;

    public NotaFiscal(int numero, int idproduto, int idcliente, float valor) {
        this.numero = numero;
        this.idproduto = idproduto;
        this.idcliente = idcliente;
        this.valor = valor;
    }

    public int getIdProduto() {return idproduto;}

    public int getNumero() {return numero;}

    public double getValor() {
        return valor;
    }

    public int getIdcliente() {
        return idcliente;
    }
}
