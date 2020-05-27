<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Anuncio</title>
        <link href="cssMolPrestador.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
         <section>
            <form id="fdados"><br>
                <label id="lbprestador">Prestador</label><br><br>
                <input type="text" name="nome" placeholder="Nome*" id="nome"/>
                <label name="lbservico">Serviço*:</label>
                <select name="servico" id="sevico">
                  <option value="A">Preencher com o banco</option><br>
                </select><br><br><label>Anúncio</label><br><br>
                <input type="text" name="titulo" placeholder="Título*" id="titulo"/>
                <input type="number" name="preco" placeholder="Preço*" id="preco"/><br>
                <input type="number" name="telefone" placeholder="Telefone" id="telefone"/>
                <label>Categoria*:</label>
                <select name="categoria" id="categoria">
                  <option value="A">Preencher com o banco</option>
                </select><br>
                <textarea id="conteudo"  name="conteudo" cols="80" placeholder="Descrição"></textarea><br><br>
                <label id="lbdescricao">Imagem Principal:</label><input type="file" id="imgprincipal" name="imgprincipal"><br><br>
                <label id="lbdescricao">Imagem Opcional:</label><input type="file" id="imgopcional" name="imgopcional1"><br><br>
                <label id="lbdescricao">Imagem Opcional:</label><input type="file" id="imgopcional" name="imgopcional2"><br><br>
                <button type="submit" name="registrar" id="registrar">Registrar</button>
            </form>
        </section>
    </body>
</html>
