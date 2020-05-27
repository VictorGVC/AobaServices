var modal = document.getElementById('id01');
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
    }
}

function PreencheCBCategoria(){
    
    var cbcategoria = document.getElementById("categoria");
    
    while (cbcategoria.length > 0) 
        cbcategoria.remove(0);

    for (var i = 0; i < Variaveldobanco.length; i++) {
        var elem = document.createElement('tag')
        elem.text  = Variaveldobanco.Descricao;
    
        cbcategoria.add(elem, cbcategoria.options[i]);
    }
    
}

function PreencheCBServicos(){
    
    var cbservicos = document.getElementById("servicos");
    
    while (cbservicos.length > 0) 
        cbservicos.remove(0);

    for (var i = 0; i < Variaveldobanco.length; i++) {
        var elem = document.createElement('tag')
        elem.text  = Variaveldobanco.Descricao;
    
        cbservicos.add(elem, cbservicos.options[i]);
    }
    
}

function SalvarAnuncio(){
    
}

function SalvarPrestador(){
    
    
    
}