package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class administrador_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <link href=\"css/cssadm.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <section>\n");
      out.write("            <form id=\"fdados\"><br>\n");
      out.write("                <label id=\"lbprestador\">Prestador</label><br><br>\n");
      out.write("                <input type=\"text\" name=\"login\" placeholder=\"Login*\" id=\"login\"/>\n");
      out.write("                <input type=\"text\" name=\"nome\" placeholder=\"Nome*\" id=\"nome\"/><br>\n");
      out.write("                <input type=\"text\" name=\"senha\" placeholder=\"Senha*\" id=\"senha\"/>\n");
      out.write("                <label>Serviço:</label>\n");
      out.write("                <select name=\"servico*\" id=\"sevico\">\n");
      out.write("                  <option value=\"A\">Preencher com o banco</option>\n");
      out.write("                </select><br><br><label>Anúncio</label><br><br>\n");
      out.write("                <input type=\"text\" name=\"titulo\" placeholder=\"Título*\" id=\"titulo\"/>\n");
      out.write("                <input type=\"number\" name=\"preco\" placeholder=\"Preço*\" id=\"preco\"/><br>\n");
      out.write("                <input type=\"number\" name=\"telefone\" placeholder=\"Telefone\" id=\"telefone\"/>\n");
      out.write("                <label>Categoria:</label>\n");
      out.write("                <select name=\"categoria*\" id=\"categoria\">\n");
      out.write("                  <option value=\"A\">Preencher com o banco</option>\n");
      out.write("                </select><br>\n");
      out.write("                <textarea id=\"conteudo\"  name=\"conteudo\" cols=\"80\" placeholder=\"Descrição\"></textarea><br><br>\n");
      out.write("                <label id=\"lbdescricao\">Imagem Principal:</label><input type=\"file\" id=\"imgprincipal\" name=\"imgprincipal\"><br><br>\n");
      out.write("                <label id=\"lbdescricao\">Imagem Opcional:</label><input type=\"file\" id=\"imgopcional\" name=\"imgopcional1\"><br><br>\n");
      out.write("                <label id=\"lbdescricao\">Imagem Opcional:</label><input type=\"file\" id=\"imgopcional\" name=\"imgopcional2\"><br><br>\n");
      out.write("                <input type=\"submit\" name=\"registrar\" id=\"registrar\">\n");
      out.write("            </form>\n");
      out.write("        </section>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
