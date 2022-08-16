package com.reactapp.core.service;

import com.google.gson.Gson;
import com.reactapp.core.dao.ClienteDao;
import com.reactapp.core.models.Cliente;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.tika.io.IOUtils;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;

public class ServiceTestImpl implements ServiceTest{

    @Reference
    private ClienteDao clienteDao;

    @Override
    public void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        String userPostString = null;
        try {
            userPostString = IOUtils.toString(request.getReader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Cliente objWordConverter = new Cliente();
        try {
            objWordConverter = new Gson().fromJson(userPostString, Cliente.class);

            clienteDao.saveClient(objWordConverter);

        }catch (Exception e){
            try {
                response.getWriter().write("Não é um json");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

    }
}
