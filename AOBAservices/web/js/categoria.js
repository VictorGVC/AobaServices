/* global fetch */

function atualizaTabela()
{
    const URL = "CategoriaServ?opcao=atualiza";
    fetch(URL).then(response => {
        return response.text();
    }).then(result => {
       return document.querySelector('#tabela').innerHTML = result; 
    }).catch(err => {
        console.log(err);
    });
//    estadoOriginal();
}