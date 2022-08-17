package com.reactapp.core.service;

import com.reactapp.core.models.ProdutoDTO;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;

public interface ServiceProduct {
    void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    ProdutoDTO getDTO(int id);
}
