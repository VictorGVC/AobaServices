<%-- 
    Document   : administrador
    Created on : 27/05/2020, 17:05:05
    Author     : HITRON
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="js/categoria.js" type="text/javascript"></script>
        <link href="css/cssadm.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="atualizaTabela()">
        <div class="corpo">
            
            
            <div id="head" class="head">
                <button class="btnCabecalho" id="novo" onclick="novo()">novo</button>
                <button class="btnCabecalho" id="salvar" onclick="salvar()">salvar</button>
                <button class="btnCabecalho" id="apagar" onclick="apagar()">apagar</button>
                <button class="btnCabecalho" id="cancelar" onclick="cancelar()">cancelar</button>
                <a class="btnCabecalho" href='homeA.jsp'>voltar</a>
            </div>
            <%-- main --%>
            <div class="maingrid">
                <%-- crud --%>
                <div>
                    <form class="form-input" id="data">
                        <label class="label-input">
                            <input type="text" name="codigo" id="codigo" placeholder="Código">
                        </label>
                        <label class="label-input">
                            <i class="fas fa-clipboard-list"></i>
                            <input type="text" name="categoria" id="categoria" placeholder="Categoria">
                        </label>
                    </form>
                </div>
                <%-- tabela --%>
                <div id="divTable">
                    <table id="minhaTabela" class="table">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Categoria</th>
                            </tr>
                        </thead>
                        <tbody id = "tabela"></tbody>
                    </table>
                </div>
            </div>
        </div>
        <div id="snackbar"></div>
    </body>
</html>
