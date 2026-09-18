import java.util.Random;

/**
 * Encuentra el mayor elemento de una matriz generada aleatoriamente.
 *
 * <p><b>Método: Recorrido Lineal (Fila por Fila)</b></p>
 * <p>Se recorre la matriz completa comparando cada elemento con el
 * máximo encontrado hasta el momento. Si un elemento es mayor,
 * se actualiza el máximo.</p>
 *
 * <p><b>¿Por qué recorrer TODOS los elementos?</b></p>
 * <ul>
 *   <li>La matriz no tiene orden, el máximo puede estar en cualquier posición</li>
 *   <li>No hay atajos: es imposible saber cuál es el máximo sin comparar todos</li>
 *   <li>Definición: el máximo es el más grande de TODOS los elementos</li>
 * </ul>
 *
 * <p><b>Complejidad:</b></p>
 * <ul>
 *   <li>Siempre O(n×m) — debemos visitar cada elemento exactamente una vez</li>
 *   <li>Espacio: O(1) — solo una variable para el máximo</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class MayorMatriz {

    /**
     * Encuentra el mayor elemento de la matriz mostrando cada comparación.
     *
     * @param matriz Matriz de enteros
     * @return Valor del elemento mayor
     */
    public static int encontrarMayor(int[][] matriz) {
        int max = matriz[0][0]; // Empezar con el primer elemento
        int filaMax = 0;
        int colMax = 0;
        int comparaciones = 0;

        System.out.println("  Inicio: max = " + max + " (posición [0][0])");
        System.out.println();

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                comparaciones++;

                // Saltar el primer elemento ya que es el inicial
                if (i == 0 && j == 0) {
                    continue;
                }

                System.out.println("  Comparación #" + comparaciones +
                                   ": matriz[" + i + "][" + j + "] = " + matriz[i][j] +
                                   " vs max = " + max);

                if (matriz[i][j] > max) {
                    max = matriz[i][j];
                    filaMax = i;
                    colMax = j;
                    System.out.println("    → ¡Nuevo máximo! max = " + max);
                } else {
                    System.out.println("    → No es mayor, se mantiene max = " + max);
                }
            }
        }

        System.out.println();
        System.out.println("  Total de comparaciones: " + comparaciones);
        System.out.println("  Posición del máximo: [" + filaMax + "][" + colMax + "]");

        return max;
    }

    /**
     * Imprime la matriz en formato tabular resaltando una posición.
     *
     * @param matriz Matriz a imprimir
     * @param filaResaltada Fila a resaltar (-1 para ninguna)
     * @param colResaltada Columna a resaltar (-1 para ninguna)
     */
    public static void imprimirMatriz(int[][] matriz, int filaResaltada, int colResaltada) {
        System.out.print("  ┌");
        for (int j = 0; j < matriz[0].length; j++) {
            System.out.print("────");
            if (j < matriz[0].length - 1) System.out.print("┬");
        }
        System.out.println("┐");

        for (int i = 0; i < matriz.length; i++) {
            System.out.print("  │");
            for (int j = 0; j < matriz[i].length; j++) {
                if (i == filaResaltada && j == colResaltada) {
                    System.out.print("*" + String.format("%2d", matriz[i][j]) + "*");
                } else {
                    System.out.print(" " + String.format("%2d", matriz[i][j]) + " ");
                }
                if (j < matriz[i].length - 1) System.out.print("│");
            }
            System.out.println("│");

            if (i < matriz.length - 1) {
                System.out.print("  ├");
                for (int j = 0; j < matriz[0].length; j++) {
                    System.out.print("────");
                    if (j < matriz[0].length - 1) System.out.print("┼");
                }
                System.out.println("┤");
            }
        }

        System.out.print("  └");
        for (int j = 0; j < matriz[0].length; j++) {
            System.out.print("────");
            if (j < matriz[0].length - 1) System.out.print("┴");
        }
        System.out.println("┘");
    }

    public static void main(String[] args) {
        Random random = new Random();
        final int FILAS = random.nextInt(3) + 3; // 3 a 5 filas
        final int COLUMNAS = random.nextInt(3) + 4; // 4 a 6 columnas
        final int MAX_VALOR = 20; // valores de 1 a 20

        // Generar matriz aleatoria
        int[][] matriz = new int[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                matriz[i][j] = random.nextInt(MAX_VALOR) + 1;
            }
        }

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     ENCONTRAR EL MAYOR ELEMENTO DE UNA MATRIZ           ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Matriz (" + FILAS + "x" + COLUMNAS + ") | Rango: 1-" + MAX_VALOR);
        System.out.println();
        System.out.println("Matriz original:");
        imprimirMatriz(matriz, -1, -1);

        // =================================================================
        // BÚSQUEDA DEL MAYOR
        // =================================================================
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  RECORRIDO: FILA POR FILA (Comparación con máximo)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();

        long inicio = System.nanoTime();
        int mayor = encontrarMayor(matriz);
        long fin = System.nanoTime();
        long tiempoNs = fin - inicio;

        // Encontrar posición del mayor para resaltar
        int filaMayor = -1, colMayor = -1;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == mayor) {
                    filaMayor = i;
                    colMayor = j;
                }
            }
        }

        System.out.println();
        System.out.println("Matriz con el mayor resaltado (*" + mayor + "*):");
        imprimirMatriz(matriz, filaMayor, colMayor);

        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            ANÁLISIS DE COMPLEJIDAD                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Caso          │ Operaciones │ Complejidad │ Justificación ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Mejor caso    │    n×m      │   O(n×m)    │ Debe comparar ║");
        System.out.println("║  Peor caso     │    n×m      │   O(n×m)    │ todos los     ║");
        System.out.println("║  Promedio      │    n×m      │   O(n×m)    │ elementos     ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ESPACIO: O(1) — solo 1 variable para el máximo          ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ¿POR QUÉ SIEMPRE O(n×m)?                               ║");
        System.out.println("║  El máximo puede estar en cualquier posición. Sin orden   ║");
        System.out.println("║  en la matriz, no hay atajos. Debemos revisar cada        ║");
        System.out.println("║  elemento para estar seguros de encontrar el mayor.       ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  TIEMPO: " + String.format("%.3f", tiempoNs / 1_000_000.0) + " ms (" + tiempoNs + " ns)                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
