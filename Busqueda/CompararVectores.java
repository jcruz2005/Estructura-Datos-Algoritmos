import java.util.Random;

/**
 * Compara si dos vectores generados aleatoriamente son idénticos.
 *
 * <p><b>Método: Comparación Lineal Secuencial con Early Termination</b></p>
 * <p>Se comparan los vectores posición por posición. Si se encuentra
 * alguna diferencia, se detiene inmediatamente (early termination).</p>
 *
 * <p><b>¿Por qué este método?</b></p>
 * <ul>
 *   <li>Detecta diferencias en O(1) si están al inicio</li>
 *   <li>No necesita espacio adicional O(1)</li>
 *   <li>No requiere preprocessamiento</li>
 *   <li>Es el método más eficiente para este problema</li>
 * </ul>
 *
 * <p><b>Complejidad:</b></p>
 * <ul>
 *   <li>Mejor caso: O(1) — primera posición difiere</li>
 *   <li>Peor caso: O(n) — vectores idénticos, recorre todo</li>
 *   <li>Promedio: O(n/2) ≈ O(n) — diferencia en posición intermedia</li>
 *   <li>Espacio: O(1) — sin memoria adicional</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class CompararVectores {

    /**
     * Compara dos vectores y retorna true si son idénticos.
     * Se detiene en la primera diferencia encontrada.
     *
     * @param a Primer vector
     * @param b Segundo vector
     * @return true si son idénticos, false si difieren
     */
    public static boolean compararVectores(int[] a, int[] b) {
        // Si tienen distinto tamaño, no son iguales
        if (a.length != b.length) {
            return false;
        }

        // Comparación secuencial con early termination
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false; // Diferencia encontrada, salir inmediatamente
            }
        }

        return true; // Todos los elementos coinciden
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
        final int N = random.nextInt(6) + 5; // 5 a 10 elementos
        final int MAX_VALOR = 5; // Valores de 1 a 5

        // Generar dos vectores aleatorios
        int[] vectorA = new int[N];
        int[] vectorB = new int[N];

        for (int i = 0; i < N; i++) {
            vectorA[i] = random.nextInt(MAX_VALOR) + 1;
            vectorB[i] = random.nextInt(MAX_VALOR) + 1;
        }

        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║     COMPARACIÓN DE DOS VECTORES                 ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Tamaño: " + N + " | Rango: 1-" + MAX_VALOR);
        System.out.println();
        System.out.println("Vector A:");
        imprimirVector(vectorA);
        System.out.println("Vector B:");
        imprimirVector(vectorB);

        // Medición de tiempo
        long inicio = System.nanoTime();
        boolean sonIguales = compararVectores(vectorA, vectorB);
        long fin = System.nanoTime();
        long tiempoNs = fin - inicio;
        double tiempoMs = tiempoNs / 1_000_000.0;

        System.out.println();
        if (sonIguales) {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║  Los vectores SON IDÉNTICOS                      ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
        } else {
            System.out.println("╔══════════════════════════════════════════════════╗");
            System.out.println("║  Los vectores SON DIFERENTES                     ║");
            System.out.println("╚══════════════════════════════════════════════════╝");
        }

        // =================================================================
        // ANÁLISIS DE COMPLEJIDAD
        // =================================================================
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            ANÁLISIS DE COMPLEJIDAD                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Caso          │ Comparaciones │ Complejidad │ Ejemplo     ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Mejor caso    │      1        │    O(1)     │ Posición 0   ║");
        System.out.println("║                │               │             │ difiere      ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Peor caso     │      n        │    O(n)     │ Vectores     ║");
        System.out.println("║                │               │             │ idénticos    ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Promedio      │     n/2       │    O(n)     │ Diferencia   ║");
        System.out.println("║                │               │             │ en el medio  ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ESPACIO: O(1) — sin memoria adicional                    ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  TIEMPO DE EJECUCIÓN: " + String.format("%.3f", tiempoMs) + " ms (" + tiempoNs + " ns)    ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  ¿POR QUÉ EARLY TERMINATION?                             ║");
        System.out.println("║  Si los vectores difieren en la posición 0, solo se      ║");
        System.out.println("║  realiza 1 comparación en vez de n. Esto optimiza        ║");
        System.out.println("║  significativamente el caso donde los vectores son        ║");
        System.out.println("║  diferentes y la diferencia está al inicio.              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
