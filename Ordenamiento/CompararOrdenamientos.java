import java.util.Random;

/**
 * Compara dos algoritmos de ordenamiento: Bubble Sort y Selection Sort.
 *
 * <p><b>Bubble Sort:</b></p>
 * <ul>
 *   <li>Compara elementos adyacentes</li>
 *   <li>Intercambia si están en orden incorrecto</li>
 *   <li>"Burbujea" el mayor hacia el final</li>
 *   <li>Más intercambios, pero estable</li>
 * </ul>
 *
 * <p><b>Selection Sort:</b></p>
 * <ul>
 *   <li>Busca el mínimo元素</li>
 *   <li>Lo pone al inicio</li>
 *   <li>Pocos intercambios (n-1 máximo)</li>
 *   <li>No es estable</li>
 * </ul>
 *
 * <p><b>Complejidad:</b></p>
 * <ul>
 *   <li>Ambos: O(n²) tiempo, O(1) espacio</li>
 *   <li>Bubble Sort: más intercambios</li>
 *   <li>Selection Sort: menos intercambios</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class CompararOrdenamientos {

    /** Contadores para Bubble Sort */
    private static int bubbleComparaciones = 0;
    private static int bubbleIntercambios = 0;

    /** Contadores para Selection Sort */
    private static int selectionComparaciones = 0;
    private static int selectionIntercambios = 0;

    /**
     * Bubble Sort con contadores.
     *
     * @param arr Vector a ordenar
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        bubbleComparaciones = 0;
        bubbleIntercambios = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean huboIntercambio = false;

            for (int j = 0; j < n - 1 - i; j++) {
                bubbleComparaciones++;

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    bubbleIntercambios++;
                    huboIntercambio = true;

                    System.out.println("    Bubble: swap pos[" + j + "] y pos[" + (j + 1) +
                                       "] → [" + arr[j] + ", " + arr[j + 1] + "]");
                }
            }

            if (!huboIntercambio) break;
        }
    }

    /**
     * Selection Sort con contadores.
     *
     * @param arr Vector a ordenar
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        selectionComparaciones = 0;
        selectionIntercambios = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            // Buscar el mínimo
            for (int j = i + 1; j < n; j++) {
                selectionComparaciones++;

                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            // Intercambiar si encontró un nuevo mínimo
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;

                selectionIntercambios++;

                System.out.println("    Selection: swap pos[" + i + "] (valor " + arr[minIdx] +
                                   ") con pos[" + minIdx + "] (valor " + arr[i] + ")");
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

    /**
     * Crea una copia del vector.
     *
     * @param arr Vector original
     * @return Copia del vector
     */
    public static int[] copiarVector(int[] arr) {
        int[] copia = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copia[i] = arr[i];
        }
        return copia;
    }

    public static void main(String[] args) {
        Random random = new Random();
        final int N = random.nextInt(6) + 10; // 10 a 15 elementos
        final int MAX_VALOR = 50; // valores de 1 a 50

        // Generar vector aleatorio
        int[] vectorOriginal = new int[N];
        for (int i = 0; i < N; i++) {
            vectorOriginal[i] = random.nextInt(MAX_VALOR) + 1;
        }

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║     COMPARACIÓN: BUBBLE SORT vs SELECTION SORT              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Tamaño: " + N + " | Rango: 1-" + MAX_VALOR);
        System.out.println();
        System.out.println("Vector original:");
        imprimirVector(vectorOriginal);

        // =================================================================
        // BUBBLE SORT
        // =================================================================
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  BUBBLE SORT");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        int[] copiaBubble = copiarVector(vectorOriginal);
        long inicioBubble = System.nanoTime();
        bubbleSort(copiaBubble);
        long finBubble = System.nanoTime();
        long tiempoBubble = finBubble - inicioBubble;

        System.out.println();
        System.out.println("  Vector ordenado:");
        imprimirVector(copiaBubble);

        // =================================================================
        // SELECTION SORT
        // =================================================================
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  SELECTION SORT");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        int[] copiaSelection = copiarVector(vectorOriginal);
        long inicioSelection = System.nanoTime();
        selectionSort(copiaSelection);
        long finSelection = System.nanoTime();
        long tiempoSelection = finSelection - inicioSelection;

        System.out.println();
        System.out.println("  Vector ordenado:");
        imprimirVector(copiaSelection);

        // =================================================================
        // COMPARACIÓN
        // =================================================================
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                 COMPARACIÓN FINAL                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Métrica         │ Bubble Sort    │ Selection Sort          ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Comparaciones   │ " + String.format("%-14d", bubbleComparaciones) + "│ " + String.format("%-22d", selectionComparaciones) + "║");
        System.out.println("║  Intercambios    │ " + String.format("%-14d", bubbleIntercambios) + "│ " + String.format("%-22d", selectionIntercambios) + "║");
        System.out.println("║  Tiempo (ns)     │ " + String.format("%-14d", tiempoBubble) + "│ " + String.format("%-22d", tiempoSelection) + "║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Complejidad     │ O(n²)          │ O(n²)                   ║");
        System.out.println("║  Espacio         │ O(1)           │ O(1)                    ║");
        System.out.println("║  Estable         │ Sí             │ No                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  DIFERENCIAS:                                               ║");
        System.out.println("║  • Bubble: más intercambios, pero estable                   ║");
        System.out.println("║  • Selection: menos intercambios, no estable                ║");
        System.out.println("║  • Ambos: O(n²) tiempo, O(1) espacio                       ║");
        System.out.println("║  • Selection gana en intercambios                           ║");
        System.out.println("║  • Bubble gana en estabilidad                               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
