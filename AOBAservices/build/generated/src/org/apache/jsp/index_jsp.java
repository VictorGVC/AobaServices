package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Login</title>\r\n");
      out.write("        <meta charset=\"UTF-8\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("        <link href=\"css/estilo.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("        <script src=\"js/funcoes.js\" type=\"text/javascript\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div style=\"width: 150px; margin: 0 auto\">\r\n");
      out.write("            <button onclick=\"document.getElementById('id01').style.display='block'\">Login</button>\r\n");
      out.write("            <div id=\"id01\" class=\"modal\">\r\n");
      out.write("                <form class=\"modal-content animate\" action=\"\" method=\"post\">\r\n");
      out.write("                    <div class=\"imgcontainer\">\r\n");
      out.write("                        <span onclick=\"document.getElementById('id01').style.display='none'\" class=\"close\" title=\"Close Modal\">&times;</span>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"container\">\r\n");
      out.write("                        <label for=\"login\"><b>Login</b></label>\r\n");
      out.write("                        <input type=\"text\" placeholder=\"Usuário\" name=\"login\" required>\r\n");
      out.write("                        <label for=\"password\"><b>Senha</b></label>\r\n");
      out.write("                        <input type=\"password\" placeholder=\"Senha\" name=\"password\" required>\r\n");
      out.write("                        <button type=\"submit\" value=\"Login\">Login</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"container\" style=\"background-color:#f1f1f1\">\r\n");
      out.write("                        <button type=\"button\" onclick=\"document.getElementById('id01').style.display='none'\" class=\"cancelbtn\">Cancel</button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("            ");

                String login = request.getParameter("login");
                String senha = request.getParameter("password");
                
                if(login != null && senha != null && !login.isEmpty() && !senha.isEmpty())
                {
                    session.setAttribute("login", login);
                }
            
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
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
