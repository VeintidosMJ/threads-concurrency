package com.iudigital.concurrency.activity.snThread;
import com.iudigital.concurrency.activity.domain.ClienteProducto;
import com.iudigital.concurrency.activity.domain.Producto;
import java.util.List;
import java.util.ArrayList;

public class CajeraProductoMain {

    public static void main(String[] args) {
        List<Producto> productos;
        productos = new ArrayList<>();
        setProductos(productos);

        ClienteProducto cliente1 = new ClienteProducto(" Tony Stark", productos);
        ClienteProducto cliente2 = new ClienteProducto(" Superman", productos);
        ClienteProducto cliente3 = new ClienteProducto(" Aquaman", productos);

        //calculamos el tiempo inicial de referencia

        long initialTime = System.currentTimeMillis();
        CajeraProducto cajeraUno = new CajeraProducto(" Valentina");
        CajeraProducto cajeraDos = new CajeraProducto(" Paola");
        CajeraProducto cajeraTres = new CajeraProducto(" Franchesca");

        cajeraUno.procesarCompra(cliente1, initialTime);
        cajeraDos.procesarCompra(cliente2, initialTime);
        cajeraTres.procesarCompra(cliente3, initialTime);
    }
    private static void setProductos(List<Producto> productos){
        Producto productoUno = new Producto("Zapatos", 500, 6);
        Producto productoDos = new Producto("Pantalones", 800, 18);
        Producto productoTres = new Producto("Camisas", 500, 46);

        productos.add(productoUno);
        productos.add(productoDos);
        productos.add(productoTres);
    }
}
