import java.util.Random;

/**
 * ListasEnlazadas_ejercicio_9 - Cola de impresión usando lista enlazada (FIFO).
 *
 * <p>TrabajoImpresion: archivo, páginas, usuario.</p>
 * <p>head = próximo a imprimir, tail = último encolado.</p>
 *
 * <p>Complejidad:</p>
 * <ul>
 *   <li>Agregar trabajo: O(1) - usa tail</li>
 *   <li>Imprimir próximo: O(1) - usa head</li>
 *   <li>Consultar próximo: O(1) - head</li>
 *   <li>Mostrar todos: O(n)</li>
 * </ul>
 *
 * <p>¿Por qué FIFO en impresora?</p>
 * <p>Justicia: quien envía primero, imprime primero. LIFO causaría que
 * documentos nuevos salten la cola y los antiguos esperen indefinidamente.</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ListasEnlazadas_ejercicio_9 {

    static class TrabajoImpresion {
        String archivo;
        int paginas;
        String usuario;

        TrabajoImpresion(String archivo, int paginas, String usuario) {
            this.archivo = archivo;
            this.paginas = paginas;
            this.usuario = usuario;
        }

        @Override
        public String toString() {
            return archivo + " (" + paginas + " pág" + (paginas == 1 ? "" : "s") + ") - " + usuario;
        }
    }

    private static class Nodo {
        TrabajoImpresion trabajo;
        Nodo siguiente;

        Nodo(TrabajoImpresion t) {
            this.trabajo = t;
            this.siguiente = null;
        }
    }

    private Nodo head; // Próximo a imprimir
    private Nodo tail; // Último encolado
    private int size;
    private final int maxTrabajos;

    public ListasEnlazadas_ejercicio_9(int maxTrabajos) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.maxTrabajos = maxTrabajos;
    }

    public void agregarTrabajo(TrabajoImpresion t) {
        if (size >= maxTrabajos) throw new IllegalStateException("Cola llena (" + maxTrabajos + ")");
        Nodo nuevo = new Nodo(t);
        if (isEmpty()) {
            head = tail = nuevo;
        } else {
            tail.siguiente = nuevo;
            tail = nuevo;
        }
        size++;
    }

    public TrabajoImpresion imprimirSiguiente() {
        if (isEmpty()) throw new IllegalStateException("No hay trabajos pendientes");
        TrabajoImpresion t = head.trabajo;
        head = head.siguiente;
        size--;
        if (head == null) tail = null;
        return t;
    }

    public TrabajoImpresion proximoTrabajo() {
        if (isEmpty()) throw new IllegalStateException("Cola vacía");
        return head.trabajo;
    }

    public boolean hayPendientes() { return !isEmpty(); }
    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    @Override
    public String toString() {
        if (isEmpty()) return "  (cola vacía)";
        StringBuilder sb = new StringBuilder("\n");
        sb.append("  ┌────────────────────────────────────────────────────┐\n");
        sb.append("  │                   COLA DE IMPRESIÓN                │\n");
        sb.append("  ├────────────────────────────────────────────────────┤\n");
        Nodo actual = head;
        int pos = 1;
        while (actual != null) {
            String prefijo = actual == head ? "► IMPRIMIENDO " : String.format("%2d. ", pos++);
            sb.append("  │ ").append(String.format("%-2s", prefijo))
              .append(String.format("%-50s", actual.trabajo.toString())).append(" │\n");
            actual = actual.siguiente;
        }
        sb.append("  └────────────────────────────────────────────────────┘\n");
        return sb.toString();
    }

    public void imprimirEstado() {
        System.out.println("  head = " + (head != null ? head.trabajo.archivo : "null")
                + " | tail = " + (tail != null ? tail.trabajo.archivo : "null")
                + " | size = " + size + "/" + maxTrabajos
                + " | " + (isEmpty() ? "SIN PENDIENTES" : "Normal"));
        if (!isEmpty()) System.out.println("  Imprimiendo ahora: " + head.trabajo);
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int MAX = 20;
        ListasEnlazadas_ejercicio_9 impresora = new ListasEnlazadas_ejercicio_9(MAX);

        String[] archivos = {
            "Informe_ventas.pdf", "Contrato_cliente.docx", "Presentacion.pptx",
            "Factura_001.pdf", "Manual_usuario.pdf", "Presupuesto.xlsx",
            "Certificado.pdf", "Carta_formal.docx", "Tabla_datos.csv", "Foto_equipo.jpg",
            "Curriculum.pdf", "Factura_mensual.pdf", "Diapositivas.pptx", "Nota_credito.docx"
        };
        String[] usuarios = {"Ana", "Carlos", "Lucia", "Pedro", "Sofia", "Juan", "Maria", "Diego"};

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 9 — COLA DE IMPRESIÓN (cola enlazada, máx 20 trabajos)        ║");
        System.out.println("║  FIFO: head=próximo, tail=último | agregar/imprimir=O(1)                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ IMPRESORA LISTA ━━━");
        impresora.imprimirEstado();
        System.out.println(impresora);
        System.out.println();

        int ops = 12 + random.nextInt(8);

        for (int i = 1; i <= ops; i++) {
            System.out.println("━━━ EVENTO #" + i + " ━━━");

            boolean enviar = random.nextDouble() < 0.6 || impresora.isEmpty();

            if (enviar) {
                String arch = archivos[random.nextInt(archivos.length)] + "_" + random.nextInt(100);
                int pag = 1 + random.nextInt(15);
                String user = usuarios[random.nextInt(usuarios.length)];
                TrabajoImpresion t = new TrabajoImpresion(arch, pag, user);

                System.out.println("  → ENVIAR: " + t);
                try {
                    impresora.agregarTrabajo(t);
                    System.out.println("  ✓ Encolado (nuevo tail)");
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            } else {
                System.out.println("  → IMPRIMIR SIGUIENTE");
                try {
                    TrabajoImpresion t = impresora.imprimirSiguiente();
                    System.out.println("  ✓ IMPRESO: " + t);
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            }

            if (impresora.hayPendientes()) {
                System.out.println("  → Próximo: " + impresora.proximoTrabajo());
            }

            System.out.println();
            impresora.imprimirEstado();
            System.out.println(impresora);
        }

        System.out.println("━━━ ¿POR QUÉ FIFO EN IMPRESIÓN? ━━━");
        System.out.println();
        System.out.println("  • Orden de llegada = orden de impresión (justicia)");
        System.out.println("  • Evita inanición (starvation) de trabajos antiguos");
        System.out.println("  • LIFO haría que lo último enviado salte la cola");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}