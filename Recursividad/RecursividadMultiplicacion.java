import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Realiza la multiplicación de dos números enteros de forma recursiva
 * sin utilizar el operador *.
 * Utiliza sumas repetidas: a × b = a + a + ... + a (b veces)
 */
public class RecursividadMultiplicacion {

    /** Límite máximo de entrada para evitar overflow en sumas acumuladas */
    private static final int MAX_LIMITE = 100000;

    /** Contador de profundidad de recursión */
    private static int profundidadMaxima = 0;

    /**
     * Multiplica dos números positivos usando sumas repetidas.
     * Caso base: b == 0 → retorna 0
     * Caso recursivo: a + multiplicar(a, b-1)
     *
     * @param a número a sumar repetidamente (debe ser positivo)
     * @param b número de veces que se suma a (debe ser positivo)
     * @return resultado de la multiplicación
     */
    public static long multiplicar(int a, int b) {
        profundidadMaxima++;

        // Caso base: cualquier número por 0 es 0
        if (b == 0 || a == 0) {
            return 0;
        }

        // Caso recursivo: a + multiplicar(a, b-1)
        return a + multiplicar(a, b - 1);
    }

    /**
     * Genera el proceso de la multiplicación como string.
     * Ejemplo: multiplicar(5, 3) → "5 + 5 + 5"
     *
     * @param a número a repetir
     * @param b cantidad de repeticiones (debe ser positivo)
     * @return string con el proceso de sumas
     */
    public static String getProceso(int a, int b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b; i++) {
            sb.append(a);
            if (i < b - 1) {
                sb.append(" + ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   MULTIPLICACIÓN RECURSIVA (sin usar *)      ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        try {
            // Entrada
            System.out.print("Ingrese el primer número: ");
            int a = scanner.nextInt();
            System.out.print("Ingrese el segundo número: ");
            int b = scanner.nextInt();

            // Validación de límite
            if (Math.abs(a) > MAX_LIMITE || Math.abs(b) > MAX_LIMITE) {
                System.out.println();
                System.out.println("╔══════════════════════════════════════════════╗");
                System.out.println("║  ERROR: El número excede el límite máximo   ║");
                System.out.println("║  Límite permitido: " + MAX_LIMITE + "                    ║");
                System.out.println("╚══════════════════════════════════════════════╝");
                scanner.close();
                return;
            }

            // Determinar si el resultado será negativo (XOR: true si solo uno es negativo)
            boolean esNegativo = (a < 0) ^ (b < 0);

            // Trabajar con valores absolutos
            int absA = Math.abs(a);
            int absB = Math.abs(b);

            // Cálculo recursivo
            long tiempoInicio = System.nanoTime();
            long resultado = multiplicar(absA, absB);
            long tiempoFin = System.nanoTime();
            double tiempoMs = (tiempoFin - tiempoInicio) / 1_000_000.0;

            // Aplicar signo si es necesario
            if (esNegativo) {
                resultado = -resultado;
            }

            // Salida
            System.out.println();
            String signoA = a < 0 ? "(" + a + ")" : String.valueOf(a);
            String signoB = b < 0 ? "(" + b + ")" : String.valueOf(b);

            if (absB == 0 || absA == 0) {
                System.out.println("  " + signoA + " × " + signoB + " = 0");
            } else {
                System.out.println("  Proceso: " + signoA + " × " + signoB + " = " +
                                   getProceso(absA, absB) + " = " + resultado);
            }

            // Estadísticas
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║              ESTADÍSTICAS                    ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.printf("║  • Profundidad máxima de recursión: %-9d║%n", profundidadMaxima);
            System.out.printf("║  • Valor absoluto: %d × %-19d║%n", absA, absB);
            System.out.printf("║  • Tiempo de ejecución: %-19s║%n", String.format("%.2f ms", tiempoMs));
            System.out.println("╚══════════════════════════════════════════════╝");

        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║  ERROR: Debe ingresar un número entero      ║");
            System.out.println("╚══════════════════════════════════════════════╝");
        } finally {
            scanner.close();
        }
    }
}
