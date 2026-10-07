import java.util.Random;

/**
 * ListasEnlazadas_ejercicio_8 - Invierte una palabra usando pila enlazada de caracteres.
 *
 * <p>Algoritmo:</p>
 * <ol>
 *   <li>Recorrer cada carácter de la palabra → push en pila</li>
 *   <li>Vaciar pila con pop → construir palabra invertida</li>
 * </ol>
 *
 * <p>Funciona por LIFO: el último carácter insertado es el primero en salir.</p>
 *
 * <p>Complejidad para palabra de longitud n:</p>
 * <ul>
 *   <li>Cargar pila: O(n) - n push</li>
 *   <li>Vaciar pila: O(n) - n pop</li>
 *   <li>Total: O(n)</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ListasEnlazadas_ejercicio_8 {

    private static class Nodo {
        char valor;
        Nodo siguiente;

        Nodo(char valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo head;
    private int size;

    public void clear() { head = null; size = 0; }
    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    public void push(char c) {
        Nodo n = new Nodo(c);
        n.siguiente = head;
        head = n;
        size++;
    }

    public char pop() {
        if (isEmpty()) throw new IllegalStateException("Pila vacía");
        char c = head.valor;
        head = head.siguiente;
        size--;
        return c;
    }

    /**
     * Invierte una palabra usando la pila.
     */
    public String invertir(String palabra) {
        clear();
        System.out.println("  Palabra original: " + palabra);
        System.out.println("  ──────────────────────────────");

        // Paso 1: push de cada carácter
        System.out.println("  FASE 1: CARGAR PILA (push)");
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            push(c);
            System.out.println("    push('" + c + "') → pila: " + pilaToString());
        }

        System.out.println("\n  FASE 2: VACIAR PILA (pop)");
        StringBuilder invertida = new StringBuilder();
        while (!isEmpty()) {
            char c = pop();
            invertida.append(c);
            System.out.println("    pop() → '" + c + "' → invertida: " + invertida + " | pila: " + pilaToString());
        }

        System.out.println("  ──────────────────────────────");
        System.out.println("  Palabra invertida: " + invertida);
        System.out.println();

        return invertida.toString();
    }

    private String pilaToString() {
        if (isEmpty()) return "[vacía]";
        StringBuilder sb = new StringBuilder("[tope ");
        Nodo actual = head;
        while (actual != null) {
            sb.append(actual.valor);
            if (actual.siguiente != null) sb.append(" → ");
            actual = actual.siguiente;
        }
        sb.append(" base]");
        return sb.toString();
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        ListasEnlazadas_ejercicio_8 inversor = new ListasEnlazadas_ejercicio_8();

        String[] palabras = {
            "algoritmo",
            "estructura",
            "datos",
            "programacion",
            "computadora",
            "teclado",
            "monitor",
            "raton",
            "java",
            "python",
            "hola",
            "mundo",
            "pila",
            "cola",
            "nodo"
        };

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 8 — INVERTIR PALABRA (pila enlazada char, < 20 chars)         ║");
        System.out.println("║  LIFO: último en entrar = primero en salir                               ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Probar varias palabras aleatorias
        for (int i = 0; i < 5; i++) {
            String p = palabras[random.nextInt(palabras.length)];
            System.out.println("━━━ PALABRA #" + (i + 1) + " ━━━");
            inversor.invertir(p);
        }

        System.out.println("━━━ EXPLICACIÓN LIFO ━━━");
        System.out.println();
        System.out.println("  Palabra: A L G O R I T M O");
        System.out.println("  Push orden: A → L → G → O → R → I → T → M → O (tope)");
        System.out.println("  Pop orden:  O → M → T → I → R → O → G → L → A");
        System.out.println("  Resultado:  O M T I R O G L A");
        System.out.println();
        System.out.println("  El último carácter (O) sale primero → palabra invertida.");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}