import java.util.Random;

/**
 * ListasEnlazadas_ejercicio_4 - ListasEnlazadas_ejercicio_4 genérica (FIFO) usando lista enlazada simple con head y tail.
 *
 * <p>La lógica FIFO (head = frente, tail = final) es independiente del tipo T.
 * {@code enqueue} usa tail para O(1), {@code dequeue} usa head para O(1).</p>
 *
 * <p>Complejidad:</p>
 * <ul>
 *   <li>{@code enqueue}: O(1)</li>
 *   <li>{@code dequeue}: O(1)</li>
 *   <li>{@code front}: O(1)</li>
 *   <li>{@code contains}: O(n)</li>
 * </ul>
 *
 * @param <T> tipo de elementos
 * @author SANCHEZ SOLANO, Juan Cruz
 * @version 1.0
 */
public class ListasEnlazadas_ejercicio_4<T> {

    private static class Nodo<T> {
        T valor;
        Nodo<T> siguiente;

        Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo<T> head; // Frente
    private Nodo<T> tail; // Final
    private int size;
    private final int capacidadMaxima;

    public ListasEnlazadas_ejercicio_4(int capacidadMaxima) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.capacidadMaxima = capacidadMaxima;
    }

    public void enqueue(T valor) {
        if (size >= capacidadMaxima) throw new IllegalStateException("Límite " + capacidadMaxima + " alcanzado");
        Nodo<T> nuevo = new Nodo<>(valor);
        if (isEmpty()) {
            head = tail = nuevo;
        } else {
            tail.siguiente = nuevo;
            tail = nuevo;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Dequeue: cola vacía");
        T valor = head.valor;
        head = head.siguiente;
        size--;
        if (head == null) tail = null;
        return valor;
    }

    public T front() {
        if (isEmpty()) throw new IllegalStateException("Front: cola vacía");
        return head.valor;
    }

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    public boolean contains(T valor) {
        Nodo<T> actual = head;
        while (actual != null) {
            if (valor == null ? actual.valor == null : valor.equals(actual.valor)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = head;
        while (actual != null) {
            sb.append(actual.valor);
            if (actual.siguiente != null) sb.append(" → ");
            actual = actual.siguiente;
        }
        sb.append("]  (frente → final)");
        return sb.toString();
    }

    public void imprimirEstado(String nombre) {
        System.out.println("  " + nombre + ": head = " + (head != null ? head.valor : "null")
                + " | tail = " + (tail != null ? tail.valor : "null")
                + " | size = " + size + "/" + capacidadMaxima
                + " | " + (isEmpty() ? "VACÍA" : "Normal"));
        System.out.println("  ListasEnlazadas_ejercicio_4: " + this);
    }

    // =================================================================
    // CLASE CLIENTE PARA DEMO
    // =================================================================
    public static class Cliente {
        String nombre;
        int id;

        public Cliente(String nombre, int id) {
            this.nombre = nombre;
            this.id = id;
        }

        @Override
        public String toString() {
            return nombre + "(#" + id + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Cliente)) return false;
            Cliente c = (Cliente) o;
            return id == c.id && nombre.equals(c.nombre);
        }
    }

    // =================================================================
    // MAIN
    // =================================================================
    public static void main(String[] args) {
        Random random = new Random();
        final int MAX = 20;

        ListasEnlazadas_ejercicio_4<String> colaNombres = new ListasEnlazadas_ejercicio_4<>(MAX);
        ListasEnlazadas_ejercicio_4<Cliente> colaClientes = new ListasEnlazadas_ejercicio_4<>(MAX);

        String[] nombres = {"Ana", "Carlos", "Lucia", "Pedro", "Sofia", "Juan", "Maria", "Diego", "Paula", "Martin"};
        String[] apellidos = {"Garcia", "Lopez", "Martinez", "Rodriguez", "Gonzalez", "Perez", "Sanchez", "Romero"};

        System.out.println("╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║  EJERCICIO 4 — COLA GENÉRICA ENLAZADA (ListasEnlazadas_ejercicio_4<T>, máx 20 nodos)            ║");
        System.out.println("║  ListasEnlazadas_ejercicio_4<String> (nombres) y ListasEnlazadas_ejercicio_4<Cliente> con misma lógica FIFO           ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        System.out.println("━━━ ESTADO INICIAL ━━━");
        colaNombres.imprimirEstado("ListasEnlazadas_ejercicio_4<String>");
        System.out.println();
        colaClientes.imprimirEstado("ListasEnlazadas_ejercicio_4<Cliente>");
        System.out.println();

        int ops = 15 + random.nextInt(10);

        for (int i = 1; i <= ops; i++) {
            System.out.println("━━━ OPERACIÓN #" + i + " ━━━");

            boolean usarNombres = random.nextBoolean();
            double r = random.nextDouble();

            if (usarNombres) {
                System.out.println("  → ListasEnlazadas_ejercicio_4<String>");
                if (r < 0.55 || colaNombres.isEmpty()) {
                    String nom = nombres[random.nextInt(nombres.length)] + " " + apellidos[random.nextInt(apellidos.length)];
                    colaNombres.enqueue(nom);
                    System.out.println("    ENQUEUE(" + nom + ") ✓ (nuevo tail)");
                } else if (r < 0.85) {
                    System.out.println("    DEQUEUE() → " + colaNombres.dequeue() + " ✓ (head avanza)");
                } else {
                    System.out.println("    FRONT() → " + colaNombres.front() + " ✓");
                }
                colaNombres.imprimirEstado("ListasEnlazadas_ejercicio_4<String>");
            } else {
                System.out.println("  → ListasEnlazadas_ejercicio_4<Cliente>");
                if (r < 0.55 || colaClientes.isEmpty()) {
                    String nom = nombres[random.nextInt(nombres.length)] + " " + apellidos[random.nextInt(apellidos.length)];
                    int id = 1000 + random.nextInt(9000);
                    Cliente c = new Cliente(nom, id);
                    colaClientes.enqueue(c);
                    System.out.println("    ENQUEUE(" + c + ") ✓ (nuevo tail)");
                } else if (r < 0.85) {
                    System.out.println("    DEQUEUE() → " + colaClientes.dequeue() + " ✓ (head avanza)");
                } else {
                    System.out.println("    FRONT() → " + colaClientes.front() + " ✓");
                }
                colaClientes.imprimirEstado("ListasEnlazadas_ejercicio_4<Cliente>");
            }
            System.out.println();
        }

        System.out.println("━━━ FIFO INDEPENDIENTE DEL TIPO ━━━");
        System.out.println();
        System.out.println("  ListasEnlazadas_ejercicio_4<String>: primero en entrar = primero en salir");
        System.out.println("  ListasEnlazadas_ejercicio_4<Cliente>: primero en entrar = primero en salir");
        System.out.println("  La lógica de head/tail y enlaces es IDÉNTICA.");
        System.out.println("  Solo cambia el TIPO de dato que se almacena en cada nodo.");
        System.out.println();
        System.out.println("═══ FIN DE LA DEMOSTRACIÓN ═══");
    }
}