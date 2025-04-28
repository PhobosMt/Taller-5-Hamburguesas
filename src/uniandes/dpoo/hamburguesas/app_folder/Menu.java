package uniandes.dpoo.hamburguesas.app_folder;

import java.util.Scanner;
import java.io.IOException;
import uniandes.dpoo.hamburguesas.mundo.*;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;

/**
 * Esta clase gestiona la interfaz de usuario del restaurante,
 * mostrando el menú de opciones y permitiendo al usuario realizar acciones.
 */
public class Menu {

    private static Restaurante restaurante = new Restaurante();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Menú principal interactivo
        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            if (opcion == 1) {
                verMenu();
            } else if (opcion == 2) {
                iniciarNuevoPedido();
            } else if (opcion == 3) {
                agregarProductoAlPedido();
            } else if (opcion == 4) {
                cerrarPedido();
            } else if (opcion == 5) {
                System.out.println("¡Gracias por usar la aplicación!");
                scanner.close();
                break;
            } else {
                System.out.println("Opción no válida, intente de nuevo.");
            }
        }
    }

    // Muestra las opciones del menú principal
    private static void mostrarMenu() {
        System.out.println("********** Menú Restaurante **********");
        System.out.println("1. Ver Menú (Productos y Combos)");
        System.out.println("2. Iniciar un nuevo pedido");
        System.out.println("3. Agregar un producto al pedido");
        System.out.println("4. Cerrar el pedido y generar factura");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Muestra los productos y combos disponibles
    private static void verMenu() {
        System.out.println("\n--- Menú de Productos Básicos ---");
        for (ProductoMenu producto : restaurante.getMenuBase()) {
            System.out.println(producto.getNombre() + " - $" + producto.getPrecio());
        }

        System.out.println("\n--- Menú de Combos ---");
        for (Combo combo : restaurante.getMenuCombos()) {
            System.out.println(combo.getNombre() + " - $" + combo.getPrecio());
        }
    }

    // Inicia un nuevo pedido
    private static void iniciarNuevoPedido() {
        if (restaurante.getPedidoEnCurso() != null) {
            System.out.println("Ya hay un pedido en curso. Primero cierra el pedido anterior.");
            return;
        }

        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        System.out.print("Ingrese la dirección del cliente: ");
        String direccionCliente = scanner.nextLine();

        try {
            restaurante.iniciarPedido(nombreCliente, direccionCliente);
            System.out.println("Pedido iniciado para " + nombreCliente);
        } catch (YaHayUnPedidoEnCursoException e) {
            System.out.println(e.getMessage());
        }
    }

    // Agrega un producto al pedido en curso
    private static void agregarProductoAlPedido() {
        if (restaurante.getPedidoEnCurso() == null) {
            System.out.println("No hay un pedido en curso. Inicie un pedido primero.");
            return;
        }

        System.out.print("Ingrese el nombre del producto a agregar: ");
        String nombreProducto = scanner.nextLine();

        ProductoMenu productoSeleccionado = null;
        for (ProductoMenu producto : restaurante.getMenuBase()) {
            if (producto.getNombre().equals(nombreProducto)) {
                productoSeleccionado = producto;
                break;
            }
        }

        if (productoSeleccionado == null) {
            System.out.println("Producto no encontrado en el menú.");
        } else {
            restaurante.getPedidoEnCurso().agregarProducto(productoSeleccionado);
            System.out.println("Producto agregado al pedido: " + productoSeleccionado.getNombre());
        }
    }

    // Cierra el pedido y genera la factura
    private static void cerrarPedido() {
        if (restaurante.getPedidoEnCurso() == null) {
            System.out.println("No hay un pedido en curso.");
            return;
        }

        try {
            restaurante.cerrarYGuardarPedido();
            System.out.println("Pedido cerrado y factura generada.");
        } catch (NoHayPedidoEnCursoException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}