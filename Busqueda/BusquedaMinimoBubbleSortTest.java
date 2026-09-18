import java.util.Arrays;

/**
 * Archivo de pruebas para la clase {@link BusquedaMinimoBubbleSort}.
 * 
 * <p>Valida que el algoritmo Bubble Sort funcione correctamente en
 * distintos escenarios: vector ya ordenado, invertido, con duplicados,
 * un solo elemento, y valores aleatorios.</p>
 * 
 * <p>Se ejecuta con assertions de Java (-ea). Si alguna falla,
 * el programa lanza AssertionError con un mensaje descriptivo.</p>
 * 
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class BusquedaMinimoBubbleSortTest {

    /** Contador de pruebas pasadas */
    private static int pruebasPasadas = 0;
    /** Contador de pruebas fallidas */
    private static int pruebasFallidas = 0;

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     TESTS — BusquedaMinimoBubbleSort         ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        // =================================================================
        // TEST 1: Vector con valores conocidos
        // Verifica que después de ordenar, el mínimo quede en la posición 0
        // =================================================================
        test("Vector con valores conocidos",
             new int[]{5, 3, 8, 1, 9, 2},
             1);

        // =================================================================
        // TEST 2: Vector ya ordenado ascendentemente
        // Bubble Sort no debería hacer ningún intercambio
        // =================================================================
        test("Vector ya ordenado",
             new int[]{1, 2, 3, 4, 5},
             1);

        // =================================================================
        // TEST 3: Vector ordenado inversamente (peor caso para Bubble Sort)
        // =================================================================
        test("Vector ordenado inversamente (peor caso)",
             new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
             1);

        // =================================================================
        // TEST 4: Vector con un solo elemento
        // Caso trivial: no hay nada que ordenar
        // =================================================================
        test("Vector con un solo elemento",
             new int[]{42},
             42);

        // =================================================================
        // TEST 5: Vector con todos los elementos iguales
        // El mínimo es el mismo valor repetido
        // =================================================================
        test("Vector con todos los elementos iguales",
             new int[]{7, 7, 7, 7, 7},
             7);

        // =================================================================
        // TEST 6: Vector con duplicados y valores variados
        // =================================================================
        test("Vector con duplicados",
             new int[]{5, 3, 5, 1, 3, 9, 1},
             1);

        // =================================================================
        // TEST 7: Vector de dos elementos (menor primero)
        // =================================================================
        test("Vector de dos elementos (menor primero)",
             new int[]{2, 8},
             2);

        // =================================================================
        // TEST 8: Vector de dos elementos (mayor primero)
        // =================================================================
        test("Vector de dos elementos (mayor primero)",
             new int[]{8, 2},
             2);

        // =================================================================
        // TEST 9: Verificar que el vector queda completamente ordenado
        // No solo el mínimo, sino todo el vector en orden ascendente
        // =================================================================
        testVectorCompletoOrdenado(
            "Vector queda completamente ordenado",
            new int[]{15, 3, 99, 1, 42, 7}
        );

        // =================================================================
        // TEST 10: Vector aleatorio grande — verificar orden completo
        // Genera 100 valores aleatorios y valida el orden
        // =================================================================
        testVectorAleatorioGrande("Vector aleatorio de 100 elementos");

        // =================================================================
        // RESUMEN DE RESULTADOS
        // =================================================================
        System.out.println();
        System.out.println("══════════════════════════════════════════════");
        System.out.println("  RESUMEN: " + pruebasPasadas + " pasadas, "
                           + pruebasFallidas + " fallidas");
        System.out.println("══════════════════════════════════════════════");

        if (pruebasFallidas > 0) {
            System.out.println("  ❌ ALGUNAS PRUEBAS FALLARON");
            System.exit(1);
        } else {
            System.out.println("  ✅ TODAS LAS PRUEBAS PASARON CORRECTAMENTE");
        }
    }

    /**
     * Ejecuta una prueba: ordena el vector con Bubble Sort y verifica
     * que el mínimo (posición 0) sea el valor esperado.
     * 
     * @param nombre   Descripción de la prueba
     * @param original Vector a ordenar (se modifica)
     * @param esperado Valor mínimo esperado después de ordenar
     */
    private static void test(String nombre, int[] original, int esperado) {
        // Se trabaja con una copia para no afectar otros tests
        int[] vector = Arrays.copyOf(original, original.length);
        int[] originalCopia = Arrays.copyOf(original, original.length);

        // Ejecutar Bubble Sort
        BusquedaMinimoBubbleSort.bubbleSort(vector);

        // El mínimo debe estar en la posición 0
        int obtenido = vector[0];

        if (obtenido == esperado) {
            System.out.println("  ✅ PASS: " + nombre
                + "  | entrada: " + Arrays.toString(originalCopia)
                + "  | mínimo: " + obtenido);
            pruebasPasadas++;
        } else {
            System.out.println("  ❌ FAIL: " + nombre
                + "  | entrada: " + Arrays.toString(originalCopia)
                + "  | esperado: " + esperado
                + "  | obtenido: " + obtenido);
            pruebasFallidas++;
        }
    }

    /**
     * Verifica que un vector quede completamente ordenado en orden
     * ascendente después de aplicar Bubble Sort.
     * 
     * @param nombre   Descripción de la prueba
     * @param original Vector a ordenar (se modifica)
     */
    private static void testVectorCompletoOrdenado(String nombre, int[] original) {
        int[] vector = Arrays.copyOf(original, original.length);
        int[] originalCopia = Arrays.copyOf(original, original.length);

        BusquedaMinimoBubbleSort.bubbleSort(vector);

        // Verificar que cada elemento sea menor o igual al siguiente
        boolean ordenado = true;
        for (int i = 0; i < vector.length - 1; i++) {
            if (vector[i] > vector[i + 1]) {
                ordenado = false;
                break;
            }
        }

        if (ordenado) {
            System.out.println("  ✅ PASS: " + nombre
                + "  | entrada: " + Arrays.toString(originalCopia)
                + "  | resultado: " + Arrays.toString(vector));
            pruebasPasadas++;
        } else {
            System.out.println("  ❌ FAIL: " + nombre
                + "  | entrada: " + Arrays.toString(originalCopia)
                + "  | resultado: " + Arrays.toString(vector));
            pruebasFallidas++;
        }
    }

    /**
     * Genera un vector aleatorio grande y verifica que quede ordenado.
     * 
     * @param nombre Descripción de la prueba
     */
    private static void testVectorAleatorioGrande(String nombre) {
        int n = 100;
        int[] vector = new int[n];
        java.util.Random rand = new java.util.Random();

        for (int i = 0; i < n; i++) {
            vector[i] = rand.nextInt(10000) + 1;
        }

        BusquedaMinimoBubbleSort.bubbleSort(vector);

        // Verificar orden ascendente
        boolean ordenado = true;
        for (int i = 0; i < vector.length - 1; i++) {
            if (vector[i] > vector[i + 1]) {
                ordenado = false;
                break;
            }
        }

        // Verificar que el mínimo sea el primer elemento
        int minimo = vector[0];
        for (int val : vector) {
            if (val < minimo) {
                minimo = val;
            }
        }
        boolean minimoCorrecto = (vector[0] == minimo);

        if (ordenado && minimoCorrecto) {
            System.out.println("  ✅ PASS: " + nombre
                + "  | " + n + " elementos ordenados correctamente"
                + "  | mínimo: " + vector[0]);
            pruebasPasadas++;
        } else {
            System.out.println("  ❌ FAIL: " + nombre
                + "  | ordenado: " + ordenado
                + "  | mínimo correcto: " + minimoCorrecto);
            pruebasFallidas++;
        }
    }
}
