package com.reactapp.core.dao;

import com.reactapp.core.service.DatabaseService;
import com.reactapp.core.models.Cliente;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component(immediate = true, service = ClienteDao.class)
public class ClienteDaoImpl implements ClienteDao{

    @Reference
    private DatabaseService databaseService;

    @Override
    public void saveClient(Cliente cliente) throws SQLException {

    }

    @Override
    public void delClient(int id) {

    }

    @Override
    public void alteraClient(int id, String oldNome, String newNome) {

    }

    @Override
    public void verificaNotaFiscal(int id) {

    }
}
