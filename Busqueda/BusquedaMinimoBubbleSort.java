import java.util.Random;

/**
 * Clase que demuestra la búsqueda del valor mínimo en un vector
 * utilizando el algoritmo de ordenamiento Bubble Sort.
 * 
 * <p>El programa genera un vector de N valores aleatorios entre 1 y 10000,
 * lo ordena ascendente usando Bubble Sort, y luego obtiene el mínimo
 * (que quedará en la posición 0 tras el ordenamiento).</p>
 * 
 * <p><b>Nota sobre eficiencia:</b> Bubble Sort tiene una complejidad
 * temporal de O(n²) en el peor y promedio caso, lo que lo hace ineficiente
 * para vectores grandes. Para simplemente encontrar el mínimo, existen
 * algoritmos mucho más eficientes con complejidad O(n).</p>
 * 
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class BusquedaMinimoBubbleSort {

    /**
     * Método principal que ejecuta la demostración.
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        // =====================================================================
        // 1. DEFINICIÓN DEL TAMAÑO DEL VECTOR
        // Se define cuántos elementos tendrá el vector.
        // Puedes modificar este valor para probar con diferentes tamaños.
        // =====================================================================
        final int N = 20; // Número de elementos del vector

        // =====================================================================
        // 2. GENERACIÓN DEL VECTOR CON VALORES ALEATORIOS
        // Se crea un vector de tamaño N y se llena con valores aleatorios
        // entre 1 y 10000 utilizando la clase Random de Java.
        // =====================================================================
        int[] vector = new int[N];
        Random random = new Random();

        for (int i = 0; i < N; i++) {
            // nextInt(10000) genera valores desde 0 hasta 9999.
            // Le sumamos 1 para que el rango sea de 1 a 10000.
            vector[i] = random.nextInt(10000) + 1;
        }

        // =====================================================================
        // 3. MOSTRAR EL VECTOR ORIGINAL
        // Se imprime el vector antes de ordenar para comparar después.
        // =====================================================================
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   BÚSQUEDA DEL MÍNIMO CON BUBBLE SORT       ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Vector original (N = " + N + "):");
        imprimirVector(vector);

        // =====================================================================
        // 4. ORDENAMIENTO CON BUBBLE SORT
        // 
        // EXPLICACIÓN DEL ALGORITMO:
        // Bubble Sort funciona comparando elementos adyacentes e
        // intercambiándolos si están en el orden incorrecto. En cada
        // pasada completa (iteración externa), el elemento más grande
        // "burbujea" hacia su posición final (al final del vector).
        // 
        // ¿POR QUÉ ES MENOS EFICIENTE?
        // - Realiza O(n²) comparaciones incluso en el mejor caso (con
        //   la implementación básica).
        // - Para un vector de N elementos, hace aproximadamente
        //   N*(N-1)/2 comparaciones en total.
        // - Cada pasada solo garantiza colocar UN elemento en su
        //   posición final, por lo que necesita N-1 pasadas.
        // - En contraste, encontrar el mínimo sin ordenar solo
        //   requiere recorrer el vector una vez: O(n).
        // - Otros algoritmos como Quick Sort (O(n log n)) o incluso
        //   Selection Sort (aunque también es O(n²), hace menos
        //   intercambios) son superiores.
        // 
        // COMPLEJIDAD:
        // - Peor caso:    O(n²) — vector ordenado inversamente
        // - Mejor caso:   O(n²) — sin optimización (se puede mejorar a O(n))
        // - Espacio:      O(1)  — ordena in-place
        // =====================================================================
        bubbleSort(vector);

        // =====================================================================
        // 5. MOSTRAR EL VECTOR ORDENADO
        // =====================================================================
        System.out.println();
        System.out.println("Vector después de Bubble Sort (ordenado ascendentemente):");
        imprimirVector(vector);

        // =====================================================================
        // 6. OBTENER EL VALOR MÍNIMO
        // Después de ordenar ascendentemente, el mínimo siempre estará
        // en la posición 0 del vector.
        // =====================================================================
        int minimo = vector[0];

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  Valor mínimo encontrado: " + String.format("%-20d", minimo) + "║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }

    /**
     * Implementa el algoritmo de ordenamiento Bubble Sort.
     * 
     * <p>Recorre el vector repetidamente, comparando elementos adyacentes
     * e intercambiándolos si el izquierdo es mayor que el derecho.</p>
     * 
     * <p>Complejidad temporal: O(n²) en peor y promedio caso.</p>
     * <p>Complejidad espacial: O(1) — ordena in-place.</p>
     * 
     * @param arr El vector de enteros a ordenar (se modifica in-place)
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        int comparaciones = 0; // Contador para demostrar la cantidad de operaciones
        int intercambios = 0;  // Contador de intercambios realizados

        // =====================================================================
        // PASADA EXTERNA: cada iteración coloca el siguiente elemento más
        // grande en su posición final.
        // Se necesitan (n-1) pasadas como máximo para ordenar el vector.
        // =====================================================================
        for (int i = 0; i < n - 1; i++) {

            boolean seIntercambio = false; // Bandera para detectar si ya está ordenado

            // =================================================================
            // PASADA INTERNA: compara elementos adyacentes desde el inicio
            // hasta el último elemento no ordenado (n-1-i).
            // Después de cada pasada externa, los últimos i elementos
            // ya están en su posición final, por eso restamos i.
            // =================================================================
            for (int j = 0; j < n - 1 - i; j++) {

                comparaciones++; // Cada comparación cuenta como una operación

                // =============================================================
                // COMPARACIÓN E INTERCAMBIO:
                // Si el elemento actual es mayor que el siguiente, se
                // intercambian para mover el valor más grande hacia la derecha.
                // =============================================================
                if (arr[j] > arr[j + 1]) {
                    // Intercambio usando una variable temporal (swap clásico)
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    seIntercambio = true; // Hubo al menos un intercambio
                    intercambios++;
                }
            }

            // =============================================================
            // OPTIMIZACIÓN: Si en una pasada completa no hubo ningún
            // intercambio, el vector ya está ordenado y podemos terminar.
            // Esta mejora reduce el mejor caso a O(n), pero el peor
            // y promedio caso siguen siendo O(n²).
            // =============================================================
            if (!seIntercambio) {
                System.out.println("  → El vector ya estaba parcialmente ordenado. " +
                                   "Finalización anticipada en pasada " + (i + 1) + ".");
                break;
            }
        }

        // =====================================================================
        // ESTADÍSTICAS DE RENDIMIENTO
        // Muestra cuántas comparaciones e intercambios se realizaron,
        // demostrando la ineficiencia de O(n²).
        // =====================================================================
        System.out.println();
        System.out.println("  Estadísticas de Bubble Sort:");
        System.out.println("  • Comparaciones realizadas: " + comparaciones);
        System.out.println("  • Intercambios realizados:  " + intercambios);
        System.out.println("  • Para n=" + n + ", el máximo de comparaciones posibles era: " +
                           (n * (n - 1) / 2) + " = n*(n-1)/2");
    }

    /**
     * Imprime los elementos del vector en formato de lista.
     * 
     * @param arr El vector a imprimir
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
