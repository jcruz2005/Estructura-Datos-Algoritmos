import java.util.Random;

/**
 * ListasEnlazadas_ejercicio_6 - Cola de atención en banco usando lista enlazada.
 *
 * <p>Cliente: nombre, turno, motivo. FIFO: head=frente, tail=final.</p>
 *
 * <p>Complejidad:</p>
 * <ul>
 *   <li>Agregar cliente: O(1) - usa tail</li>
 *   <li>Atender cliente: O(1) - usa head</li>
 *   <li>Consultar próximo: O(1) - head</li>
 *   <li>Imprimir fila: O(n)</li>
 * </ul>
 *
 * <p>Casos especiales: actualizar head/tail al vaciar, único elemento.</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ListasEnlazadas_ejercicio_6 {

    static class Cliente {
        String nombre;
        int turno;
        String motivo;

        Cliente(String nombre, int turno, String motivo) {
            this.nombre = nombre;
            this.turno = turno;
            this.motivo = motivo;
        }

        @Override
        public String toString() {
            return "Turno #" + turno + ": " + nombre + " - " + motivo;
        }
    }

    private static class Nodo {
        Cliente cliente;
        Nodo siguiente;

        Nodo(Cliente cliente) {
            this.cliente = cliente;
            this.siguiente = null;
        }
    }

    private Nodo head; // Primer cliente (próximo a atender)
    private Nodo tail; // Último cliente (recién llegado)
    private int size;
    private final int maxClientes;
    private int proximoTurno;

    public ListasEnlazadas_ejercicio_6(int maxClientes) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.maxClientes = maxClientes;
        this.proximoTurno = 1;
    }

    public void agregarCliente(String nombre, String motivo) {
        if (size >= maxClientes) throw new IllegalStateException("Fila llena (" + maxClientes + ")");
        Cliente c = new Cliente(nombre, proximoTurno++, motivo);
        Nodo nuevo = new Nodo(c);
        if (isEmpty()) {
            head = tail = nuevo;
        } else {
            tail.siguiente = nuevo;
            tail = nuevo;
        }
        size++;
        System.out.println("  ✓ Cliente agregado: " + c);
    }

    public Cliente atenderSiguiente() {
        if (isEmpty()) throw new IllegalStateException("No hay clientes esperando");
        Cliente c = head.cliente;
        head = head.siguiente;
        size--;
        if (head == null) tail = null; // Quedó vacía
        return c;
    }

    public Cliente proximo() {
        if (isEmpty()) throw new IllegalStateException("Fila vacía");
        return head.cliente;
    }

    public boolean hayEspera() { return !isEmpty(); }
    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    @Override
    public String toString() {
        if (isEmpty()) return "  (fila vacía)";
        StringBuilder sb = new StringBuilder("\n");
        sb.append("  ┌────────────────────────────────────────────┐\n");
        sb.append("  │              FILA DE ESPERA                │\n");
        sb.append("  ├────────────────────────────────────────────┤\n");
        Nodo actual = head;
        int pos = 1;
        while (actual != null) {
            sb.append("  │ ").append(String.format("%2d", pos++)).append(". ")
              .append(String.format("%-40s", actual.cliente.toString())).append(" │\n");
            actual = actual.siguiente;
        }
        sb.append("  └────────────────────────────────────────────┘\n");
        return sb.toString();
    }

    public void imprimirEstado() {
        System.out.println("  head = " + (head != null ? "Turno #" + head.cliente.turno : "null")
                + " | tail = " + (tail != null ? "Turno #" + tail.cliente.turno : "null")
                + " | size = " + size + "/" + maxClientes
                + " | " + (isEmpty() ? "SIN ESPERA" : "Normal"));
        if (!isEmpty()) System.out.println("  Próximo: " + head.cliente);
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int MAX = 20;
        ListasEnlazadas_ejercicio_6 banco = new ListasEnlazadas_ejercicio_6(MAX);

        String[] nombres = {"Ana Garcia", "Carlos Lopez", "Lucia Martinez", "Pedro Rodriguez",
            "Sofia Gonzalez", "Juan Perez", "Maria Sanchez", "Diego Romero",
            "Paula Torres", "Martin Flores", "Valeria Ruiz", "Andres Herrera"};
        String[] motivos = {"Depósito", "Retiro", "Transferencia", "Préstamo", "Tarjeta",
            "Cheques", "Inversiones", "Seguros", "Reclamo", "Consulta"};

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 6 — COLA ATENCIÓN CLIENTES (cola enlazada, máx 20)            ║");
        System.out.println("║  FIFO: head=frente, tail=final | agregar=O(1), atender=O(1)             ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ BANCO ABIERTO ━━━");
        banco.imprimirEstado();
        System.out.println(banco);
        System.out.println();

        int ops = 15 + random.nextInt(10);

        for (int i = 1; i <= ops; i++) {
            System.out.println("━━━ EVENTO #" + i + " ━━━");

            boolean llegada = random.nextDouble() < 0.65 || banco.isEmpty();

            if (llegada) {
                String nom = nombres[random.nextInt(nombres.length)];
                String mot = motivos[random.nextInt(motivos.length)];
                System.out.println("  → LLEGADA: " + nom + " (" + mot + ")");
                try {
                    banco.agregarCliente(nom, mot);
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            } else {
                System.out.println("  → ATENDER SIGUIENTE");
                try {
                    Cliente c = banco.atenderSiguiente();
                    System.out.println("  ✓ Atendido: " + c);
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            }

            if (banco.hayEspera()) {
                System.out.println("  → Próximo: " + banco.proximo());
            }

            System.out.println();
            banco.imprimirEstado();
            System.out.println(banco);
        }

        System.out.println("━━━ ¿POR QUÉ FIFO EN BANCO? ━━━");
        System.out.println();
        System.out.println("  • Justicia: quien llega primero, se atiende primero");
        System.out.println("  • LIFO (pila) atendería al último que llegó → injusto");
        System.out.println("  • head = próximo a atender, tail = último en llegar");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}