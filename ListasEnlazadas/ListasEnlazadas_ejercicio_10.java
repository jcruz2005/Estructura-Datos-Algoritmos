import java.util.Random;

/**
 * ListasEnlazadas_ejercicio_10 - Lista doblemente enlazada genérica.
 *
 * <p>Cada nodo tiene referencias {@code next} y {@code previous}.
 * La lista mantiene {@code head} (primero) y {@code tail} (último).</p>
 *
 * <p>Ventajas de referencias bidireccionales:</p>
 * <ul>
 *   <li>Recorrido hacia adelante (head → tail) y hacia atrás (tail → head)</li>
 *   <li>Inserción al final O(1) usando tail</li>
 *   <li>Eliminación de nodo conocido O(1) sin recorrer desde head</li>
 *   <li>Navegación bidireccional en interfaces de usuario, historial, etc.</li>
 * </ul>
 *
 * <p>Complejidad:</p>
 * <ul>
 *   <li>Insertar al inicio: O(1)</li>
 *   <li>Insertar al final: O(1) - usa tail</li>
 *   <li>Eliminar nodo (con referencia): O(1)</li>
 *   <li>Buscar para eliminar: O(n)</li>
 *   <li>Recorrer: O(n)</li>
 * </ul>
 *
 * @param <T> tipo de elementos
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ListasEnlazadas_ejercicio_10<T> {

    static class Nodo<T> {
        T valor;
        Nodo<T> next;
        Nodo<T> prev;

        Nodo(T valor) {
            this.valor = valor;
            this.next = null;
            this.prev = null;
        }
    }

    private Nodo<T> head;
    private Nodo<T> tail;
    private int size;
    private final int maxNodos;

    public ListasEnlazadas_ejercicio_10(int maxNodos) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.maxNodos = maxNodos;
    }

    /** Inserta al inicio (head). O(1) */
    public void insertarInicio(T valor) {
        if (size >= maxNodos) throw new IllegalStateException("Límite " + maxNodos + " alcanzado");
        Nodo<T> nuevo = new Nodo<>(valor);
        if (isEmpty()) {
            head = tail = nuevo;
        } else {
            nuevo.next = head;
            head.prev = nuevo;
            head = nuevo;
        }
        size++;
    }

    /** Inserta al final (tail). O(1) gracias a tail. */
    public void insertarFinal(T valor) {
        if (size >= maxNodos) throw new IllegalStateException("Límite " + maxNodos + " alcanzado");
        Nodo<T> nuevo = new Nodo<>(valor);
        if (isEmpty()) {
            head = tail = nuevo;
        } else {
            tail.next = nuevo;
            nuevo.prev = tail;
            tail = nuevo;
        }
        size++;
    }

    /**
     * Elimina la primera ocurrencia de un valor.
     * Busca desde head (O(n)) y luego desenlaza (O(1)).
     * @return true si encontró y eliminó
     */
    public boolean eliminar(T valor) {
        if (isEmpty()) return false;

        Nodo<T> actual = head;
        while (actual != null) {
            boolean igual = (valor == null ? actual.valor == null : valor.equals(actual.valor));
            if (igual) {
                // Desenlazar
                if (actual.prev != null) {
                    actual.prev.next = actual.next;
                } else {
                    head = actual.next; // Era el primero
                }
                if (actual.next != null) {
                    actual.next.prev = actual.prev;
                } else {
                    tail = actual.prev; // Era el último
                }
                size--;
                return true;
            }
            actual = actual.next;
        }
        return false;
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    /** Recorrido hacia adelante (head → tail) */
    public String toStringAdelante() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = head;
        while (actual != null) {
            sb.append(actual.valor);
            if (actual.next != null) sb.append(" ⇄ ");
            actual = actual.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /** Recorrido hacia atrás (tail → head) */
    public String toStringAtras() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = tail;
        while (actual != null) {
            sb.append(actual.valor);
            if (actual.prev != null) sb.append(" ⇄ ");
            actual = actual.prev;
        }
        sb.append("]");
        return sb.toString();
    }

    public void imprimirEstado() {
        System.out.println("  head = " + (head != null ? head.valor : "null")
                + " | tail = " + (tail != null ? tail.valor : "null")
                + " | size = " + size + "/" + maxNodos
                + " | " + (isEmpty() ? "VACÍA" : "Normal"));
        System.out.println("  Adelante: " + toStringAdelante());
        System.out.println("  Atrás:    " + toStringAtras());
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int MAX = 20;
        ListasEnlazadas_ejercicio_10<Integer> lista = new ListasEnlazadas_ejercicio_10<>(MAX);

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 10 — LISTA DOBLEMENTE ENLAZADA GENÉRICA (máx 20 nodos)        ║");
        System.out.println("║  next + prev | head + tail | recorrido bidireccional | elimina O(1)*     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ ESTADO INICIAL ━━━");
        lista.imprimirEstado();
        System.out.println();

        int ops = 15 + random.nextInt(10);

        for (int i = 1; i <= ops; i++) {
            System.out.println("━━━ OPERACIÓN #" + i + " ━━━");

            double r = random.nextDouble();

            try {
                if (r < 0.35) {
                    // Insertar inicio
                    int v = random.nextInt(100);
                    System.out.println("  → INSERTAR_INICIO(" + v + ")");
                    lista.insertarInicio(v);
                    System.out.println("  ✓ Insertado al inicio (nuevo head)");
                } else if (r < 0.65) {
                    // Insertar final
                    int v = random.nextInt(100);
                    System.out.println("  → INSERTAR_FINAL(" + v + ")");
                    lista.insertarFinal(v);
                    System.out.println("  ✓ Insertado al final (nuevo tail)");
                } else {
                    // Eliminar
                    int v = random.nextInt(100);
                    System.out.println("  → ELIMINAR(" + v + ")");
                    boolean ok = lista.eliminar(v);
                    System.out.println("  " + (ok ? "✓ Eliminado" : "✗ No encontrado"));
                }
            } catch (IllegalStateException e) {
                System.out.println("  ✗ " + e.getMessage());
            }

            System.out.println();
            lista.imprimirEstado();
        }

        // Casos límite
        System.out.println("━━━ CASOS LÍMITE DE ELIMINACIÓN ━━━");
        System.out.println();

        // Llenar con valores conocidos
        lista.clear();
        for (int v : new int[]{10, 20, 30, 40, 50}) lista.insertarFinal(v);
        System.out.println("  Lista inicial: " + lista.toStringAdelante());
        lista.imprimirEstado();

        System.out.println("\n  → Eliminar PRIMERO (10):");
        lista.eliminar(10);
        lista.imprimirEstado();

        System.out.println("\n  → Eliminar ÚLTIMO (50):");
        lista.eliminar(50);
        lista.imprimirEstado();

        System.out.println("\n  → Eliminar INTERMEDIO (30):");
        lista.eliminar(30);
        lista.imprimirEstado();

        System.out.println("\n  → Eliminar INEXISTENTE (999):");
        System.out.println("  Resultado: " + (lista.eliminar(999) ? "Eliminado" : "No encontrado"));
        lista.imprimirEstado();

        // Un solo nodo
        lista.clear();
        lista.insertarInicio(42);
        System.out.println("\n  → Un solo nodo (42):");
        lista.imprimirEstado();
        lista.eliminar(42);
        System.out.println("  → Eliminar el único nodo:");
        lista.imprimirEstado();
        System.out.println("  ✓ head y tail ambos null");

        System.out.println("\n━━━ ¿POR QUÉ NEXT Y PREV? ━━━");
        System.out.println();
        System.out.println("  • next: avanza hacia adelante (head → tail)");
        System.out.println("  • prev: retrocede hacia atrás (tail → head)");
        System.out.println("  • Permiten recorrer EN AMBOS SENTIDOS");
        System.out.println("  • Insertar al final O(1) sin recorrer desde head");
        System.out.println("  • Eliminar nodo conocido O(1) actualizando vecinos");
        System.out.println("  • Útil en: historial navegador, listas reproducción, undo/redo");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }

    // Método auxiliar para limpiar en demo
    private void clear() {
        head = tail = null;
        size = 0;
    }
}