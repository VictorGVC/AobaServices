package servlet;

import dal.DALCategoria;
import entidades.Categoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "categoria", urlPatterns = {"/categoria"})
public class categoria extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String opcao = request.getParameter("aopcao");
            
            switch(opcao.toLowerCase())
            {
                case "atualiza": 
                    
                    response.getWriter().print(atualiza());
                    break;
                    
                case "novo": 
                    
                    response.getWriter().print(novo(request.getParameter("cod"),request.getParameter("descricao")));
                    break;
                    
                case "excluir": 
                    
                    response.getWriter().print(excluir(request.getParameter("cod")));
                    break;
            }
        }
    }
    
    private String novo(String cods, String categoria){
        String ans;
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
                ans = "Gravado com sucesso!";
            else
                ans = "Problemas ao cadastrar";
        }
        else
        {
            c.setCat_cod(cod);
            if(dal.alterar(c))
                ans = "Alterado com sucesso!";
            else
                ans = "Problemas ao alterar";
        }
        return ans;
    }
    
    private String atualiza()
    {
        DALCategoria cat = new DALCategoria();
        List<Categoria> lista = cat.getProdutos();
        String ans = "";
        ans = "<select name='categoria' id='categoria'>";
        for(Categoria c: lista)
        {
            ans+="<option value="+c.getCat_cod()+">"+c.getCat_descricao()+"</option>";
        }
        ans += "</select><br>";
        return ans;
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
