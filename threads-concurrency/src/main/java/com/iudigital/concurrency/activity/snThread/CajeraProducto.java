package com.iudigital.concurrency.activity.snThread;

import com.iudigital.concurrency.activity.domain.ClienteProducto;
import com.iudigital.concurrency.activity.domain.Producto;

public class CajeraProducto {

    //Hacemos la logica para gestionar el proceso de cobro
    private String nombreCajera;

    public CajeraProducto(String nombreCajera) {
        this.nombreCajera = nombreCajera;
    }

    public void procesarCompra(ClienteProducto cliente, long timeStanp){
        System.out.println("La cajera " + this.nombreCajera + " comienza a procesar la compra del cliente"
        + cliente.getNombre() + " en el tiempo " + (System.currentTimeMillis() - timeStanp) /1000
        + " seg ");

        int contCliente = 1;
        for(Producto producto : cliente.getProductos()){
            this.esperarXSegundos();
            System.out.println("Procesando  el producto " + contCliente +
                    " nombre producto " + producto.getNombre() +
                    " precio producto " + producto.getPrecio() +
                    " cantidad de producto " + producto.getCantidad() +
                    " costo total del producto " + producto.getCantidad() * producto.getPrecio() +
                    " ->Tiempo: " + (System.currentTimeMillis() - timeStanp)/1000 + " seg ");
            System.out.println("------------------Procesando Producto----------------------");
        }
        System.out.println("La cajera " + this.nombreCajera + " ha terminado de procesar la compra del cliente:" + cliente.getNombre()
        + " en el tiempo: " + (System.currentTimeMillis() - timeStanp)/1000 + " seg ");
        System.out.println("===================================");

    }

    private void esperarXSegundos() {
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
