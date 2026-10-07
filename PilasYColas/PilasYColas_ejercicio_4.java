import java.util.Random;

/**
 * PilasYColas_ejercicio_4 - Simulador de historial de navegador usando pila (arreglo String[]).
 *
 * <p>Cada URL visitada se apila. Al volver atrás ({@code back}), se desapila
 * la URL actual y la anterior se convierte en la actual.</p>
 *
 * <p>Principio: <b>LIFO</b> — la última página visitada es la primera a la que
 * se vuelve al hacer "atrás".</p>
 *
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class PilasYColas_ejercicio_4 {

    /** Arreglo de URLs */
    private final String[] urls;

    /** Índice de la página actual (cima) (-1 = vacío) */
    private int top;

    /** Capacidad máxima */
    private final int capacidad;

    /**
     * Crea un historial con la capacidad indicada.
     *
     * @param capacidad máximo de URLs en el historial
     */
    public PilasYColas_ejercicio_4(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad > 0");
        this.capacidad = capacidad;
        this.urls = new String[capacidad];
        this.top = -1;
    }

    /**
     * Visita una nueva página (push).
     *
     * @param url dirección de la página
     * @throws IllegalStateException si el historial está lleno
     */
    public void visitar(String url) {
        if (estaLleno()) {
            throw new IllegalStateException("Historial lleno, no se puede visitar: " + url);
        }
        top++;
        urls[top] = url;
    }

    /**
     * Vuelve a la página anterior (pop).
     *
     * @return URL de la página a la que se vuelve
     * @throws IllegalStateException si no hay página anterior
     */
    public String volver() {
        if (top <= 0) {
            throw new IllegalStateException("No hay página anterior (historial con 0 o 1 elemento)");
        }
        String actual = urls[top];
        top--;
        String anterior = urls[top];
        System.out.println("  ← Volviendo de: " + actual);
        System.out.println("  → Página actual ahora: " + anterior);
        return anterior;
    }

    /**
     * Consulta la página actual (peek).
     *
     * @return URL actual
     * @throws IllegalStateException si historial vacío
     */
    public String actual() {
        if (estaVacio()) {
            throw new IllegalStateException("Historial vacío");
        }
        return urls[top];
    }

    /**
     * Verifica si el historial está vacío.
     */
    public boolean estaVacio() {
        return top == -1;
    }

    /**
     * Verifica si el historial está lleno.
     */
    public boolean estaLleno() {
        return top == capacidad - 1;
    }

    /**
     * Cantidad de páginas en el historial.
     */
    public int size() {
        return top + 1;
    }

    /**
     * Representación visual del historial (base = primera visitada, cima = actual).
     */
    @Override
    public String toString() {
        if (estaVacio()) return "  (historial vacío)";
        StringBuilder sb = new StringBuilder("\n");
        for (int i = top; i >= 0; i--) {
            sb.append(i == top ? "  ► " : "    ");
            sb.append("#").append(i + 1).append(": ").append(urls[i]);
            if (i == top) sb.append(" ← ACTUAL");
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Imprime estado compacto.
     */
    public void imprimirEstado() {
        System.out.println("  Estado: " + (estaVacio() ? "VACÍO" : estaLleno() ? "LLENO" : "Normal")
                + " | Páginas: " + size() + "/" + capacidad);
        if (!estaVacio()) System.out.println("  Actual: " + urls[top]);
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int CAPACIDAD = 20;
        PilasYColas_ejercicio_4 historial = new PilasYColas_ejercicio_4(CAPACIDAD);

        String[] urlsDisponibles = {
            "https://google.com",
            "https://github.com",
            "https://stackoverflow.com",
            "https://youtube.com",
            "https://wikipedia.org",
            "https://twitter.com",
            "https://linkedin.com",
            "https://reddit.com",
            "https://medium.com",
            "https://dev.to"
        };

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 4 — HISTORIAL DE NAVEGACIÓN (pila String[], capacidad 20)     ║");
        System.out.println("║  Principio: LIFO — 'Atrás' vuelve a la última visitada                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ HISTORIAL INICIAL ━━━");
        historial.imprimirEstado();
        System.out.println(historial);
        System.out.println();

        // Visita inicial obligatoria
        String primera = urlsDisponibles[random.nextInt(urlsDisponibles.length)];
        System.out.println("━━━ VISITA INICIAL ━━━");
        System.out.println("  → VISITAR: " + primera);
        historial.visitar(primera);
        historial.imprimirEstado();
        System.out.println(historial);
        System.out.println();

        int operaciones = 15 + random.nextInt(10); // 15-24

        for (int i = 1; i <= operaciones; i++) {
            System.out.println("━━━ OPERACIÓN #" + i + " ━━━");

            // 70% visitar, 30% volver (pero no volver si solo 1 elemento)
            boolean visitar = random.nextDouble() < 0.7 || historial.size() <= 1;

            if (visitar) {
                String url = urlsDisponibles[random.nextInt(urlsDisponibles.length)];
                System.out.println("  → VISITAR: " + url);
                try {
                    historial.visitar(url);
                    System.out.println("  ✓ Agregada al historial");
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            } else {
                System.out.println("  → VOLVER (back)");
                try {
                    historial.volver();
                    System.out.println("  ✓ Retrocedido");
                } catch (IllegalStateException e) {
                    System.out.println("  ✗ " + e.getMessage());
                }
            }

            System.out.println();
            historial.imprimirEstado();
            System.out.println(historial);
        }

        System.out.println("━━━ ¿POR QUÉ PILA PARA HISTORIAL? ━━━");
        System.out.println();
        System.out.println("  • El botón 'Atrás' del navegador recupera la ÚLTIMA página visitada");
        System.out.println("  • Eso es LIFO: Last In, First Out");
        System.out.println("  • Una COLA (FIFO) iría a la PRIMERA página visitada (inicio),");
        System.out.println("    no a la anterior inmediata");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}