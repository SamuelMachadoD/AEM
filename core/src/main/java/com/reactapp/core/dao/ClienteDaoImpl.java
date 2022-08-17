package com.reactapp.core.dao;

import com.reactapp.core.models.Cliente;
import com.reactapp.core.service.DatabaseService;
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
        try(Connection connection = databaseService.getConnection()) {
            String sql = "INSERT INTO cliente (nome) VALUES (?)";

            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, cliente.getNome());
                ps.execute();

            }catch (Exception e){
                throw new RuntimeException(e.getMessage()+"Error while saving client");
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage()+"Error while trying to connect to database");
        }
    }

    @Override
    public void delClient(int id) {
        try(Connection connection = databaseService.getConnection()) {
            String sql = "DELETE FROM cliente WHERE id=(?)";

            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.execute();

            }catch (Exception e){
                throw new RuntimeException(e.getMessage()+"Error while saving word");
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage()+"Error while trying to connect to database");
        }
    }

    @Override
    public void alteraClient(int id,  String nome) {
        try(Connection connection = databaseService.getConnection()) {
            String sql = "UPDATE cliente p SET p.nome = (?) WHERE id=(?)";

            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, nome);
                ps.setInt(2, id);
                ps.execute();

            }catch (Exception e){
                throw new RuntimeException(e.getMessage()+"Error while saving word");
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage()+"Error while trying to connect to database");
        }
    }

    @Override
    public void verificaNotaFiscal(int id) {

    }
}
