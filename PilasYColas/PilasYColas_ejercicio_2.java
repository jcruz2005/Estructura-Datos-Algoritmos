import java.util.Random;

/**
 * PilasYColas_ejercicio_2 - Simulador de una torre de platos usando una pila (arreglo estático).
 *
 * <p>Cada plato se identifica con un número entero. La torre sigue el principio
 * <b>LIFO (Last In, First Out)</b>: el último plato puesto es el primero en sacarse.</p>
 *
 * <p>Operaciones: agregar plato (push), retirar plato superior (pop),
 * consultar plato superior (peek), verificar si está vacía/llena.</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class PilasYColas_ejercicio_2 {

    /** Arreglo que almacena los IDs de los platos */
    private final int[] platos;

    /** Índice del plato en la cima (-1 = torre vacía) */
    private int top;

    /** Capacidad máxima de la torre */
    private final int capacidad;

    /**
     * Crea una torre de platos con la capacidad indicada.
     *
     * @param capacidad máximo número de platos (debe ser > 0)
     */
    public PilasYColas_ejercicio_2(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.capacidad = capacidad;
        this.platos = new int[capacidad];
        this.top = -1;
    }

    /**
     * Agrega un plato sobre la torre.
     *
     * @param idPlato identificador del plato
     * @throws IllegalStateException si la torre está llena
     */
    public void agregarPlato(int idPlato) {
        if (estaLlena()) {
            throw new IllegalStateException("No se puede agregar: torre llena (" + capacidad + " platos)");
        }
        top++;
        platos[top] = idPlato;
    }

    /**
     * Retira el plato superior de la torre.
     *
     * @return ID del plato retirado
     * @throws IllegalStateException si la torre está vacía
     */
    public int retirarPlato() {
        if (estaVacia()) {
            throw new IllegalStateException("No se puede retirar: torre vacía");
        }
        int id = platos[top];
        top--;
        return id;
    }

    /**
     * Consulta cuál es el plato superior sin retirarlo.
     *
     * @return ID del plato en la cima
     * @throws IllegalStateException si la torre está vacía
     */
    public int platoSuperior() {
        if (estaVacia()) {
            throw new IllegalStateException("No hay plato superior: torre vacía");
        }
        return platos[top];
    }

    /**
     * Verifica si la torre está vacía.
     *
     * @return {@code true} si no hay platos
     */
    public boolean estaVacia() {
        return top == -1;
    }

    /**
     * Verifica si la torre está llena.
     *
     * @return {@code true} si alcanzó la capacidad máxima
     */
    public boolean estaLlena() {
        return top == capacidad - 1;
    }

    /**
     * Devuelve la cantidad actual de platos.
     *
     * @return número de platos en la torre
     */
    public int cantidadPlatos() {
        return top + 1;
    }

    /**
     * Representación visual de la torre (base abajo, cima arriba).
     *
     * @return cadena con la torre
     */
    @Override
    public String toString() {
        if (estaVacia()) {
            return "  (torre vacía)";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = top; i >= 0; i--) {
            sb.append("  ┌─────────┐\n");
            sb.append("  │ Plato ").append(String.format("%3d", platos[i])).append(" │ ← ");
            if (i == top) sb.append("CIMA");
            else if (i == 0) sb.append("BASE");
            else sb.append("     ");
            sb.append("\n");
            sb.append("  └─────────┘\n");
        }
        return sb.toString();
    }

    /**
     * Imprime estado compacto en una línea.
     */
    public void imprimirEstadoCompacto() {
        System.out.print("  Estado: ");
        if (estaVacia()) {
            System.out.print("[VACÍA]");
        } else if (estaLlena()) {
            System.out.print("[LLENA] ");
        } else {
            System.out.print("[Normal]");
        }
        System.out.print(" | Platos: " + cantidadPlatos() + "/" + capacidad);
        if (!estaVacia()) {
            System.out.print(" | Superior: #" + platos[top]);
        }
        System.out.println();
        System.out.print("  Pila: [");
        for (int i = 0; i <= top; i++) {
            System.out.print("#" + platos[i]);
            if (i < top) System.out.print(", ");
        }
        System.out.println("]  (base → cima)");
    }

    // =================================================================
    // MAIN - Demostración automática
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int CAPACIDAD = 20;
        PilasYColas_ejercicio_2 torre = new PilasYColas_ejercicio_2(CAPACIDAD);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 2 — SIMULADOR DE TORRE DE PLATOS (pila, capacidad 20)          ║");
        System.out.println("║  Principio: LIFO — El último plato puesto es el primero en sacarse        ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ TORRE INICIAL ━━━");
        torre.imprimirEstadoCompacto();
        System.out.println(torre);
        System.out.println();

        int operaciones = 12 + random.nextInt(8); // 12-19 operaciones

        for (int i = 1; i <= operaciones; i++) {
            System.out.println("━━━ OPERACIÓN #" + i + " ━━━");

            // 65% agregar, 35% retirar (pero no retirar si vacía)
            boolean agregar = random.nextDouble() < 0.65 || torre.estaVacia();

            if (agregar) {
                int idPlato = 100 + random.nextInt(900); // 100-999
                System.out.println("  → AGREGAR plato #" + idPlato);
                try {
                    torre.agregarPlato(idPlato);
                    System.out.println("  ✓ Plato #" + idPlato + " colocado en la cima");
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            } else {
                System.out.println("  → RETIRAR plato superior");
                try {
                    int id = torre.retirarPlato();
                    System.out.println("  ✓ Plato #" + id + " retirado de la cima");
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            }

            // Mostrar plato superior si existe
            if (!torre.estaVacia()) {
                System.out.println("  → Plato superior actual: #" + torre.platoSuperior());
            }

            System.out.println();
            torre.imprimirEstadoCompacto();
            System.out.println(torre);
        }

        // Demostración de por qué pila y no cola
        System.out.println("━━━ ¿POR QUÉ PILA Y NO COLA? ━━━");
        System.out.println();
        System.out.println("  En una torre de platos:");
        System.out.println("  • Solo se puede acceder al plato de ARRIBA (cima)");
        System.out.println("  • Para sacar el de abajo, hay que sacar todos los de arriba");
        System.out.println("  • Esto es exactamente LIFO: Last In, First Out");
        System.out.println();
        System.out.println("  Una COLA (FIFO) sacaría el plato de ABAJO (base),");
        System.out.println("  lo cual es físicamente imposible sin derrumbar la torre.");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}