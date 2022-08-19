package com.reactapp.core.models;

public class Produto {
    private int id;
    private String nome;
    private String categoria;
    private float preco;

    public Produto(){

    };

    public Produto(int id, String nome, String categoria, float preco){
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
    }


    public String getNome() {return nome;}
    public String getCategoria() {return categoria;}
    public float getPreco() {return preco;}
    public int getId() { return id;}
}
