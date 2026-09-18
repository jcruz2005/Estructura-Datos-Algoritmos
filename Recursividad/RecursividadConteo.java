import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Realiza una cuenta regresiva desde n hasta 0 usando recursividad.
 * No retorna ningún valor, solo imprime los números por pantalla.
 * Caso base: n < 0 → se detiene sin hacer nada (return).
 * Caso recursivo: imprimir n y llamar a countdown(n-1).
 */
public class RecursividadConteo {

    /** Límite máximo de recursión para evitar StackOverflowError */
    private static final int MAX_RECURSION = 5000;

    /** Contador de llamadas recursivas */
    private static int contadorRecursiones = 0;

    /**
     * Cuenta regresiva desde n hasta 0 de forma recursiva.
     * No retorna valor (void), solo imprime cada número.
     *
     * Caso base: n < 0 → no hace nada (detiene la recursión)
     * Caso recursivo: imprime n y llama a countdown(n - 1)
     *
     * @param n número entero desde el cual empezar la cuenta regresiva
     */
    public static void countdown(int n) {
        // Caso base: si n es negativo, paramos
        if (n < 0) {
            return;
        }

        contadorRecursiones++;

        // Mostrar número actual
        System.out.println("  → " + n);

        // Caso recursivo: siguiente número
        countdown(n - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║      CUENTA REGRESIVA RECURSIVA (n → 0)     ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        try {
            // Entrada
            System.out.print("Ingrese un número entero no negativo: ");
            int n = scanner.nextInt();
            System.out.println();

            // Validación de número negativo
            if (n < 0) {
                System.out.println();
                System.out.println("╔══════════════════════════════════════════════╗");
                System.out.println("║  ERROR: No se permiten números negativos    ║");
                System.out.println("╚══════════════════════════════════════════════╝");
                return;
            }

            // Validación de límite de recursión
            if (n > MAX_RECURSION) {
                System.out.println();
                System.out.println("╔══════════════════════════════════════════════╗");
                System.out.println("║  ERROR: El número excede el límite máximo   ║");
                System.out.printf("║  Máximo permitido: %-27d║%n", MAX_RECURSION);
                System.out.println("╚══════════════════════════════════════════════╝");
                return;
            }

            // Reset del contador para evitar acumulación
            contadorRecursiones = 0;

            // Ejecución del conteo recursivo
            System.out.println();
            System.out.println("  ═══════════════════════════════════════════");
            long tiempoInicio = System.nanoTime();
            countdown(n);
            long tiempoFin = System.nanoTime();
            double tiempoMs = (tiempoFin - tiempoInicio) / 1_000_000.0;
            System.out.println("  ═══════════════════════════════════════════");

            // Estadísticas derivadas del counter real
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║              ESTADÍSTICAS                    ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.printf("║  • Llamadas recursivas efectivas: %-10d║%n", contadorRecursiones);
            System.out.printf("║  • Números impresos en pantalla: %-11d║%n", contadorRecursiones);
            System.out.printf("║  • Rango: %d → 0%-28s║%n", n, "");
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
