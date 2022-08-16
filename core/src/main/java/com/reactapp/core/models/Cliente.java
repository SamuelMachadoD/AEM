package com.reactapp.core.models;

public class Cliente {
    private int id;
    private String nome;

    public Cliente(){

    };

    public Cliente(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public String getNome() { return nome;}
}
