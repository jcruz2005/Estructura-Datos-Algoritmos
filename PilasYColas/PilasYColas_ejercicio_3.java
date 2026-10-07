import java.util.Random;

/**
 * PilasYColas_ejercicio_3 - Verifica si los paréntesis de una expresión matemática
 * están correctamente balanceados usando una pila de caracteres.
 *
 * <p>Algoritmo:</p>
 * <ul>
 *   <li>Recorre la expresión carácter por carácter</li>
 *   <li>Si encuentra '(', hace {@code push}</li>
 *   <li>Si encuentra ')', hace {@code pop}</li>
 *   <li>Si hay ')' y la pila está vacía → INVÁLIDA</li>
 *   <li>Al final, si la pila está vacía → VÁLIDA, si no → INVÁLIDA</li>
 * </ul>
 *
 * <p>Principio: <b>LIFO</b> — cada paréntesis de apertura debe cerrarse
 * en orden inverso (el último que se abre es el primero que se cierra).</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class PilasYColas_ejercicio_3 {

    /** Pila interna para paréntesis de apertura */
    private final char[] pila;

    /** Índice de la cima (-1 = vacía) */
    private int top;

    /** Capacidad máxima */
    private final int capacidad;

    /**
     * Crea un validador con capacidad para n paréntesis.
     *
     * @param capacidad máximo de paréntesis anidados (debe ser > 0)
     */
    public PilasYColas_ejercicio_3(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("Capacidad debe ser > 0");
        }
        this.capacidad = capacidad;
        this.pila = new char[capacidad];
        this.top = -1;
    }

    /**
     * Verifica si una expresión tiene paréntesis balanceados.
     *
     * @param expresion cadena con la expresión matemática
     * @return {@code true} si está balanceada, {@code false} si no
     */
    public boolean validar(String expresion) {
        // Reiniciar pila
        top = -1;

        System.out.println("  Expresión: " + expresion);
        System.out.println("  ─────────────────────────────");

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (c == '(') {
                // Push: paréntesis de apertura
                if (top == capacidad - 1) {
                    System.out.println("  [" + i + "] '" + c + "' → PUSH → PILA LLENA (desbordamiento)");
                    return false;
                }
                top++;
                pila[top] = c;
                System.out.println("  [" + i + "] '" + c + "' → PUSH → pila: " + estadoPila());

            } else if (c == ')') {
                // Pop: paréntesis de cierre
                if (top == -1) {
                    System.out.println("  [" + i + "] '" + c + "' → POP → PILA VACÍA → INVÁLIDA (cierre sin apertura)");
                    return false;
                }
                char abierto = pila[top];
                top--;
                System.out.println("  [" + i + "] '" + c + "' → POP (empareja con '(" + "' en pos " + encontrarApertura(expresion, i) + ") → pila: " + estadoPila());
            }
            // Otros caracteres se ignoran
        }

        // Al finalizar: válida solo si pila vacía
        boolean valida = (top == -1);
        System.out.println("  ─────────────────────────────");
        System.out.println("  Resultado: " + (valida ? "✓ VÁLIDA" : "✗ INVÁLIDA (quedan " + (top + 1) + " paréntesis sin cerrar)"));
        System.out.println();

        return valida;
    }

    /**
     * Devuelve el estado actual de la pila como cadena.
     */
    private String estadoPila() {
        if (top == -1) return "[vacía]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(pila[i]);
            if (i < top) sb.append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Encuentra la posición del paréntesis de apertura correspondiente
     * (solo para fines de visualización).
     */
    private int encontrarApertura(String expr, int posCierre) {
        int balance = 0;
        for (int i = posCierre; i >= 0; i--) {
            if (expr.charAt(i) == ')') balance++;
            else if (expr.charAt(i) == '(') {
                balance--;
                if (balance == 0) return i;
            }
        }
        return -1;
    }

    // =================================================================
    // MAIN - Demostración automática con expresiones de prueba
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int CAPACIDAD = 20;
        PilasYColas_ejercicio_3 validador = new PilasYColas_ejercicio_3(CAPACIDAD);

        // Expresiones de prueba predefinidas (válidas e inválidas)
        String[] expresionesValidas = {
            "(5 + 3) * (2 + 1)",
            "((a + b) * (c - d))",
            "(x)",
            "()",
            "(a + (b * c) - d)",
            "((()))",
            "(a) * (b) + (c)",
            "((x + y) * (z - w)) / 2"
        };

        String[] expresionesInvalidas = {
            "(5 + 3)) * (2 + 1",
            "((a + b) * (c - d)",
            ")(",
            "(()",
            "())",
            "(a + b)) * c",
            "((x + y)",
            "a + b) * (c - d("
        };

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 3 — VALIDACIÓN DE PARÉNTESIS BALANCEADOS (pila char[], cap 20) ║");
        System.out.println("║  Algoritmo: push '(', pop ')', inválida si pop en vacía o pila no vacía   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Probar todas las válidas
        System.out.println("━━━ EXPRESIONES VÁLIDAS ━━━");
        System.out.println();
        for (String expr : expresionesValidas) {
            validador.validar(expr);
        }

        // Probar todas las inválidas
        System.out.println("━━━ EXPRESIONES INVÁLIDAS ━━━");
        System.out.println();
        for (String expr : expresionesInvalidas) {
            validador.validar(expr);
        }

        // Expresiones aleatorias
        System.out.println("━━━ EXPRESIONES ALEATORIAS ━━━");
        System.out.println();

        String[] tokens = {"a", "b", "x", "y", "1", "2", "5", "10"};
        String[] ops = {"+", "-", "*", "/"};

        for (int i = 0; i < 5; i++) {
            StringBuilder expr = new StringBuilder();
            int pares = 1 + random.nextInt(4); // 1-4 pares de paréntesis
            int abiertos = 0;

            for (int j = 0; j < pares * 2 + random.nextInt(5); j++) {
                double r = random.nextDouble();
                if (r < 0.3 && abiertos < pares) {
                    expr.append('(');
                    abiertos++;
                } else if (r < 0.5 && abiertos > 0) {
                    expr.append(')');
                    abiertos--;
                } else if (r < 0.7) {
                    expr.append(tokens[random.nextInt(tokens.length)]);
                } else {
                    expr.append(ops[random.nextInt(ops.length)]);
                }
            }
            // Cerrar paréntesis pendientes
            while (abiertos > 0) {
                expr.append(')');
                abiertos--;
            }

            validador.validar(expr.toString());
        }

        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}