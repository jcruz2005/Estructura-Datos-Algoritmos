/**
 * Implementa el algoritmo de ordenamiento Insertion Sort para cadenas de texto.
 *
 * <p><b>¿Qué es Insertion Sort?</b></p>
 * <p>Algoritmo que ordena tomando cada elemento e insertándolo en su posición
 * correcta dentro de la parte ya ordenada del arreglo. Funciona como ordenar
 * cartas en la mano: tomás una carta y la insertás en el lugar correcto.</p>
 *
 * <p><b>Ordenamiento case-insensitive:</b></p>
 * <p>Usa compareToIgnoreCase() para comparar nombres ignorando mayúsculas/
 * minúsculas. Esto significa que "Ana" y "ana" se consideran iguales, y "clara"
 * y "CLARA" también. El orden resultante es puramente alfabético.</p>
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
public class InsertionSortNombres {

    /** Contador de movimientos realizados (inserciones) */
    private static int movimientos = 0;

    /** Contador de comparaciones realizadas */
    private static int comparaciones = 0;

    /**
     * Ordena el vector de nombres usando Insertion Sort con comparación
     * case-insensitive. Muestra cada paso del proceso de inserción.
     *
     * @param arr Vector de nombres a ordenar
     */
    public static void insertionSort(String[] arr) {
        int n = arr.length;
        movimientos = 0;
        comparaciones = 0;

        // Empezamos desde la posición 1 (el primero ya está "ordenado")
        for (int i = 1; i < n; i++) {
            String clave = arr[i]; // Nombre a insertar
            int j = i - 1;

            System.out.println("  ━━━ INSERCIÓN " + i + ": \"" + clave + "\" ━━━");

            // Mover elementos que van después alfabéticamente que la clave
            // compareToIgnoreCase() retorna > 0 si la clave va antes
            while (j >= 0 && arr[j].compareToIgnoreCase(clave) > 0) {
                comparaciones++;
                arr[j + 1] = arr[j]; // Desplazar a la derecha
                movimientos++;
                System.out.println("    → Mover \"" + arr[j] + "\" de pos[" + j + "] a pos[" + (j + 1) + "]");
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
                System.out.println("    → Insertar \"" + clave + "\" en pos[" + (j + 1) + "]");
            } else {
                System.out.println("    → \"" + clave + "\" ya está en posición correcta (pos[" + (j + 1) + "])");
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
    public static void imprimirVector(String[] arr) {
        System.out.print("    [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("\"" + arr[i] + "\"");
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        String[] nombres = {"pedro", "Luis", "EVA", "clara", "Ana"};

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║   INSERTION SORT - ORDENAMIENTO DE NOMBRES (NOMBRES)     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Este ejemplo ordena nombres de forma alfabética (case-insensitive).");
        System.out.println("Usa compareToIgnoreCase() para ignorar mayúsculas/minúsculas.");
        System.out.println();
        System.out.println("Vector original (mezcla de mayúsculas y minúsculas):");
        imprimirVector(nombres);

        // =================================================================
        // ORDENAMIENTO
        // =================================================================
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  PROCESO DE ORDENAMIENTO");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();
        System.out.println("  Lógica: Tomamos cada nombre y lo insertamos en su");
        System.out.println("  posición correcta dentro de la parte ya ordenada.");
        System.out.println("  Comparación: compareToIgnoreCase() ignora mayúsculas.");
        System.out.println();

        long inicio = System.nanoTime();
        insertionSort(nombres);
        long fin = System.nanoTime();
        long tiempoNs = fin - inicio;

        System.out.println();
        System.out.println("Vector ordenado alfabéticamente:");
        imprimirVector(nombres);

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
        System.out.println("║  SOBRE compareToIgnoreCase():                             ║");
        System.out.println("║  • Compara caracteres ignorando mayúsculas/minúsculas    ║");
        System.out.println("║  • \"Ana\" vs \"ana\" → 0 (iguales)                         ║");
        System.out.println("║  • \"Ana\" vs \"EVA\" → negativo (Ana va antes)             ║");
        System.out.println("║  • \"pedro\" vs \"Luis\" → positivo (Luis va antes)         ║");
        System.out.println("║  • Resultado: orden alfabético puro, sin distinción      ║");
        System.out.println("║    entre mayúsculas y minúsculas                         ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  CASOS DE USO IDEALES:                                   ║");
        System.out.println("║  • Arreglos pequeños (n < 50)                            ║");
        System.out.println("║  • Datos casi ordenados (ej: agregar pocos nombres)      ║");
        System.out.println("║  • Datos que llegan en tiempo real (streaming)           ║");
        System.out.println("║  • Como sub-algoritmo en Quick Sort (particiones pequeñas)║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
