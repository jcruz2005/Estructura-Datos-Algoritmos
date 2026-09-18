import java.util.Scanner;

/**
 * Calcula la suma descendente de un número hasta 1 usando recursividad.
 * Fórmula: n + (n-1) + (n-2) + ... + 1
 * Utiliza long para soportar números hasta ~4 mil millones.
 * @author SANCHEZ SOLANO, Juan Cruz
 */
public class RecursividadSuma {

    /** Límite máximo de recursión para evitar StackOverflowError */
    private static final int MAX_RECURSION = 5000;

    /** Contador de llamadas recursivas */
    private static int contadorRecursiones = 0;

    /**
     * Calcula la suma descendente de forma recursiva.
     * Caso base: 0 = 0, 1 = 1
     * Caso recursivo: suma(n) = n + suma(n-1)
     *
     * @param n número entero no negativo
     * @return suma de n hasta 1
     */
    public static long suma(int n) {
        contadorRecursiones++;

        // Caso base
        if (n <= 0) {
            return 0;
        }

        // Caso recursivo: n + suma(n-1)
        return n + suma(n - 1);
    }

    /**
     * Genera la expresión de la suma: n + (n-1) + ... + 1
     */
    public static String formatearExpresion(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            sb.append(i);
            if (i > 1) {
                sb.append(" + ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║    SUMA DESCENDENTE RECURSIVA (n + ... + 1) ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        // Entrada
        System.out.print("Ingrese un número entero no negativo: ");
        int n = scanner.nextInt();

        // Validación
        if (n < 0) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║  ERROR: No se permiten números negativos    ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            scanner.close();
            return;
        }

        if (n > MAX_RECURSION) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║  ERROR: El número excede el límite máximo   ║");
            System.out.println("║  Máximo permitido: " + MAX_RECURSION + " (por pila de Java)   ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            scanner.close();
            return;
        }

        // Cálculo
        long tiempoInicio = System.nanoTime();
        long resultado = suma(n);
        long tiempoFin = System.nanoTime();
        double tiempoMs = (tiempoFin - tiempoInicio) / 1_000_000.0;

        // Salida
        System.out.println();
        if (n == 0) {
            System.out.println("  Suma(0) = 0");
        } else {
            System.out.println("  Expresión: " + formatearExpresion(n));
            System.out.println("  Resultado: " + n + " + ... + 1 = " + resultado);
        }

        // Estadísticas
        String resultadoStr = String.valueOf(resultado);
        String digitos = resultadoStr.length() + " dígitos";

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║              ESTADÍSTICAS                    ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf("║  • Nivel máximo de recursión: %-14d║%n", contadorRecursiones);
        System.out.printf("║  • Cantidad de sumas: %-22d║%n", Math.max(0, contadorRecursiones - 1));
        System.out.printf("║  • Tamaño del resultado: %-19s║%n", digitos);
        System.out.printf("║  • Tiempo de ejecución: %-19s║%n", String.format("%.2f ms", tiempoMs));
        System.out.println("╚══════════════════════════════════════════════╝");

        scanner.close();
    }
}
