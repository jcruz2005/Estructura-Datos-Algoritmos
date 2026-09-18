import java.util.Random;

/**
 * Suma todos los elementos de una matriz generada aleatoriamente.
 *
 * <p><b>Método: Recorrido Fila por Fila (Row-major order)</b></p>
 * <p>Se recorre la matriz iterando por cada fila y dentro de cada fila
 * por cada columna, de izquierda a derecha y de arriba a abajo.</p>
 *
 * <p><b>¿Por qué este método?</b></p>
 * <ul>
 *   <li>Simplicidad: dos bucles anidados fáciles de entender</li>
 *   <li>Cache-friendly: en memoria, las filas están contiguas</li>
 *   <li>Natural: forma en que pensamos las matrices</li>
 * </ul>
 *
 * <p><b>Complejidad:</b></p>
 * <ul>
 *   <li>Siempre O(n×m) — debemos visitar cada elemento exactamente una vez</li>
 *   <li>Espacio: O(1) — solo una variable acumuladora</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class SumaMatriz {

    /**
     * Suma todos los elementos de la matriz usando recorrido fila por fila.
     *
     * @param matriz Matriz de enteros
     * @return Suma total de todos los elementos
     */
    public static int sumarMatriz(int[][] matriz) {
        int suma = 0;
        int operaciones = 0; // Contador de operaciones

        for (int i = 0; i < matriz.length; i++) { // Recorre filas
            for (int j = 0; j < matriz[i].length; j++) { // Recorre columnas
                suma += matriz[i][j]; // Acumula el valor
                operaciones++; // Cada acceso + suma es una operación
            }
        }

        System.out.println("  Total de operaciones (accesos + sumas): " + operaciones);
        return suma;
    }

    /**
     * Imprime la matriz en formato tabular.
     *
     * @param matriz Matriz a imprimir
     */
    public static void imprimirMatriz(int[][] matriz) {
        System.out.print("  ┌");
        for (int j = 0; j < matriz[0].length; j++) {
            System.out.print("────");
            if (j < matriz[0].length - 1) System.out.print("┬");
        }
        System.out.println("┐");

        for (int i = 0; i < matriz.length; i++) {
            System.out.print("  │");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(String.format(" %2d", matriz[i][j]));
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
        System.out.println("║     SUMA DE ELEMENTOS DE UNA MATRIZ                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Matriz (" + FILAS + "x" + COLUMNAS + ") | Rango: 1-" + MAX_VALOR);
        System.out.println();
        imprimirMatriz(matriz);

        // =================================================================
        // SUMA CON RECORRIDO FILA POR FILA
        // =================================================================
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  RECORRIDO: FILA POR FILA (Row-major)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();

        long inicio = System.nanoTime();
        int sumaTotal = sumarMatriz(matriz);
        long fin = System.nanoTime();
        long tiempoNs = fin - inicio;

        System.out.println();
        System.out.println("  Suma total: " + sumaTotal);
        System.out.println("  Tiempo: " + tiempoNs + " ns (" + String.format("%.3f", tiempoNs / 1_000_000.0) + " ms)");

        // =================================================================
        // ANÁLISIS DE COMPLEJIDAD
        // =================================================================
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            ANÁLISIS DE COMPLEJIDAD                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Caso          │ Elementos │ Complejidad │ Descripción    ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Mejor caso    │   n×m     │   O(n×m)    │ Siempre debe   ║");
        System.out.println("║  Peor caso     │   n×m     │   O(n×m)    │ recorrer todos  ║");
        System.out.println("║  Promedio      │   n×m     │   O(n×m)    │ los elementos  ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ESPACIO: O(1) — solo una variable acumuladora            ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  OPERACIONES REALIZADAS:                                  ║");
        System.out.println("║  • Accesos a memoria: " + (FILAS * COLUMNAS) + " (uno por cada elemento)         ║");
        System.out.println("║  • Sumas: " + (FILAS * COLUMNAS) + " (una por cada elemento)                  ║");
        System.out.println("║  • Total: " + (FILAS * COLUMNAS * 2) + " operaciones                             ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ¿POR QUÉ FILA POR FILA?                                 ║");
        System.out.println("║  • Simple: dos bucles anidados                            ║");
        System.out.println("║  • Cache-friendly: filas contiguas en memoria             ║");
        System.out.println("║  • Natural: así pensamos las matrices                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
