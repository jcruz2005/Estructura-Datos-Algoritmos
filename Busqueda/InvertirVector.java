import java.util.Random;

/**
 * Invierte un vector utilizando dos métodos distintos y compara su rendimiento.
 *
 * <p><b>Método 1 - Vector Auxiliar:</b></p>
 * <ul>
 *   <li>Crea un nuevo vector del mismo tamaño</li>
 *   <li>Copia los elementos en orden inverso</li>
 *   <li>Espacio: O(n) — necesita memoria adicional</li>
 * </ul>
 *
 * <p><b>Método 2 - In-Place (Swap):</b></p>
 * <ul>
 *   <li>Intercambia elementos desde los extremos hacia el centro</li>
 *   <li>No necesita memoria adicional</li>
 *   <li>Espacio: O(1) — modifica el vector original</li>
 * </ul>
 *
 * <p><b>Complejidad Temporal:</b></p>
 * <ul>
 *   <li>Ambos métodos: O(n) — recorren el vector una vez</li>
 *   <li>El Método 2 es más eficiente en espacio</li>
 * </ul>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class InvertirVector {

    /**
     * Invierte un vector utilizando un vector auxiliar.
     * El vector original NO se modifica.
     *
     * @param arr Vector a invertir
     * @return Nuevo vector con los elementos en orden inverso
     */
    public static int[] invertirConAuxiliar(int[] arr) {
        int n = arr.length;
        int[] auxiliar = new int[n];

        for (int i = 0; i < n; i++) {
            auxiliar[i] = arr[n - 1 - i];
        }

        return auxiliar;
    }

    /**
     * Invierte un vector in-place utilizando intercambio (swap).
     * El vector original SÍ se modifica.
     *
     * <p><b>Lógica:</b> Se comparan elementos desde los extremos
     * (inicio y fin) moviéndose hacia el centro, intercambiándolos.</p>
     *
     * @param arr Vector a invertir (se modifica)
     */
    public static void invertirInPlace(int[] arr) {
        int izquierda = 0;
        int derecha = arr.length - 1;

        while (izquierda < derecha) {
            // Intercambiar elementos
            int temp = arr[izquierda];
            arr[izquierda] = arr[derecha];
            arr[derecha] = temp;

            izquierda++;
            derecha--;
        }
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
        final int N = random.nextInt(6) + 5; // 5 a 10 elementos
        final int MAX_VALOR = 10; // valores de 1 a 10

        // Generar vector aleatorio
        int[] vectorOriginal = new int[N];
        for (int i = 0; i < N; i++) {
            vectorOriginal[i] = random.nextInt(MAX_VALOR) + 1;
        }

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     INVERTIR VECTOR - COMPARACIÓN DE MÉTODOS            ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Tamaño: " + N + " | Rango: 1-" + MAX_VALOR);
        System.out.println();
        System.out.println("Vector original:");
        imprimirVector(vectorOriginal);

        // =================================================================
        // MÉTODO 1: VECTOR AUXILIAR
        // =================================================================
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  MÉTODO 1: VECTOR AUXILIAR");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        int[] copia1 = copiarVector(vectorOriginal);
        long inicio1 = System.nanoTime();
        int[] invertido1 = invertirConAuxiliar(copia1);
        long fin1 = System.nanoTime();
        long tiempo1 = fin1 - inicio1;

        System.out.println("  Proceso: Copiar elementos en orden inverso");
        System.out.println();
        System.out.println("  Original:  ");
        imprimirVector(copia1);
        System.out.println("  Invertido: ");
        imprimirVector(invertido1);
        System.out.println();
        System.out.println("  Tiempo: " + tiempo1 + " ns (" + String.format("%.3f", tiempo1 / 1_000_000.0) + " ms)");
        System.out.println("  Memoria adicional: O(n) = " + N + " enteros");

        // =================================================================
        // MÉTODO 2: IN-PLACE (SWAP) - MOSTRAR PASOS
        // =================================================================
        System.out.println();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  MÉTODO 2: IN-PLACE (SWAP)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        int[] copia2 = copiarVector(vectorOriginal);
        System.out.println("  Proceso: Intercambiar elementos desde los extremos");
        System.out.println();
        System.out.println("  Vector inicial:");
        imprimirVector(copia2);
        System.out.println();

        // Mostrar pasos del swap SIN timing
        int izquierda = 0;
        int derecha = copia2.length - 1;
        int paso = 1;
        while (izquierda < derecha) {
            System.out.println("  Paso " + paso + ": swap(pos " + izquierda + ", pos " + derecha + ")");
            System.out.println("    Antes: ");
            imprimirVector(copia2);

            int temp = copia2[izquierda];
            copia2[izquierda] = copia2[derecha];
            copia2[derecha] = temp;

            System.out.println("    Después: ");
            imprimirVector(copia2);
            System.out.println();

            izquierda++;
            derecha--;
            paso++;
        }

        System.out.println("  Vector final:");
        imprimirVector(copia2);

        // =================================================================
        // MÉTODO 2: IN-PLACE (SWAP) - MEDIR TIEMPO
        // =================================================================
        int[] copia2Tiempo = copiarVector(vectorOriginal);
        long inicio2 = System.nanoTime();
        invertirInPlace(copia2Tiempo);
        long fin2 = System.nanoTime();
        long tiempo2 = fin2 - inicio2;

        System.out.println();
        System.out.println("  Tiempo: " + tiempo2 + " ns (" + String.format("%.3f", tiempo2 / 1_000_000.0) + " ms)");
        System.out.println("  Memoria adicional: O(1) = constante");

        // =================================================================
        // COMPARACIÓN
        // =================================================================
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            COMPARACIÓN DE MÉTODOS                         ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Método        │ Tiempo     │ Espacio │ Ventaja          ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Auxiliar      │ " + String.format("%-9s", tiempo1 + " ns") + " │  O(n)   │ Original intacto   ║");
        System.out.println("║  In-Place      │ " + String.format("%-9s", tiempo2 + " ns") + " │  O(1)   │ Sin memoria extra  ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Diferencia de tiempo: " + Math.abs(tiempo1 - tiempo2) + " ns                          ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  CONCLUSIÓN:                                              ║");
        System.out.println("║  • Ambos son O(n) en tiempo                               ║");
        System.out.println("║  • In-Place es mejor en memoria O(1) vs O(n)              ║");
        System.out.println("║  • Auxiliar preserva el vector original                   ║");
        System.out.println("║  • Usar In-Place a menos que necesites conservar copia    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
