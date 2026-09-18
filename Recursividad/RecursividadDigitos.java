import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Cuenta la cantidad de dígitos de un número entero positivo usando recursividad.
 * Caso base: si n < 10, retorna 1 (el número tiene un solo dígito).
 * Caso recursivo: 1 + digitos(n / 10) — se elimina el último dígito y se suma 1.
 *
 * Ejemplo: digitos(1234) → 1 + digitos(123) → 1 + 1 + digitos(12) → ... → 4
 * @author SANCHEZ SOLANO, Juan Cruz
 */
public class RecursividadDigitos {

    /** Contador de llamadas recursivas */
    private static int contadorRecursiones = 0;

    /**
     * Cuenta la cantidad de dígitos de un número entero positivo de forma recursiva.
     *
     * Caso base: n < 10 → retorna 1 (un solo dígito, incluye el 0)
     * Caso recursivo: 1 + digitos(n / 10)
     *
     * @param n número entero no negativo
     * @return cantidad de dígitos del número
     */
    public static int digitos(int n) {
        contadorRecursiones++;

        // Caso base: si tiene un solo dígito (0-9), retorna 1
        if (n < 10) {
            return 1;
        }

        // Caso recursivo: 1 dígito actual + los dígitos restantes
        return 1 + digitos(n / 10);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     CONTEO RECURSIVO DE DÍGITOS              ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        try {
            // Entrada
            System.out.print("Ingrese un número entero no negativo: ");
            int n = scanner.nextInt();
            System.out.println();

            // Validación de número negativo
            if (n < 0) {
                System.out.println("╔══════════════════════════════════════════════╗");
                System.out.println("║  ERROR: No se permiten números negativos    ║");
                System.out.println("╚══════════════════════════════════════════════╝");
                return;
            }

            // Reset del contador
            contadorRecursiones = 0;

            // Cálculo recursivo
            System.out.println("  ═══════════════════════════════════════════");
            long tiempoInicio = System.nanoTime();
            int cantidadDigitos = digitos(n);
            long tiempoFin = System.nanoTime();
            double tiempoMs = (tiempoFin - tiempoInicio) / 1_000_000.0;
            System.out.println("  ═══════════════════════════════════════════");

            // Salida del resultado
            System.out.println();
            System.out.println("  Resultado: " + n + " tiene " + cantidadDigitos + " dígito(s)");

            // Desglose del proceso recursivo
            System.out.println();
            System.out.println("  Proceso:");
            int aux = n;
            for (int i = 0; i < cantidadDigitos; i++) {
                if (aux < 10) {
                    System.out.println("    → " + aux + " < 10 → caso base → return 1");
                } else {
                    System.out.println("    → " + aux + " / 10 = " + (aux / 10) + " → 1 + digitos(" + (aux / 10) + ")");
                }
                aux = aux / 10;
            }

            // Estadísticas
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║              ESTADÍSTICAS                    ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.printf("║  • Llamadas recursivas efectivas: %-10d║%n", contadorRecursiones);
            System.out.printf("║  • Dígitos encontrados: %-19d║%n", cantidadDigitos);
            System.out.printf("║  • Número ingresado: %-22d║%n", n);
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
