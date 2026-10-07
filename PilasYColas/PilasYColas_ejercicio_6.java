import java.util.Random;

/**
 * PilasYColas_ejercicio_6 - Cola simple (FIFO) de enteros usando arreglo estático.
 *
 * <p>Utiliza dos índices:</p>
 * <ul>
 *   <li>{@code front}: posición del primer elemento (frente)</li>
 *   <li>{@code rear}: posición del último elemento (final)</li>
 * </ul>
 *
 * <p>Principio: <b>FIFO (First In, First Out)</b> — el primero en entrar
 * es el primero en salir.</p>
 *
 * <p>⚠️ Esta implementación NO es circular: al hacer dequeue, {@code front}
 * avanza y las posiciones liberadas al inicio NO se reutilizan.
 * Esto provoca desperdicio de espacio (ver Ejercicio 9).</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class PilasYColas_ejercicio_6 {

    /** Arreglo interno */
    private final int[] elementos;

    /** Índice del frente (primer elemento) */
    private int front;

    /** Índice del final (último elemento) */
    private int rear;

    /** Capacidad máxima */
    private final int capacidad;

    /**
     * Crea una cola con la capacidad indicada.
     *
     * @param capacidad máximo de elementos (> 0)
     */
    public PilasYColas_ejercicio_6(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad > 0");
        this.capacidad = capacidad;
        this.elementos = new int[capacidad];
        this.front = 0;
        this.rear = -1; // Cola vacía: rear = -1
    }

    /**
     * Agrega un elemento al final de la cola.
     *
     * @param valor entero a encolar
     * @throws IllegalStateException si la cola está llena
     */
    public void enqueue(int valor) {
        if (isFull()) {
            throw new IllegalStateException("Enqueue fallido: cola llena (rear = " + rear + ", capacidad = " + capacidad + ")");
        }
        rear++;
        elementos[rear] = valor;
    }

    /**
     * Retira y devuelve el elemento del frente.
     *
     * @return elemento desencolado
     * @throws IllegalStateException si la cola está vacía
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Dequeue fallido: cola vacía");
        }
        int valor = elementos[front];
        front++;
        return valor;
    }

    /**
     * Devuelve el elemento del frente sin retirarlo.
     *
     * @return elemento en el frente
     * @throws IllegalStateException si la cola está vacía
     */
    public int front() {
        if (isEmpty()) {
            throw new IllegalStateException("Front fallido: cola vacía");
        }
        return elementos[front];
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return {@code true} si no hay elementos
     */
    public boolean isEmpty() {
        return front > rear;
    }

    /**
     * Verifica si la cola está llena.
     *
     * @return {@code true} si rear llegó al final del arreglo
     */
    public boolean isFull() {
        return rear == capacidad - 1;
    }

    /**
     * Cantidad actual de elementos.
     *
     * @return número de elementos en la cola
     */
    public int size() {
        return isEmpty() ? 0 : rear - front + 1;
    }

    /**
     * Representación visual del contenido actual (frente → final).
     */
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = front; i <= rear; i++) {
            sb.append(elementos[i]);
            if (i < rear) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Imprime estado detallado con front, rear y arreglo completo.
     */
    public void imprimirEstado() {
        System.out.println("  front = " + front + " | rear = " + rear + " | size = " + size() + "/" + capacidad);
        System.out.println("  Cola (frente → final): " + this);

        // Mostrar arreglo completo con marcadores
        System.out.print("  Arreglo completo: [");
        for (int i = 0; i < capacidad; i++) {
            if (isEmpty()) {
                System.out.print("·");
            } else if (i < front) {
                System.out.print("·"); // Liberado (desperdicio)
            } else if (i <= rear) {
                System.out.print(elementos[i]); // Ocupado
            } else {
                System.out.print("·"); // Libre al final
            }
            if (i < capacidad - 1) System.out.print(" ");
        }
        System.out.println("]");
        System.out.println("  Índices:       " + generarIndiceVisual());
        System.out.println("  Estado: " + (isEmpty() ? "VACÍA" : isFull() ? "LLENA" : "Normal"));
    }

    private String generarIndiceVisual() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacidad; i++) {
            if (i == front && i == rear && !isEmpty()) sb.append("FR");
            else if (i == front) sb.append("F ");
            else if (i == rear) sb.append("R ");
            else sb.append("  ");
            if (i < capacidad - 1) sb.append(" ");
        }
        return sb.toString();
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int CAPACIDAD = 20;
        PilasYColas_ejercicio_6 cola = new PilasYColas_ejercicio_6(CAPACIDAD);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 6 — COLA SIMPLE DE ENTEROS (arreglo, FIFO, capacidad 20)       ║");
        System.out.println("║  front/rear | NO circular | Desperdicia espacio al hacer dequeue          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ ESTADO INICIAL ━━━");
        cola.imprimirEstado();
        System.out.println();

        int operaciones = 15 + random.nextInt(10); // 15-24

        for (int i = 1; i <= operaciones; i++) {
            System.out.println("━━━ OPERACIÓN #" + i + " ━━━");

            // 60% enqueue, 40% dequeue
            boolean encolar = random.nextDouble() < 0.6 || cola.isEmpty();

            if (encolar) {
                int valor = random.nextInt(100);
                System.out.println("  → ENQUEUE(" + valor + ")");
                try {
                    cola.enqueue(valor);
                    System.out.println("  ✓ Encolado: " + valor + " (rear = " + cola.rear + ")");
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            } else {
                System.out.println("  → DEQUEUE()");
                try {
                    int valor = cola.dequeue();
                    System.out.println("  ✓ Desencolado: " + valor + " (front = " + cola.front + ")");
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            }

            // Mostrar front si no vacía
            if (!cola.isEmpty()) {
                System.out.println("  → Frente actual (front): " + cola.front());
            }

            System.out.println();
            cola.imprimirEstado();
        }

        // Casos límite
        System.out.println("━━━ CASOS LÍMITE ━━━");
        System.out.println();

        // Vaciar
        System.out.println("  → Vaciar cola:");
        while (!cola.isEmpty()) {
            System.out.println("     DEQUEUE() → " + cola.dequeue());
        }
        cola.imprimirEstado();

        // Dequeue en vacía
        System.out.println("  → DEQUEUE() en vacía:");
        try { cola.dequeue(); } catch (IllegalStateException e) { System.out.println("     ✗ " + e.getMessage()); }

        // Front en vacía
        System.out.println("  → FRONT() en vacía:");
        try { cola.front(); } catch (IllegalStateException e) { System.out.println("     ✗ " + e.getMessage()); }

        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}