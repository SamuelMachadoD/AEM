package com.reactapp.core.service;

import com.reactapp.core.dao.NotaDao;
import com.reactapp.core.dao.ProdutoDao;
import com.reactapp.core.models.NotaFiscal;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Component(immediate = true,service = RelatorioService.class)
public class RelatorioServiceImpl implements RelatorioService, JsonConverter{

    int id = 0;

    @Reference
    private NotaDao NDao;

    @Reference
    private ProdutoDao PDao;

    @Override
    public void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp) throws IOException {
        getJsonParameter(req, resp);
        try {
            List<NotaFiscal> relatorio = new ArrayList<>(NDao.getNotaID(id));
            HTML(req, resp, relatorio);
        }catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void HTML(SlingHttpServletRequest req, SlingHttpServletResponse resp, List<NotaFiscal> relatorio) {
        PrintWriter out;
        try {
            out = resp.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=ISO-8859-1>");
            out.println("<title>Relatório CLIENTE</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table>");
            out.println("<thead>");
            out.println("<th>Nota Fiscal</th>");
            out.println("<th>ID do produto</th>");
            out.println("<th>ID do cliente/th>");
            out.println("<th>Valor</th>");
            out.println("</thead>");
            out.println("<tbody>");
            for (NotaFiscal nf:relatorio) {
                out.println("<tr>");
                out.println("<th>"+nf.getNumero()+"</th>");
                out.println("<th>"+nf.getIdProduto()+"</th>");
                out.println("<th>"+nf.getIdcliente()+"</th>");
                out.println("<th>"+nf.getValor()+"</th>");
            }
            out.println("</tr>");
            out.println("<tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getJsonObj(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

    }

    @Override
    public void getJsonParameter(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/html");
            String idString = request.getParameter("id");
            id = Integer.parseInt(idString);
        }catch (Exception e){
            response.getWriter().write("{\"Mensagem\": \"" + ("Parametro Nulo") + "\"}");
        }
    }
}
