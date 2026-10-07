import java.util.Random;

/**
 * Ordenamiento_ejercicio_9 - Comparación lado a lado de Bubble, Selection e Insertion Sort.
 *
 * <p>Ejecuta los tres algoritmos sobre el MISMO array aleatorio y muestra:</p>
 * <ul>
 *   <li>Array ordenado (verificación de corrección)</li>
 *   <li>Cantidad de comparaciones</li>
 *   <li>Cantidad de intercambios (Bubble/Selection) o desplazamientos (Insertion)</li>
 *   <li>Tiempo de ejecución</li>
 * </ul>
 *
 * <p><b>Objetivo didáctico:</b> Comparar ESTRATEGIAS, no solo resultado final.
 * Mismo O(n²) teórico, pero constantes y comportamiento real difieren.</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class Ordenamiento_ejercicio_9 {

    // =================================================================
    // CLASE INTERNA: ESTADÍSTICAS POR ALGORITMO
    // =================================================================
    private static class Stats {
        String nombre;
        long comparaciones = 0;
        long intercambios = 0; // En Insertion = desplazamientos
        long tiempoNs = 0;

        Stats(String nombre) {
            this.nombre = nombre;
        }
    }

    // =================================================================
    // BUBBLE SORT
    // Estrategia: Compara adyacentes, intercambia si desordenado.
    // "Burbujea" el mayor hacia el final en cada pasada.
    // =================================================================
    private static void bubbleSort(int[] arr, Stats s) {
        int n = arr.length;
        s.comparaciones = 0;
        s.intercambios = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean huboIntercambio = false;

            for (int j = 0; j < n - 1 - i; j++) {
                s.comparaciones++;

                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    s.intercambios++;
                    huboIntercambio = true;
                }
            }

            // Early termination: si no hubo intercambios, ya está ordenado
            if (!huboIntercambio) break;
        }
    }

    // =================================================================
    // SELECTION SORT
    // Estrategia: Busca el mínimo en la parte no ordenada
    // y lo intercambia con el primer elemento no ordenado.
    // =================================================================
    private static void selectionSort(int[] arr, Stats s) {
        int n = arr.length;
        s.comparaciones = 0;
        s.intercambios = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            // Buscar el mínimo en arr[i..n-1]
            for (int j = i + 1; j < n; j++) {
                s.comparaciones++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            // Intercambiar si encontró un nuevo mínimo
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                s.intercambios++;
            }
        }
    }

    // =================================================================
    // INSERTION SORT
    // Estrategia: Toma cada elemento e insértalo en su posición
    // correcta dentro de la parte ya ordenada (desplazando mayores).
    // =================================================================
    private static void insertionSort(int[] arr, Stats s) {
        int n = arr.length;
        s.comparaciones = 0;
        s.intercambios = 0; // Aquí cuenta DESPLAZAMIENTOS + inserción final

        for (int i = 1; i < n; i++) {
            int clave = arr[i];
            int j = i - 1;

            // Mover elementos mayores que la clave hacia la derecha
            while (j >= 0 && arr[j] > clave) {
                s.comparaciones++;
                arr[j + 1] = arr[j]; // Desplazamiento
                s.intercambios++;
                j--;
            }

            // Si salimos del while por comparación (j >= 0), contamos esa última
            if (j >= 0) {
                s.comparaciones++;
            }

            // Insertar la clave en su posición correcta
            arr[j + 1] = clave;
            if (j + 1 != i) {
                s.intercambios++; // Cuenta la inserción final como movimiento
            }
        }
    }

    // =================================================================
    // UTILIDADES
    // =================================================================
    private static int[] copiarArray(int[] original) {
        int[] copia = new int[original.length];
        System.arraycopy(original, 0, copia, 0, original.length);
        return copia;
    }

    private static void imprimirArray(int[] arr) {
        System.out.print("    [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    private static void imprimirSeparador(int ancho) {
        System.out.print("╠");
        for (int i = 0; i < ancho; i++) System.out.print("═");
        System.out.println("╣");
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        // Array aleatorio único (10-15 elementos, valores 1-100)
        Random random = new Random();
        final int N = 10 + random.nextInt(6); // 10-15
        final int MAX_VALOR = 100;

        int[] original = new int[N];
        for (int i = 0; i < N; i++) {
            original[i] = random.nextInt(MAX_VALOR) + 1;
        }

        // Cabecera
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  COMPARACIÓN: BUBBLE SORT vs SELECTION SORT vs INSERTION SORT              ║");
        System.out.printf("║  Array: %,d elementos aleatorios (1-%d)                                    ║%n", N, MAX_VALOR);
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("Array original:");
        imprimirArray(original);
        System.out.println();

        // Ejecutar los 3 algoritmos
        Stats[] resultados = new Stats[3];
        String[] nombres = {"Bubble Sort", "Selection Sort", "Insertion Sort"};

        for (int i = 0; i < 3; i++) {
            int[] copia = copiarArray(original);
            resultados[i] = new Stats(nombres[i]);

            long inicio = System.nanoTime();

            switch (i) {
                case 0 -> bubbleSort(copia, resultados[i]);
                case 1 -> selectionSort(copia, resultados[i]);
                case 2 -> insertionSort(copia, resultados[i]);
            }

            long fin = System.nanoTime();
            resultados[i].tiempoNs = fin - inicio;

            // Verificar que todos produjeron el mismo resultado (solo primera vez)
            if (i == 0) {
                System.out.println("━━━ RESULTADOS ━━━");
                System.out.print("Array ordenado (los 3): ");
                imprimirArray(copia);
                System.out.println();
            }
        }

        // Tabla comparativa
        System.out.println();
        System.out.println("╔════════════════════╦═════════════╦═════════════╦═════════════╗");
        System.out.println("║ Métrica            ║ Bubble Sort ║ Selection   ║ Insertion   ║");
        System.out.println("╠════════════════════╬═════════════╬═════════════╬═════════════╣");
        System.out.printf("║ Comparaciones      ║ %11d ║ %11d ║ %11d ║%n",
                resultados[0].comparaciones, resultados[1].comparaciones, resultados[2].comparaciones);
        System.out.printf("║ Intercambios/Despl.║ %11d ║ %11d ║ %11d ║%n",
                resultados[0].intercambios, resultados[1].intercambios, resultados[2].intercambios);
        System.out.printf("║ Tiempo (ns)        ║ %11d ║ %11d ║ %11d ║%n",
                resultados[0].tiempoNs, resultados[1].tiempoNs, resultados[2].tiempoNs);
        System.out.println("╚════════════════════╩═════════════╩═════════════╩═════════════╝");
        System.out.println();

        // Análisis educativo
        imprimirAnalisisEducativo(resultados);
    }

    // =================================================================
    // ANÁLISIS EDUCATIVO: POR QUÉ DIFIEREN LAS ESTRATEGIAS
    // =================================================================
    private static void imprimirAnalisisEducativo(Stats[] r) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  📚 ANÁLISIS EDUCATIVO: COMPARANDO ESTRATEGIAS (no solo resultados)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();

        System.out.println("  ▸ COMPLEJIDAD TEÓRICA (los 3):");
        System.out.println("      Mejor caso:  O(n)   (Bubble con early exit, Insertion ordenado)");
        System.out.println("      Peor caso:   O(n²)  (todos en array inverso)");
        System.out.println("      Promedio:    O(n²)  (array aleatorio)");
        System.out.println("      Espacio:     O(1)   (in-place, sin memoria extra)");
        System.out.println();

        System.out.println("  ▸ DIFERENCIAS CLAVE EN LA PRÁCTICA:");
        System.out.println();
        System.out.println("  📊 BUBBLE SORT — Estrategia: 'Comparar e intercambiar adyacentes'");
        System.out.println("      • Comparaciones: SIEMPRE ~n²/2 (aunque early exit ayuda en ordenado)");
        System.out.println("      • Intercambios:  MUCHOS (cada inversión requiere swap)");
        System.out.println("      • Ventaja: ESTABLE (mantiene orden de elementos iguales)");
        System.out.println("      • Uso real: Casi nulo. Solo didáctico o n muy pequeño (<50).");
        System.out.println();

        System.out.println("  📊 SELECTION SORT — Estrategia: 'Buscar mínimo y poner en lugar'");
        System.out.println("      • Comparaciones: SIEMPRE n(n-1)/2 ≈ n²/2 (no tiene early exit)");
        System.out.println("      • Intercambios:  MÍNIMOS (máximo n-1, uno por posición)");
        System.out.println("      • Desventaja: NO ESTABLE (puede cambiar orden de iguales)");
        System.out.println("      • Uso real: Cuando writes son costosos (memoria flash, EEPROM).");
        System.out.println();

        System.out.println("  📊 INSERTION SORT — Estrategia: 'Insertar en posición correcta'");
        System.out.println("      • Comparaciones: VARIABLE (pocas si hay orden parcial)");
        System.out.println("      • Desplazamientos: Variables (cercanos a comparaciones)");
        System.out.println("      • Ventaja: MEJOR CASO O(n) real en array ya ordenado");
        System.out.println("      • Ventaja: ESTABLE y adaptativo (rápido en casi-ordenados)");
        System.out.println("      • Uso real: Arrays pequeños, casi-ordenados, sub-algoritmo en QuickSort/TimSort.");
        System.out.println();

        System.out.println("  ▸ QUÉ MUESTRAN LOS NÚMEROS DE ESTA EJECUCIÓN:");
        System.out.printf("      • Bubble:     %,d comp / %,d swaps — many swaps, stable%n",
                r[0].comparaciones, r[0].intercambios);
        System.out.printf("      • Selection:  %,d comp / %,d swaps — fixed comps, min swaps, not stable%n",
                r[1].comparaciones, r[1].intercambios);
        System.out.printf("      • Insertion:  %,d comp / %,d moves — adaptive, fewer comps if partial order%n",
                r[2].comparaciones, r[2].intercambios);
        System.out.println();

        System.out.println("  ▸ CONCLUSIÓN PRÁCTICA:");
        System.out.println("      • Mismo Big-O NO significa mismo rendimiento real.");
        System.out.println("      • CONSTANTES ocultas en O() marcan diferencia 2x-10x.");
        System.out.println("      • Insertion Sort suele ser el MÁS RÁPIDO de los 3 en práctica");
        System.out.println("        (menos comparaciones, cache-friendly, early exit natural).");
        System.out.println("      • Selection Sort gana solo si ESCRIBIR en memoria es muy caro.");
        System.out.println("      • Bubble Sort casi nunca es la mejor opción (salvo didáctica).");
        System.out.println();
        System.out.println("  ▸ PRÓXIMO PASO PARA APRENDER MÁS:");
        System.out.println("      • Prueba con array YA ORDEADO → Insertion O(n), otros O(n²)");
        System.out.println("      • Prueba con array INVERSO → Todos O(n²), Selection menos swaps");
        System.out.println("      • Prueba con array CASI ORDEADO → Insertion destruye a los otros");
        System.out.println("      • Cambia N a 1000 → Diferencias de tiempo se magnifican");
        System.out.println();
    }
}