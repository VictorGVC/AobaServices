<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Anuncio</title>
        <link href="cssMolPrestador.css" rel="stylesheet" type="text/css"/>
        <a href="Conexao.java"></a>
    </head>
    <body>
         <section>
            <form id="fdados"><br>
                <label id="lbprestador">Prestador</label><br><br>
                <input type="text" name="login" placeholder="Login*" id="login"/>
                <input type="text" name="nome" placeholder="Nome*" id="nome"/><br>
                <input type="text" name="senha" placeholder="Senha*" id="senha"/>
                <label>Serviço:</label>
                <select name="servico*" id="sevico">
                  <option value="A">Preencher com o banco</option>
                </select><br><br><label>Anúncio</label><br><br>
                <input type="text" name="titulo" placeholder="Título*" id="titulo"/>
                <input type="number" name="preco" placeholder="Preço*" id="preco"/><br>
                <input type="number" name="telefone" placeholder="Telefone" id="telefone"/>
                <label>Categoria:</label>
                <select name="categoria*" id="categoria">
                  <option value="A">Preencher com o banco</option>
                </select><br>
                <textarea id="conteudo"  name="conteudo" cols="80" placeholder="Descrição"></textarea><br><br>
                <label id="lbdescricao">Imagem Principal:</label><input type="file" id="imgprincipal" name="imgprincipal"><br><br>
                <label id="lbdescricao">Imagem Opcional:</label><input type="file" id="imgopcional" name="imgopcional1"><br><br>
                <label id="lbdescricao">Imagem Opcional:</label><input type="file" id="imgopcional" name="imgopcional2"><br><br>
                <input type="submit" name="registrar" id="registrar">
            </form>
        </section>
    </body>
    <%
        Conexao banco = new Conexao();
    %>
    <%! 
                private boolean SalvarFoto() throws IOException{
                                Boolean flag;
                                try{
                                    banco.manipular("INSERT INTO foto (fot_fotoprincipal,fot_fotoopcional1,fot_fotoopcional2) "
                                                                                    + "values (, , )");
                                    }catch (Exception e) {
                                            e.printStackTrace();
                                    }
                                banco.manipular(sqlAnuncio);
                                return flag;
                        }
                private boolean SalvarAnuncio() throws IOException{
                                Boolean flag;
                                String sqlAnuncio = "INSERT INTO anuncio(anu_titulo,anu_telefone,anu_preco,anu_desc,fot_cod,cat_cod)"
                                                    + "VALUES('#1','#2',#3,'#4',#5,#6)"; 
                                   sql = sql.replaceAll("#1","" + m.getMar_nome());
                                   sql = sql.replaceAll("#2","" + m.getMar_nome());
                                   sql = sql.replaceAll("#3","" + m.getMar_nome());
                                   sql = sql.replaceAll("#4","" + m.getMar_nome());
                                   sql = sql.replaceAll("#5","" + m.getMar_nome());
                                   sql = sql.replaceAll("#6","" + m.getMar_nome());
                                banco.manipular(sqlAnuncio);
                                return flag;
                        }

                private boolean Salvar() throws IOException{
                        Boolean flag;
                        SalvarFoto();
                        int codFoto;
                        SalvarAnuncio(codFoto);
                        int codAnuncio;
                        String sqlPrestador = "INSERT INTO prestador(usu_login,pre_nome,ser_cod,anu_cod)"
                                                    + "VALUES('#1','#2',#3,#4)"; 
                                   sql = sql.replaceAll("#1","" + m.getMar_nome());
                                   sql = sql.replaceAll("#2","" + m.getMar_nome());
                                   sql = sql.replaceAll("#3","" + m.getMar_nome());
                                   sql = sql.replaceAll("#4","" + m.getMar_nome());
                        return flag;
                }
               %>
</html>
