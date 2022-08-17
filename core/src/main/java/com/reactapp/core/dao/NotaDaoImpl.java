package com.reactapp.core.dao;

import com.reactapp.core.models.NotaFiscal;
import com.reactapp.core.service.DatabaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component(immediate = true, service = NotaDao.class)
public class NotaDaoImpl implements NotaDao{

    @Reference
    private DatabaseService databaseService;

    @Override
    public List<NotaFiscal> getNotaID(int id) {
        try(Connection connection = databaseService.getConnection()) {
            String sql = "SELECT * FROM notas WHERE idcliente=?";
            List<NotaFiscal> notas = new ArrayList<NotaFiscal>();
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,id);
                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        notas.add(new NotaFiscal(rs.getInt("numero"),rs.getInt("idproduto"),
                                        rs.getInt("idcliente"),rs.getFloat("valor")));
                    }
                    return notas;
                }
            }catch (Exception e){
                throw new RuntimeException(e.getMessage()+"Error while getting product");
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage()+"Error while trying to connect to database");
        }
    }

}
