# Sistema de Control de Inventario con ABB

Proyecto Integrador de Estructura de Datos - Instituto Tecnológico Superior de Xalapa.

**Docente:** Víctor Hugo Vásquez Herrera

## Integrantes
- Yael Aminadab Hernandez Reyes
- Jose Francisco Hernandez Herrera

## Descripción
Aplicación de consola en Java que simula el inventario de un almacén. Cada producto
es un nodo de un Árbol Binario de Búsqueda (ABB) ordenado alfabéticamente por nombre
usando `compareTo()`.

Funciones: insertar, buscar (O(log n) en promedio), eliminar (hoja, un hijo, dos hijos
con sucesor inorden) y mostrar el inventario con recorrido InOrder.

## Estructura
- `Producto.java` - Nodo (nombre, stock, precio, izq, der)
- `ArbolBinarioBusqueda.java` - Lógica del ABB
- `Main.java` - Menú interactivo con Scanner

## Cómo ejecutarlo
1. Clonar el repositorio:
   ```
   git clone: 
   ```
2. Abrir la carpeta como proyecto en IntelliJ IDEA.
3. Ejecutar `Main.java` (clic en el triángulo verde o Shift+F10).

O por terminal, dentro de la carpeta `src`:
```
javac *.java
java Main
```
