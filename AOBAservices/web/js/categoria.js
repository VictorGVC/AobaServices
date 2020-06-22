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
    Original();
}

function Original()
{
    document.getElementById("novo").disabled = false;
    document.getElementById("data").disabled = true;
    document.getElementById("codigo").disabled = true;
    document.getElementById("categoria").disabled = true;
    document.getElementById("alterar").disabled = true;
    document.getElementById("apagar").disabled = true;
    document.getElementById("cancelar").disabled = true;
    document.getElementById("salvar").disabled = true;
}

function edita()
{
    document.getElementById("novo").disabled = true;
    document.getElementById("categoria").disabled = false;
    document.getElementById("salvar").disabled = false;
    document.getElementById("data").disabled = false;
    document.getElementById("cancelar").disabled = false;
    document.getElementById("alterar").disabled = false;
    document.getElementById("apagar").disabled = false;
}

function novo()
{
    edita();
}

function salvar()
{
    const URL = "CategoriaServ";
    var form = document.getElementById("data");
    const data = new URLSearchParams();
    document.getElementById("codigo").disabled = false;
    for(const pair of new FormData(form))
    {
        data.append(pair[0], pair[1]);
    }
    document.getElementById("codigo").disabled = true;
    data.append("opcao", "novo");
    if(form.categoria.value !== "") {
            
        fetch(URL, {method: "post", body: data}).then(response => {
            return response.text();
        }).then(result => {
                form.reset();
        }).catch(err => {
            console.log(err);
        });
    }
}

function seleciona(aoba)
{
    var tabela = document.querySelector('#mainTable');
    var linhas = tabela.getElementsByTagName("tr");
    linhas[aoba].className = "selecionado";
}

function cancelar()
{
    Original();
}

function apagar()
{
    var sel = document.getElementsByClassName("selecionado");
    var cod = "";
    var descr = "";
    var entrou = false;
    var aux = sel[0].innerText.toString().replace('\t','');
    for(var i = 0;i < aux.length;i++){
        if((aux[i] >= 'a' && aux[i] <='z') || (aux[i] >= 'A' && aux[i] <='Z'))
            entrou = true;
        if(!entrou)
            cod+=aux[i];
        else
            descr+=aux[i];
    }
    const URL = "CategoriaServ";
    const data = new URLSearchParams();
    data.append("codigo",cod);
    data.append("opcao", "excluir");
            
    fetch(URL, {method: "post", body: data}).then(response => {
        return response.text();
    }).then(result => {
            showToast(result);
    }).catch(err => {
        console.log(err);
    });
    document.getElementById("codigo").value = "";
}
