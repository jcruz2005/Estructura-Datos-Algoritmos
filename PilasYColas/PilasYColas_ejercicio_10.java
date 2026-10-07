import java.util.Random;

/**
 * PilasYColas_ejercicio_10 - Cola circular (FIFO) usando arreglo estático y operador módulo %.
 *
 * <p>Cuando {@code front} o {@code rear} llegan al final del arreglo,
 * vuelven al índice 0 usando:</p>
 * <pre>
 *   (indice + 1) % capacidad
 * </pre>
 *
 * <p>Esto permite reutilizar las posiciones liberadas por dequeue.</p>
 *
 * <p>Estrategia para distinguir vacía/llena:</p>
 * <ul>
 *   <li>Vacía: {@code size == 0}</li>
 *   <li>Llena: {@code size == capacidad}</li>
 *   <li>Se mantiene variable {@code size} para evitar ambigüedad
 *       (cuando front == rear puede ser vacía o llena)</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class PilasYColas_ejercicio_10 {

    private final int[] elementos;
    private int front;      // Índice del primer elemento
    private int rear;       // Índice del último elemento
    private int size;       // Cantidad actual de elementos
    private final int capacidad;

    public PilasYColas_ejercicio_10(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad > 0");
        this.capacidad = capacidad;
        this.elementos = new int[capacidad];
        this.front = 0;
        this.rear = -1; // Cola vacía
        this.size = 0;
    }

    /**
     * Avanza un índice circularmente.
     */
    private int siguiente(int idx) {
        return (idx + 1) % capacidad;
    }

    /**
     * Retrocede un índice circularmente.
     */
    private int anterior(int idx) {
        return (idx - 1 + capacidad) % capacidad;
    }

    public void enqueue(int valor) {
        if (isFull()) {
            throw new IllegalStateException("Cola circular llena (size = " + size + ")");
        }
        rear = siguiente(rear);
        elementos[rear] = valor;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Cola circular vacía");
        int valor = elementos[front];
        front = siguiente(front);
        size--;
        return valor;
    }

    public int front() {
        if (isEmpty()) throw new IllegalStateException("Cola vacía");
        return elementos[front];
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == capacidad; }
    public int size() { return size; }

    /**
     * Imprime visualización completa mostrando circularidad.
     */
    public void imprimirVisualizacion() {
        System.out.println("\n  ┌─ COLA CIRCULAR (capacidad = " + capacidad + ") ─┐");
        System.out.println("  │ front = " + front + " | rear = " + rear + " | size = " + size + " | "
                + (isEmpty() ? "VACÍA" : isFull() ? "LLENA" : "Normal") + " │");

        // Índices
        System.out.print("  │ idx: ");
        for (int i = 0; i < capacidad; i++) {
            System.out.print(String.format("%2d ", i));
        }
        System.out.println("│");

        // Valores con marcadores de posición lógica
        System.out.print("  │ val: ");
        for (int i = 0; i < capacidad; i++) {
            if (isEmpty()) {
                System.out.print(" · ");
            } else if (estaEnCola(i)) {
                System.out.print(String.format("%2d ", elementos[i]));
            } else {
                System.out.print(" · "); // Libre (reutilizable)
            }
        }
        System.out.println("│");

        // Marcadores F y R
        System.out.print("  │      ");
        for (int i = 0; i < capacidad; i++) {
            String m = "  ";
            if (!isEmpty()) {
                if (i == front && i == rear) m = "FR";
                else if (i == front) m = "F ";
                else if (i == rear) m = "R ";
            }
            System.out.print(m + " ");
        }
        System.out.println("  │");

        // Flechas circulares
        System.out.print("  │      ");
        for (int i = 0; i < capacidad; i++) {
            if (!isEmpty() && i == rear && size > 1) {
                System.out.print("↻ ");
            } else if (!isEmpty() && i == anterior(front) && size > 1) {
                System.out.print("↺ ");
            } else {
                System.out.print("  ");
            }
        }
        System.out.println("  │");

        System.out.println("  │  · = libre/reutilizable  │");
        System.out.println("  └────────────────────────────┘");

        // Mostrar orden lógico
        if (!isEmpty()) {
            System.out.print("  Orden lógico (frente → final): [");
            for (int k = 0; k < size; k++) {
                int idx = (front + k) % capacidad;
                System.out.print(elementos[idx]);
                if (k < size - 1) System.out.print(" → ");
            }
            System.out.println("]");
        }
    }

    /**
     * Verifica si un índice físico está dentro de la cola lógica actual.
     */
    private boolean estaEnCola(int idxFisico) {
        if (isEmpty()) return false;
        if (front <= rear) {
            return idxFisico >= front && idxFisico <= rear;
        } else {
            // Cola envuelve el final del arreglo
            return idxFisico >= front || idxFisico <= rear;
        }
    }

    public void imprimirEstado() {
        System.out.println("  front=" + front + " rear=" + rear + " size=" + size + "/" + capacidad
                + " | " + (isEmpty() ? "VACÍA" : isFull() ? "LLENA" : "Normal"));
        if (!isEmpty()) {
            System.out.print("  Cola: [");
            for (int k = 0; k < size; k++) {
                int idx = (front + k) % capacidad;
                System.out.print(elementos[idx]);
                if (k < size - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    // =================================================================
    // MAIN - Demostración clara de la circularidad
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int CAPACIDAD = 8; // Pequeño para ver bien la circularidad
        PilasYColas_ejercicio_10 cola = new PilasYColas_ejercicio_10(CAPACIDAD);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 10 — COLA CIRCULAR (arreglo, módulo %, capacidad 8)            ║");
        System.out.println("║  rear vuelve al inicio | Reutiliza posiciones liberadas                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // PASO 1: Llenar hasta el final
        System.out.println("━━━ PASO 1: LLENAR HASTA QUE rear LLEGA AL FINAL ━━━");
        for (int i = 1; i <= CAPACIDAD; i++) {
            int v = 10 * i;
            cola.enqueue(v);
            System.out.println("  ENQUEUE(" + v + ") → rear=" + cola.rear + " (físico)");
            if (cola.rear == CAPACIDAD - 1) {
                System.out.println("  ⚡ rear llegó al final del arreglo (índice " + (CAPACIDAD - 1) + ")");
            }
        }
        cola.imprimirVisualizacion();

        // PASO 2: Dequeue para liberar posiciones al principio
        System.out.println("\n━━━ PASO 2: DEQUEUE - LIBERAR POSICIONES AL INICIO ━━━");
        for (int i = 0; i < 3; i++) {
            int v = cola.dequeue();
            System.out.println("  DEQUEUE() → " + v + " (front ahora = " + cola.front + ")");
        }
        cola.imprimirVisualizacion();
        System.out.println("  ⚡ Se liberaron índices 0, 1, 2 (ahora son reutilizables)");

        // PASO 3: Enqueue - rear vuelve al comienzo gracias a %
        System.out.println("\n━━━ PASO 3: ENQUEUE - rear VUELVE AL COMIENZO (módulo %) ━━━");
        for (int i = 1; i <= 3; i++) {
            int v = 100 + i;
            System.out.println("  ENQUEUE(" + v + ") → rear = (rear + 1) % " + CAPACIDAD + " = " + cola.siguiente(cola.rear));
            cola.enqueue(v);
        }
        cola.imprimirVisualizacion();
        System.out.println("  ⚡ rear volvió a 0, 1, 2 ¡Reutilizando posiciones liberadas!");

        // PASO 4: Más operaciones mixtas
        System.out.println("\n━━━ PASO 4: OPERACIONES MIXTAS ALEATORIAS ━━━");
        int ops = 10 + random.nextInt(6);
        for (int i = 1; i <= ops; i++) {
            System.out.println("\n  Operación #" + i + ":");
            boolean enq = random.nextDouble() < 0.55 || cola.isEmpty();

            if (enq && !cola.isFull()) {
                int v = 200 + random.nextInt(800);
                cola.enqueue(v);
                System.out.println("  ENQUEUE(" + v + ") → rear=" + cola.rear);
            } else if (!cola.isEmpty()) {
                int v = cola.dequeue();
                System.out.println("  DEQUEUE() → " + v + " (front=" + cola.front + ")");
            } else {
                System.out.println("  (cola llena o vacía, saltando...)");
            }
            cola.imprimirEstado();
        }

        cola.imprimirVisualizacion();

        System.out.println("\n━━━ DIFERENCIA CLAVE: COLA SIMPLE vs CIRCULAR ━━━");
        System.out.println();
        System.out.println("  COLA SIMPLE (Ej. 9):");
        System.out.println("    • front avanza, rear se queda en final");
        System.out.println("    • Espacios al inicio = DESPERDICIADOS para siempre");
        System.out.println("    • Falla enqueue aunque haya huecos");
        System.out.println();
        System.out.println("  COLA CIRCULAR (Ej. 10):");
        System.out.println("    • (índice + 1) % capacidad hace que índices 'den la vuelta'");
        System.out.println("    • rear vuelve a 0, 1, 2... reutilizando huecos");
        System.out.println("    • enqueue SIEMPRE funciona mientras size < capacidad");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}