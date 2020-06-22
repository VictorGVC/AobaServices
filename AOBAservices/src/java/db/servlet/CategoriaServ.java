package db.servlet;

import db.dal.DALCategoria;
import db.entidades.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoriaServ", urlPatterns = {"/CategoriaServ"})
public class CategoriaServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String opcao = request.getParameter("opcao");
            
            switch(opcao.toLowerCase())
            {
                case "atualiza": 
                    
                    int id = 0;
                    DALCategoria cat = new DALCategoria();
                    List<Categoria> lista = cat.getCategorias();
                    String str = "";
                    for(Categoria c: lista)
                    {
                        str+="<tr class='selecionado' onclick='("+(id++)+")'><td>"+c.getCat_cod()+"</td><td>"+c.getCat_descricao()+"</td></tr>";
                    }                    
                    response.getWriter().print(str);
                    break;
                    
                case "novo": 
                    
                    response.getWriter().print(novo(request.getParameter("codigo"),request.getParameter("categoria")));
                    break;
                    
                case "excluir": 
                    
                    response.getWriter().print(excluir(request.getParameter("codigo")));
                    break;
            }
        }
    }
    
    private String novo(String cods, String categoria){
        String str;
        DALCategoria dal = new DALCategoria();
        Categoria c = new Categoria();
        c.setCat_descricao(categoria);
        int cod;
        try{
            cod = Integer.parseInt(cods);
        }
        catch(Exception e){cod = 0;}
        
        if(cod == 0)
        {
            if(dal.salvar(c))
                str = "Gravado!";
            else
                str = "Problemas";
        }
        else
        {
            c.setCat_cod(cod);
            if(dal.alterar(c))
                str = "Alterado com sucesso!";
            else
                str = "Problemas ao alterar";
        }
        return str;
    }
    
     private String excluir(String cod)
    {
        String ans;
        DALCategoria dal = new DALCategoria();
        if(dal.apagar(cod))
            ans = "Apagado com sucesso!";
        else
            ans = "Não foi possivel apagar";
        return ans;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
