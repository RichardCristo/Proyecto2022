var n1 = 0;
function sumarAlCarrito(){
    n1 ++;
    document.getElementById("contadorCarrito").innerHTML = n1;
}

function agregarAlCarritoClasica(){
    sumarAlCarrito();
    document.getElementById("datosClasica").innerHTML = "clasica";
}

function agregarAlCarritoNoble(){
    sumarAlCarrito();
    document.getElementById("datosNoble").innerHTML = "noble";
}

function agregarAlCarritoMonarca(){
    sumarAlCarrito();
    document.getElementById("datosMonarca").innerHTML = "monarca";
}

function agregarAlCarritoPollo(){
    sumarAlCarrito();
    document.getElementById("datosPollo").innerHTML = "chicken special";
}

function agregarAlCarritoQueso(){
    sumarAlCarrito();
    document.getElementById("datosQueso").innerHTML = "cheeseburguer";
}

function agregarAlCarritoVegana(){
    sumarAlCarrito();
    document.getElementById("datosVegana").innerHTML = "vegana";
}
