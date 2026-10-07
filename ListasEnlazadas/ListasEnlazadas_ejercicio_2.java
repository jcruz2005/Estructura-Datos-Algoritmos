import java.util.Random;

/**
 * ListasEnlazadas_ejercicio_2 - Cola de enteros usando lista enlazada simple.
 *
 * <p>Mantiene dos referencias:</p>
 * <ul>
 *   <li>{@code head}: frente de la cola (primer elemento, para dequeue O(1))</li>
 *   <li>{@code tail}: final de la cola (último elemento, para enqueue O(1))</li>
 * </ul>
 *
 * <p>¿Por qué mantener ambos?</p>
 * <ul>
 *   <li>Con solo {@code head}, {@code enqueue} requeriría recorrer toda la lista O(n)</li>
 *   <li>Con {@code tail}, {@code enqueue} es O(1): tail.siguiente = nuevo; tail = nuevo</li>
 *   <li>{@code dequeue} siempre usa {@code head}: head = head.siguiente O(1)</li>
 * </ul>
 *
 * <p>Complejidad:</p>
 * <ul>
 *   <li>{@code enqueue}: O(1)</li>
 *   <li>{@code dequeue}: O(1)</li>
 *   <li>{@code front}: O(1)</li>
 *   <li>{@code contains}: O(n)</li>
 *   <li>{@code imprimir}: O(n)</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ListasEnlazadas_ejercicio_2 {

    private static class Nodo {
        int valor;
        Nodo siguiente;

        Nodo(int valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    /** Frente de la cola (primer elemento insertado) */
    private Nodo head;

    /** Final de la cola (último elemento insertado) */
    private Nodo tail;

    private int size;
    private final int capacidadMaxima;

    public ListasEnlazadas_ejercicio_2(int capacidadMaxima) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.capacidadMaxima = capacidadMaxima;
    }

    /**
     * Agrega un elemento al final de la cola.
     * Si la cola está vacía, head y tail apuntan al nuevo nodo.
     * Si no, tail.siguiente = nuevo; tail = nuevo.
     */
    public void enqueue(int valor) {
        if (size >= capacidadMaxima) {
            throw new IllegalStateException("Enqueue fallido: límite de " + capacidadMaxima + " nodos");
        }
        Nodo nuevo = new Nodo(valor);
        if (isEmpty()) {
            head = tail = nuevo;
        } else {
            tail.siguiente = nuevo;
            tail = nuevo;
        }
        size++;
    }

    /**
     * Retira y devuelve el elemento del frente.
     * head avanza al siguiente nodo.
     * Si queda vacía, tail también se pone en null.
     */
    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Dequeue fallido: cola vacía");
        int valor = head.valor;
        head = head.siguiente;
        size--;
        if (head == null) tail = null; // Cola quedó vacía
        return valor;
    }

    /** Devuelve el elemento del frente sin retirarlo. */
    public int front() {
        if (isEmpty()) throw new IllegalStateException("Front fallido: cola vacía");
        return head.valor;
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    /** Busca un valor recorriendo desde head. O(n) */
    public boolean contains(int valor) {
        Nodo actual = head;
        while (actual != null) {
            if (actual.valor == valor) return true;
            actual = actual.siguiente;
        }
        return false;
    }

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
        sb.append("]  (frente → final)");
        return sb.toString();
    }

    public void imprimirEstado() {
        System.out.println("  head = " + (head != null ? head.valor : "null")
                + " | tail = " + (tail != null ? tail.valor : "null")
                + " | size = " + size + "/" + capacidadMaxima
                + " | " + (isEmpty() ? "VACÍA" : "Normal"));
        System.out.println("  Cola (frente → final): " + this);
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int MAX_NODOS = 20;
        ListasEnlazadas_ejercicio_2 cola = new ListasEnlazadas_ejercicio_2(MAX_NODOS);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 2 — COLA ENLAZADA DE ENTEROS (head + tail, máx 20 nodos)       ║");
        System.out.println("║  head=frente O(1) | tail=final O(1) | sin tail enqueue sería O(n)         ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ ESTADO INICIAL ━━━");
        cola.imprimirEstado();
        System.out.println();

        int operaciones = 15 + random.nextInt(10);

        for (int i = 1; i <= operaciones; i++) {
            System.out.println("━━━ OPERACIÓN #" + i + " ━━━");

            double r = random.nextDouble();

            try {
                if (r < 0.55 || cola.isEmpty()) {
                    // ENQUEUE
                    int valor = random.nextInt(100);
                    System.out.println("  → ENQUEUE(" + valor + ")");
                    cola.enqueue(valor);
                    System.out.println("  ✓ Encolado: " + valor + " (nuevo tail)");
                } else if (r < 0.85) {
                    // DEQUEUE
                    System.out.println("  → DEQUEUE()");
                    int valor = cola.dequeue();
                    System.out.println("  ✓ Desencolado: " + valor + " (head avanza)");
                } else if (r < 0.95) {
                    // FRONT
                    System.out.println("  → FRONT()");
                    System.out.println("  ✓ Frente: " + cola.front());
                } else {
                    // CONTAINS
                    int valor = random.nextInt(100);
                    System.out.println("  → CONTAINS(" + valor + ")");
                    System.out.println("  ✓ " + (cola.contains(valor) ? "ENCONTRADO" : "NO encontrado"));
                }
            } catch (IllegalStateException e) {
                System.out.println("  ✗ " + e.getMessage());
            }

            System.out.println();
            cola.imprimirEstado();
        }

        // Casos límite
        System.out.println("━━━ CASOS LÍMITE ━━━");
        System.out.println();

        System.out.println("  → Vaciar cola:");
        while (!cola.isEmpty()) {
            System.out.println("     DEQUEUE() → " + cola.dequeue());
        }
        cola.imprimirEstado();

        System.out.println("  → DEQUEUE en vacía:");
        try { cola.dequeue(); } catch (IllegalStateException e) { System.out.println("     ✗ " + e.getMessage()); }

        System.out.println("  → FRONT en vacía:");
        try { cola.front(); } catch (IllegalStateException e) { System.out.println("     ✗ " + e.getMessage()); }

        System.out.println("  → CONTAINS en vacía: " + cola.contains(99));

        // Un solo elemento
        System.out.println("\n  → ENQUEUE(1) (un solo elemento):");
        cola.enqueue(1);
        cola.imprimirEstado();

        System.out.println("  → DEQUEUE (elimina el único):");
        cola.dequeue();
        cola.imprimirEstado();
        System.out.println("  ✓ head y tail ambos null tras vaciar");

        System.out.println("\n═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}