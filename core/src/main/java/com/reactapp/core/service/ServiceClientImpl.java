package com.reactapp.core.service;

import com.google.gson.Gson;
import com.reactapp.core.dao.ClienteDao;
import com.reactapp.core.dao.NotaDao;
import com.reactapp.core.models.Cliente;
import com.reactapp.core.models.NotaFiscal;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.tika.io.IOUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Component(immediate = true, service = ServiceClient.class)
public class ServiceClientImpl implements ServiceClient, JsonConverter {
    @Reference
    private ClienteDao clienteDao;

    @Reference
    private NotaDao notaDao;

    Cliente conversor = new Cliente();
    int id = 0;

    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws SQLException, IOException {
        try {
            getJsonObj(request, response);
            clienteDao.saveClient(conversor);
        } catch (SQLException e) {
            response.getWriter().write("{\"Mensagem\": \"" + ("Erro no banco de dados") + "\"}");
        }
    }

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            getJsonParameter(request, response);
            if(notaDao.getNotaID(id) != null){
                List<NotaFiscal> notas = notaDao.getNotaID(id);
                String todasNotas = new Gson().toJson(notas);
                try{
                    response.getWriter().write(todasNotas);
                }catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        }catch(Exception e){
            response.getWriter().write("{\"Mensagem\": \"" + ("Erro no banco de dados") + "\"}");
        }
    }

    @Override
    public void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            getJsonParameter(request, response);
            clienteDao.delClient(id);
        }catch(Exception e){
            response.getWriter().write("{\"Mensagem\": \"" + ("Erro no banco de dados") + "\"}");
        }
    }


    @Override
    public void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            getJsonParameter(request, response);
            getJsonObj(request, response);
            clienteDao.alteraClient(id, conversor.getNome());
        }catch(Exception e){
            response.getWriter().write("{\"Mensagem\": \"" + ("Erro no banco de dados") + "\"}");
        }
    }

    @Override
    public void getJsonObj(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String userPostString = null;
        try {
            userPostString = IOUtils.toString(request.getReader());
        } catch (IOException e) {
            response.getWriter().write("{\"Mensagem\": \"" + e.getMessage() + "\"}");
        }
        try {
            conversor = new Gson().fromJson(userPostString, Cliente.class);
        } catch (Exception e) {
            try {
                response.getWriter().write("{\"Mensagem\": \"" + ("Json invalido") + "\"}");
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
            response.getWriter().write("{\"Mensagem\": \"" + ("Parametro Nulo") + "\"}");
        }
    }

}
