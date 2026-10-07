import java.util.Random;

/**
 * ListasEnlazadas_ejercicio_5 - Historial de navegador usando pila enlazada.
 *
 * <p>Cada nodo almacena una URL. {@code head} = página actual (tope).</p>
 * <ul>
 *   <li>Visitar página = {@code push} (nuevo nodo se vuelve head)</li>
 *   <li>Volver atrás = {@code pop} (head avanza, página actual se descarta)</li>
 *   <li>Consultar actual = {@code peek}</li>
 * </ul>
 *
 * <p>Complejidad:</p>
 * <ul>
 *   <li>Visitar: O(1)</li>
 *   <li>Volver atrás: O(1)</li>
 *   <li>Consultar actual: O(1)</li>
 *   <li>Imprimir historial: O(n)</li>
 * </ul>
 *
 * <p>¿Por qué pila (LIFO)?</p>
 * <p>El botón "Atrás" del navegador debe ir a la página MÁS RECIENTE visitada.
 * La última en entrar (push) es la primera en salir (pop) al hacer "volver".
 * Una cola (FIFO) iría a la primera página visitada, no a la anterior inmediata.</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ListasEnlazadas_ejercicio_5 {

    private static class Nodo {
        String url;
        Nodo siguiente;

        Nodo(String url) {
            this.url = url;
            this.siguiente = null;
        }
    }

    private Nodo head; // Página actual (tope)
    private int size;
    private final int maxPaginas;

    public ListasEnlazadas_ejercicio_5(int maxPaginas) {
        this.head = null;
        this.size = 0;
        this.maxPaginas = maxPaginas;
    }

    public void visitar(String url) {
        if (size >= maxPaginas) throw new IllegalStateException("Historial lleno (" + maxPaginas + ")");
        Nodo nuevo = new Nodo(url);
        nuevo.siguiente = head;
        head = nuevo;
        size++;
    }

    public String volver() {
        if (size <= 1) throw new IllegalStateException("No hay página anterior (size = " + size + ")");
        String actual = head.url;
        head = head.siguiente;
        size--;
        return head.url; // Retorna la nueva actual
    }

    public String actual() {
        if (isEmpty()) throw new IllegalStateException("Historial vacío");
        return head.url;
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    @Override
    public String toString() {
        if (isEmpty()) return "  (historial vacío)";
        StringBuilder sb = new StringBuilder("\n");
        Nodo actual = head;
        int pos = 1;
        while (actual != null) {
            sb.append(actual == head ? "  ► " : "    ");
            sb.append("#").append(pos++).append(": ").append(actual.url);
            if (actual == head) sb.append(" ← ACTUAL");
            sb.append("\n");
            actual = actual.siguiente;
        }
        return sb.toString();
    }

    public void imprimirEstado() {
        System.out.println("  head = " + (head != null ? head.url : "null")
                + " | size = " + size + "/" + maxPaginas
                + " | " + (isEmpty() ? "VACÍO" : "Normal"));
        if (!isEmpty()) System.out.println("  Página actual: " + head.url);
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int MAX = 20;
        ListasEnlazadas_ejercicio_5 historial = new ListasEnlazadas_ejercicio_5(MAX);

        String[] urls = {
            "https://google.com",
            "https://github.com",
            "https://stackoverflow.com",
            "https://youtube.com",
            "https://wikipedia.org",
            "https://twitter.com",
            "https://linkedin.com",
            "https://reddit.com",
            "https://medium.com",
            "https://dev.to"
        };

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 5 — HISTORIAL NAVEGACIÓN (pila enlazada, máx 20)             ║");
        System.out.println("║  LIFO: visitar=push, volver=pop, actual=peek                             ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ HISTORIAL INICIAL ━━━");
        historial.imprimirEstado();
        System.out.println(historial);
        System.out.println();

        // Visita inicial
        String primera = urls[random.nextInt(urls.length)];
        System.out.println("━━━ VISITA INICIAL ━━━");
        System.out.println("  → VISITAR: " + primera);
        historial.visitar(primera);
        historial.imprimirEstado();
        System.out.println(historial);
        System.out.println();

        int ops = 15 + random.nextInt(10);

        for (int i = 1; i <= ops; i++) {
            System.out.println("━━━ OPERACIÓN #" + i + " ━━━");

            boolean visitar = random.nextDouble() < 0.7 || historial.size() <= 1;

            if (visitar) {
                String url = urls[random.nextInt(urls.length)];
                System.out.println("  → VISITAR: " + url);
                try {
                    historial.visitar(url);
                    System.out.println("  ✓ Agregada al historial (nuevo head)");
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            } else {
                System.out.println("  → VOLVER (back)");
                try {
                    String anterior = historial.volver();
                    System.out.println("  ✓ Retrocedido. Nueva actual: " + anterior);
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            }

            System.out.println();
            historial.imprimirEstado();
            System.out.println(historial);
        }

        System.out.println("━━━ ¿POR QUÉ PILA (LIFO)? ━━━");
        System.out.println();
        System.out.println("  • 'Atrás' = última página visitada = tope de la pila");
        System.out.println("  • Push = visitar, Pop = volver");
        System.out.println("  • Cola (FIFO) iría a la PRIMERA visitada (inicio), no a la anterior");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}