var modal = document.getElementById('id01');
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
    }
}

function PreencheCBCategoria(){
    var monta;
    const URL = "categoria?opcao=atualiza";
    fetch(URL).then(response => {
        return response.text();
    }).then(result => {
       return document.querySelector('#Bcat').innerHTML = result; 
    }).catch(err => {
        console.log(err);
    });
}

function PreencheCBServicos(){
    
}

function showToast(msg){
    var toast = document.getElementById("snackbar");
    toast.className = "show";
    toast.innerText = msg;
    setTimeout(function(){
        toast.className = toast.className.replace("show", "");
        toast.innerText = "";
    }, 3000);
}

function SalvarAnuncio(){
    
}

function SalvarCategoria(){
    var form = document.getElementById("id_do_formulario");
    const URL_TO_FETCH = 'categoria';
    event.preventDefault();

    const data = new URLSearchParams();
    for (const pair of new FormData(form)) {
        data.append(pair[0], pair[1]);
    }
    
    data.append("opcao", "novo");
    
    if(form.categoria.value !== "") {

        fetch(URL, {method: "post", body: data}).then(response => {
            return response.text();
        }).then(result => {
                showToast(result);
                form.reset();
        }).catch(err => {
            console.log(err);
        });
    }
    else
        showToast("todos campos são obrigatórios");
    carregaTabela();
}

function SalvarPrestador(){
    
    
    
}

function buscaLogin(){
    
    event.preventDefault();

    const URL_TO_FETCH = 'PrestadorServ';
    
    const data = new URLSearchParams();
    for (const pair of new FormData(document.getElementById('fdados'))) {
        data.append(pair[0], pair[1]);
    }
    data.append('acao', 'login');
    fetch(URL_TO_FETCH, { method: 'post', body: data 
    }).then(function (response) {
        return response.text();
    }).then(function (retorno) {
         
    }).catch(function (error) {
        console.error(error);
    });
}

function carregaAnuncios()
{   
    var filtro=document.getElementById("filtro").value;
    const URL_TO_FETCH='AnunciosServ?acao=consultar&filtro='+filtro;
    fetch(URL_TO_FETCH,{method:'get'}).then(function(response)
    {
        response.text().then(function(result)
        {
            document.getElementById('preview').innerHTML = result;
        });
    }).catch (function(err) {console.error(err);});

}