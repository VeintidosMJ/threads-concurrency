package com.iudigital.concurrency.activity.whithThread;

import com.iudigital.concurrency.activity.domain.ClienteProducto;
import com.iudigital.concurrency.activity.domain.Producto;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class CajeraThreadProductosPrincipal {
    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>();
        setProductos(productos);

        String cliente1Nombre = JOptionPane.showInputDialog("Ingrese el nombre del primer cliente:");
        String cliente2Nombre = JOptionPane.showInputDialog("Ingrese el nombre del segundo cliente:");
        String cliente3Nombre = JOptionPane.showInputDialog("Ingrese el nombre del tercer cliente:");

        ClienteProducto cliente1 = new ClienteProducto(cliente1Nombre, productos);
        ClienteProducto cliente2 = new ClienteProducto(cliente2Nombre, productos);
        ClienteProducto cliente3 = new ClienteProducto(cliente3Nombre, productos);

        int numCajeras = 3;
        CyclicBarrier barrera = new CyclicBarrier(numCajeras, () -> System.out.println("Todas las cajeras están listas para procesar"));

        long tiempoInicial = System.currentTimeMillis();

        CajeraThreadProducto cajeraUno = new CajeraThreadProducto("Cajera 1", tiempoInicial, cliente1, barrera);
        CajeraThreadProducto cajeraDos = new CajeraThreadProducto("Cajera 2", tiempoInicial, cliente2, barrera);
        CajeraThreadProducto cajeraTres = new CajeraThreadProducto("Cajera 3", tiempoInicial, cliente3, barrera);

        cajeraUno.start();
        cajeraDos.start();
        cajeraTres.start();

        try {
            cajeraUno.join();
            cajeraDos.join();
            cajeraTres.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Todos los clientes han sido atendidos.");
    }

    private static void setProductos(List<Producto> productos) {
        System.out.println("===============================================================");
        System.out.println("Ingrese los detalles de los productos (se ingresarán 3 productos)");
        System.out.println("===============================================================");

        for (int i = 1; i <= 3; i++) {
            String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto " + i + ":");
            double precioProducto = 0;
            int cantidadProducto = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    precioProducto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del producto " + i + ":"));
                    cantidadProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del producto " + i + ":"));
                    validInput = true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Entrada no válida. Por favor ingrese números válidos para el precio y la cantidad.");
                }
            }

            Producto producto = new Producto(nombreProducto, precioProducto, cantidadProducto);
            productos.add(producto);
        }
        System.out.println("===============================================================");
        System.out.println("Productos ingresados correctamente:");
        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + " - Precio: " + producto.getPrecio() + " - Cantidad: " + producto.getCantidad());
        }
        System.out.println("===============================================================");
    }
}


