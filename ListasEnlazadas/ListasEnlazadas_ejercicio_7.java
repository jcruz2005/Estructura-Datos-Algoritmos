import java.util.Random;

/**
 * ListasEnlazadas_ejercicio_7 - Verifica paréntesis balanceados con pila enlazada.
 *
 * <p>Algoritmo: recorre expresión char por char.</p>
 * <ul>
 *   <li>'(' → push en pila</li>
 *   <li>')' → pop de pila (si vacía → inválida)</li>
 *   <li>Al final: válida solo si pila vacía</li>
 * </ul>
 *
 * <p>Complejidad: O(n) donde n = longitud de expresión.
 * push/pop = O(1).</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ListasEnlazadas_ejercicio_7 {

    private static class Nodo {
        char valor;
        int posicion; // Para mostrar dónde estaba el '('
        Nodo siguiente;

        Nodo(char valor, int posicion) {
            this.valor = valor;
            this.posicion = posicion;
            this.siguiente = null;
        }
    }

    private Nodo head;
    private int size;

    public void clear() {
        head = null;
        size = 0;
    }

    public void push(char c, int pos) {
        Nodo nuevo = new Nodo(c, pos);
        nuevo.siguiente = head;
        head = nuevo;
        size++;
    }

    public Nodo pop() {
        if (head == null) return null;
        Nodo n = head;
        head = head.siguiente;
        size--;
        return n;
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    /**
     * Valida una expresión. Imprime cada paso.
     * @return true si válida, false si inválida
     */
    public boolean validar(String expr) {
        clear();

        System.out.println("  Expresión: " + expr);
        System.out.println("  ──────────────────────────────────────");

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == '(') {
                push(c, i);
                System.out.println("  [" + i + "] '(' → PUSH → pila: " + toString());
            } else if (c == ')') {
                if (isEmpty()) {
                    System.out.println("  [" + i + "] ')' → POP → PILA VACÍA → INVÁLIDA");
                    System.out.println("  ──────────────────────────────────────");
                    System.out.println("  Resultado: ✗ INVÁLIDA (cierre sin apertura)\n");
                    return false;
                }
                Nodo abierto = pop();
                System.out.println("  [" + i + "] ')' → POP (empareja con '(' en pos " + abierto.posicion + ") → pila: " + toString());
            }
        }

        boolean valida = isEmpty();
        System.out.println("  ──────────────────────────────────────");
        System.out.println("  Resultado: " + (valida ? "✓ VÁLIDA" : "✗ INVÁLIDA (" + size + " paréntesis sin cerrar)"));
        System.out.println();

        return valida;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[vacía]";
        StringBuilder sb = new StringBuilder("[");
        Nodo actual = head;
        while (actual != null) {
            sb.append("'(" + actual.posicion + "'");
            if (actual.siguiente != null) sb.append(" ");
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        ListasEnlazadas_ejercicio_7 validador = new ListasEnlazadas_ejercicio_7();

        String[] validas = {
            "(2 + 3) * (5 - 1)",
            "((a + b) * c)",
            "(x)",
            "()",
            "(a + (b * c) - d)",
            "((()))",
            "(a) * (b) + (c)",
            "((x + y) * (z - w)) / 2"
        };

        String[] invalidas = {
            "(2 + 3",
            "())(",
            "((a + b)",
            "(()",
            "())",
            "(a + b)) * c",
            "((x + y)",
            "a + b) * (c - d("
        };

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 7 — VALIDADOR PARÉNTESIS (pila enlazada char)                 ║");
        System.out.println("║  push '(', pop ')', inválida si pop en vacía o pila no vacía al final   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ EXPRESIONES VÁLIDAS ━━━");
        for (String e : validas) validador.validar(e);

        System.out.println("━━━ EXPRESIONES INVÁLIDAS ━━━");
        for (String e : invalidas) validador.validar(e);

        // Aleatorias
        System.out.println("━━━ EXPRESIONES ALEATORIAS ━━━");
        String[] vars = {"a", "b", "x", "y", "1", "2", "n", "m"};
        String[] ops = {"+", "-", "*", "/"};

        for (int i = 0; i < 6; i++) {
            StringBuilder expr = new StringBuilder();
            int abiertos = 0;
            int maxPares = 1 + random.nextInt(4);

            for (int j = 0; j < maxPares * 2 + random.nextInt(5); j++) {
                double r = random.nextDouble();
                if (r < 0.3 && abiertos < maxPares) {
                    expr.append('(');
                    abiertos++;
                } else if (r < 0.5 && abiertos > 0) {
                    expr.append(')');
                    abiertos--;
                } else if (r < 0.7) {
                    expr.append(vars[random.nextInt(vars.length)]);
                } else {
                    expr.append(ops[random.nextInt(ops.length)]);
                }
            }
            while (abiertos > 0) { expr.append(')'); abiertos--; }

            validador.validar(expr.toString());
        }

        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}