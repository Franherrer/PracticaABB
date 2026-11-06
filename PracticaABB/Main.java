package PracticaABB;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArbolBinarioBusqueda inventario = new ArbolBinarioBusqueda();
        String opcion;

        do {
            System.out.println("\n===== CONTROL DE INVENTARIO (ABB) =====");
            System.out.println("a) Registrar / Insertar nuevo producto");
            System.out.println("b) Buscar producto por nombre");
            System.out.println("c) Dar de baja / Eliminar producto");
            System.out.println("d) Mostrar inventario completo (InOrder)");
            System.out.println("e) Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextLine().trim().toLowerCase();

            switch (opcion) {
                case "a":
                    registrar(sc, inventario);
                    break;
                case "b":
                    buscar(sc, inventario);
                    break;
                case "c":
                    eliminar(sc, inventario);
                    break;
                case "d":
                    System.out.println("\n--- Inventario ordenado (A-Z) ---");
                    inventario.imprimirInOrden();
                    break;
                case "e":
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (!opcion.equals("e"));

        sc.close();
    }

    // Pide el nombre y lo deja en minúsculas y sin espacios de sobra.
    // Así "Arroz" y "arroz" son el mismo producto y compareTo() ordena bien.
    private static String leerNombre(Scanner sc) {
        System.out.print("Nombre del producto: ");
        return sc.nextLine().trim().toLowerCase();
    }

    private static void registrar(Scanner sc, ArbolBinarioBusqueda inventario) {
        String nombre = leerNombre(sc);
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        try {
            System.out.print("Stock (cantidad): ");
            int stock = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Precio unitario: ");
            double precio = Double.parseDouble(sc.nextLine().trim());

            if (stock < 0 || precio < 0) {
                System.out.println("El stock y el precio no pueden ser negativos.");
                return;
            }
            if (inventario.buscar(nombre) != null) {
                System.out.println("El producto \"" + nombre + "\" ya existe. No se registró.");
            } else {
                inventario.insertar(nombre, stock, precio);
                System.out.println("Producto registrado: " + nombre);
            }
        } catch (NumberFormatException e) {
            System.out.println("Dato inválido: el stock y el precio deben ser números.");
        }
    }

    private static void buscar(Scanner sc, ArbolBinarioBusqueda inventario) {
        String nombre = leerNombre(sc);
        Producto p = inventario.buscar(nombre);
        if (p != null) {
            System.out.println("Producto encontrado:");
            System.out.println(p);
        } else {
            System.out.println("El producto \"" + nombre + "\" no existe en el inventario.");
        }
        System.out.println("Comparaciones realizadas: " + inventario.getComparaciones());
    }

    private static void eliminar(Scanner sc, ArbolBinarioBusqueda inventario) {
        String nombre = leerNombre(sc);
        if (inventario.buscar(nombre) == null) {
            System.out.println("El producto \"" + nombre + "\" no existe, no se eliminó nada.");
        } else {
            inventario.eliminar(nombre);
            System.out.println("Producto eliminado: " + nombre);
            System.out.println(inventario.getCasoEliminacion());
        }
    }
}
