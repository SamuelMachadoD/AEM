package com.reactapp.core.service;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

public interface ServiceTest {

    void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response);

    void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response);
}
