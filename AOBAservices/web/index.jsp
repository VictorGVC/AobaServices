<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/estilo.css" rel="stylesheet" type="text/css"/>
        <script src="js/funcoes.js" type="text/javascript"></script>
    </head>
    <body>
        <div style="width: 150px; margin: 0 auto">
            <button onclick="document.getElementById('id01').style.display='block'">Login</button>
            <div id="id01" class="modal">
                <form class="modal-content animate" action="" method="post">
                    <div class="imgcontainer">
                        <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
                    </div>
                    <div class="container">
                        <label for="login"><b>Login</b></label>
                        <input type="text" placeholder="Usuário" name="login" required>
                        <label for="password"><b>Senha</b></label>
                        <input type="password" placeholder="Senha" name="password" required>
                        <button type="submit" value="Login">Login</button>
                    </div>
                    <div class="container" style="background-color:#f1f1f1">
                        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
                    </div>
                </form>
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
