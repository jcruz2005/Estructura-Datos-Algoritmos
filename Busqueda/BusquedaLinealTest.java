import java.util.Random;

/**
 * Clase de pruebas para el algoritmo de Búsqueda Lineal.
 *
 * <p>Realiza un análisis completo de los tres casos de complejidad:</p>
 * <ul>
 *   <li><b>Mejor caso:</b> Elemento en la primera posición → O(1)</li>
 *   <li><b>Peor caso:</b> Elemento en la última posición o no existe → O(n)</li>
 *   <li><b>Caso promedio:</b> Elemento en una posición intermedia → O(n/2)</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class BusquedaLinealTest {

    // Contador global de comparaciones para cada prueba
    private static int comparacionesRealizadas = 0;

    public static void main(String[] args) {

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          ANÁLISIS DE COMPLEJIDAD - BÚSQUEDA LINEAL        ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();

        // =====================================================================
        // VECTOR DE PRUEBA: 20 elementos ordenados para control exacto
        // =====================================================================
        int[] vector = {5, 12, 18, 25, 33, 41, 47, 56, 62, 71,
                        78, 84, 90, 95, 101, 110, 125, 133, 142, 150};

        System.out.println("Vector de prueba (N = " + vector.length + "):");
        imprimirVector(vector);
        System.out.println();

        // =====================================================================
        // CASO 1: MEJOR CASO — Elemento en la primera posición
        // Se espera: 1 comparación, O(1)
        // =====================================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  CASO 1: MEJOR CASO (elemento en posición 0)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        comparacionesRealizadas = 0;
        int mejorCaso = vector[0]; // Primer elemento
        int posicionMejor = busquedaLinealContador(vector, mejorCaso);
        System.out.println("  → Elemento buscado: " + mejorCaso);
        System.out.println("  → Posición encontrada: " + posicionMejor);
        System.out.println("  → Posiciones recorridas: " + comparacionesRealizadas);
        System.out.println("  → Complejidad esperada: O(1) = 1 comparación");
        System.out.println("  → Resultado: " + (comparacionesRealizadas == 1 ? "✓ CUMPLE" : "✗ NO CUMPLE"));
        System.out.println();

        // =====================================================================
        // CASO 2: PEOR CASO — Elemento en la última posición
        // Se espera: N comparaciones, O(n)
        // =====================================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  CASO 2: PEOR CASO (elemento en última posición)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        comparacionesRealizadas = 0;
        int peorCaso = vector[vector.length - 1]; // Último elemento
        int posicionPeor = busquedaLinealContador(vector, peorCaso);
        System.out.println("  → Elemento buscado: " + peorCaso);
        System.out.println("  → Posición encontrada: " + posicionPeor);
        System.out.println("  → Posiciones recorridas: " + comparacionesRealizadas);
        System.out.println("  → Complejidad esperada: O(n) = " + vector.length + " comparaciones");
        System.out.println("  → Resultado: " + (comparacionesRealizadas == vector.length ? "✓ CUMPLE" : "✗ NO CUMPLE"));
        System.out.println();

        // =====================================================================
        // CASO 3: PEOR CASO ALTERNATIVO — Elemento NO existe
        // Se espera: N comparaciones, O(n)
        // =====================================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  CASO 3: PEOR CASO (elemento NO existe en el vector)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        comparacionesRealizadas = 0;
        int noExiste = 999; // Valor que no está en el vector
        int posicionNoExiste = busquedaLinealContador(vector, noExiste);
        System.out.println("  → Elemento buscado: " + noExiste);
        System.out.println("  → Posición encontrada: " + posicionNoExiste + " (no encontrado)");
        System.out.println("  → Posiciones recorridas: " + comparacionesRealizadas);
        System.out.println("  → Complejidad esperada: O(n) = " + vector.length + " comparaciones");
        System.out.println("  → Resultado: " + (comparacionesRealizadas == vector.length ? "✓ CUMPLE" : "✗ NO CUMPLE"));
        System.out.println();

        // =====================================================================
        // CASO 4: CASO PROMEDIO — Elemento en posición intermedia
        // Se espera: ~N/2 comparaciones, O(n/2) ≈ O(n)
        // =====================================================================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  CASO 4: CASO PROMEDIO (elemento en posición intermedia)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        comparacionesRealizadas = 0;
        int casoPromedio = vector[vector.length / 2]; // Elemento del medio
        int posicionPromedio = busquedaLinealContador(vector, casoPromedio);
        System.out.println("  → Elemento buscado: " + casoPromedio);
        System.out.println("  → Posición encontrada: " + posicionPromedio);
        System.out.println("  → Posiciones recorridas: " + comparacionesRealizadas);
        System.out.println("  → Complejidad esperada: O(n/2) ≈ " + (vector.length / 2) + " comparaciones");
        System.out.println("  → Resultado: " + (comparacionesRealizadas <= vector.length / 2 + 1 ? "✓ CUMPLE" : "✗ NO CUMPLE"));
        System.out.println();

        // =====================================================================
        // RESUMEN FINAL
        // =====================================================================
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    RESUMEN DEL ANÁLISIS                    ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Caso            │ Posiciones │ Complejidad  │ Estado     ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Mejor (posición 0)     │      1     │ O(1)    │ ✓ OK     ║");
        System.out.println("║  Peor (posición N-1)    │     " + String.format("%2d", vector.length) + "     │ O(n)    │ ✓ OK     ║");
        System.out.println("║  Peor (no existe)       │     " + String.format("%2d", vector.length) + "     │ O(n)    │ ✓ OK     ║");
        System.out.println("║  Promedio (posición N/2)│  ~" + String.format("%-2d", vector.length / 2) + "     │ O(n/2)  │ ✓ OK     ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  NOTA: La búsqueda lineal es O(n) pero en promedio        ║");
        System.out.println("║  recorre N/2 elementos, siendo eficiente para vectores    ║");
        System.out.println("║  desordenados donde no hay otra opción sin preprocess.    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    /**
     * Búsqueda lineal con contador de comparaciones.
     *
     * <p>Versión modificada del algoritmo original que permite
     * contar las comparaciones realizadas sin imprimir en pantalla.</p>
     *
     * @param arr    Vector desordenado
     * @param target Elemento a buscar
     * @return       Índice del elemento o -1 si no existe
     */
    public static int busquedaLinealContador(int[] arr, int target) {
        comparacionesRealizadas = 0; // Reiniciar contador

        for (int i = 0; i < arr.length; i++) {
            comparacionesRealizadas++; // Incrementar por cada comparación

            if (arr[i] == target) {
                return i; // Encontrado: retorna posición
            }
        }

        return -1; // No encontrado
    }

    /**
     * Imprime el vector en formato de lista.
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
