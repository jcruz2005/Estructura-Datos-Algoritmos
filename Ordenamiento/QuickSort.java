import java.util.Random;

/**
 * Implementa el algoritmo de ordenamiento QuickSort usando el primer elemento como pivote.
 *
 * <p><b>¿Qué es QuickSort?</b></p>
 * <p>Algoritmo de divide y vencerás que particiona el array en dos subarreglos:
 * uno con elementos menores al pivote y otro con elementos mayores. Luego
 * ordena recursivamente cada subarreglo.</p>
 *
 * <p><b>¿Por qué usar el primer elemento como pivote?</b></p>
 * <ul>
 *   <li>Simplicidad: fácil de implementar y entender</li>
 *   <li>Educativo: muestra claramente el proceso de partición</li>
 *   <li>En arrays aleatorios, funciona razonablemente bien</li>
 * </ul>
 *
 * <p><b>Complejidad:</b></p>
 * <ul>
 *   <li>Mejor caso: O(n log n) — pivote divide equitativamente</li>
 *   <li>Peor caso: O(n²) — array ya ordenado o inverso</li>
 *   <li>Promedio: O(n log n)</li>
 *   <li>Espacio: O(log n) — por la recursión</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class QuickSort {

    /** Contador de comparaciones realizadas */
    private static int comparaciones = 0;

    /** Contador de intercambios realizados */
    private static int intercambios = 0;

    /** Contador de nivel de recursión */
    private static int nivelRecursion = 0;

    /**
     * Ordena el array usando QuickSort con el primer elemento como pivote.
     *
     * @param arr Array a ordenar
     * @param bajo Índice inferior
     * @param alto Índice superior
     */
    public static void quickSort(int[] arr, int bajo, int alto) {
        if (bajo < alto) {
            // Mostrar el subarreglo actual
            System.out.println("  ━━━ Nivel " + nivelRecursion + ": QuickSort([" + bajo + ".." + alto + "]) ━━━");
            System.out.print("    Subarreglo: ");
            imprimirSubarreglo(arr, bajo, alto);

            // Particionar y obtener la posición del pivote
            int pivoteIdx = particion(arr, bajo, alto);

            System.out.println("    Pivote: " + arr[pivoteIdx] + " (posición " + pivoteIdx + ")");
            System.out.print("    Izquierdo: ");
            if (pivoteIdx > bajo) {
                imprimirSubarreglo(arr, bajo, pivoteIdx - 1);
            } else {
                System.out.println("[]");
            }
            System.out.print("    Derecho:   ");
            if (pivoteIdx < alto) {
                imprimirSubarreglo(arr, pivoteIdx + 1, alto);
            } else {
                System.out.println("[]");
            }
            System.out.println();

            // Recursión izquierda
            nivelRecursion++;
            quickSort(arr, bajo, pivoteIdx - 1);
            nivelRecursion--;

            // Recursión derecha
            nivelRecursion++;
            quickSort(arr, pivoteIdx + 1, alto);
            nivelRecursion--;
        }
    }

    /**
     * Particiona el array usando el primer elemento como pivote.
     * Elementos menores al pivote van a la izquierda, mayores a la derecha.
     *
     * @param arr Array a particionar
     * @param bajo Índice inferior
     * @param alto Índice superior
     * @return Posición final del pivote
     */
    public static int particion(int[] arr, int bajo, int alto) {
        int pivote = arr[bajo]; // Primer elemento como pivote
        int i = bajo + 1; // Indice para elementos menores

        System.out.println("    ─── Partición: pivote = " + pivote + " ───");

        for (int j = bajo + 1; j <= alto; j++) {
            comparaciones++;

            if (arr[j] < pivote) {
                // Intercambiar arr[i] con arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                intercambios++;

                System.out.println("      Comparar " + arr[j] + " < " + pivote + " → swap pos[" + i + "] y pos[" + j + "]");
                i++;
            } else {
                System.out.println("      Comparar " + arr[j] + " >= " + pivote + " → no swap");
            }
        }

        // Colocar el pivote en su posición final
        int temp = arr[bajo];
        arr[bajo] = arr[i - 1];
        arr[i - 1] = temp;
        intercambios++;

        System.out.println("      Pivote " + pivote + " → posición " + (i - 1));
        System.out.print("      Resultado: ");
        imprimirSubarreglo(arr, bajo, alto);
        System.out.println();

        return i - 1;
    }

    /**
     * Imprime un subarreglo en formato de lista.
     *
     * @param arr Array
     * @param bajo Índice inferior
     * @param alto Índice superior
     */
    public static void imprimirSubarreglo(int[] arr, int bajo, int alto) {
        System.out.print("[");
        for (int i = bajo; i <= alto; i++) {
            System.out.print(arr[i]);
            if (i < alto) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Imprime el array completo en formato de lista.
     *
     * @param arr Array a imprimir
     */
    public static void imprimirArray(int[] arr) {
        System.out.print("  [");
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
        final int N = random.nextInt(4) + 7; // 7 a 10 elementos
        final int MAX_VALOR = 30; // valores de 1 a 30

        // Generar array aleatorio
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = random.nextInt(MAX_VALOR) + 1;
        }

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║     QUICKSORT - ORDENAMIENTO RÁPIDO (PIVOTE: PRIMER ELEM.) ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Tamaño: " + N + " | Rango: 1-" + MAX_VALOR);
        System.out.println("Pivote: Primer elemento del subarreglo");
        System.out.println();
        System.out.println("Array original:");
        imprimirArray(arr);

        // =================================================================
        // ORDENAMIENTO
        // =================================================================
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  PROCESO DE ORDENAMIENTO");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();

        long inicio = System.nanoTime();
        quickSort(arr, 0, arr.length - 1);
        long fin = System.nanoTime();
        long tiempoNs = fin - inicio;

        System.out.println();
        System.out.println("Array ordenado:");
        imprimirArray(arr);

        // =================================================================
        // ANÁLISIS DE COMPLEJIDAD
        // =================================================================
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║            ANÁLISIS DE COMPLEJIDAD                          ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Caso          │ Comparaciones │ Intercambios │ Complejidad ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Mejor         │  n log n      │  n log n     │ O(n log n)  ║");
        System.out.println("║  Promedio      │  n log n      │  n log n     │ O(n log n)  ║");
        System.out.println("║  Peor          │  n²           │  n²          │ O(n²)       ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ESPACIO: O(log n) — por la recursión                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ESTADÍSTICAS DE ESTA EJECUCIÓN:                           ║");
        System.out.println("║  • Comparaciones: " + String.format("%-6d", comparaciones) + "                             ║");
        System.out.println("║  • Intercambios:  " + String.format("%-6d", intercambios) + "                             ║");
        System.out.println("║  • Tiempo: " + String.format("%.3f", tiempoNs / 1_000_000.0) + " ms (" + tiempoNs + " ns)                ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ¿POR QUÉ QUICKSORT?                                       ║");
        System.out.println("║  • Divide y vencerás: particiona en dos mitades             ║");
        System.out.println("║  • Pivote: separa menores de mayores                        ║");
        System.out.println("║  • Recursión: ordena cada subarreglo independientemente      ║");
        System.out.println("║  • Promedio O(n log n) lo hace muy eficiente                ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  NOTA: El pivote (primer elemento) puede causar O(n²)       ║");
        System.out.println("║  en arrays ordenados. Usar pivote aleatorio mejora esto.    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
