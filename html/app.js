var n1 = 0;
let pedido = [];
function sumarAlCarrito(){
    n1 ++;
    document.getElementById("contadorCarrito").innerHTML = n1;
}

function agregarAlCarrito(nombre){
    sumarAlCarrito();
    const elP = document.createElement("p");
    elP.innerText = nombre;
    document.getElementById("datos").append(elP);
    papu.appendChild(elP);
}

function traerMenu(){
    nombreAux = " ";
    ingAux = " ";
    precioAux = " ";
    fetch('http://localhost:8083/menu',{method:'GET'})
    .then(response=>{
        response.json().then(data =>{
            nombreAux = JSON.stringify(data[1].nombre);
            ingAux = JSON.stringify(data[1].ingredientes);
            precioAux = JSON.stringify(data[1].precio);
            document.getElementById("col1").innerHTML  = 
            "<div class='col' id='col1'><div class='card' style='width: 18rem;'><img src='burga clasica.jpg' class='card-img-top' alt='...'><div class='card-body'><h5 class='card-title' id='nombreHamburguesa'> "+nombreAux+" </h5> <p class='card-text'>"+ingAux+"</p><p class='card-text'> "+precioAux+" </p> <a href='#' class='btn btn-primary colorBoton' onclick= 'agregarAlCarrito('La Clasica')'>Añadir al carrito</a> </div></div> </div>"
            nombreAux = JSON.stringify(data[2].nombre);
            ingAux = JSON.stringify(data[2].ingredientes);
            precioAux = JSON.stringify(data[2].precio);
            document.getElementById("col2").innerHTML  = 
            " <div class='col' id='col2'><div class='card' style='width: 18rem;'><img src='burga panceta.jpg' class='card-img-top alt='...'><div class='card-body'><h5 class='card-title'>"+nombreAux+"</h5><p class='card-text'>"+ingAux+" </p><p class='card-text'>"+precioAux+" </p><a href='#' class='btn btn-primary colorBoton' onclick= 'agregarAlCarrito('La Noble')'>Añadir al carrito</a></div></div>"
            nombreAux = JSON.stringify(data[3].nombre);
            ingAux = JSON.stringify(data[3].ingredientes);
            precioAux = JSON.stringify(data[3].precio);
            document.getElementById("col3").innerHTML  = 
            "<div class='col' id ='col3'><div class='card' style='width: 18rem;'><img src='burga pepinoide.png' class='card-img-top' alt='...'><div class='card-body'><h5 class='card-title'>"+nombreAux+"</h5><p class='card-text'>"+ingAux+"</p><p class='card-text'>"+precioAux+"</p><a href='#' class='btn btn-primary colorBoton' onclick= 'agregarAlCarrito('La Monarca')'>Añadir al carrito</a></div></div></div>"
            nombreAux = JSON.stringify(data[4].nombre);
            ingAux = JSON.stringify(data[4].ingredientes);
            precioAux = JSON.stringify(data[4].precio);
            document.getElementById("col4").innerHTML  = 
            "<div class='col' id='col4'> <div class='card' style='width: 18rem;'><img src='burga pollo.jpeg' class='card-img-top' alt='...'><div class='card-body'><h5 class='card-title'>"+nombreAux+"</h5><p class='card-text'>"+ingAux+"</p><p class='card-text'>"+precioAux+"</p><a href='#' class='btn btn-primary colorBoton' onclick= 'agregarAlCarrito('Chicken Especial')'>Añadir al carrito</a></div></div> </div>"
            nombreAux = JSON.stringify(data[5].nombre);
            ingAux = JSON.stringify(data[5].ingredientes);
            precioAux = JSON.stringify(data[5].precio);
            document.getElementById("col5").innerHTML  = 
            "<div class='col' id='col5'><div class='card' style='width: 18rem;'><img src='burga simple.jpeg' class='card-img-top' alt='...'><div class='card-body'><h5 class='card-title'>"+nombreAux+"</h5><p class='card-text'>"+ingAux+"</p><p class='card-text'>"+precioAux+"</p><a href='#' class='btn btn-primary colorBoton' onclick= 'agregarAlCarrito('Cheeseburguer')'>Añadir al carrito</a></div></div></div>"
            nombreAux = JSON.stringify(data[6].nombre);
            ingAux = JSON.stringify(data[6].ingredientes);
            precioAux = JSON.stringify(data[6].precio);
            document.getElementById("col6").innerHTML  = 
            "<div class='col' id='col6'><div class='card' style='width: 18rem;'><img src='burge veggie.jpg' class='card-img-top' alt='...'><div class='card-body'><h5 class='card-title'>"+nombreAux+"</h5><p class='card-text'>"+ingAux+"</p><p class='card-text'>"+precioAux+"</p><a href='#' class='btn btn-primary colorBoton' onclick= 'agregarAlCarrito('vegana')'>Añadir al carrito</a></div></div></div>"
        })
    });
}
function agregarPedido(){
    
    fetch("http://localhost:8083/api/agregarPedido/" + pedido);

    
}


