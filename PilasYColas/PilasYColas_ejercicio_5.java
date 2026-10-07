import java.util.Random;

/**
 * PilasYColas_ejercicio_5 - Implementación genérica de una pila usando arreglo.
 *
 * <p>Utiliza genéricos de Java ({@code <T>}) para permitir almacenar
 * cualquier tipo de objeto sin crear clases separadas para cada tipo.</p>
 *
 * <p>Ventajas de los genéricos:</p>
 * <ul>
 *   <li>Reutilización de código: una sola implementación para Integer, String, etc.</li>
 *   <li>Seguridad de tipos en tiempo de compilación</li>
 *   <li>Evita casting manual</li>
 * </ul>
 *
 * <p>Principio: <b>LIFO (Last In, First Out)</b></p>
 *
 * @param <T> tipo de elementos que almacenará la pila
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class PilasYColas_ejercicio_5<T> {

    /** Arreglo interno (usamos Object[] y hacemos casting seguro) */
    @SuppressWarnings("unchecked")
    private final T[] elementos;

    /** Índice de la cima (-1 = vacía) */
    private int top;

    /** Capacidad máxima */
    private final int capacidad;

    /**
     * Crea una pila genérica con la capacidad indicada.
     *
     * @param capacidad máximo de elementos (debe ser > 0)
     */
    @SuppressWarnings("unchecked")
    public PilasYColas_ejercicio_5(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad > 0");
        this.capacidad = capacidad;
        this.elementos = (T[]) new Object[capacidad];
        this.top = -1;
    }

    /**
     * Inserta un elemento en la cima.
     *
     * @param valor elemento a insertar
     * @throws IllegalStateException si la pila está llena
     */
    public void push(T valor) {
        if (isFull()) {
            throw new IllegalStateException("Push fallido: pila llena (" + capacidad + ")");
        }
        elementos[++top] = valor;
    }

    /**
     * Retira y devuelve el elemento de la cima.
     *
     * @return elemento retirado
     * @throws IllegalStateException si la pila está vacía
     */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Pop fallido: pila vacía");
        }
        return elementos[top--];
    }

    /**
     * Devuelve el elemento de la cima sin retirarlo.
     *
     * @return elemento en la cima
     * @throws IllegalStateException si la pila está vacía
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Peek fallido: pila vacía");
        }
        return elementos[top];
    }

    /**
     * Verifica si la pila está vacía.
     *
     * @return {@code true} si no hay elementos
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Verifica si la pila está llena.
     *
     * @return {@code true} si alcanzó capacidad máxima
     */
    public boolean isFull() {
        return top == capacidad - 1;
    }

    /**
     * Cantidad actual de elementos.
     *
     * @return número de elementos
     */
    public int size() {
        return top + 1;
    }

    /**
     * Representación en cadena (base → cima).
     */
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(elementos[i]);
            if (i < top) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Imprime estado detallado.
     */
    public void imprimirEstado(String nombre) {
        System.out.println("  " + nombre + ": top=" + top + " | size=" + size() + "/" + capacidad
                + " | " + (isEmpty() ? "VACÍA" : isFull() ? "LLENA" : "Normal"));
        System.out.println("  Contenido: " + this);
        if (!isEmpty()) System.out.println("  Cima: " + elementos[top]);
    }

    // =================================================================
    // MAIN - Demostración con PilasYColas_ejercicio_5<Integer> y PilasYColas_ejercicio_5<String>
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int CAPACIDAD = 20;

        // PilasYColas_ejercicio_5 de enteros
        PilasYColas_ejercicio_5<Integer> pilaInt = new PilasYColas_ejercicio_5<>(CAPACIDAD);
        // PilasYColas_ejercicio_5 de strings
        PilasYColas_ejercicio_5<String> pilaStr = new PilasYColas_ejercicio_5<>(CAPACIDAD);

        String[] palabras = {
            "alpha", "beta", "gamma", "delta", "epsilon",
            "zeta", "eta", "theta", "iota", "kappa"
        };

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 5 — PILA GENÉRICA (PilasYColas_ejercicio_5<T>, capacidad 20)                      ║");
        System.out.println("║  Demuestra: PilasYColas_ejercicio_5<Integer> y PilasYColas_ejercicio_5<String> con la MISMA implementación     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ ESTADO INICIAL ━━━");
        pilaInt.imprimirEstado("PilasYColas_ejercicio_5<Integer>");
        System.out.println();
        pilaStr.imprimirEstado("PilasYColas_ejercicio_5<String>");
        System.out.println();

        int operaciones = 12 + random.nextInt(8); // 12-19

        for (int i = 1; i <= operaciones; i++) {
            System.out.println("━━━ OPERACIÓN #" + i + " ━━━");

            // Alternar entre pilas
            boolean usarInt = random.nextBoolean();

            // 65% push, 35% pop/peek
            double r = random.nextDouble();

            if (usarInt) {
                System.out.println("  → Operando en PilasYColas_ejercicio_5<Integer>");
                if (r < 0.65 || pilaInt.isEmpty()) {
                    int valor = random.nextInt(100);
                    System.out.println("    PUSH(" + valor + ")");
                    pilaInt.push(valor);
                    System.out.println("    ✓ Insertado: " + valor);
                } else if (r < 0.85) {
                    System.out.println("    POP()");
                    try {
                        int v = pilaInt.pop();
                        System.out.println("    ✓ Retirado: " + v);
                    } catch (IllegalStateException e) {
                        System.out.println("    ✗ " + e.getMessage());
                    }
                } else {
                    System.out.println("    PEEK()");
                    try {
                        System.out.println("    ✓ Cima: " + pilaInt.peek());
                    } catch (IllegalStateException e) {
                        System.out.println("    ✗ " + e.getMessage());
                    }
                }
                System.out.println();
                pilaInt.imprimirEstado("PilasYColas_ejercicio_5<Integer>");
            } else {
                System.out.println("  → Operando en PilasYColas_ejercicio_5<String>");
                if (r < 0.65 || pilaStr.isEmpty()) {
                    String valor = palabras[random.nextInt(palabras.length)] + random.nextInt(100);
                    System.out.println("    PUSH(\"" + valor + "\")");
                    pilaStr.push(valor);
                    System.out.println("    ✓ Insertado: " + valor);
                } else if (r < 0.85) {
                    System.out.println("    POP()");
                    try {
                        String v = pilaStr.pop();
                        System.out.println("    ✓ Retirado: " + v);
                    } catch (IllegalStateException e) {
                        System.out.println("    ✗ " + e.getMessage());
                    }
                } else {
                    System.out.println("    PEEK()");
                    try {
                        System.out.println("    ✓ Cima: " + pilaStr.peek());
                    } catch (IllegalStateException e) {
                        System.out.println("    ✗ " + e.getMessage());
                    }
                }
                System.out.println();
                pilaStr.imprimirEstado("PilasYColas_ejercicio_5<String>");
            }
            System.out.println();
        }

        // Demostrar que son la misma clase
        System.out.println("━━━ VENTAJA DE GENÉRICOS ━━━");
        System.out.println();
        System.out.println("  Una sola clase PilasYColas_ejercicio_5<T> sirve para:");
        System.out.println("    • PilasYColas_ejercicio_5<Integer>  → números");
        System.out.println("    • PilasYColas_ejercicio_5<String>   → textos");
        System.out.println("    • PilasYColas_ejercicio_5<Double>   → decimales");
        System.out.println("    • PilasYColas_ejercicio_5<Jugador>  → objetos propios");
        System.out.println("    • Cualquier tipo T");
        System.out.println();
        System.out.println("  Sin genéricos, necesitaríamos:");
        System.out.println("    • PilaEnteros, PilaStrings, PilaDoubles, PilaJugadores...");
        System.out.println("    • Código duplicado y propenso a errores");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}