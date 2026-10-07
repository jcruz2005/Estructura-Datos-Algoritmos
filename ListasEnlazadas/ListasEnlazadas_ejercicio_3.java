import java.util.Random;

/**
 * ListasEnlazadas_ejercicio_3 - ListasEnlazadas_ejercicio_3 genérica usando lista enlazada simple.
 *
 * <p>La lógica de la pila (push/pop en head) NO depende del tipo de dato.
 * Los genéricos permiten reutilizar la misma implementación para Integer,
 * String, o cualquier objeto propio.</p>
 *
 * <p>Ventajas de genéricos:</p>
 * <ul>
 *   <li>Seguridad de tipos en tiempo de compilación</li>
 *   <li>Evita casting manual</li>
 *   <li>Una sola clase para todos los tipos</li>
 * </ul>
 *
 * <p>Complejidad:</p>
 * <ul>
 *   <li>{@code push}: O(1)</li>
 *   <li>{@code pop}: O(1)</li>
 *   <li>{@code peek}: O(1)</li>
 *   <li>{@code contains}: O(n)</li>
 * </ul>
 *
 * @param <T> tipo de elementos
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ListasEnlazadas_ejercicio_3<T> {

    private static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;

        Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo<T> head;
    private int size;
    private final int capacidadMaxima;

    public ListasEnlazadas_ejercicio_3(int capacidadMaxima) {
        this.head = null;
        this.size = 0;
        this.capacidadMaxima = capacidadMaxima;
    }

    public void push(T valor) {
        if (size >= capacidadMaxima) throw new IllegalStateException("Límite " + capacidadMaxima + " alcanzado");
        Nodo<T> nuevo = new Nodo<>(valor);
        nuevo.siguiente = head;
        head = nuevo;
        size++;
    }

    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Pop: pila vacía");
        T valor = head.valor;
        head = head.siguiente;
        size--;
        return valor;
    }

    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Peek: pila vacía");
        return head.valor;
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    public boolean contains(T valor) {
        Nodo<T> actual = head;
        while (actual != null) {
            if (valor == null ? actual.valor == null : valor.equals(actual.valor)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = head;
        while (actual != null) {
            sb.append(actual.valor);
            if (actual.siguiente != null) sb.append(" → ");
            actual = actual.siguiente;
        }
        sb.append("]  (tope → base)");
        return sb.toString();
    }

    public void imprimirEstado(String nombre) {
        System.out.println("  " + nombre + ": head = " + (head != null ? head.valor : "null")
                + " | size = " + size + "/" + capacidadMaxima
                + " | " + (isEmpty() ? "VACÍA" : "Normal"));
        System.out.println("  Contenido: " + this);
    }

    // =================================================================
    // CLASE PROPIA PARA DEMO
    // =================================================================
    public static class Producto {
        String nombre;
        double precio;

        public Producto(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        @Override
        public String toString() {
            return nombre + "($" + precio + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Producto)) return false;
            Producto p = (Producto) o;
            return nombre.equals(p.nombre) && precio == p.precio;
        }
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int MAX = 20;

        ListasEnlazadas_ejercicio_3<Integer> pilaInt = new ListasEnlazadas_ejercicio_3<>(MAX);
        ListasEnlazadas_ejercicio_3<String> pilaStr = new ListasEnlazadas_ejercicio_3<>(MAX);
        ListasEnlazadas_ejercicio_3<Producto> pilaObj = new ListasEnlazadas_ejercicio_3<>(MAX);

        String[] palabras = {"manzana", "banana", "cereza", "durazno", "uva", "kiwi", "mango", "pera"};
        String[] productosNombres = {"Laptop", "Mouse", "Monitor", "Teclado", "Auriculares", "Webcam", "Disco SSD", "Impresora"};

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 3 — PILA GENÉRICA ENLAZADA (ListasEnlazadas_ejercicio_3<T>, máx 20 nodos)            ║");
        System.out.println("║  Misma lógica para Integer, String y ObjetoPropio (Producto)            ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ ESTADO INICIAL ━━━");
        pilaInt.imprimirEstado("ListasEnlazadas_ejercicio_3<Integer>");
        System.out.println();
        pilaStr.imprimirEstado("ListasEnlazadas_ejercicio_3<String>");
        System.out.println();
        pilaObj.imprimirEstado("ListasEnlazadas_ejercicio_3<Producto>");
        System.out.println();

        int ops = 12 + random.nextInt(8);

        for (int i = 1; i <= ops; i++) {
            System.out.println("━━━ OPERACIÓN #" + i + " ━━━");

            int pilaSel = random.nextInt(3); // 0=int, 1=string, 2=obj
            double r = random.nextDouble();

            if (pilaSel == 0) {
                System.out.println("  → ListasEnlazadas_ejercicio_3<Integer>");
                if (r < 0.6 || pilaInt.isEmpty()) {
                    int v = random.nextInt(100);
                    pilaInt.push(v);
                    System.out.println("    PUSH(" + v + ") ✓");
                } else if (r < 0.85) {
                    System.out.println("    POP() → " + pilaInt.pop() + " ✓");
                } else {
                    System.out.println("    PEEK() → " + pilaInt.peek() + " ✓");
                }
                pilaInt.imprimirEstado("ListasEnlazadas_ejercicio_3<Integer>");
            } else if (pilaSel == 1) {
                System.out.println("  → ListasEnlazadas_ejercicio_3<String>");
                if (r < 0.6 || pilaStr.isEmpty()) {
                    String v = palabras[random.nextInt(palabras.length)] + random.nextInt(10);
                    pilaStr.push(v);
                    System.out.println("    PUSH(" + v + ") ✓");
                } else if (r < 0.85) {
                    System.out.println("    POP() → " + pilaStr.pop() + " ✓");
                } else {
                    System.out.println("    PEEK() → " + pilaStr.peek() + " ✓");
                }
                pilaStr.imprimirEstado("ListasEnlazadas_ejercicio_3<String>");
            } else {
                System.out.println("  → ListasEnlazadas_ejercicio_3<Producto>");
                if (r < 0.6 || pilaObj.isEmpty()) {
                    String nom = productosNombres[random.nextInt(productosNombres.length)];
                    double precio = 100 + random.nextInt(900) + random.nextDouble();
                    Producto p = new Producto(nom, Math.round(precio * 100.0) / 100.0);
                    pilaObj.push(p);
                    System.out.println("    PUSH(" + p + ") ✓");
                } else if (r < 0.85) {
                    System.out.println("    POP() → " + pilaObj.pop() + " ✓");
                } else {
                    System.out.println("    PEEK() → " + pilaObj.peek() + " ✓");
                }
                pilaObj.imprimirEstado("ListasEnlazadas_ejercicio_3<Producto>");
            }
            System.out.println();
        }

        System.out.println("━━━ DEMOSTRACIÓN DE GENÉRICOS ━━━");
        System.out.println();
        System.out.println("  Una sola clase ListasEnlazadas_ejercicio_3<T> funciona para:");
        System.out.println("    • ListasEnlazadas_ejercicio_3<Integer>  → push(42), pop() → 42");
        System.out.println("    • ListasEnlazadas_ejercicio_3<String>   → push(\"hola\"), pop() → \"hola\"");
        System.out.println("    • ListasEnlazadas_ejercicio_3<Producto> → push(new Producto(...)), pop() → Producto");
        System.out.println();
        System.out.println("  Sin genéricos necesitaríamos: PilaEnteros, PilaStrings, PilaProductos...");
        System.out.println("  La lógica de enlazar/desenlazar nodos es IDÉNTICA para cualquier T.");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}