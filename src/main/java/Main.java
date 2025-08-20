import java.util.*;

public class Main {
    private static final GutendexClient client = new GutendexClient();
    private static final List<Libro> librosRegistrados = new ArrayList<>();
    private static final Set<Autor> autoresRegistrados = new HashSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> buscarLibro(scanner);
                case 2 -> listarLibros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivos(scanner);
                case 5 -> listarLibrosPorIdioma(scanner);
                case 0 -> {
                    System.out.println("👋 Saliendo del sistema. ¡Hasta pronto!");
                    return;
                }
                default -> System.out.println("❌ Opción no válida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n===== MENÚ LITERALURA =====");
        System.out.println("1. Buscar libro por título");
        System.out.println("2. Listar libros registrados");
        System.out.println("3. Listar autores registrados");
        System.out.println("4. Listar autores vivos en un determinado año");
        System.out.println("5. Listar libros por idioma");
        System.out.println("0. Salir del sistema");
        System.out.print("Seleccione una opción: ");
    }

    private static void buscarLibro(Scanner scanner) {
        System.out.print("Ingrese título del libro: ");
        String titulo = scanner.nextLine();

        List<Libro> resultados = client.buscarLibro(titulo);

        if (resultados.isEmpty()) {
            System.out.println("❌ No se encontraron resultados.");
        } else {
            resultados.forEach(libro -> {
                System.out.println(libro);
                librosRegistrados.add(libro);
                autoresRegistrados.addAll(libro.getAutores());
            });
        }
    }

    private static void listarLibros() {
        if (librosRegistrados.isEmpty()) {
            System.out.println("📚 No hay libros registrados.");
        } else {
            librosRegistrados.forEach(System.out::println);
        }
    }

    private static void listarAutores() {
        if (autoresRegistrados.isEmpty()) {
            System.out.println("👤 No hay autores registrados.");
        } else {
            autoresRegistrados.forEach(System.out::println);
        }
    }

    private static void listarAutoresVivos(Scanner scanner) {
        System.out.print("Ingrese un año: ");
        int anio = scanner.nextInt();

        autoresRegistrados.stream()
                .filter(a -> a.estaVivoEn(anio))
                .forEach(System.out::println);
    }

    private static void listarLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingrese código de idioma (ej: 'en', 'es', 'fr'): ");
        String idioma = scanner.nextLine();

        librosRegistrados.stream()
                .filter(libro -> libro.getIdioma().equalsIgnoreCase(idioma))
                .forEach(System.out::println);
    }
}
