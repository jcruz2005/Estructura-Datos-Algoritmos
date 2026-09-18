import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Calcula el factorial de un número entero positivo usando recursividad.
 * Utiliza BigInteger para soportar números arbitrariamente grandes.
 */
public class RecursividadFactorial {

    /** Contador de llamadas recursivas */
    private static int contadorRecursiones = 0;

    /**
     * Calcula el factorial de forma recursiva.
     * Caso base: 0! = 1, 1! = 1
     * Caso recursivo: n! = n * (n-1)!
     *
     * @param n número entero positivo
     * @return factorial de n como BigInteger
     */
    public static BigInteger factorial(int n) {
        contadorRecursiones++;

        // Caso base
        if (n <= 1) {
            return BigInteger.ONE;
        }

        // Caso recursivo: n! = n * (n-1)!
        return BigInteger.valueOf(n).multiply(factorial(n - 1));
    }

    /**
     * Genera la lista de factores del 1 al n.
     *
     * @param n número entero positivo
     * @return lista de factores
     */
    public static ArrayList<Integer> getFactores(int n) {
        ArrayList<Integer> factores = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            factores.add(i);
        }
        return factores;
    }

    /**
     * Formatea la expresión de factorización: 1 × 2 × 3 × ... × n
     */
    public static String formatearExpresion(ArrayList<Integer> factores) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < factores.size(); i++) {
            sb.append(factores.get(i));
            if (i < factores.size() - 1) {
                sb.append(" × ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║       CÁLCULO RECURSIVO DE FACTORIAL         ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        // Entrada
        System.out.print("Ingrese un número entero positivo: ");
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

        // Cálculo
        long tiempoInicio = System.nanoTime();
        BigInteger resultado = factorial(n);
        long tiempoFin = System.nanoTime();
        double tiempoMs = (tiempoFin - tiempoInicio) / 1_000_000.0;

        // Lista de factores
        ArrayList<Integer> factores = getFactores(n);

        // Salida
        System.out.println();
        if (n <= 1) {
            System.out.println("  " + n + "! = 1");
        } else {
            System.out.println("  Factores: " + formatearExpresion(factores));
            System.out.println("  " + n + "! = " + resultado);
        }

        // Estadísticas
        String digitos = resultado.toString().length() + " dígitos";

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║              ESTADÍSTICAS                    ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.printf("║  • Nivel máximo de recursión: %-14d║%n", contadorRecursiones);
        System.out.printf("║  • Cantidad de multiplicaciones: %-11d║%n", Math.max(0, contadorRecursiones - 1));
        System.out.printf("║  • Tamaño del resultado: %-19s║%n", digitos);
        System.out.printf("║  • Tiempo de ejecución: %-19s║%n", String.format("%.2f ms", tiempoMs));
        System.out.println("╚══════════════════════════════════════════════╝");

        scanner.close();
    }
}
