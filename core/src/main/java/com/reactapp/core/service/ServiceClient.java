package com.reactapp.core.service;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public interface ServiceClient {
    void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws SQLException, IOException;

    void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void getJsonObj(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void getJsonParameter(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;
}
