import java.util.Random;

/**
 * PilasYColas_ejercicio_1 - Implementación de una pila de enteros usando arreglo estático.
 *
 * <p>Utiliza un arreglo {@code int[]} de tamaño fijo y una variable {@code top}
 * para indicar la posición del elemento en la cima.</p>
 *
 * <p>Operaciones principales: {@code push}, {@code pop}, {@code peek},
 * {@code isEmpty}, {@code isFull}, {@code size}.</p>
 *
 * <p>Principio: <b>LIFO (Last In, First Out)</b> - El último en entrar es el primero en salir.</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class PilasYColas_ejercicio_1 {

    /** Arreglo interno que almacena los elementos */
    private final int[] elementos;

    /** Índice del elemento en la cima (-1 = pila vacía) */
    private int top;

    /** Capacidad máxima de la pila */
    private final int capacidad;

    /**
     * Crea una pila con la capacidad especificada.
     *
     * @param capacidad tamaño máximo de la pila (debe ser > 0)
     * @throws IllegalArgumentException si capacidad <= 0
     */
    public PilasYColas_ejercicio_1(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.capacidad = capacidad;
        this.elementos = new int[capacidad];
        this.top = -1; // Pila vacía: top = -1
    }

    /**
     * Inserta un elemento en la cima de la pila.
     *
     * @param valor entero a insertar
     * @throws IllegalStateException si la pila está llena
     */
    public void push(int valor) {
        if (isFull()) {
            throw new IllegalStateException("Push fallido: pila llena (capacidad = " + capacidad + ")");
        }
        top++;
        elementos[top] = valor;
    }

    /**
     * Retira y devuelve el elemento de la cima.
     *
     * @return elemento que estaba en la cima
     * @throws IllegalStateException si la pila está vacía
     */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Pop fallido: pila vacía");
        }
        int valor = elementos[top];
        top--;
        return valor;
    }

    /**
     * Devuelve el elemento de la cima sin retirarlo.
     *
     * @return elemento en la cima
     * @throws IllegalStateException si la pila está vacía
     */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Peek fallido: pila vacía");
        }
        return elementos[top];
    }

    /**
     * Verifica si la pila está vacía.
     *
     * @return {@code true} si no hay elementos, {@code false} en caso contrario
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Verifica si la pila está llena.
     *
     * @return {@code true} si alcanzó la capacidad máxima, {@code false} en caso contrario
     */
    public boolean isFull() {
        return top == capacidad - 1;
    }

    /**
     * Devuelve la cantidad actual de elementos en la pila.
     *
     * @return número de elementos (0 a capacidad)
     */
    public int size() {
        return top + 1;
    }

    /**
     * Devuelve una representación en cadena del contenido de la pila
     * (de base a cima).
     *
     * @return cadena con los elementos
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(elementos[i]);
            if (i < top) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Imprime el estado detallado de la pila.
     */
    public void imprimirEstado() {
        System.out.println("  top = " + top + " | size = " + size() + " | capacidad = " + capacidad);
        System.out.println("  Contenido (base -> cima): " + this);
        if (!isEmpty()) {
            System.out.println("  Cima (peek): " + elementos[top]);
        }
        System.out.println("  Estado: " + (isEmpty() ? "VACÍA" : isFull() ? "LLENA" : "NORMAL"));
    }

    // =================================================================
    // MAIN - Demostración automática
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int CAPACIDAD = 20;
        PilasYColas_ejercicio_1 pila = new PilasYColas_ejercicio_1(CAPACIDAD);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 1 — PILA DE ENTEROS (arreglo estático, capacidad 20)           ║");
        System.out.println("║  Principio: LIFO (Last In, First Out)                                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ ESTADO INICIAL ━━━");
        pila.imprimirEstado();
        System.out.println();

        // Secuencia de operaciones aleatorias
        int operaciones = 15 + random.nextInt(10); // 15-24 operaciones

        for (int i = 1; i <= operaciones; i++) {
            System.out.println("━━━ OPERACIÓN #" + i + " ━━━");

            // Decidir operación: 60% push, 30% pop, 10% peek
            double r = random.nextDouble();

            try {
                if (r < 0.6 || pila.isEmpty()) {
                    // PUSH
                    int valor = random.nextInt(100); // 0-99
                    System.out.println("  → PUSH(" + valor + ")");
                    pila.push(valor);
                    System.out.println("  ✓ Insertado: " + valor);
                } else if (r < 0.9) {
                    // POP
                    System.out.println("  → POP()");
                    int valor = pila.pop();
                    System.out.println("  ✓ Retirado: " + valor);
                } else {
                    // PEEK
                    System.out.println("  → PEEK()");
                    int valor = pila.peek();
                    System.out.println("  ✓ Cima: " + valor + " (sin retirar)");
                }
            } catch (IllegalStateException e) {
                System.out.println("  ✗ " + e.getMessage());
            }

            System.out.println();
            pila.imprimirEstado();
            System.out.println();
        }

        // Pruebas de casos límite
        System.out.println("━━━ PRUEBAS DE CASOS LÍMITE ━━━");
        System.out.println();

        // Vaciar la pila
        System.out.println("  → Vaciar pila completamente:");
        while (!pila.isEmpty()) {
            System.out.println("     POP() → " + pila.pop());
        }
        pila.imprimirEstado();

        // Pop en vacía
        System.out.println("  → POP() en pila vacía:");
        try {
            pila.pop();
        } catch (IllegalStateException e) {
            System.out.println("     ✗ " + e.getMessage());
        }

        // Peek en vacía
        System.out.println("  → PEEK() en pila vacía:");
        try {
            pila.peek();
        } catch (IllegalStateException e) {
            System.out.println("     ✗ " + e.getMessage());
        }

        // Llenar la pila
        System.out.println("  → Llenar pila hasta capacidad (" + CAPACIDAD + "):");
        for (int i = 0; i < CAPACIDAD; i++) {
            pila.push(i * 10);
        }
        pila.imprimirEstado();

        // Push en llena
        System.out.println("  → PUSH(999) en pila llena:");
        try {
            pila.push(999);
        } catch (IllegalStateException e) {
            System.out.println("     ✗ " + e.getMessage());
        }

        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}