/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Swal */
function ValidarAlerta(){
    var nombre, placa, tipo;
    nombre = document.getElementsByName("NombreUser").value;
    placa = document.getElementsByName("PlacaUser").value;
    tipo = document.getElementsByName("AutoUser").value;
    if(nombre === null){
        return false;
    }else if(placa === null){
        return false;
    }else if(tipo === null){
        return false;
    }else{
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Registro Exitoso, ahora diríjase a la universidad',
            showConfirmButton: false,
            timer: 2000
        });
    }
}
function ValidarAlertaAdmin(){
    var nombre, placa, tipo;
    nombre = document.getElementsByName("NombreUser").value;
    placa = document.getElementsByName("PlacaUser").value;
    tipo = document.getElementsByName("AutoUser").value;
    if(nombre === null){
        return false;
    }else if(placa === null){
        return false;
    }else if(tipo === null){
        return false;
    }else{
        Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Registro Exitoso',
            showConfirmButton: false,
            timer: 2000
        });
    }
}
