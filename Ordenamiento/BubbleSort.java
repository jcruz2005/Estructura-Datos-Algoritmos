import java.util.Random;

/**
 * Implementa el algoritmo de ordenamiento Bubble Sort.
 *
 * <p><b>¿Qué es Bubble Sort?</b></p>
 * <p>Algoritmo que "burbujea" los elementos más grandes hacia el final,
 * comparando pares adyacentes y intercambiándolos si están desordenados.</p>
 *
 * <p><b>¿Por qué se usa para fines didácticos?</b></p>
 * <ul>
 *   <li>Simplicidad: fácil de entender e implementar</li>
 *   <li>Visual: se puede ver cómo "burbujean" los elementos</li>
 *   <li>Enseña conceptos: comparaciones, intercambios, complejidad</li>
 *   <li>Base para otros: entenderlo ayuda a aprender algoritmos más complejos</li>
 * </ul>
 *
 * <p><b>¿Por qué para conjuntos pequeños?</b></p>
 * <ul>
 *   <li>Sobrecarga baja: no tiene complejidad extra</li>
 *   <li>N cuadrado aceptable: para n < 100, O(n²) es suficiente</li>
 *   <li>In-place: no necesita memoria adicional</li>
 *   <li>Estable: mantiene el orden de elementos iguales</li>
 * </ul>
 *
 * <p><b>Complejidad:</b></p>
 * <ul>
 *   <li>Mejor caso (ordenado): O(n) — con early termination</li>
 *   <li>Peor caso (inverso): O(n²)</li>
 *   <li>Promedio: O(n²)</li>
 *   <li>Espacio: O(1) — in-place</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class BubbleSort {

    /** Contador de comparaciones realizadas */
    private static int comparaciones = 0;

    /** Contador de intercambios realizados */
    private static int intercambios = 0;

    /**
     * Ordena el vector usando Bubble Sort con contadores.
     *
     * @param arr Vector a ordenar
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        comparaciones = 0;
        intercambios = 0;

        for (int i = 0; i < n - 1; i++) {
            System.out.println("  ━━━ PASADA " + (i + 1) + " ━━━");

            boolean huboIntercambio = false; // Para early termination

            for (int j = 0; j < n - 1 - i; j++) {
                comparaciones++;

                System.out.println("    Comparar pos[" + j + "]=" + arr[j] +
                                   " con pos[" + (j + 1) + "]=" + arr[j + 1]);

                if (arr[j] > arr[j + 1]) {
                    // Intercambiar
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    intercambios++;
                    huboIntercambio = true;

                    System.out.println("    → SWAP! → [" + arr[j] + ", " + arr[j + 1] + "]");
                } else {
                    System.out.println("    → OK (no swap)");
                }
            }

            System.out.println("  Vector después de pasada " + (i + 1) + ":");
            imprimirVector(arr);
            System.out.println();

            // Early termination: si no hubo intercambios, ya está ordenado
            if (!huboIntercambio) {
                System.out.println("  ¡No hubo intercambios! Vector ya ordenado.");
                System.out.println("  (Early termination en pasada " + (i + 1) + ")");
                break;
            }
        }
    }

    /**
     * Imprime el vector en formato de lista.
     *
     * @param arr Vector a imprimir
     */
    public static void imprimirVector(int[] arr) {
        System.out.print("    [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Random random = new Random();
        final int N = random.nextInt(6) + 5; // 5 a 10 elementos
        final int MAX_VALOR = 20; // valores de 1 a 20

        // Generar vector aleatorio
        int[] vector = new int[N];
        for (int i = 0; i < N; i++) {
            vector[i] = random.nextInt(MAX_VALOR) + 1;
        }

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     BUBBLE SORT - ORDENAMIENTO POR BURBUJA              ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Tamaño: " + N + " | Rango: 1-" + MAX_VALOR);
        System.out.println();
        System.out.println("Vector original:");
        imprimirVector(vector);

        // =================================================================
        // ORDENAMIENTO
        // =================================================================
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  PROCESO DE ORDENAMIENTO");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();

        long inicio = System.nanoTime();
        bubbleSort(vector);
        long fin = System.nanoTime();
        long tiempoNs = fin - inicio;

        System.out.println();
        System.out.println("Vector ordenado:");
        imprimirVector(vector);

        // =================================================================
        // ANÁLISIS DE COMPLEJIDAD
        // =================================================================
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            ANÁLISIS DE COMPLEJIDAD                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Caso          │ Comparaciones │ Intercambios │ Complej.   ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Mejor (orden) │     n-1       │      0       │   O(n)     ║");
        System.out.println("║  Peor (rev.)   │  n(n-1)/2     │   n(n-1)/2   │   O(n²)    ║");
        System.out.println("║  Promedio      │  n(n-1)/2     │   n(n-1)/4   │   O(n²)    ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ESPACIO: O(1) — in-place, sin memoria adicional         ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ESTADÍSTICAS DE ESTA EJECUCIÓN:                         ║");
        System.out.println("║  • Comparaciones: " + String.format("%-6d", comparaciones) + "                           ║");
        System.out.println("║  • Intercambios:  " + String.format("%-6d", intercambios) + "                           ║");
        System.out.println("║  • Tiempo: " + String.format("%.3f", tiempoNs / 1_000_000.0) + " ms (" + tiempoNs + " ns)              ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ¿POR QUÉ BUBBLE SORT?                                   ║");
        System.out.println("║  • Didáctico: fácil de visualizar y entender              ║");
        System.out.println("║  • Simple: solo dos bucles anidados                       ║");
        System.out.println("║  • Estable: mantiene orden de elementos iguales           ║");
        System.out.println("║  • Small data: aceptable para n < 100                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
