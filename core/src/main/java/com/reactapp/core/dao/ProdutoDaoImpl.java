package com.reactapp.core.dao;

import com.reactapp.core.models.Produto;

import java.sql.SQLException;
import java.util.List;

public class ProdutoDaoImpl implements ProdutoDao{
    @Override
    public void saveProduct(Produto produto) throws SQLException {

    }

    @Override
    public void delProduct(int id) {

    }

    @Override
    public void alteraProduct(int id, String oldNome, String newNome) {

    }

    @Override
    public List<Produto> listaProduto() {
        return null;
    }

    @Override
    public Produto listaProdutoID(int id) {
        return null;
    }
}
