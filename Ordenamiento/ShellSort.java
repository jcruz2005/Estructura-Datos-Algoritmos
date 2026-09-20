import java.util.Random;

/**
 * Implementa el algoritmo de ordenamiento Shell Sort.
 *
 * <p><b>¿Qué es Shell Sort?</b></p>
 * <p>Variante mejorada del Insertion Sort que compara elementos separados por
 * un "gap" (distancia). Reduce el gap progresivamente hasta llegar a 1, donde
 * se comporta como un Insertion Sort final sobre un arreglo ya casi ordenado.</p>
 *
 * <p><b>¿Por qué es mejor que Insertion Sort?</b></p>
 * <ul>
 *   <li>Insertion Sort solo mueve elementos una posición a la vez → lento con desorden</li>
 *   <li>Shell Sort mueve elementos varias posiciones con gap grande → reduce desorden rápido</li>
 *   <li>En el paso final (gap=1), el arreglo ya está casi ordenado</li>
 *   <li>Muchos menos movimientos totales que Insertion Sort puro</li>
 * </ul>
 *
 * <p><b>Secuencia de gaps (Shell):</b> n/2, n/4, ..., 1</p>
 *
 * <p><b>Complejidad:</b></p>
 * <ul>
 *   <li>Mejor caso: O(n log n)</li>
 *   <li>Peor caso: O(n²) con secuencia de Shell clásica</li>
 *   <li>Promedio: O(n^1.3) con secuencia de Knuth</li>
 *   <li>Espacio: O(1) — in-place</li>
 * </ul>
 *
 * <p><b>Estabilidad:</b> No es estable (puede cambiar el orden de elementos iguales).</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ShellSort {

    /** Contador de comparaciones realizadas */
    private static int comparaciones = 0;

    /** Contador de intercambios realizados */
    private static int intercambios = 0;

    /**
     * Ordena el vector usando Shell Sort con secuencia n/2, n/4, ..., 1.
     * Muestra cada fase del proceso con su gap y el estado del vector.
     *
     * @param arr Vector a ordenar
     */
    public static void shellSort(int[] arr) {
        int n = arr.length;
        comparaciones = 0;
        intercambios = 0;

        System.out.println("  Fórmula del gap: n/2, n/4, ..., 1");
        System.out.println("  Gap inicial: " + (n / 2));
        System.out.println();

        int fase = 1;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            System.out.println("  ━━━ FASE " + fase + " — GAP = " + gap + " ━━━");
            System.out.println("  Comparando elementos separados por " + gap + " posiciones");
            System.out.println();

            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;

                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    comparaciones++;
                    arr[j] = arr[j - gap];
                    intercambios++;
                    System.out.println("    → Mover " + arr[j - gap] + " de pos[" + (j - gap) + "] a pos[" + j + "]");
                }

                if (j >= gap) {
                    comparaciones++;
                }

                arr[j] = temp;
            }

            System.out.println();
            System.out.print("  Vector después de fase " + fase + " (gap=" + gap + "): ");
            imprimirVector(arr);
            System.out.println();
            fase++;
        }
    }

    /**
     * Ordena el vector usando Insertion Sort clásico.
     * Se usa para comparar estadísticas con Shell Sort.
     *
     * @param arr Vector a ordenar
     * @return Arreglo con comparaciones [0] e intercambios [1]
     */
    public static int[] insertionSort(int[] arr) {
        int n = arr.length;
        int comps = 0;
        int swaps = 0;

        for (int i = 1; i < n; i++) {
            int clave = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > clave) {
                comps++;
                arr[j + 1] = arr[j];
                swaps++;
                j--;
            }

            if (j >= 0) {
                comps++;
            }

            arr[j + 1] = clave;
        }

        return new int[]{comps, swaps};
    }

    /**
     * Genera un arreglo con valores aleatorios entre min y max.
     *
     * @param size Tamaño del arreglo
     * @param min  Valor mínimo (inclusivo)
     * @param max  Valor máximo (inclusivo)
     * @return Arreglo con valores aleatorios
     */
    public static int[] generarAleatorio(int size, int min, int max) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(max - min + 1) + min;
        }
        return arr;
    }

    /**
     * Imprime el vector en formato de lista.
     *
     * @param arr Vector a imprimir
     */
    public static void imprimirVector(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Copia un arreglo (para no modificar el original).
     *
     * @param arr Arreglo original
     * @return Copia del arreglo
     */
    public static int[] copiarArreglo(int[] arr) {
        int[] copia = new int[arr.length];
        System.arraycopy(arr, 0, copia, 0, arr.length);
        return copia;
    }

    public static void main(String[] args) {
        int[] original = generarAleatorio(15, 1, 50);

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          SHELL SORT - ORDENAMIENTO POR SHELL             ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("  Shell Sort mejora Insertion Sort usando gaps grandes");
        System.out.println("  para mover elementos varias posiciones de un solo paso.");
        System.out.println("  Luego reduce el gap hasta 1 (Insertion Sort final).");
        System.out.println();
        System.out.println("  Tamaño del arreglo: " + original.length);
        System.out.println("  Rango de valores: 1 - 50");
        System.out.println();

        System.out.println("  Vector original (aleatorio):");
        System.out.print("    ");
        imprimirVector(original);
        System.out.println();

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  PROCESO DE ORDENAMIENTO - SHELL SORT");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();

        int[] arrShell = copiarArreglo(original);
        long inicioShell = System.nanoTime();
        shellSort(arrShell);
        long finShell = System.nanoTime();
        long tiempoShell = finShell - inicioShell;

        System.out.println("  Vector FINAL ordenado (Shell Sort):");
        System.out.print("    ");
        imprimirVector(arrShell);

        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  COMPARACIÓN CON INSERTION SORT");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();

        int[] arrInsertion = copiarArreglo(original);
        long inicioInsertion = System.nanoTime();
        int[] statsInsertion = insertionSort(arrInsertion);
        long finInsertion = System.nanoTime();
        long tiempoInsertion = finInsertion - inicioInsertion;

        System.out.println("  Vector ordenado (Insertion Sort):");
        System.out.print("    ");
        imprimirVector(arrInsertion);
        System.out.println();

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          ESTADÍSTICAS COMPARATIVAS                       ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Métrica          │  Shell Sort   │  Insertion Sort      ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Comparaciones    │  " + String.format("%-12d", comparaciones) + "│  " + String.format("%-12d", statsInsertion[0]) + "    ║");
        System.out.println("║  Intercambios     │  " + String.format("%-12d", intercambios) + "│  " + String.format("%-12d", statsInsertion[1]) + "    ║");
        System.out.println("║  Tiempo (ns)      │  " + String.format("%-12d", tiempoShell) + "│  " + String.format("%-12d", tiempoInsertion) + "    ║");
        System.out.println("║  Tiempo (ms)      │  " + String.format("%-12.3f", tiempoShell / 1_000_000.0) + "│  " + String.format("%-12.3f", tiempoInsertion / 1_000_000.0) + "    ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");

        double reductionComps = statsInsertion[0] > 0
                ? (1.0 - (double) comparaciones / statsInsertion[0]) * 100
                : 0;
        double reductionSwaps = statsInsertion[1] > 0
                ? (1.0 - (double) intercambios / statsInsertion[1]) * 100
                : 0;

        System.out.println("║  Reducción comparaciones: " + String.format("%.1f%%", reductionComps) + "                      ║");
        System.out.println("║  Reducción intercambios:  " + String.format("%.1f%%", reductionSwaps) + "                      ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ¿POR QUÉ SHELL SORT ES MEJOR?                           ║");
        System.out.println("║                                                          ║");
        System.out.println("║  1. Gap grande: Mueve elementos varias posiciones a la   ║");
        System.out.println("║     vez, reduciendo el desorden rápidamente               ║");
        System.out.println("║                                                          ║");
        System.out.println("║  2. Gap = 1: El Insertion Sort final trabaja sobre un    ║");
        System.out.println("║     arreglo ya casi ordenado → pocas comparaciones       ║");
        System.out.println("║                                                          ║");
        System.out.println("║  3. Insertion Sort solo mueve 1 posición a la vez →      ║");
        System.out.println("║     muchos movimientos con datos desordenados            ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ANÁLISIS DE COMPLEJIDAD                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Caso          │ Shell Sort │ Insertion Sort             ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Mejor caso    │ O(n log n) │ O(n)                       ║");
        System.out.println("║  Peor caso     │ O(n²)      │ O(n²)                      ║");
        System.out.println("║  Promedio      │ O(n^1.3)   │ O(n²)                      ║");
        System.out.println("║  Espacio       │ O(1)       │ O(1)                       ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  CASOS DE USO IDEALES:                                   ║");
        System.out.println("║  • Arreglos medianos-grandes (n > 50)                    ║");
        System.out.println("║  • Cuando se necesita mejor rendimiento que O(n²)        ║");
        System.out.println("║  • Datos desordenados (Insertion Sort sería muy lento)   ║");
        System.out.println("║  • No se requiere estabilidad                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
