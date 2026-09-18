import java.util.Random;
import java.util.Scanner;

/**
 * Clase que implementa la Búsqueda Lineal (o Secuencial) para encontrar
 * un elemento dentro de un vector desordenado.
 *
 * <p><b>¿Por qué Búsqueda Lineal?</b></p>
 * <p>En un vector desordenado, la búsqueda lineal es el ÚNICO método
 * que garantiza encontrar el elemento en O(n) sin preprocessamiento.</p>
 *
 * <p>Otras opciones como la Búsqueda Binaria requieren que el vector
 * esté ordenado previamente, lo que costaría O(n log n) extra.</p>
 *
 * <p><b>Complejidad:</b></p>
 * <ul>
 *   <li>Peor caso: O(n) — elemento al final o no existe</li>
 *   <li>Mejor caso: O(1) — elemento en la primera posición</li>
 *   <li>Promedio: O(n/2) ≈ O(n)</li>
 *   <li>Espacio: O(1) — no usa memoria adicional</li>
 * </ul>
 *
 * @author GitHub Copilot
 * @version 1.0
 */
public class BusquedaLineal {

    public static void main(String[] args) {

        // =====================================================================
        // 1. DEFINICIÓN DEL TAMAÑO DEL VECTOR
        // =====================================================================
        final int N = 15;

        // =====================================================================
        // 2. GENERACIÓN DEL VECTOR CON VALORES ALEATORIOS
        // Se crea un vector de N elementos con valores entre 1 y 100
        // para facilitar la visualización.
        // =====================================================================
        int[] vector = new int[N];
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            vector[i] = random.nextInt(100) + 1; // Valores de 1 a 100
        }

        // =====================================================================
        // 3. MOSTRAR EL VECTOR ORIGINAL
        // =====================================================================
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║     BÚSQUEDA LINEAL EN VECTOR DESORDENADO       ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Vector generado (N = " + N + "):");
        imprimirVector(vector);

        // =====================================================================
        // 4. SOLICITAR EL ELEMENTO A BUSCAR
        // Se usa Scanner para que el usuario ingrese el valor a buscar.
        // =====================================================================
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nIngrese el elemento a buscar: ");
        int elementoBuscado = scanner.nextInt();

        // =====================================================================
        // 5. EJECUTAR LA BÚSQUEDA LINEAL
        // Se llama al método que realiza la búsqueda secuencial.
        // =====================================================================
        System.out.println();
        System.out.println("Iniciando búsqueda lineal...");
        System.out.println("Comparando elemento con cada posición del vector:");
        System.out.println();

        int posicion = busquedaLineal(vector, elementoBuscado);

        // =====================================================================
        // 6. MOSTRAR RESULTADO
        // =====================================================================
        System.out.println();
        if (posicion != -1) {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║  Elemento " + String.format("%-3d", elementoBuscado) +
                               " encontrado en la posición: " + posicion + "       ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
        } else {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║  Elemento " + String.format("%-3d", elementoBuscado) +
                               " NO fue encontrado en el vector.       ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
        }

        scanner.close();
    }

    /**
     * Implementa el algoritmo de Búsqueda Lineal (Secuencial).
     *
     * <p>Recorre el vector posición por posición, comparando cada
     * elemento con el valor buscado. Si encuentra una coincidencia,
     * retorna el índice. Si termina de recorrer sin encontrar,
     * retorna -1.</p>
     *
     * <p><b>¿Por qué es ideal para vectores desordenados?</b></p>
     * <ul>
     *   <li>No requiere ordenamiento previo</li>
     *   <li>No necesita memoria adicional</li>
     *   <li>Funciona con cualquier tipo de datos</li>
     *   <li>Es simple de implementar y entender</li>
     *   <li>En el mejor caso es O(1)</li>
     * </ul>
     *
     * @param arr     Vector desordenado de enteros
     * @param target  Elemento a buscar
     * @return        Índice donde se encuentra el elemento, o -1 si no existe
     */
    public static int busquedaLineal(int[] arr, int target) {
        int comparaciones = 0; // Contador de comparaciones realizadas

        // =================================================================
        // RECORRIDO SECUENCIAL: se compara cada elemento del vector
        // con el elemento buscado, desde la posición 0 hasta N-1.
        //
        // En el peor caso (elemento al final o inexistente), se realizan
        // N comparaciones. En el mejor caso (primera posición), solo 1.
        // =================================================================
        for (int i = 0; i < arr.length; i++) {
            comparaciones++; // Cada iteración es una comparación

            // =============================================================
            // COMPARACIÓN: si el elemento actual coincide con el buscado,
            // se retorna el índice inmediatamente (fin de la búsqueda).
            // =============================================================
            if (arr[i] == target) {
                System.out.println("  Posición " + i + ": " + arr[i] +
                                   " ← ¡COINCIDENCIA! (Comparación #" + comparaciones + ")");
                System.out.println();
                System.out.println("  Total de comparaciones realizadas: " + comparaciones);
                return i; // Retorna el índice donde se encontró
            }

            // Mostrar cada comparación realizada para fines educativos
            System.out.println("  Posición " + i + ": " + arr[i] +
                               " ≠ " + target + " (Comparación #" + comparaciones + ")");
        }

        // =================================================================
        // SI NO SE ENCONTRÓ: se recorrió todo el vector sin éxito.
        // =================================================================
        System.out.println();
        System.out.println("  Se recorrió todo el vector (" + comparaciones +
                           " comparaciones). Elemento no encontrado.");
        return -1; // Convención: -1 indica que no existe
    }

    /**
     * Imprime los elementos del vector en formato de lista.
     *
     * @param arr Vector a imprimir
     */
    public static void imprimirVector(int[] arr) {
        System.out.print("  [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
