import java.util.Random;

/**
 * PilasYColas_ejercicio_9 - Cola simple (NO circular) que DEMUESTRA
 * el problema del desperdicio de espacio.
 *
 * <p>Cuando se hace dequeue, {@code front} avanza pero las posiciones
 * liberadas al inicio NO se reutilizan. Eventualmente {@code rear}
 * llega al final y no se puede hacer más enqueue, aunque haya espacios
 * libres al principio.</p>
 *
 * <p>Arreglo pequeño (5-10 elementos) para visualizar claramente el problema.</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class PilasYColas_ejercicio_9 {

    private final int[] elementos;
    private int front;
    private int rear;
    private final int capacidad;

    public PilasYColas_ejercicio_9(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad > 0");
        this.capacidad = capacidad;
        this.elementos = new int[capacidad];
        this.front = 0;
        this.rear = -1;
    }

    public void enqueue(int valor) {
        if (isFull()) {
            throw new IllegalStateException("DESPERDICIO: rear=" + rear + " (final), front=" + front
                    + " → hay " + front + " posiciones libres al inicio PERO no se pueden usar");
        }
        rear++;
        elementos[rear] = valor;
    }

    public int dequeue() {
        if (isEmpty()) throw new IllegalStateException("Cola vacía");
        int v = elementos[front];
        front++;
        return v;
    }

    public int front() {
        if (isEmpty()) throw new IllegalStateException("Cola vacía");
        return elementos[front];
    }

    public boolean isEmpty() { return front > rear; }
    public boolean isFull() { return rear == capacidad - 1; }
    public int size() { return isEmpty() ? 0 : rear - front + 1; }

    /**
     * Imprime visualización completa del arreglo con marcadores.
     */
    public void imprimirVisualizacion() {
        System.out.println("\n  ┌─ VISUALIZACIÓN DEL ARREGLO ─┐");
        System.out.println("  │ capacidad = " + capacidad + " | front = " + front + " | rear = " + rear + " | size = " + size() + " │");

        // Línea de índices
        System.out.print("  │ idx: ");
        for (int i = 0; i < capacidad; i++) {
            System.out.print(String.format("%2d ", i));
        }
        System.out.println("│");

        // Línea de valores
        System.out.print("  │ val: ");
        for (int i = 0; i < capacidad; i++) {
            if (isEmpty()) {
                System.out.print(" · ");
            } else if (i < front) {
                System.out.print(" ░ "); // Liberado (desperdicio) - carácter de sombreado
            } else if (i <= rear) {
                System.out.print(String.format("%2d ", elementos[i])); // Ocupado
            } else {
                System.out.print(" · "); // Libre al final
            }
        }
        System.out.println("│");

        // Marcadores
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

        // Leyenda
        System.out.println("  ├──────────────────────────────┤");
        System.out.println("  │  ░ = LIBERADO (desperdicio)  │");
        System.out.println("  │  · = NUNCA USADO / LIBRE     │");
        System.out.println("  │  números = ELEMENTOS VÁLIDOS │");
        System.out.println("  └──────────────────────────────┘");
    }

    /**
     * Imprime estado compacto.
     */
    public void imprimirEstado() {
        System.out.println("  front=" + front + " rear=" + rear + " size=" + size() + "/" + capacidad
                + " | " + (isEmpty() ? "VACÍA" : isFull() ? "LLENA (¡DESPERDICIO!)" : "Normal"));
        if (!isEmpty()) System.out.println("  Contenido cola: " + this);
    }

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

    // =================================================================
    // MAIN - Forzamos la situación de desperdicio
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int CAPACIDAD = 8; // Pequeño para ver el problema rápido
        PilasYColas_ejercicio_9 cola = new PilasYColas_ejercicio_9(CAPACIDAD);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 9 — DESPERDICIO DE ESPACIO EN COLA SIMPLE (capacidad 8)        ║");
        System.out.println("║  NO circular | front avanza | posiciones al inicio NO se reutilizan       ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ LLENANDO LA COLA HASTA EL FINAL ━━━");
        for (int i = 1; i <= CAPACIDAD; i++) {
            int v = 10 * i;
            cola.enqueue(v);
            System.out.println("  ENQUEUE(" + v + ") → rear=" + cola.rear);
        }
        cola.imprimirVisualizacion();
        cola.imprimirEstado();

        System.out.println("\n━━━ INTENTAR ENQUEUE MÁS (debe fallar) ━━━");
        try {
            cola.enqueue(999);
        } catch (IllegalStateException e) {
            System.out.println("  ✗ " + e.getMessage());
        }

        System.out.println("\n━━━ DEQUEUE PARA LIBERAR ESPACIO AL INICIO ━━━");
        for (int i = 0; i < 3; i++) {
            int v = cola.dequeue();
            System.out.println("  DEQUEUE() → " + v + " (front ahora = " + cola.front + ")");
        }
        cola.imprimirVisualizacion();
        cola.imprimirEstado();

        System.out.println("\n━━━ INTENTAR ENQUEUE DE NUEVO (debe fallar - ¡DESPERDICIO!) ━━━");
        System.out.println("  Hay " + cola.front + " posiciones libres al inicio (índices 0-" + (cola.front - 1) + ")");
        System.out.println("  Pero rear = " + cola.rear + " (final del arreglo)");
        System.out.println("  No se puede encolar aunque hay espacio...");
        try {
            cola.enqueue(888);
        } catch (IllegalStateException e) {
            System.out.println("  ✗ " + e.getMessage());
        }

        System.out.println("\n━━━ VACIAR Y LLENAR DE NUEVO (mismo problema) ━━━");
        while (!cola.isEmpty()) cola.dequeue();
        System.out.println("  Cola vaciada. front=" + cola.front + ", rear=" + cola.rear);
        cola.imprimirVisualizacion();

        System.out.println("\n  → Llenar de nuevo...");
        for (int i = 1; i <= CAPACIDAD; i++) {
            cola.enqueue(100 + i);
        }
        cola.imprimirVisualizacion();

        System.out.println("\n  → 3 DEQUEUE...");
        for (int i = 0; i < 3; i++) cola.dequeue();
        cola.imprimirVisualizacion();

        System.out.println("\n  → Intentar ENQUEUE (fallará de nuevo)...");
        try { cola.enqueue(777); } catch (IllegalStateException e) { System.out.println("  ✗ " + e.getMessage()); }

        System.out.println("\n━━━ CONCLUSIÓN ━━━");
        System.out.println();
        System.out.println("  El problema: front avanza, rear llega al final,");
        System.out.println("  posiciones 0..(front-1) quedan INUTILIZABLES.");
        System.out.println("  Solución: COLA CIRCULAR (ver Ejercicio 10) usando módulo %.");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}