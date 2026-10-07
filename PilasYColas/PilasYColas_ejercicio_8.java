import java.util.Random;

/**
 * Documento - Representa un documento en la cola de impresión.
 */
class Documento {
    private final String nombre;
    private final int paginas;

    public Documento(String nombre, int paginas) {
        this.nombre = nombre;
        this.paginas = paginas;
    }

    public String getNombre() { return nombre; }
    public int getPaginas() { return paginas; }

    @Override
    public String toString() {
        return nombre + " (" + paginas + " pág" + (paginas == 1 ? "" : "s") + ")";
    }
}

/**
 * PilasYColas_ejercicio_8 - Simulador de cola de impresora usando cola (FIFO).
 *
 * <p>Los documentos se imprimen en el mismo orden en que se envían:
 * <b>FIFO (First In, First Out)</b>.</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class PilasYColas_ejercicio_8 {

    private final Documento[] cola;
    private int front;
    private int rear;
    private final int capacidad;

    public PilasYColas_ejercicio_8(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad > 0");
        this.capacidad = capacidad;
        this.cola = new Documento[capacidad];
        this.front = 0;
        this.rear = -1;
    }

    /**
     * Agrega un documento a la cola de impresión.
     */
    public void agregarDocumento(Documento doc) {
        if (estaLlena()) {
            throw new IllegalStateException("Cola de impresión llena (" + capacidad + " docs)");
        }
        rear++;
        cola[rear] = doc;
    }

    /**
     * Imprime y retira el siguiente documento.
     */
    public Documento imprimirSiguiente() {
        if (estaVacia()) {
            throw new IllegalStateException("No hay documentos pendientes");
        }
        Documento doc = cola[front];
        front++;
        return doc;
    }

    /**
     * Consulta el próximo documento a imprimir (sin retirarlo).
     */
    public Documento proximoDocumento() {
        if (estaVacia()) {
            throw new IllegalStateException("Cola vacía");
        }
        return cola[front];
    }

    public boolean hayPendientes() { return !estaVacia(); }
    public boolean estaVacia() { return front > rear; }
    public boolean estaLlena() { return rear == capacidad - 1; }
    public int size() { return estaVacia() ? 0 : rear - front + 1; }

    @Override
    public String toString() {
        if (estaVacia()) return "  (cola vacía)";
        StringBuilder sb = new StringBuilder("\n");
        sb.append("  ┌─────────────────────────────────────┐\n");
        sb.append("  │        COLA DE IMPRESIÓN            │\n");
        sb.append("  ├─────────────────────────────────────┤\n");
        for (int i = front; i <= rear; i++) {
            sb.append("  │ ").append(String.format("%-2d", i - front + 1))
              .append(". ").append(String.format("%-28s", cola[i].toString())).append(" │\n");
        }
        sb.append("  └─────────────────────────────────────┘\n");
        return sb.toString();
    }

    public void imprimirEstado() {
        System.out.println("  Estado: " + (estaVacia() ? "SIN PENDIENTES" : estaLlena() ? "LLENA" : "Normal")
                + " | Pendientes: " + size() + "/" + capacidad);
        if (!estaVacia()) {
            Documento p = cola[front];
            System.out.println("  Imprimiendo ahora: " + p);
        }
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int CAPACIDAD = 20;
        PilasYColas_ejercicio_8 impresora = new PilasYColas_ejercicio_8(CAPACIDAD);

        String[] nombresDoc = {
            "Informe_ventas.pdf", "Contrato_cliente.docx", "Presentacion.pptx",
            "Factura_001.pdf", "Manual_usuario.pdf", "Presupuesto.xlsx",
            "Certificado.pdf", "Carta_formal.docx", "Tabla_datos.csv", "Foto_equipo.jpg"
        };

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 8 — COLA DE IMPRESIÓN (cola FIFO, capacidad 20)               ║");
        System.out.println("║  Documentos se imprimen en orden de llegada                               ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ IMPRESORA LISTA ━━━");
        impresora.imprimirEstado();
        System.out.println(impresora);
        System.out.println();

        int operaciones = 12 + random.nextInt(8); // 12-19

        for (int i = 1; i <= operaciones; i++) {
            System.out.println("━━━ EVENTO #" + i + " ━━━");

            boolean enviar = random.nextDouble() < 0.6 || impresora.estaVacia();

            if (enviar) {
                String nombre = nombresDoc[random.nextInt(nombresDoc.length)] + "_" + random.nextInt(100);
                int paginas = 1 + random.nextInt(20);
                Documento doc = new Documento(nombre, paginas);

                System.out.println("  → ENVIAR A IMPRIMIR: " + doc);
                try {
                    impresora.agregarDocumento(doc);
                    System.out.println("  ✓ Encolado en posición " + (impresora.rear - impresora.front + 1));
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            } else {
                System.out.println("  → IMPRIMIR SIGUIENTE");
                try {
                    Documento doc = impresora.imprimirSiguiente();
                    System.out.println("  ✓ IMPRESO: " + doc);
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            }

            if (impresora.hayPendientes()) {
                Documento p = impresora.proximoDocumento();
                System.out.println("  → Próximo en cola: " + p);
            }

            System.out.println();
            impresora.imprimirEstado();
            System.out.println(impresora);
        }

        System.out.println("━━━ ¿POR QUÉ FIFO EN IMPRESIÓN? ━━━");
        System.out.println();
        System.out.println("  • Justicia: quien envía primero, imprime primero");
        System.out.println("  • Evita que documentos urgentes queden atrapados detrás");
        System.out.println("  • LIFO (pila) imprimiría lo último enviado → caos");
        System.out.println("  • FIFO = orden de llegada = orden de impresión");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}