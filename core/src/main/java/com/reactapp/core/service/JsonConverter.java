package com.reactapp.core.service;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import java.io.IOException;

public interface JsonConverter {
    void getJsonObj(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;
    void getJsonParameter(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException;

    void getMsg(SlingHttpServletResponse response, String msg) throws IOException;
}
