import java.util.Random;

/**
 * PilasYColas_ejercicio_7 - Simulador de atención en oficina usando cola (FIFO).
 *
 * <p>Cada persona recibe un número de turno al llegar. Se atiende
 * en orden de llegada: <b>FIFO (First In, First Out)</b>.</p>
 *
 * <p>Operaciones: agregar persona (enqueue), atender siguiente (dequeue),
 * consultar próximo (front), verificar si hay espera.</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class PilasYColas_ejercicio_7 {

    /** Arreglo con números de turno */
    private final int[] turnos;

    /** Frente de la cola */
    private int front;

    /** Final de la cola */
    private int rear;

    /** Capacidad máxima */
    private final int capacidad;

    /** Contador para asignar números de turno únicos */
    private int proximoTurno;

    /**
     * Crea el sistema de turnos.
     *
     * @param capacidad máximo de personas esperando
     */
    public PilasYColas_ejercicio_7(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad > 0");
        this.capacidad = capacidad;
        this.turnos = new int[capacidad];
        this.front = 0;
        this.rear = -1;
        this.proximoTurno = 1;
    }

    /**
     * Agrega una persona y le asigna un turno.
     *
     * @return número de turno asignado
     * @throws IllegalStateException si la cola está llena
     */
    public int agregarPersona() {
        if (estaLlena()) {
            throw new IllegalStateException("Sala de espera llena (" + capacidad + " personas)");
        }
        rear++;
        turnos[rear] = proximoTurno;
        int turnoAsignado = proximoTurno;
        proximoTurno++;
        return turnoAsignado;
    }

    /**
     * Atiende a la siguiente persona (la que lleva más tiempo esperando).
     *
     * @return número de turno atendido
     * @throws IllegalStateException si no hay nadie esperando
     */
    public int atenderSiguiente() {
        if (estaVacia()) {
            throw new IllegalStateException("No hay personas esperando");
        }
        int turno = turnos[front];
        front++;
        return turno;
    }

    /**
     * Consulta quién es el próximo a ser atendido.
     *
     * @return número de turno del primero en la cola
     * @throws IllegalStateException si cola vacía
     */
    public int proximo() {
        if (estaVacia()) {
            throw new IllegalStateException("Nadie esperando");
        }
        return turnos[front];
    }

    /**
     * Verifica si hay personas esperando.
     */
    public boolean hayEspera() {
        return !estaVacia();
    }

    /**
     * Verifica si la cola está vacía.
     */
    public boolean estaVacia() {
        return front > rear;
    }

    /**
     * Verifica si la sala de espera está llena.
     */
    public boolean estaLlena() {
        return rear == capacidad - 1;
    }

    /**
     * Cantidad de personas esperando.
     */
    public int cantidadEsperando() {
        return estaVacia() ? 0 : rear - front + 1;
    }

    /**
     * Representación visual de la cola de espera.
     */
    @Override
    public String toString() {
        if (estaVacia()) return "  (nadie esperando)";
        StringBuilder sb = new StringBuilder("\n");
        sb.append("  ┌────────────────────────────────────┐\n");
        sb.append("  │         SALA DE ESPERA             │\n");
        sb.append("  ├────────────────────────────────────┤\n");
        for (int i = front; i <= rear; i++) {
            sb.append("  │  Turno #").append(String.format("%-3d", turnos[i]));
            if (i == front) sb.append("  ← PRÓXIMO A ATENDER");
            sb.append(" │\n");
        }
        sb.append("  └────────────────────────────────────┘\n");
        return sb.toString();
    }

    /**
     * Imprime estado compacto.
     */
    public void imprimirEstado() {
        System.out.println("  Estado: " + (estaVacia() ? "SIN ESPERA" : estaLlena() ? "LLENO" : "Normal")
                + " | Esperando: " + cantidadEsperando() + "/" + capacidad);
        if (!estaVacia()) System.out.println("  Próximo turno: #" + turnos[front]);
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int CAPACIDAD = 20;
        PilasYColas_ejercicio_7 oficina = new PilasYColas_ejercicio_7(CAPACIDAD);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 7 — SISTEMA DE TURNOS (cola FIFO, capacidad 20)                ║");
        System.out.println("║  Primera en llegar, primera en ser atendida                               ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ OFICINA ABIERTA ━━━");
        oficina.imprimirEstado();
        System.out.println(oficina);
        System.out.println();

        int operaciones = 15 + random.nextInt(10); // 15-24

        for (int i = 1; i <= operaciones; i++) {
            System.out.println("━━━ EVENTO #" + i + " ━━━");

            // 65% llegada, 35% atención
            boolean llegada = random.nextDouble() < 0.65 || oficina.estaVacia();

            if (llegada) {
                System.out.println("  → LLEGA NUEVA PERSONA");
                try {
                    int turno = oficina.agregarPersona();
                    System.out.println("  ✓ Turno asignado: #" + turno);
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            } else {
                System.out.println("  → ATENDER SIGUIENTE");
                try {
                    int turno = oficina.atenderSiguiente();
                    System.out.println("  ✓ Atendido turno: #" + turno);
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            }

            if (oficina.hayEspera()) {
                System.out.println("  → Próximo a atender: #" + oficina.proximo());
            }

            System.out.println();
            oficina.imprimirEstado();
            System.out.println(oficina);
        }

        System.out.println("━━━ ¿POR QUÉ FIFO Y NO LIFO? ━━━");
        System.out.println();
        System.out.println("  En una oficina/turnos:");
        System.out.println("  • Quien llega PRIMERO debe ser atendido PRIMERO (justicia)");
        System.out.println("  • LIFO (pila) atendería al ÚLTIMO que llegó → injusto");
        System.out.println("  • FIFO garantiza orden de llegada = orden de atención");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}