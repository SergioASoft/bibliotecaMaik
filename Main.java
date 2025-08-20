import java.util.*;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENÚ BIBLIOTECA ===");
            System.out.println("1. Registrar libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar libros disponibles");
            System.out.println("6. Mostrar usuarios");
            System.out.println("7. Mostrar historial de préstamos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Código: ");
                    String codigo = scanner.nextLine();
                    biblioteca.registrarLibro(new Libro(titulo, autor, codigo, true));
                    break;

                case 2:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("ID Usuario: ");
                    String id = scanner.nextLine();
                    biblioteca.registrarUsuario(new Usuario(nombre, id, new ArrayList<>()));
                    break;

                case 3:
                    biblioteca.mostrarUsuarios();
                    biblioteca.mostrarLibrosDisponibles();
                    System.out.print("Ingrese código del libro a prestar: ");
                    String codigoPrestamo = scanner.nextLine();
                    System.out.print("Ingrese ID usuario: ");
                    String idUsuario = scanner.nextLine();

                    Libro libroPrestamo = biblioteca.libros.stream()
                        .filter(l -> l.codigo.equals(codigoPrestamo))
                        .findFirst().orElse(null);
                    Usuario usuarioPrestamo = biblioteca.usuarios.stream()
                        .filter(u -> u.idUSuario.equals(idUsuario))
                        .findFirst().orElse(null);

                    if (libroPrestamo != null && usuarioPrestamo != null) {
                        biblioteca.prestarLibro(usuarioPrestamo, libroPrestamo);
                    } else {
                        System.out.println("Error: libro o usuario no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Código libro a devolver: ");
                    String codigoDevolver = scanner.nextLine();
                    System.out.print("ID usuario: ");
                    String idDev = scanner.nextLine();

                    Libro libroDevolver = biblioteca.libros.stream()
                        .filter(l -> l.codigo.equals(codigoDevolver))
                        .findFirst().orElse(null);
                    Usuario usuarioDev = biblioteca.usuarios.stream()
                        .filter(u -> u.idUSuario.equals(idDev))
                        .findFirst().orElse(null);

                    if (libroDevolver != null && usuarioDev != null) {
                        biblioteca.devolverLibro(usuarioDev, libroDevolver);
                    } else {
                        System.out.println("Error: libro o usuario no encontrado.");
                    }
                    break;

                case 5:
                    biblioteca.mostrarLibrosDisponibles();
                    break;

                case 6:
                    biblioteca.mostrarUsuarios();
                    break;

                case 7:
                    biblioteca.mostrarHistorialPrestamos();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
