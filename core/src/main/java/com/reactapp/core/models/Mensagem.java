package com.reactapp.core.models;

public class Mensagem{
    private String erro;

    public Mensagem(String erro){
        this.erro = erro;
    }
    public Mensagem(){
    }

    public String getMsg() {
        return erro;
    }
}
