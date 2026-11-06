package PracticaABB;

public class ArbolBinarioBusqueda {

    private Producto raiz;
    private int comparaciones;           // Comparaciones que hizo la ultima busqueda
    private String casoEliminacion = ""; // Que caso de borrado se aplico (para mostrarlo)

    public int getComparaciones() {
        return comparaciones;
    }

    public String getCasoEliminacion() {
        return casoEliminacion;
    }

    // Metodo para insertar un producto
    public void insertar(String nombre, int stock, double precio) {
        raiz = insertarRecursivo(raiz, nombre, stock, precio);
    }

    // Metodo recursivo para insertar
    // Se usa compareTo() en lugar de < y > porque son cadenas (String)
    private Producto insertarRecursivo(Producto actual, String nombre, int stock, double precio) {

        if (actual == null) {
            return new Producto(nombre, stock, precio);
        }

        int cmp = nombre.compareTo(actual.nombre);

        if (cmp < 0) {
            // Alfabeticamente es menor: va a la izquierda
            actual.izq = insertarRecursivo(actual.izq, nombre, stock, precio);
        } else if (cmp > 0) {
            // Alfabeticamente es mayor: va a la derecha
            actual.der = insertarRecursivo(actual.der, nombre, stock, precio);
        }
        // Si cmp == 0 el producto ya existe: no se inserta (evita duplicados)

        return actual;
    }

    // Metodo para buscar un producto por nombre (Divide y Venceras)
    // Devuelve el producto o null si no existe
    public Producto buscar(String nombre) {
        comparaciones = 0;
        return buscarRecursivo(raiz, nombre);
    }

    // Metodo recursivo para buscar
    private Producto buscarRecursivo(Producto actual, String nombre) {

        if (actual == null) {
            return null; // No existe
        }

        comparaciones++;
        int cmp = nombre.compareTo(actual.nombre);

        if (cmp == 0) {
            return actual; // Encontrado
        } else if (cmp < 0) {
            // Se descarta toda la rama derecha
            return buscarRecursivo(actual.izq, nombre);
        } else {
            // Se descarta toda la rama izquierda
            return buscarRecursivo(actual.der, nombre);
        }
    }

    // Metodo para eliminar un producto
    public void eliminar(String nombre) {
        casoEliminacion = "";
        raiz = eliminarRecursivo(raiz, nombre);
    }

    // Metodo recursivo para eliminar
    private Producto eliminarRecursivo(Producto actual, String nombre) {

        if (actual == null) {
            return null;
        }

        int cmp = nombre.compareTo(actual.nombre);

        if (cmp < 0) {
            actual.izq = eliminarRecursivo(actual.izq, nombre);
        } else if (cmp > 0) {
            actual.der = eliminarRecursivo(actual.der, nombre);
        } else {

            // CASO 1: Es una hoja (no tiene hijos)
            if (actual.izq == null && actual.der == null) {
                if (casoEliminacion.isEmpty()) {
                    casoEliminacion = "Caso 1: nodo hoja (sin hijos)";
                }
                return null;
            }

            // CASO 2: Tiene exactamente un hijo
            if (actual.izq == null) {
                if (casoEliminacion.isEmpty()) {
                    casoEliminacion = "Caso 2: nodo con un hijo";
                }
                return actual.der;
            }

            if (actual.der == null) {
                if (casoEliminacion.isEmpty()) {
                    casoEliminacion = "Caso 2: nodo con un hijo";
                }
                return actual.izq;
            }

            // CASO 3: Tiene dos hijos
            if (casoEliminacion.isEmpty()) {
                casoEliminacion = "Caso 3: nodo con dos hijos (sucesor inorden)";
            }
            Producto sucesor = obtenerMinimo(actual.der);

            // Reemplazamos los datos del nodo con los del sucesor
            actual.nombre = sucesor.nombre;
            actual.stock = sucesor.stock;
            actual.precio = sucesor.precio;

            // Eliminamos el sucesor original
            actual.der = eliminarRecursivo(
                actual.der, sucesor.nombre
            );
        }

        return actual;
    }

    // Metodo auxiliar exclusivo del Caso 3
    private Producto obtenerMinimo(Producto actual) {

        // Buscar el nodo mas pequeño del lado derecho
        while (actual.izq != null) {
            actual = actual.izq;
        }

        return actual;
    }

    // Metodo principal para imprimir el inventario ordenado alfabeticamente
    public void imprimirInOrden() {

        if (raiz == null) {
            System.out.println("El inventario esta vacio.");
            return;
        }

        imprimirRec(raiz);
    }

    // Metodo recursivo para imprimir nodo por nodo
    private void imprimirRec(Producto actual) {

        if (actual != null) {

            // 1. Izquierda
            imprimirRec(actual.izq);

            // 2. Imprimir
            System.out.println(actual);

            // 3. Derecha
            imprimirRec(actual.der);
        }
    }
}
