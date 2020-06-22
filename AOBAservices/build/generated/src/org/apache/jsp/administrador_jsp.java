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
      out.write("        <script src=\"js/categoria.js\" type=\"text/javascript\"></script>\n");
      out.write("        <link href=\"css/cssadm.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body onload=\"atualizaTabela()\">\n");
      out.write("        <div class=\"corpo\">\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            <div id=\"head\" class=\"head\">\n");
      out.write("                <button class=\"btnCabecalho\" id=\"novo\" onclick=\"novo()\">novo</button>\n");
      out.write("                <button class=\"btnCabecalho\" id=\"salvar\" onclick=\"salvar()\">salvar</button>\n");
      out.write("                <button class=\"btnCabecalho\" id=\"apagar\" onclick=\"apagar()\">apagar</button>\n");
      out.write("                <button class=\"btnCabecalho\" id=\"cancelar\" onclick=\"cancelar()\">cancelar</button>\n");
      out.write("                <a class=\"btnCabecalho\" href='homeA.jsp'>voltar</a>\n");
      out.write("            </div>\n");
      out.write("            ");
      out.write("\n");
      out.write("            <div class=\"maingrid\">\n");
      out.write("                ");
      out.write("\n");
      out.write("                <div>\n");
      out.write("                    <form class=\"form-input\" id=\"data\">\n");
      out.write("                        <label class=\"label-input\">\n");
      out.write("                            <input type=\"text\" name=\"codigo\" id=\"codigo\" placeholder=\"Código\">\n");
      out.write("                        </label>\n");
      out.write("                        <label class=\"label-input\">\n");
      out.write("                            <i class=\"fas fa-clipboard-list\"></i>\n");
      out.write("                            <input type=\"text\" name=\"categoria\" id=\"categoria\" placeholder=\"Categoria\">\n");
      out.write("                        </label>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("                ");
      out.write("\n");
      out.write("                <div id=\"divTable\">\n");
      out.write("                    <table id=\"minhaTabela\" class=\"table\">\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Código</th>\n");
      out.write("                                <th>Categoria</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody id = \"tabela\"></tbody>\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div id=\"snackbar\"></div>\n");
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
