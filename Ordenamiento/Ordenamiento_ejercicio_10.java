import java.util.Random;

/**
 * Ordenamiento_ejercicio_10 - Ordena un ranking de jugadores por puntaje (mayor a menor)
 * usando Insertion Sort.
 *
 * <p><b>JUSTIFICACIÓN DE LA ELECCIÓN DEL ALGORITMO:</b></p>
 * <ul>
 *   <li><b>Tamaño pequeño (n ≤ 15):</b> Insertion Sort tiene bajo overhead y es
 *       muy eficiente para n < 50. No vale la pena la complejidad de QuickSort/Ordenamiento_ejercicio_8.</li>
 *   <li><b>Datos casi ordenados naturalmente:</b> En rankings reales, nuevos jugadores
 *       se insertan en un ranking ya ordenado → Insertion Sort brilla (O(n) mejor caso).</li>
 *   <li><b>Estabilidad:</b> Mantiene el orden de llegada ante empates de puntaje
 *       (justo para torneo: quien llegó primero queda arriba).</li>
 *   <li><b>In-place + simple:</b> O(1) memoria extra, código compacto y legible.</li>
 *   <li><b>Adaptativo:</b> Si el array ya viene parcialmente ordenado, acelera solo.</li>
 * </ul>
 *
 * <p>Complejidad: Mejor O(n) · Promedio O(n²) · Peor O(n²) · Espacio O(1)</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class Ordenamiento_ejercicio_10 {

    // =================================================================
    // CLASE JUGADOR
    // =================================================================
    private static class Jugador {
        String nombre;
        int puntaje;

        Jugador(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }

        @Override
        public String toString() {
            return String.format("%-12s %5d pts", nombre, puntaje);
        }
    }

    // =================================================================
    // ESTADÍSTICAS
    // =================================================================
    private static class Stats {
        long comparaciones = 0;
        long desplazamientos = 0;
        long tiempoNs = 0;
    }

    // =================================================================
    // INSERTION SORT: mayor a menor puntaje (descendente)
    // =================================================================
    private static void insertionSortDesc(Jugador[] arr, Stats s) {
        int n = arr.length;
        s.comparaciones = 0;
        s.desplazamientos = 0;

        for (int i = 1; i < n; i++) {
            Jugador clave = arr[i];
            int j = i - 1;

            // Mover jugadores con MENOR puntaje hacia la derecha
            // (queremos orden DESCENDENTE: mayor puntaje primero)
            while (j >= 0 && arr[j].puntaje < clave.puntaje) {
                s.comparaciones++;
                arr[j + 1] = arr[j]; // Desplazamiento
                s.desplazamientos++;
                j--;
            }

            // Contar la comparación que hizo salir del while (si j >= 0)
            if (j >= 0) {
                s.comparaciones++;
            }

            // Insertar la clave en su posición correcta
            arr[j + 1] = clave;
            if (j + 1 != i) {
                s.desplazamientos++;
            }
        }
    }

    // =================================================================
    // GENERADOR DE JUGADORES ALEATORIOS
    // =================================================================
    private static final String[] NOMBRES = {
        "Ana", "Pedro", "Lucia", "Carlos", "Maria", "Juan", "Sofia", "Luis",
        "Valentina", "Diego", "Camila", "Martin", "Isabella", "Andres", "Paula",
        "Tomas", "Julia", "Nicolas", "Renata", "Santiago"
    };

    private static Jugador[] generarJugadoresAleatorios(int cantidad, Random rand) {
        Jugador[] jugadores = new Jugador[cantidad];
        for (int i = 0; i < cantidad; i++) {
            String nombre = NOMBRES[rand.nextInt(NOMBRES.length)] + (i + 1);
            int puntaje = 500 + rand.nextInt(2000); // 500 - 2499
            jugadores[i] = new Jugador(nombre, puntaje);
        }
        return jugadores;
    }

    private static void imprimirRanking(Jugador[] arr, String titulo) {
        System.out.println("\n━━━ " + titulo + " ━━━");
        System.out.println("╔══════════════╦════════════╗");
        System.out.println("║ Posición     ║ Jugador    ║");
        System.out.println("╠══════════════╬════════════╣");
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("║ %-12d ║ %s ║%n", i + 1, arr[i]);
        }
        System.out.println("╚══════════════╩════════════╝");
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();

        // Configuración: 10-15 jugadores, puntajes 500-2499
        final int CANTIDAD = 10 + random.nextInt(6); // 10-15
        Jugador[] ranking = generarJugadoresAleatorios(CANTIDAD, random);

        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  RANKING DE JUGADORES - ORDENAMIENTO POR PUNTAJE (DESCENDENTE)       ║");
        System.out.println("║  Algoritmo: INSERTION SORT                                           ║");
        System.out.println("║  Justificación: n ≤ 15, estable, adaptativo, bajo overhead           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");

        // Mostrar ranking original (desordenado)
        imprimirRanking(ranking, "RANKING ORIGINAL (SIN ORDENAR)");

        // Ordenar con Insertion Sort
        Stats stats = new Stats();
        long inicio = System.nanoTime();
        insertionSortDesc(ranking, stats);
        long fin = System.nanoTime();
        stats.tiempoNs = fin - inicio;

        // Mostrar ranking ordenado
        imprimirRanking(ranking, "RANKING ORDENADO (MAYOR A MENOR PUNTAJE)");

        // Estadísticas
        System.out.println("\n━━━ ESTADÍSTICAS DEL ORDENAMIENTO ━━━");
        System.out.printf("  Comparaciones:     %,d%n", stats.comparaciones);
        System.out.printf("  Desplazamientos:   %,d%n", stats.desplazamientos);
        System.out.printf("  Tiempo:            %.3f ms (%,d ns)%n", stats.tiempoNs / 1_000_000.0, stats.tiempoNs);

        // Explicación educativa
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  📚 POR QUÉ INSERTION SORT ES LA MEJOR OPCIÓN AQUÍ:");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println();
        System.out.println("  1️⃣  TAMAÑO PEQUEÑO (n ≤ 15):");
        System.out.println("      • QuickSort/MergeSort tienen overhead de recursión/partición");
        System.out.println("      • Insertion Sort: código simple, sin recursión, cache-friendly");
        System.out.println("      • Para n=15: Insertion ~225 ops vs QuickSort ~60 ops + overhead");
        System.out.println();
        System.out.println("  2️⃣  ESCENARIO REAL DE RANKING:");
        System.out.println("      • Ranking base ya ordenado + pocos nuevos jugadores");
        System.out.println("      • Insertion Sort = O(n) si casi ordenado (caso típico)");
        System.out.println("      • Otros algoritmos no se adaptan al orden parcial");
        System.out.println();
        System.out.println("  3️⃣  ESTABILIDAD (CRÍTICO EN RANKINGS):");
        System.out.println("      • Empate en puntaje → se respeta orden de llegada/registro");
        System.out.println("      • QuickSort clásico NO es estable; MergeSort sí pero más complejo");
        System.out.println("      • Insertion Sort: estable por naturaleza");
        System.out.println();
        System.out.println("  4️⃣  SIMPLICIDAD Y MANTENIMIENTO:");
        System.out.println("      • 15 líneas de código vs 50+ para QuickSort robusto");
        System.out.println("      • Fácil de auditar, testear y modificar reglas (ej: criterio de desempate)");
        System.out.println();
        System.out.println("  💡 CONCLUSIÓN: Para n ≤ 50 y datos casi ordenados, Insertion Sort");
        System.out.println("      suele ser MÁS RÁPIDO en práctica que algoritmos O(n log n) 'teóricos'.");
        System.out.println();
    }
}