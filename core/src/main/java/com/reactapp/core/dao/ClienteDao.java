package com.reactapp.core.dao;

import com.reactapp.core.models.Cliente;

import java.sql.SQLException;

public interface ClienteDao {

    void saveClient(Cliente cliente) throws SQLException;

    void delClient(int id);
    void alteraClient(int id, String newNome);
}
