import java.util.Random;

/**
 * Ordenamiento_ejercicio_8 - Visualización educativa de división y fusión (Merge Sort).
 *
 * <p>Muestra paso a paso:</p>
 * <ul>
 *   <li>Cómo se DIVIDE el arreglo recursivamente</li>
 *   <li>Cuándo llega a la CONDICIÓN DE CORTE (tamaño 1)</li>
 *   <li>Cómo se FUSIONAN (merge) los subarreglos ordenados</li>
 * </ul>
 *
 * <p><b>Complejidad:</b> SIEMPRE O(n log n) - mejor, promedio y peor caso idénticos.</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class Ordenamiento_ejercicio_8 {

    // =================================================================
    // CLASE INTERNA: ESTADÍSTICAS
    // =================================================================
    private static class Stats {
        long comparaciones = 0;
        long copias = 0;
        int profundidadMaxima = 0;
        long tiempoNs = 0;
        StringBuilder arbolVisual = new StringBuilder();

        void registrarProfundidad(int profundidad) {
            if (profundidad > profundidadMaxima) profundidadMaxima = profundidad;
        }
    }

    private static Stats stats;
    private static Random random = new Random();

    // =================================================================
    // MÉTODO PRINCIPAL
    // =================================================================
    public static void main(String[] args) {
        // Array aleatorio de 10 a 15 elementos
        int n = 10 + random.nextInt(6); // 10-15
        int[] original = new int[n];
        for (int i = 0; i < n; i++) {
            original[i] = random.nextInt(100) + 1; // 1-100
        }

        imprimirCabecera(original);

        int[] arr = original.clone();
        stats = new Stats();

        long inicio = System.nanoTime();
        int[] ordenado = mergeSort(arr, 0, arr.length - 1, 0);
        long fin = System.nanoTime();

        stats.tiempoNs = fin - inicio;

        imprimirResultado(ordenado);
        imprimirEstadisticas();
        imprimirExplicacionEducativa();
    }

    // =================================================================
    // MERGESORT RECURSIVO CON VISUALIZACIÓN
    // =================================================================
    /**
     * Ordena el subarreglo arr[izq..der] y retorna array ordenado.
     * Imprime visualización de división, caso base y fusión.
     */
    private static int[] mergeSort(int[] arr, int izq, int der, int profundidad) {
        String indent = "  ".repeat(profundidad);
        stats.registrarProfundidad(profundidad);

        // Mostrar subarreglo actual
        stats.arbolVisual.append(indent).append("DIVIDE: [");
        for (int i = izq; i <= der; i++) {
            stats.arbolVisual.append(arr[i]);
            if (i < der) stats.arbolVisual.append(",");
        }
        stats.arbolVisual.append("]");

        // CONDICIÓN DE CORTE: subarreglo de tamaño 1
        if (izq == der) {
            stats.arbolVisual.append(" → BASE: [").append(arr[izq]).append("] (tamaño 1)\n");
            return new int[]{arr[izq]};
        }

        // Dividir en dos mitades
        int mid = izq + (der - izq) / 2;
        stats.arbolVisual.append(" → [");
        for (int i = izq; i <= mid; i++) {
            stats.arbolVisual.append(arr[i]);
            if (i < mid) stats.arbolVisual.append(",");
        }
        stats.arbolVisual.append("] | [");
        for (int i = mid + 1; i <= der; i++) {
            stats.arbolVisual.append(arr[i]);
            if (i < der) stats.arbolVisual.append(",");
        }
        stats.arbolVisual.append("]\n");

        // Recursión izquierda y derecha
        int[] izquierda = mergeSort(arr, izq, mid, profundidad + 1);
        int[] derecha = mergeSort(arr, mid + 1, der, profundidad + 1);

        // Fusionar
        int[] fusionado = merge(izquierda, derecha, profundidad);

        stats.arbolVisual.append(indent).append("FUSIONA: [");
        for (int i = 0; i < izquierda.length; i++) {
            stats.arbolVisual.append(izquierda[i]);
            if (i < izquierda.length - 1) stats.arbolVisual.append(",");
        }
        stats.arbolVisual.append("] + [");
        for (int i = 0; i < derecha.length; i++) {
            stats.arbolVisual.append(derecha[i]);
            if (i < derecha.length - 1) stats.arbolVisual.append(",");
        }
        stats.arbolVisual.append("] → [");
        for (int i = 0; i < fusionado.length; i++) {
            stats.arbolVisual.append(fusionado[i]);
            if (i < fusionado.length - 1) stats.arbolVisual.append(",");
        }
        stats.arbolVisual.append("]\n");

        return fusionado;
    }

    // =================================================================
    // MERGE: FUSIÓN DE DOS SUBARREGLOS ORDENADOS
    // =================================================================
    private static int[] merge(int[] izq, int[] der, int profundidad) {
        int[] resultado = new int[izq.length + der.length];
        int i = 0, j = 0, k = 0;

        while (i < izq.length && j < der.length) {
            stats.comparaciones++;
            if (izq[i] <= der[j]) {
                resultado[k++] = izq[i++];
                stats.copias++;
            } else {
                resultado[k++] = der[j++];
                stats.copias++;
            }
        }

        while (i < izq.length) {
            resultado[k++] = izq[i++];
            stats.copias++;
        }
        while (j < der.length) {
            resultado[k++] = der[j++];
            stats.copias++;
        }

        return resultado;
    }

    // =================================================================
    // IMPRESIÓN DE RESULTADOS
    // =================================================================
    private static void imprimirCabecera(int[] arr) {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  MERGESORT - DIVISIÓN Y FUSIÓN                                            ║");
        System.out.printf("║  Array aleatorio: %,d elementos (1-100)                                   ║%n", arr.length);
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.print("Array original: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println();
    }

    private static void imprimirResultado(int[] arr) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  PROCESO DE DIVISIÓN Y FUSIÓN");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();
        System.out.print(stats.arbolVisual.toString());
        System.out.println();
        System.out.print("Array ordenado: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println();
    }

    private static void imprimirEstadisticas() {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  ESTADÍSTICAS                                                              ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║  Comparaciones:     %,15d                                           ║%n", stats.comparaciones);
        System.out.printf("║  Copias/Asignaciones: %,15d                                           ║%n", stats.copias);
        System.out.printf("║  Profundidad máxima:  %,15d                                           ║%n", stats.profundidadMaxima);
        System.out.printf("║  Tiempo:              %,15.3f ms (%,15d ns)                          ║%n",
                stats.tiempoNs / 1_000_000.0, stats.tiempoNs);
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void imprimirExplicacionEducativa() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  📚 EXPLICACIÓN EDUCATIVA: ¿POR QUÉ MERGESORT SIEMPRE ES O(n log n)?");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();
        System.out.println("  ▸ DIVISIÓN (fase 'divide'):");
        System.out.println("      El array se divide a la MITAD en cada nivel recursivo.");
        System.out.println("      Profundidad del árbol = log₂(n) niveles (redondeado hacia arriba).");
        System.out.println("      Ejemplo: n=12 → niveles: 12 → 6 → 3 → 1  (4 niveles = ⌈log₂12⌉)");
        System.out.println();
        System.out.println("  ▸ CONDICIÓN DE CORTE (caso base):");
        System.out.println("      Cuando subarreglo tiene TAMAÑO 1 → ya está ordenado por definición.");
        System.out.println("      No hay más divisiones; comienza la fase de fusión (merge).");
        System.out.println("      En la visualización: 'BASE: [x] (tamaño 1)'");
        System.out.println();
        System.out.println("  ▸ FUSIÓN / MERGE (fase 'conquer'):");
        System.out.println("      Dos subarreglos YA ORDENADOS se combinan en uno ordenado.");
        System.out.println("      Se compara el primer elemento de cada uno (punteros i, j).");
        System.out.println("      El menor pasa al resultado → O(n) comparaciones por nivel.");
        System.out.println("      Total: n elementos × log₂(n) niveles = O(n log n) SIEMPRE.");
        System.out.println();
        System.out.println("  ▸ POR QUÉ NO HAY 'PEOR CASO' COMO EN QUICKSORT:");
        System.out.println("      • QuickSort: depende del PIVOTE → puede crear particiones 0/(n-1)");
        System.out.println("      • Ordenamiento_ejercicio_8: SIEMPRE divide a la MITAD exacta (izq = der ± 1)");
        System.out.println("      • La estructura del árbol es IDÉNTICA sin importar el contenido");
        System.out.println("      • Comparaciones por nivel = n (recorre todo el array una vez)");
        System.out.println("      • Niveles = log₂(n) → Total = n × log₂(n) = Θ(n log n)");
        System.out.println();
        System.out.println("  ▸ COMPROMISO (TRADE-OFF):");
        System.out.println("      ✅ Ventaja: Rendimiento GARANTIZADO O(n log n) siempre");
        System.out.println("      ✅ Ventaja: ESTABLE (mantiene orden de elementos iguales)");
        System.out.println("      ✅ Ventaja: Ideal para listas enlazadas (sin acceso aleatorio)");
        System.out.println("      ❌ Desventaja: Espacio extra O(n) para array auxiliar");
        System.out.println("      ❌ Desventaja: Más lento en práctica que QuickSort promedio");
        System.out.println("      ❌ Desventaja: No es 'in-place' (requiere memoria extra)");
        System.out.println();
        System.out.println("  ▸ USOS REALES:");
        System.out.println("      • Java: Arrays.sort() para objetos (TimSort = Ordenamiento_ejercicio_8 optimizado)");
        System.out.println("      • Python: sorted() y list.sort() usan TimSort");
        System.out.println("      • Bases de datos: merge join, ordenamiento externo");
        System.out.println("      • Procesamiento paralelo: división natural para hilos");
        System.out.println();
    }
}