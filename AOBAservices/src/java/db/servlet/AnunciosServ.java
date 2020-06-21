package db.servlet;

import db.dal.DALAnuncio;
import db.entidades.Anuncio;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AnunciosServ", urlPatterns = {"/AnunciosServ"})
public class AnunciosServ extends HttpServlet {
    
    public String buscaAnuncios(String filtro) {
        
        String res = "";
        ArrayList<Anuncio> anuncios = (ArrayList<Anuncio>) new DALAnuncio().getProdutos(filtro);
        for (Anuncio a : anuncios) {
            res += String.format("<div class=\"anuncio\">\n" +
"                    <label>Título:&nbsp;</label><td>" + a.getAnu_titulo() + "</td><br>\n" +
"                    <a href=\"#\">\n" +
"                        <img class=\"imagem\" src=\"imagens/zarabatana.jpg\">\n" +
"                    </a>\n" +
"                </div>");
        }
        return res;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String erro = "";
        String acao = request.getParameter("acao");
        int cod;
        
        try {
            cod = Integer.parseInt(request.getParameter("cod"));
        } catch (Exception e) {
            cod = 0;
        }
        DALAnuncio ctr = new DALAnuncio();
        switch (acao.toLowerCase()) 
        {
            case "consultar":
                String filtro = request.getParameter("filtro");
                if (!filtro.isEmpty()) filtro = "upper(nome) like '%" + filtro.toUpperCase() + "%'";
                response.getWriter().print(buscaAnuncios(filtro));
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}