var menuC= function(){
    var iconoMenu= document.getElementById("menuNav");
    if(iconoMenu.className == "Menu2 escond"){
        iconoMenu.className= "Menu2 mostrar";
    }else{
        iconoMenu.className= "Menu2 escond";
    }
}
var importante= function(){
    var iconoExcl= document.getElementById("importa");
    if(iconoExcl.className == "new escond2"){
        iconoExcl.className= "new mostrar2";
    }else{
        iconoExcl.className= "new escond2";
    }
}
var icono = function (producto,cliente) {
    var icono = document.getElementById("icon" + producto);
    var favoritoA= document.getElementById("agregarFavorito"+producto);
    if (icono.innerHTML === "check_circle") {
        favoritoA.href="Login?pagina=EliminarFavorito&prod="+producto+"&clien="+cliente;
        
    } else if (icono.innerHTML === "star") {
        favoritoA.href= "Login?pagina=AgregarFavorito&prod="+producto+"&clien="+cliente;
        
    }
}
var verificar= function(){
    var est = true;
    var comentario= document.querySelector('[name=comentario]').value;
    comentario=comentario.toUpperCase();
    if(comentario==="DEJA TU COMENTARIO" || comentario.length<4 || !comentario || /^\s*$/.test(comentario)){
        est=false;
    }
    return est;
}