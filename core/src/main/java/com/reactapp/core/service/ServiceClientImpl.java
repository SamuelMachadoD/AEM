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
public class ServiceClientImpl implements ServiceClient {
    @Reference
    private ClienteDao clienteDao;
    Cliente conversor = new Cliente();
    int id = 0;

    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws SQLException, IOException {
        try {
            getJsonObj(request, response);
            clienteDao.saveClient(conversor);
        } catch (SQLException e) {
            response.getWriter().write("erro no banco de dados");
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
            response.getWriter().write("Erro no banco de dados");
        }
    }


    @Override
    public void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            getJsonParameter(request, response);
            getJsonObj(request, response);
            clienteDao.alteraClient(id, conversor.getNome());
        }catch(Exception e){
            response.getWriter().write("Erro no banco de dados");
        }
    }

    @Override
    public void getJsonObj(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String userPostString = null;
        try {
            userPostString = IOUtils.toString(request.getReader());
        } catch (IOException e) {
            response.getWriter().write("Erro: " + e.getMessage());
        }
        try {
            conversor = new Gson().fromJson(userPostString, Cliente.class);
        } catch (Exception e) {
            try {
                response.getWriter().write("Nao e um json");
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
            response.getWriter().write("Erro ao processar parametro!");
        }
    }
}
