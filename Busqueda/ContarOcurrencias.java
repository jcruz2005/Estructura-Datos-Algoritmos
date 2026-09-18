import java.util.Random;

/**
 * Cuenta cuántas veces aparece un valor específico en un vector.
 *
 * <p><b>Análisis de búsqueda para contar ocurrencias:Buen</b></p>
 * <ul>
 *   <li><b>Búsqueda Lineal:</b> Recorre TODO el vector → O(n). Es la única
 *       opción viable para vectores desordenados.</li>
 *   <li><b>Búsqueda Binaria:</b> Solo encuentra UNA ocurrencia → O(log n),
 *       pero requiere vector ordenado y lógica adicional para contar todas.</li>
 * </ul>
 *
 * <p><b>Conclusión:</b> La Búsqueda Lineal es la mejor opción porque:</p>
 * <ul>
 *   <li>Debemos recorrer el vector completo para contar todas las ocurrencias</li>
 *   <li>No requiere ordenamiento previo</li>
 *   <li>Es simple y eficiente para el problema planteado</li>
 * </ul>
 *
 * <p><b>Complejidad:</b></p>
 * <ul>
 *   <li>Siempre O(n) — se recorre todo el vector sin importar el resultado</li>
 *   <li>Espacio: O(1) — solo usa un contador adicional</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ContarOcurrencias {

    /**
     * Cuenta cuántas veces aparece el valor en el vector.
     *
     * @param arr   Vector de enteros
     * @param value Valor a buscar
     * @return      Cantidad de veces que aparece el valor
     */
    public static int contarOcurrencias(int[] arr, int value) {
        int contador = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                contador++;
            }
        }

        return contador;
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

    public static void main(String[] args) {
        Random random = new Random();
        final int N = random.nextInt(8) + 8; // 8 a 15 elementos

        int[] vector = new int[N];
        for (int i = 0; i < N; i++) {
            vector[i] = random.nextInt(10) + 1; // valores de 1 a 10
        }

        int valorBuscado = random.nextInt(10) + 1;

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║     CONTAR OCURRENCIAS EN VECTOR                ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Vector (N = " + N + "):");
        imprimirVector(vector);
        System.out.println();
        System.out.println("Valor buscado: " + valorBuscado);

        int ocurrencias = contarOcurrencias(vector, valorBuscado);

        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║  El valor " + String.format("%-2d", valorBuscado) +
                           " aparece " + String.format("%-2d", ocurrencias) +
                           " vez/veces en el vector.     ║");
        System.out.println("╚══════════════════════════════════════════════════╝");

        // =================================================================
        // ANÁLISIS DE COMPLEJIDAD
        // =================================================================
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            ANÁLISIS DE COMPLEJIDAD                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Caso          │ Comparaciones │ Complejidad │ Justificación ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Mejor caso    │      n        │    O(n)     │ Debe recorrer  ║");
        System.out.println("║  Peor caso     │      n        │    O(n)     │ todo para      ║");
        System.out.println("║  Promedio      │      n        │    O(n)     │ contar TODAS   ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ESPACIO: O(1) — solo un contador adicional               ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ¿POR QUÉ SIEMPRE O(n)?                                  ║");
        System.out.println("║  Para contar ocurrencias, el algoritmo DEBE revisar       ║");
        System.out.println("║  cada posición del vector. No se puede determinar la      ║");
        System.out.println("║  cantidad total sin recorrerlo completo. Aunque el valor  ║");
        System.out.println("║  aparezca solo 1 vez o en todas las posiciones, siempre   ║");
        System.out.println("║  serán n comparaciones.                                   ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ¿POR QUÉ NO BÚSQUEDA BINARIA?                           ║");
        System.out.println("║  1) Requiere vector ordenado → costo O(n log n) extra     ║");
        System.out.println("║  2) Solo encuentra UNA ocurrencia → lógica adicional      ║");
        System.out.println("║  3) Total: O(n log n) + O(log n + k) → peor que O(n)     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
