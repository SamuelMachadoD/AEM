package com.reactapp.core.dao;

import com.reactapp.core.models.Produto;

import java.sql.SQLException;
import java.util.List;

public interface ProdutoDao {

    void saveProduct(Produto produto) throws SQLException;
    void delProduct(int id);
    void alteraProduct(int id, String oldNome, String newNome);
    List<Produto> listaProduto();
    Produto listaProdutoID(int id);
}
