function validar() {
    var estado = true;
    var contraseña = document.querySelector('[name=Contra]').value;
    var ConfContraseña = document.querySelector('[name=ConfContra]').value;
    var numeros = /[0-9]/;
    if (contraseña.length < 8 && contraseña.length > 0) {
        e = document.getElementById('contraMen');
        e.className = "fondo";
        setTimeout(function () {
            e.className = "";
        }, 6000);
        estado = false;
    }
    if (contraseña === contraseña.toLowerCase()) {
        e = document.getElementById('contraMen');
        e.className = "fondo";
        setTimeout(function () {
            e.className = "";
        }, 6000);
        estado = false;
    }
    if (contraseña === contraseña.toUpperCase()) {
        e = document.getElementById('contraMen');
        e.className = "fondo";
        setTimeout(function () {
            e.className = "";
        }, 6000);
        estado = false;
    }
    if (contraseña !== ConfContraseña) {
        e1 = document.getElementById('ConfContraseña');
        e1.style.visibility = "visible";
        estado = false;
        setTimeout(function () {
            e1.style.visibility = 'hidden';
        }, 5000);
    }
    if (!numeros.test(contraseña)) {
        e = document.getElementById('contraMen');
        e.className = "fondo";
        setTimeout(function () {
            e.className = "";
        }, 6000);
        estado = false;
    }
    return estado;
}
function cambio() {
    var email = document.getElementById("email");
    email.style.top = '-12px';
}
function mostrarEdit() {
    e2 = document.getElementById('escondEdit');
    e2.className = "";
}

