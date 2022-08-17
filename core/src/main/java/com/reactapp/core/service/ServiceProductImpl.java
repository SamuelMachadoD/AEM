package com.reactapp.core.service;

import com.google.gson.Gson;
import com.reactapp.core.dao.ProdutoDao;
import com.reactapp.core.models.Mensagem;
import com.reactapp.core.models.Produto;
import com.reactapp.core.models.ProdutoDTO;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.tika.io.IOUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Component(immediate = true, service = ServiceProduct.class)
public class ServiceProductImpl implements ServiceProduct, JsonConverter{

    @Reference
    private ProdutoDao Dao;
    Produto conversor = new Produto();
    int id = 0;
    @Override
    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            getJsonObj(request, response);
            Dao.saveProduct(conversor);
        } catch (SQLException | IOException e) {
            getMsg(response,"Erro no banco de dados");
        }
    }

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            getJsonParameter(request, response);
            if(id != 0){
                response.getWriter().write(new Gson().toJson(getDTO(id)));
            }else{
                if(Dao.listaProduto() != null){
                    List<Produto> produtos = Dao.listaProduto();
                    String todosProdutos = new Gson().toJson(produtos);
                    try{
                        response.getWriter().write(todosProdutos);
                    }catch (IOException e){
                        throw new RuntimeException(e);
                    }
                }
            }
        }catch(Exception e){
            getMsg(response,"Erro no banco de dados");
        }

    }

    @Override
    public void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            getJsonParameter(request, response);
            Dao.delProduct(id);
        }catch(Exception e){
            getMsg(response, e.getMessage());
        }
    }

    @Override
    public void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            getJsonParameter(request, response);
            getJsonObj(request, response);
            Dao.alteraProduct(id, conversor);
        }catch(Exception e){
            getMsg(response,"Erro no banco de dados");
        }
    }

    @Override
    public ProdutoDTO getDTO(int id) {
        Produto produto = Dao.listaProdutoID(id);
        ProdutoDTO DTO = new ProdutoDTO(produto.getId(), produto.getNome(), produto.getCategoria(), produto.getPreco());
        return DTO;
    }

    @Override
    public void getJsonObj(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String userPostString = null;
        try {
            userPostString = IOUtils.toString(request.getReader());
        } catch (IOException e) {
            getMsg(response,e.getMessage());
        }
        try {
            conversor = new Gson().fromJson(userPostString, Produto.class);
        } catch (Exception e) {
            try {
                getMsg(response,"Json invalido");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void getJsonParameter(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/json");
            String idString = request.getParameter("id");
            id = Integer.parseInt(idString);
        }catch (Exception e){
            getMsg(response,"Parametro Nulo");
        }
    }

    @Override
    public void getMsg(SlingHttpServletResponse response, String msg) throws IOException {
        response.getWriter().write(new Gson().toJson(msg));
    }
}
