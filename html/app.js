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


function prueba(){
    fetch('http://localhost:8183/menu',{method:'GET'})
    .then(response => {
        const n = "";
        //response.json().then(data => {console.log(JSON.stringify(data))})});
        n = response.json().then(data => {console.log(JSON.stringify(data[1].nombre))})});
        document.getElementById("nombre1").innerHTML = n;
        <h5> </h5>
}