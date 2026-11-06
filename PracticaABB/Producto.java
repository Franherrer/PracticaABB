package PracticaABB;

public class Producto {
    String nombre;   // Llave unica del producto (ordena el ABB alfabeticamente)
    int stock;       // Cantidad disponible en el almacen
    double precio;   // Costo unitario
    Producto izq;    // Hijo izquierdo (nombres que van ANTES en el alfabeto)
    Producto der;    // Hijo derecho (nombres que van DESPUES en el alfabeto)

    // Constructor para inicializar el nodo
    public Producto(String nombre, int stock, double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.izq = null;
        this.der = null;
    }

    // Texto con los datos del producto (para imprimirlo en consola)
    @Override
    public String toString() {
        return String.format("%-15s | Stock: %4d | Precio: $%.2f", nombre, stock, precio);
    }
}
