import java.util.Random;

/**
 * ListasEnlazadas_ejercicio_1 - Implementación de una pila de enteros usando lista enlazada simple.
 *
 * <p>Cada nodo almacena un valor entero y una referencia al siguiente nodo.
 * La referencia {@code head} apunta al tope de la pila (el último elemento agregado).</p>
 *
 * <p>¿Por qué {@code head} es el tope?</p>
 * <ul>
 *   <li>Al hacer {@code push}, el nuevo nodo se convierte en el primer nodo (head)</li>
 *   <li>Al hacer {@code pop}, se retira el primer nodo (head)</li>
 *   <li>Ambas operaciones son O(1) porque solo modifican la referencia head</li>
 *   <li>No hay que recorrer la lista para llegar al final</li>
 * </ul>
 *
 * <p>Complejidad:</p>
 * <ul>
 *   <li>{@code push}: O(1)</li>
 *   <li>{@code pop}: O(1)</li>
 *   <li>{@code peek}: O(1)</li>
 *   <li>{@code contains}: O(n)</li>
 *   <li>{@code imprimir}: O(n)</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ListasEnlazadas_ejercicio_1 {

    /**
     * Nodo interno de la lista enlazada.
     */
    private static class Nodo {
        int valor;
        Nodo siguiente;

        Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    /** Referencia al tope de la pila (primer nodo de la lista) */
    private Nodo head;

    /** Contador de elementos para límite de 20 nodos */
    private int size;

    /** Capacidad máxima (solo para demo) */
    private final int capacidadMaxima;

    /**
     * Crea una pila enlazada vacía.
     *
     * @param capacidadMaxima máximo de nodos permitidos (para demo)
     */
    public ListasEnlazadas_ejercicio_1(int capacidadMaxima) {
        this.head = null;
        this.size = 0;
        this.capacidadMaxima = capacidadMaxima;
    }

    /**
     * Inserta un elemento en el tope de la pila.
     * El nuevo nodo se convierte en head, apuntando al anterior head.
     *
     * @param valor entero a insertar
     * @throws IllegalStateException si se alcanzó el límite de nodos
     */
    public void push(int valor) {
        if (size >= capacidadMaxima) {
            throw new IllegalStateException("Push fallido: límite de " + capacidadMaxima + " nodos alcanzado");
        }
        Nodo nuevo = new Nodo(valor);
        nuevo.siguiente = head; // El nuevo nodo apunta al antiguo tope
        head = nuevo;           // head ahora apunta al nuevo nodo
        size++;
    }

    /**
     * Retira y devuelve el elemento del tope.
     *
     * @return valor del elemento retirado
     * @throws IllegalStateException si la pila está vacía
     */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Pop fallido: pila vacía");
        }
        int valor = head.valor;
        head = head.siguiente; // head avanza al siguiente nodo
        size--;
        return valor;
    }

    /**
     * Devuelve el elemento del tope sin retirarlo.
     *
     * @return valor en el tope
     * @throws IllegalStateException si la pila está vacía
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Peek fallido: pila vacía");
        }
        return head.valor;
    }

    /**
     * Verifica si la pila está vacía.
     *
     * @return {@code true} si no hay elementos
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Busca si un valor existe en la pila.
     * Recorre la lista desde head hasta el final.
     *
     * @param valor valor a buscar
     * @return {@code true} si se encuentra
     */
    public boolean contains(int valor) {
        Nodo actual = head;
        while (actual != null) {
            if (actual.valor == valor) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    /**
     * Devuelve la cantidad de elementos.
     */
    public int size() {
        return size;
    }

    /**
     * Imprime el contenido de la pila (tope → base).
     */
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Nodo actual = head;
        while (actual != null) {
            sb.append(actual.valor);
            if (actual.siguiente != null) sb.append(" → ");
            actual = actual.siguiente;
        }
        sb.append("]  (tope a la izquierda)");
        return sb.toString();
    }

    /**
     * Imprime estado detallado con head y size.
     */
    public void imprimirEstado() {
        System.out.println("  head = " + (head != null ? head.valor : "null")
                + " | size = " + size + "/" + capacidadMaxima
                + " | " + (isEmpty() ? "VACÍA" : "Normal"));
        System.out.println("  Pila (tope → base): " + this);
    }

    // =================================================================
    // MAIN - Demostración automática
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int MAX_NODOS = 20;
        ListasEnlazadas_ejercicio_1 pila = new ListasEnlazadas_ejercicio_1(MAX_NODOS);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 1 — PILA ENLAZADA DE ENTEROS (lista simple, máx 20 nodos)      ║");
        System.out.println("║  head = tope | push/pop en head = O(1) | contains = O(n)                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ ESTADO INICIAL ━━━");
        pila.imprimirEstado();
        System.out.println();

        int operaciones = 15 + random.nextInt(10); // 15-24

        for (int i = 1; i <= operaciones; i++) {
            System.out.println("━━━ OPERACIÓN #" + i + " ━━━");

            double r = random.nextDouble();

            try {
                if (r < 0.5 || pila.isEmpty()) {
                    // PUSH
                    int valor = random.nextInt(100);
                    System.out.println("  → PUSH(" + valor + ")");
                    pila.push(valor);
                    System.out.println("  ✓ Insertado: " + valor + " (nuevo head)");
                } else if (r < 0.8) {
                    // POP
                    System.out.println("  → POP()");
                    int valor = pila.pop();
                    System.out.println("  ✓ Retirado: " + valor + " (head avanza)");
                } else if (r < 0.9) {
                    // PEEK
                    System.out.println("  → PEEK()");
                    int valor = pila.peek();
                    System.out.println("  ✓ Tope: " + valor + " (sin retirar)");
                } else {
                    // CONTAINS
                    int valor = random.nextInt(100);
                    System.out.println("  → CONTAINS(" + valor + ")");
                    boolean encontrado = pila.contains(valor);
                    System.out.println("  ✓ " + (encontrado ? "ENCONTRADO" : "NO encontrado"));
                }
            } catch (IllegalStateException e) {
                System.out.println("  ✗ " + e.getMessage());
            }

            System.out.println();
            pila.imprimirEstado();
        }

        // Casos límite
        System.out.println("━━━ CASOS LÍMITE ━━━");
        System.out.println();

        // Vaciar
        System.out.println("  → Vaciar pila:");
        while (!pila.isEmpty()) {
            System.out.println("     POP() → " + pila.pop());
        }
        pila.imprimirEstado();

        // Pop en vacía
        System.out.println("  → POP() en vacía:");
        try { pila.pop(); } catch (IllegalStateException e) { System.out.println("     ✗ " + e.getMessage()); }

        // Peek en vacía
        System.out.println("  → PEEK() en vacía:");
        try { pila.peek(); } catch (IllegalStateException e) { System.out.println("     ✗ " + e.getMessage()); }

        // Contains en vacía
        System.out.println("  → CONTAINS(42) en vacía:");
        System.out.println("     Resultado: " + pila.contains(42));

        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}