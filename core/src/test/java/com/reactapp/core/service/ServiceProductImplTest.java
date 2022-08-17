package com.reactapp.core.service;

import com.google.gson.Gson;
import com.reactapp.core.dao.ProdutoDao;
import com.reactapp.core.models.Produto;
import com.reactapp.core.servlets.ServletProdutos;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@ExtendWith(AemContextExtension.class)
class ServiceProductImplTest {

    private static final Gson GSON = null;
    private ServletProdutos servletProdutos;
    @Mock
    private ServiceProduct serviceProduct;

    @Mock
    private ProdutoDao Dao;
    private MockSlingHttpServletRequest request;
    private MockSlingHttpServletResponse response;

    @BeforeEach
    void setUp(AemContext context) {
        request = context.request();
        response = context.response();

        servletProdutos = new ServletProdutos(serviceProduct);
    }

    @Test
    void doGetParameter() {
        request.addRequestParameter("id","1");
        Produto produto = Dao.listaProduto().get(0);
        Mockito.when(Dao.listaProdutoID(1)).thenReturn(produto);
        try{
            doGet(request, response);
        } catch (Exception e) {
            Assertions.fail();
        }

        Assertions.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        Assertions.assertEquals("application/json;charset=UTF-8", response.getContentType());
        Assertions.assertEquals(GSON.toJson(produto), response.getOutputAsString());
    }

    @Test
    void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws IOException {
        int statusCode = HttpServletResponse.SC_OK;
        String jsonBody;
        String param = req.getParameter("id");
        try{
            if(param != null){
                Produto produto = Dao.listaProdutoID(Integer.parseInt(param));
                jsonBody = new Gson().toJson(produto);
            }else{
                List<Produto> produtoList = Dao.listaProduto();
                jsonBody = new Gson().toJson(produtoList);
            }
        }catch(Exception e){
            statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            jsonBody = "{\"message\" : \"" + e.getMessage() + "\"}";
        }
        //buildResponse(resp,statusCode, jsonBody);

    }

}