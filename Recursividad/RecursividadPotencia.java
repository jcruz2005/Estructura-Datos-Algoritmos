import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Realiza la potenciación de dos números enteros de forma recursiva
 * sin utilizar el operador * ni Math.pow().
 * Utiliza multiplicación por sumas con algoritmo de exponenciación rápida.
 * @author SANCHEZ SOLANO, Juan Cruz
 */
public class RecursividadPotencia {

    /** Límite máximo de entrada */
    private static final int MAX_LIMITE = 100;

    /** Contador de profundidad de recursión */
    private static int profundidadMaxima = 0;

    /**
     * Multiplica dos números positivos usando sumas repetidas (iterativo).
     * Se usa iterativo aquí para evitar StackOverflow con resultados grandes.
     *
     * @param a número a sumar repetidamente
     * @param b cantidad de repeticiones
     * @return resultado de la multiplicación
     */
    public static long multiplicar(int a, int b) {
        long resultado = 0;
        for (int i = 0; i < b; i++) {
            resultado += a;
        }
        return resultado;
    }

    /**
     * Calcula la potencia de forma recursiva (exponenciación rápida).
     * Caso base: exponente == 0 → retorna 1
     * Caso recursivo:
     *   - Si es par: potencia(base, exp/2)^2
     *   - Si es impar: base × potencia(base, exp-1)
     *
     * @param base base de la potencia (positivo)
     * @param exponente exponente (positivo)
     * @return resultado de base^exponente
     */
    public static long potencia(int base, int exponente) {
        profundidadMaxima++;

        // Caso base
        if (exponente == 0) {
            return 1;
        }

        // Caso recursivo: exponenciación rápida
        if (exponente % 2 == 0) {
            // Si es par: base^exp = (base^(exp/2))^2
            long mitad = potencia(base, exponente / 2);
            return multiplicar((int) mitad, (int) mitad);
        } else {
            // Si es impar: base^exp = base × base^(exp-1)
            return multiplicar(base, (int) potencia(base, exponente - 1));
        }
    }

    /**
     * Genera el proceso de la potencia como string.
     * Ejemplo: potencia(2, 3) → "2 × 2 × 2"
     *
     * @param base base de la potencia
     * @param exponente cantidad de repeticiones
     * @return string con el proceso de multiplicaciones
     */
    public static String getProceso(int base, int exponente) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < exponente; i++) {
            sb.append(base);
            if (i < exponente - 1) {
                sb.append(" × ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   POTENCIA RECURSIVA (sin usar Math.pow)     ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println();

        try {
            // Entrada
            System.out.print("Ingrese la base: ");
            int base = scanner.nextInt();
            System.out.print("Ingrese el exponente: ");
            int exponente = scanner.nextInt();

            // Validaciones
            if (exponente < 0) {
                System.out.println();
                System.out.println("╔══════════════════════════════════════════════╗");
                System.out.println("║  ERROR: El exponente debe ser positivo      ║");
                System.out.println("╚══════════════════════════════════════════════╝");
                scanner.close();
                return;
            }

            if (Math.abs(base) > MAX_LIMITE || exponente > MAX_LIMITE) {
                System.out.println();
                System.out.println("╔══════════════════════════════════════════════╗");
                System.out.println("║  ERROR: El número excede el límite máximo   ║");
                System.out.println("║  Límite permitido: " + MAX_LIMITE + "                     ║");
                System.out.println("╚══════════════════════════════════════════════╝");
                scanner.close();
                return;
            }

            // Determinar si el resultado será negativo
            boolean esNegativo = (base < 0) && (exponente % 2 != 0);

            // Trabajar con valor absoluto de la base
            int absBase = Math.abs(base);

            // Cálculo recursivo
            long tiempoInicio = System.nanoTime();
            long resultado = potencia(absBase, exponente);
            long tiempoFin = System.nanoTime();
            double tiempoMs = (tiempoFin - tiempoInicio) / 1_000_000.0;

            // Aplicar signo si es necesario
            if (esNegativo) {
                resultado = -resultado;
            }

            // Salida
            System.out.println();
            String signoBase = base < 0 ? "(" + base + ")" : String.valueOf(base);

            if (exponente == 0) {
                System.out.println("  " + signoBase + "^0 = 1");
            } else {
                System.out.println("  Proceso: " + signoBase + "^" + exponente + " = " +
                                   getProceso(absBase, exponente) + " = " + resultado);
            }

            // Estadísticas
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║              ESTADÍSTICAS                    ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.printf("║  • Profundidad máxima de recursión: %-9d║%n", profundidadMaxima);
            System.out.printf("║  • Base absoluta: %-27d║%n", absBase);
            System.out.printf("║  • Tiempo de ejecución: %-19s║%n", String.format("%.2f ms", tiempoMs));
            System.out.println("╚══════════════════════════════════════════════╝");

        } catch (InputMismatchException e) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║  ERROR: Debe ingresar un número entero      ║");
            System.out.println("╚══════════════════════════════════════════════╝");
        } finally {
            scanner.close();
        }
    }
}
