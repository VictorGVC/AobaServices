<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/cssindex.css" rel="stylesheet" type="text/css"/>
        <script src="js/funcoes.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="todo">
            <form class="cabeca">
                <div class="busca">
                    <input class="buscatxt" type="text" placeholder="digite oq está procurando...">
                    <a class="buscabtn" href="#"><img src="imagens/search.png" alt="search" style="margin-left: 7px"></a>
                </div>
                <button class="botaologin" onclick="document.getElementById('id01').style.display='block'">Login</button>
                <div id="id01" class="modal">
                    <form id="fdados" class="modal-content animate" action="" method="post" onsubmit="buscaLogin()">
                        <div class="imgcontainer">
                            <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
                        </div>
                        <div class="container">
                            <label for="login"><b>Login</b></label>
                            <input type="text" placeholder="Usuário" name="login" required>
                            <label for="password"><b>Senha</b></label>
                            <input type="password" placeholder="Senha" name="password" required>
                            <button class="signin" type="submit" value="Login">Login</button>
                        </div>
                        <div class="container" style="background-color:#f1f1f1">
                            <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
                        </div>
                    </form>
                </div>
            </form>
            <div id="preview" onload="carregaAnuncios()" class="painelanuncios">
                <a href="administrador.jsp">Modulo Administrador</a>
                <div class="anuncio">
                    <label>Anunciante:&nbsp;</label><td>Indian</td><br>
                    <label>Título:&nbsp;</label><td>Zarabatana</td><br>
                    <a href="#">
                        <img class="imagem" src="imagens/zarabatana.jpg">
                    </a>
                </div>
                <div class="anuncio">
                    <label>Anunciante:&nbsp;</label><td>Bixo Mini Chimp</td><br>
                    <label>Título:&nbsp;</label><td>Bola Gato</td><br>
                    <a href="#">
                        <img class="imagem" src="imagens/bolagato.jfif">
                    </a>
                </div>
            </div>
            <%
                String login = request.getParameter("login");
                String senha = request.getParameter("password");
                
                if(login != null && senha != null && !login.isEmpty() && !senha.isEmpty())
                {
                    session.setAttribute("login", login);
                }
            %>
        </div>
    </body>
</html>
