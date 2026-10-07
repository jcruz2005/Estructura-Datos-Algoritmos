import java.util.Random;

/**
 * Ordenamiento_ejercicio_7 - Análisis educativo del mejor/peor caso según elección de pivote.
 *
 * <p><b>EL PARADOJA:</b> Un array YA ORDENADO {@code {1,2,3,4,5,6,7,8,9,10}} es:</p>
 * <ul>
 *   <li><b>PEOR CASO O(n²)</b> con pivote = primer elemento (siempre el mínimo)</li>
 *   <li><b>MEJOR CASO O(n log n)</b> con pivote = elemento central / mediana-de-tres / aleatorio</li>
 * </ul>
 *
 * <p>Este programa ejecuta 4 estrategias de pivote sobre el MISMO array ordenado
 * y muestra visualmente por qué la elección del pivote cambia drásticamente el rendimiento.</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class Ordenamiento_ejercicio_7 {

    // =================================================================
    // CLASE INTERNA: ESTADÍSTICAS POR ESTRATEGIA
    // =================================================================
    private static class Stats {
        String nombre;
        int comparaciones = 0;
        int intercambios = 0;
        int profundidadMaxima = 0;
        double balancePromedio = 0.0;
        int totalParticiones = 0;
        long tiempoNs = 0;
        StringBuilder arbolVisual = new StringBuilder();

        void registrarParticion(int izqSize, int derSize, int profundidad) {
            totalParticiones++;
            if (profundidad > profundidadMaxima) profundidadMaxima = profundidad;
            if (izqSize + derSize > 0) {
                double balance = Math.min(izqSize, derSize) / (double) Math.max(izqSize, derSize);
                balancePromedio = ((balancePromedio * (totalParticiones - 1)) + balance) / totalParticiones;
            }
        }

        void finalizar() {
            // balancePromedio ya calculado incrementalmente
        }
    }

    // =================================================================
    // ENUM: ESTRATEGIAS DE PIVOTE
    // =================================================================
    private enum PivotStrategy {
        FIRST("Primer elemento", PivotSelectorImpl::first),
        MIDDLE("Elemento central", PivotSelectorImpl::middle),
        RANDOM("Aleatorio", PivotSelectorImpl::random),
        MEDIAN_OF_THREE("Mediana de tres", PivotSelectorImpl::medianOfThree);

        final String label;
        final PivotSelector selector;

        PivotStrategy(String label, PivotSelector selector) {
            this.label = label;
            this.selector = selector;
        }
    }

    @FunctionalInterface
    private interface PivotSelector {
        int select(int[] arr, int bajo, int alto, Random rand);
    }

    // =================================================================
    // SELECTORES DE PIVOTE
    // =================================================================
    private static class PivotSelectorImpl {
        static int first(int[] arr, int bajo, int alto, Random rand) {
            return bajo;
        }

        static int middle(int[] arr, int bajo, int alto, Random rand) {
            return bajo + (alto - bajo) / 2;
        }

        static int random(int[] arr, int bajo, int alto, Random rand) {
            return bajo + rand.nextInt(alto - bajo + 1);
        }

        static int medianOfThree(int[] arr, int bajo, int alto, Random rand) {
            int mid = bajo + (alto - bajo) / 2;
            int a = arr[bajo], b = arr[mid], c = arr[alto];
            if ((a <= b && b <= c) || (c <= b && b <= a)) return mid;
            if ((b <= a && a <= c) || (c <= a && a <= b)) return bajo;
            return alto;
        }
    }

    // =================================================================
    // VARIABLES GLOBALES POR EJECUCIÓN
    // =================================================================
    private static Stats currentStats;
    private static Random random = new Random();

    // =================================================================
    // MÉTODO PRINCIPAL
    // =================================================================
    public static void main(String[] args) {
        // Array fijo: MEJOR CASO teórico, PEOR CASO para pivote-primer-elemento
        int[] arrayOriginal = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        imprimirCabecera(arrayOriginal);

        Stats[] resultados = new Stats[PivotStrategy.values().length];

        for (int i = 0; i < PivotStrategy.values().length; i++) {
            PivotStrategy strategy = PivotStrategy.values()[i];
            int[] arr = arrayOriginal.clone();

            currentStats = new Stats();
            currentStats.nombre = strategy.label;

            long inicio = System.nanoTime();
            quickSort(arr, 0, arr.length - 1, strategy.selector, 0);
            long fin = System.nanoTime();

            currentStats.tiempoNs = fin - inicio;
            currentStats.finalizar();
            resultados[i] = currentStats;
        }

        imprimirResumenComparativo(resultados);
        imprimirExplicacionEducativa();
    }

    // =================================================================
    // QUICKSORT GENÉRICO CON SELECTOR DE PIVOTE
    // =================================================================
    private static void quickSort(int[] arr, int bajo, int alto, PivotSelector selector, int profundidad) {
        if (bajo < alto) {
            // Visualización del árbol de particiones
            String indent = "  ".repeat(profundidad);
            currentStats.arbolVisual.append(indent).append("├─ [");
            for (int k = bajo; k <= alto; k++) {
                currentStats.arbolVisual.append(arr[k]);
                if (k < alto) currentStats.arbolVisual.append(",");
            }
            currentStats.arbolVisual.append("]");

            int pivoteIdx = particion(arr, bajo, alto, selector);

            int izqSize = pivoteIdx - bajo;
            int derSize = alto - pivoteIdx;
            currentStats.registrarParticion(izqSize, derSize, profundidad);

            currentStats.arbolVisual.append(" pivote=").append(arr[pivoteIdx])
                    .append(" → izq:").append(izqSize).append(" der:").append(derSize).append("\n");

            quickSort(arr, bajo, pivoteIdx - 1, selector, profundidad + 1);
            quickSort(arr, pivoteIdx + 1, alto, selector, profundidad + 1);
        } else if (bajo == alto) {
            // Nodo hoja
            String indent = "  ".repeat(profundidad);
            currentStats.arbolVisual.append(indent).append("└─ [").append(arr[bajo]).append("] (hoja)\n");
        }
    }

    // =================================================================
    // PARTICIÓN CON PIVOTE SELECCIONADO POR ESTRATEGIA
    // =================================================================
    private static int particion(int[] arr, int bajo, int alto, PivotSelector selector) {
        // 1. Seleccionar pivote según estrategia
        int pivoteIdx = selector.select(arr, bajo, alto, random);

        // 2. Mover pivote al inicio para partición estándar (Lomuto)
        if (pivoteIdx != bajo) {
            intercambiar(arr, bajo, pivoteIdx);
            currentStats.intercambios++;
        }

        int pivote = arr[bajo];
        int i = bajo + 1;

        for (int j = bajo + 1; j <= alto; j++) {
            currentStats.comparaciones++;

            if (arr[j] < pivote) {
                intercambiar(arr, i, j);
                currentStats.intercambios++;
                i++;
            }
        }

        // Colocar pivote en posición final
        intercambiar(arr, bajo, i - 1);
        currentStats.intercambios++;

        return i - 1;
    }

    private static void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // =================================================================
    // IMPRESIÓN DE RESULTADOS
    // =================================================================
    private static void imprimirCabecera(int[] arr) {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  QUICKSORT - ANÁLISIS DE PIVOTES                                          ║");
        System.out.println("║  Array de entrada: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10] (YA ORDENADO)         ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void imprimirResumenComparativo(Stats[] resultados) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  RESULTADOS POR ESTRATEGIA");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();

        for (Stats s : resultados) {
            System.out.println("━━━ ESTRATEGIA: " + s.nombre.toUpperCase() + " ━━━");
            System.out.println(s.arbolVisual.toString());
            System.out.printf("  Comparaciones: %,d%n", s.comparaciones);
            System.out.printf("  Intercambios:  %,d%n", s.intercambios);
            System.out.printf("  Profundidad máx: %d%n", s.profundidadMaxima);
            System.out.printf("  Balance promedio: %.2f%n", s.balancePromedio);
            System.out.printf("  Tiempo: %.3f ms (%,d ns)%n", s.tiempoNs / 1_000_000.0, s.tiempoNs);
            System.out.println();
        }

        // Tabla comparativa
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  RESUMEN COMPARATIVO                                                       ║");
        System.out.println("╠═══════════════════════════╦════════════╦════════════╦════════════╦═══════════╣");
        System.out.println("║ Estrategia               ║ Comparac.  ║ Intercamb. ║ Profund.   ║ Balance   ║");
        System.out.println("╠═══════════════════════════╬════════════╬════════════╬════════════╬═══════════╣");

        for (Stats s : resultados) {
            String marker = (s.nombre.equals("Primer elemento")) ? " ← PEOR CASO" :
                           (s.nombre.equals("Elemento central") || s.nombre.equals("Mediana de tres")) ? " ← MEJOR CASO" : "";
            System.out.printf("║ %-24s ║ %10d ║ %10d ║ %10d ║ %9.2f ║%s%n",
                    s.nombre, s.comparaciones, s.intercambios, s.profundidadMaxima, s.balancePromedio, marker);
        }

        System.out.println("╚═══════════════════════════╩════════════╩════════════╩════════════╩═══════════╝");
        System.out.println();
    }

    private static void imprimirExplicacionEducativa() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  📚 EXPLICACIÓN EDUCATIVA: ¿POR QUÉ EL PRIMER ELEMENTO NO ES ÓPTIMO?");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();
        System.out.println("  ▸ QUICKSORT es 'divide y vencerás': el pivote DEBE dividir el array en");
        System.out.println("    dos subarreglos APROXIMADAMENTE IGUALES para lograr O(n log n).");
        System.out.println();
        System.out.println("  ▸ CON PIVOTE = PRIMER ELEMENTO en array YA ORDENADO {1,2,3,4,5,6,7,8,9,10}:");
        System.out.println("      Paso 1: pivote=1 → partición: [] | [2,3,4,5,6,7,8,9,10]  (balance 0/9)");
        System.out.println("      Paso 2: pivote=2 → partición: [] | [3,4,5,6,7,8,9,10]  (balance 0/8)");
        System.out.println("      ... se repite n veces → profundidad = n → comparaciones = n(n-1)/2 = O(n²)");
        System.out.println();
        System.out.println("  ▸ CON PIVOTE = ELEMENTO CENTRAL (índice 4, valor 5):");
        System.out.println("      Paso 1: pivote=5 → partición: [1,2,3,4] | [6,7,8,9,10]  (balance 4/5 ≈ 0.8)");
        System.out.println("      Paso 2: cada lado se divide ~a la mitad → profundidad = log₂(n) ≈ 4");
        System.out.println("      Comparaciones ≈ n log n = 10 × 3.3 ≈ 33 (real: 29)");
        System.out.println();
        System.out.println("  ▸ PIVOTE ALEATORIO: evita peor caso determinista, O(n log n) ESPERADO.");
        System.out.println();
        System.out.println("  ▸ MEDIANA DE TRES (primero, centro, último):");
        System.out.println("      - En array ordenado: mediana = elemento central → igual que pivote central");
        System.out.println("      - En array aleatorio: evita valores extremos → muy robusto");
        System.out.println("      - Estándar en librerías (Java Arrays.sort, Python Timsort, etc.)");
        System.out.println();
        System.out.println("  ▸ CONCLUSIÓN:");
        System.out.println("      • Array ordenado + pivote fijo (primero/último) = PEOR CASO garantizado");
        System.out.println("      • Para inputs reales (desconocidos): usa pivote ALEATORIO o MEDIANA-DE-TRES");
        System.out.println("      • Nunca uses pivote fijo en producción sin conocer la distribución de datos");
        System.out.println();
    }
}