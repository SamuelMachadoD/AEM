package com.reactapp.core.dao;

import com.reactapp.core.models.Produto;
import com.reactapp.core.service.DatabaseService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component(immediate = true, service = ProdutoDao.class)
public class ProdutoDaoImpl implements ProdutoDao{

    @Reference
    private DatabaseService databaseService;
    @Override
    public void saveProduct(Produto produto) throws SQLException {
        try(Connection connection = databaseService.getConnection()) {
            String sql = "INSERT INTO produto (nome, categoria, preco) VALUES (?,?,?)";

            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, produto.getNome());
                ps.setString(2, produto.getCategoria());
                ps.setFloat(3, produto.getPreco());
                ps.execute();

            }catch (Exception e){
                throw new RuntimeException(e.getMessage()+"Error while saving product");
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage()+"Error while trying to connect to database");
        }
    }

    @Override
    public void delProduct(int id) {
        try(Connection connection = databaseService.getConnection()) {
            String sql = "DELETE FROM produto WHERE id=(?)";

            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.execute();

            }catch (Exception e){
                throw new RuntimeException(e.getMessage()+"Error while delete product");
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage()+"Error while trying to connect to database");
        }
    }

    @Override
    public void alteraProduct(int id, Produto produto) {
        try(Connection connection = databaseService.getConnection()) {
            String sql = "UPDATE produto p SET p.nome = (?), p.categoria = (?), p.preco = (?) WHERE id=(?)";

            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, produto.getNome());
                ps.setString(2, produto.getCategoria());
                ps.setFloat(3, produto.getPreco());
                ps.setInt(4, id);
                ps.execute();

            }catch (Exception e){
                throw new RuntimeException(e.getMessage()+"Error while saving word");
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage()+"Error while trying to connect to database");
        }
    }

    @Override
    public List<Produto> listaProduto() {
        try(Connection connection = databaseService.getConnection()) {
            String sql = "SELECT * FROM produto";
            List<Produto> produtos = new ArrayList<Produto>();
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        produtos.add(new Produto(rs.getInt("id"),rs.getString("nome"),rs.getString("categoria"),rs.getFloat("preco")));
                    }
                    return produtos;
                }
            }catch (Exception e){
                throw new RuntimeException(e.getMessage()+"Error while getting products");
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage()+"Error while trying to connect to database");
        }
    }

    @Override
    public Produto listaProdutoID(int id) {
        try(Connection connection = databaseService.getConnection()) {
            String sql = "SELECT * FROM produto WHERE id=?";
            Produto produto = null;
            try(PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1,id);
                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        produto = new Produto(rs.getInt("id"),rs.getString("nome"),rs.getString("categoria"),rs.getFloat("preco"));
                    }
                    return produto;
                }
            }catch (Exception e){
                throw new RuntimeException(e.getMessage()+"Error while getting product");
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage()+"Error while trying to connect to database");
        }
    }
}
