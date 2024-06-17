package com.iudigital.concurrency.activity.whithThread;

import com.iudigital.concurrency.activity.domain.ClienteProducto;
import com.iudigital.concurrency.activity.domain.Producto;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CajeraThreadProducto extends Thread {
    private String nombre;
    private ClienteProducto cliente;
    private long tiempoInicial;
    private CyclicBarrier barrera;

    public CajeraThreadProducto(String nombre, long tiempoInicial, ClienteProducto cliente, CyclicBarrier barrera) {
        this.nombre = nombre;
        this.tiempoInicial = tiempoInicial;
        this.cliente = cliente;
        this.barrera = barrera;
    }

    @Override
    public void run() {
        try {
            System.out.println("La cajera " + this.nombre + " comenzó a procesar la compra del cliente: "
                    + this.cliente.getNombre() + " en el tiempo: " + (System.currentTimeMillis() - this.tiempoInicial) / 1000 + " seg.");
            this.barrera.await(); // Esperar a que todas las cajeras estén listas para procesar

            System.out.println("=================================================================");
            int contadorProducto = 1;
            for (Producto producto : cliente.getProductos()) {
                this.esperarXSegundos(producto.getCantidad());
                System.out.println("Procesando el producto " + contadorProducto
                        + "; Nombre del producto: " + producto.getNombre()
                        + "; Precio del producto: " + producto.getPrecio()
                        + "; Cantidad de productos: " + producto.getCantidad()
                        + "; Costo total de cada producto: " + producto.getCantidad() * producto.getPrecio()
                        + "; Del cliente: " + this.cliente.getNombre()
                        + " -> Tiempo " + (System.currentTimeMillis() - this.tiempoInicial) / 1000 + " seg.");
                System.out.println("-----------------------------------------------------------------");
                contadorProducto++;
            }
            System.out.println("=================================================================");
            System.out.println("La cajera " + this.nombre + " ha terminado de procesar la compra del cliente "
                    + this.cliente.getNombre() + " en el tiempo: " + (System.currentTimeMillis() - this.tiempoInicial) / 1000 + " seg.");
            this.barrera.await(); // Esperar a que todas las cajeras terminen al mismo tiempo
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void esperarXSegundos(int segundos) {
        try {
            TimeUnit.SECONDS.sleep(segundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}



