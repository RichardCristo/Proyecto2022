package com.example.restservice;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

@Service
public class DatosJson extends Acceso {
    public DatosJson() {
        super();
    }

    public HashMap<Integer,Hamburguesa> obtenerHamburguesas() {
        this.conectarABaseDeDatos("tpFinal");
        this.conectarAColeccion("menu");
        HashMap<Integer, Hamburguesa> listaDeHamburgesas = new HashMap<>();
        FindIterable resultado = this.getColeccion().find();
        MongoCursor iterador = resultado.iterator();
        int idAutoIncrementable = 0;
        while (iterador.hasNext()) {
            Document documento = (Document) iterador.next();
                HashMap<Integer, HashMap<String, Object>> items = new HashMap<>();
                int precio = documento.getInteger("Precio");
                String nombre = documento.getString("Nombre");
                String ingredientes = documento.getString("Ingredientes");
                Hamburguesa hamburguesa = new Hamburguesa(nombre, precio, ingredientes);
                idAutoIncrementable ++;
                listaDeHamburgesas.put(idAutoIncrementable,hamburguesa);
        }
        return listaDeHamburgesas;
    }
    public void agregarPedido(ArrayList<String> nombre){
        this.conectarABaseDeDatos("tpFinal");
        this.conectarAColeccion("pedido");
        Document document = new Document().append("hamburguesa", nombre);
        this.getColeccion().insertOne(document);
    }

   /* public void agregarItem(HashMap<String, Object> valoresRequeridos, String nombreItem, boolean estado) {
        this.conectarABaseDeDatos("proyectoIntegrador");
        this.conectarAColeccion("usuario");
        HashMap<String, Object> auxiliar = new HashMap<>();
        HashMap<String, Object> infoItem = new HashMap<>();
        auxiliar.put("idUsuario", (Integer) valoresRequeridos.get("idUsuario"));
        infoItem.put("id", this.obtenerCantidadItems(auxiliar, (Integer) valoresRequeridos.get("listaDeCompras.idLista")));
        infoItem.put("descripcion", nombreItem);
        infoItem.put("estado", estado);
        String rutaDelElemento = "listaDeCompras.$.items";
        Document infoConRutaYObjetoAInsertar = new Document(rutaDelElemento, infoItem);
        Document filtro = new Document(valoresRequeridos);
        Document operacionConInfo = new Document("$push", infoConRutaYObjetoAInsertar);
        UpdateResult resultado = this.getColeccion().updateOne(filtro, operacionConInfo);
    }

        public int obtenerCantidadItems(HashMap<String, Object> valoresRequeridos, int idLista) {
        this.conectarABaseDeDatos("proyectoIntegrador");
        this.conectarAColeccion("usuario");
        List<Lista> listas = this.obtenerListasUsuario(valoresRequeridos);
        int contador = 0;
        for (Lista listaActual : listas) {
            if (listaActual.getClavePrimaria() == idLista) {
                for (Map.Entry<Integer, HashMap<String, Object>> itemActual : listaActual.getItems().entrySet()) {
                    contador++;
                }
            }
        }
        return contador;
    }*/

    public boolean verificarUsuario(HashMap<String, Object> valoresRequeridos) {
        this.conectarABaseDeDatos("proyectoIntegrador");
        this.conectarAColeccion("usuario");

        List<Bson> filtros = new ArrayList<>();
        for (Map.Entry<String, Object> valor : valoresRequeridos.entrySet()) {
            Bson equivalencia = eq(valor.getKey(), valor.getValue());
            filtros.add(equivalencia);
        }
        Bson requisitosACumplir = Filters.and(filtros);
        FindIterable resultado = this.getColeccion().find(requisitosACumplir);
        MongoCursor iterador = resultado.iterator();

        if (iterador.hasNext()) {
            Document documento = (Document) iterador.next();
            int idUsuario = documento.getInteger("idUsuario");
            return true;
        }
        return false;
    }

    public int obtenerIdUsuario(HashMap<String, Object> valoresRequeridos) {
        if (this.verificarUsuario(valoresRequeridos)) {
            this.conectarABaseDeDatos("proyectoIntegrador");
            this.conectarAColeccion("usuario");
            List<Bson> filtros = new ArrayList<>();
            for (Map.Entry<String, Object> valor : valoresRequeridos.entrySet()) {
                Bson equivalencia = eq(valor.getKey(), valor.getValue());
                filtros.add(equivalencia);
            }
            Bson requisitosACumplir = Filters.and(filtros);
            FindIterable resultado = this.getColeccion().find(requisitosACumplir);
            MongoCursor iterador = resultado.iterator();

            Document documento = (Document) iterador.next();
            int idUsuario = documento.getInteger("idUsuario");
            return idUsuario;

        }
        return -1;
    }


    public String obtenerInfoUsuario(HashMap<String, Object> valoresRequeridos) {
        if (this.verificarUsuario(valoresRequeridos)) {
            this.conectarABaseDeDatos("proyectoIntegrador");
            this.conectarAColeccion("usuario");

            List<Bson> filtros = new ArrayList<>();
            for (Map.Entry<String, Object> valor : valoresRequeridos.entrySet()) {
                Bson equivalencia = eq(valor.getKey(), valor.getValue());
                filtros.add(equivalencia);
            }
            Bson requisitosACumplir = Filters.and(filtros);
            FindIterable resultado = this.getColeccion().find(requisitosACumplir);
            MongoCursor iterador = resultado.iterator();

            Document documento = (Document) iterador.next();
            String nombreUsuario = documento.getString("nombre");
            return nombreUsuario;

        }
        return null;
    }

    public int insertarUsuario(HashMap<String, Object> datosUsuario) {
        this.conectarABaseDeDatos("proyectoIntegrador");
        this.conectarAColeccion("usuario");
        int idUsuario = this.contarElementos(null, "usuario");
        Document documento = new Document().append("idUsuario", idUsuario).append("nombre", datosUsuario.get("nombre")).append("mail", datosUsuario.get("mail")).append("clave", datosUsuario.get("clave")).append("despensa", new ArrayList<>()).append("listaDeCompras", new ArrayList<>()).append("recetasGuardadas", new ArrayList<>()).append("menuSemanal", new ArrayList<>()).append("recetasCalificadas", new ArrayList<>());

        this.getColeccion().insertOne(documento);
        return idUsuario;
    }


    public int contarElementos(HashMap<String, Object> valoresRequeridos, String coleccion) {
        int ultimoId = 0;
        this.conectarABaseDeDatos("proyectoIntegrador");
        this.conectarAColeccion(coleccion);
        FindIterable resultado;
        List<Bson> filtros = new ArrayList<>();
        if (valoresRequeridos != null) {
            for (Map.Entry<String, Object> valor : valoresRequeridos.entrySet()) {
                Bson equivalencia = eq(valor.getKey(), valor.getValue());
                filtros.add(equivalencia);
            }
            Bson requisitosACumplir = Filters.and(filtros);
            resultado = this.getColeccion().find(requisitosACumplir);
        } else {
            resultado = this.getColeccion().find();
        }

        MongoCursor iterador = resultado.iterator();

        while (iterador.hasNext()) {
            Document documento = (Document) iterador.next();
            ultimoId++;
        }
        return ultimoId;
    }







}
