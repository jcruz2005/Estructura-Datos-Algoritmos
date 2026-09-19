/**
 * Implementa el algoritmo de ordenamiento Insertion Sort.
 *
 * <p><b>¿Qué es Insertion Sort?</b></p>
 * <p>Algoritmo que ordena tomando cada elemento e insertándolo en su posición
 * correcta dentro de la parte ya ordenada del arreglo. Funciona como ordenar
 * cartas en la mano: tomás una carta y la insertás en el lugar correcto.</p>
 *
 * <p><b>¿Por qué funciona bien en arreglos casi ordenados?</b></p>
 * <ul>
 *   <li>Cuando el arreglo ya está ordenado, cada elemento se compara una sola
 *       vez y no necesita moverse → O(n)</li>
 *   <li>Si hay pocos elementos desordenados, solo esos necesitan inserción</li>
 *   <li>No recorre todo el arreglo como Bubble Sort</li>
 *   <li>En arreglos casi ordenados, las inserciones son mínimas</li>
 * </ul>
 *
 * <p><b>Complejidad:</b></p>
 * <ul>
 *   <li>Mejor caso (ordenado): O(n) — solo n-1 comparaciones, 0 movimientos</li>
 *   <li>Peor caso (inverso): O(n²) — cada elemento se compara y mueve mucho</li>
 *   <li>Promedio: O(n²)</li>
 *   <li>Espacio: O(1) — in-place, sin memoria adicional</li>
 * </ul>
 *
 * <p><b>Estabilidad:</b> Sí, es estable (mantiene el orden de elementos iguales).</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class InsertionSort {

    /** Contador de movimientos realizados (inserciones) */
    private static int movimientos = 0;

    /** Contador de comparaciones realizadas */
    private static int comparaciones = 0;

    /**
     * Ordena el vector usando Insertion Sort con contadores detallados.
     * Muestra cada paso del proceso de inserción.
     *
     * @param arr Vector a ordenar
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        movimientos = 0;
        comparaciones = 0;

        // Empezamos desde la posición 1 (el primero ya está "ordenado")
        for (int i = 1; i < n; i++) {
            int clave = arr[i]; // Elemento a insertar
            int j = i - 1;

            System.out.println("  ━━━ INSERCIÓN " + i + ": elemento " + clave + " ━━━");

            // Mover elementos mayores que la clave hacia la derecha
            // Comparamos mientras no lleguemos al inicio y el elemento sea mayor
            while (j >= 0 && arr[j] > clave) {
                comparaciones++;
                arr[j + 1] = arr[j]; // Desplazar a la derecha
                movimientos++;
                System.out.println("    → Mover " + arr[j] + " de pos[" + j + "] a pos[" + (j + 1) + "]");
                j--;
            }

            // Si salimos del while por comparación, contamos esa última
            if (j >= 0) {
                comparaciones++;
            }

            // Insertar la clave en su posición correcta
            arr[j + 1] = clave;

            // Verificar si realmente hubo movimiento
            if (j + 1 != i) {
                movimientos++;
                System.out.println("    → Insertar " + clave + " en pos[" + (j + 1) + "]");
            } else {
                System.out.println("    → " + clave + " ya está en posición correcta (pos[" + (j + 1) + "])");
            }

            System.out.println("  Vector después de inserción " + i + ":");
            imprimirVector(arr);
            System.out.println();
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
        // Arreglo casi ordenado: solo el 5 y 4 están intercambiados
        int[] numeros = {1, 2, 3, 5, 4, 6, 7};

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║     INSERTION SORT - ORDENAMIENTO POR INSERCIÓN          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Este ejemplo usa un arreglo CASO ORDENADO para demostrar");
        System.out.println("por qué Insertion Sort es ideal para datos casi ordenados.");
        System.out.println();
        System.out.println("Vector original (casi ordenado — solo 5 y 4 intercambiados):");
        imprimirVector(numeros);

        // =================================================================
        // ORDENAMIENTO
        // =================================================================
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  PROCESO DE ORDENAMIENTO");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();
        System.out.println("  Lógica: Tomamos cada elemento y lo insertamos en su");
        System.out.println("  posición correcta dentro de la parte ya ordenada.");
        System.out.println("  [1,2,3] ya ordenado → insertar 5 → sigue ordenado");
        System.out.println("  [1,2,3,5] → insertar 4 → va antes del 5");
        System.out.println();

        long inicio = System.nanoTime();
        insertionSort(numeros);
        long fin = System.nanoTime();
        long tiempoNs = fin - inicio;

        System.out.println();
        System.out.println("Vector ordenado:");
        imprimirVector(numeros);

        // =================================================================
        // ANÁLISIS DE COMPLEJIDAD
        // =================================================================
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            ANÁLISIS DE COMPLEJIDAD                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Caso          │ Comparaciones │ Movimientos  │ Complej.  ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Mejor (orden) │     n-1       │      0       │   O(n)    ║");
        System.out.println("║  Peor (rev.)   │  n(n-1)/2     │  n(n-1)/2    │   O(n²)   ║");
        System.out.println("║  Promedio      │  n(n-1)/2     │  n(n-1)/4    │   O(n²)   ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ESPACIO: O(1) — in-place, sin memoria adicional         ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ESTADÍSTICAS DE ESTA EJECUCIÓN:                         ║");
        System.out.println("║  • Comparaciones: " + String.format("%-6d", comparaciones) + "                           ║");
        System.out.println("║  • Movimientos:   " + String.format("%-6d", movimientos) + "                           ║");
        System.out.println("║  • Tiempo: " + String.format("%.3f", tiempoNs / 1_000_000.0) + " ms (" + tiempoNs + " ns)              ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ¿POR QUÉ INSERTION SORT EN CASOS CASO ORDENADOS?        ║");
        System.out.println("║  • Solo compara con elementos anteriores                 ║");
        System.out.println("║  • Si el elemento ya está en posición, no mueve nada     ║");
        System.out.println("║  • Arreglo casi ordenado → pocas comparaciones           ║");
        System.out.println("║  • Mejor caso O(n) lo hace ideal para datos parcialmente ║");
        System.out.println("║    ordenados (ej: datos que llegan por streams)          ║");
        System.out.println("║  • En este ejemplo: solo necesitó mover el 4 una posición║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  CASOS DE USO IDEALES:                                   ║");
        System.out.println("║  • Arreglos pequeños (n < 50)                            ║");
        System.out.println("║  • Datos casi ordenados (ej: agregar pocos elementos)    ║");
        System.out.println("║  • Datos que llegan en tiempo real (streaming)           ║");
        System.out.println("║  • Como sub-algoritmo en Quick Sort (particiones pequeñas)║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
