package com.reactapp.core.service;

import com.google.gson.Gson;
import com.reactapp.core.dao.ClienteDao;
import com.reactapp.core.models.Cliente;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.tika.io.IOUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.sql.SQLException;

@Component(immediate = true, service = ServiceClient.class)
public class ServiceClientImpl implements ServiceClient, JsonConverter {
    @Reference
    private ClienteDao clienteDao;
    Cliente conversor = new Cliente();
    int id = 0;

    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws SQLException, IOException {
        try {
            getJsonObj(request, response);
            clienteDao.saveClient(conversor);
        } catch (SQLException e) {
            getMsg(response,"Erro no banco de dados");
        }
    }

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
    //consulta pela nota
    }

    @Override
    public void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            getJsonParameter(request, response);
            clienteDao.delClient(id);
        }catch(Exception e){
            getMsg(response,"Erro no banco de dados");
        }
    }


    @Override
    public void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            getJsonParameter(request, response);
            getJsonObj(request, response);
            clienteDao.alteraClient(id, conversor.getNome());
        }catch(Exception e){
            getMsg(response,"Erro no banco de dados");
        }
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
            conversor = new Gson().fromJson(userPostString, Cliente.class);
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
