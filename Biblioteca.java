import java.util.*;
import java.time.*;

public class Biblioteca {
    ArrayList<Libro> libros = new ArrayList<>();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Prestamo> prestamos = new ArrayList<>();

    // Registrar libro
    public void registrarLibro(Libro libro) {
        if (libros.contains(libro)) {
            System.out.println("El código del libro que se va a añadir ya se encuentra registrado");
        }
        libros.add(libro);
        System.out.println("Libro registrado: " + libro.titulo);
    }

    // Registrar usuario
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario registrado: " + usuario.nombre);
    }

    // Prestar libro
    public void prestarLibro(Usuario usuario, Libro libro) {
        if (!libro.disponible) {
            System.out.println("El libro ya está prestado.");
            return;
        }
        if (usuario.librosPrestados.size() >= 3) {
            System.out.println("El usuario ya tiene 3 libros prestados.");
            return;
        }

        usuario.agregarPrestamo(libro);
        LocalDate inicio = LocalDate.now();
        LocalDate limite = inicio.plusDays(7); // 7 días de préstamo
        prestamos.add(new Prestamo(libro, usuario, inicio, limite));

        System.out.println("Préstamo realizado: " + libro.titulo + 
                           " hasta " + limite);
    }

    // Devolver libro con multa
    public void devolverLibro(Usuario usuario, Libro libro) {
        Optional<Prestamo> prestamoOpt = prestamos.stream()
            .filter(p -> p.libro.equals(libro) && p.usuario.equals(usuario))
            .findFirst();

        if (prestamoOpt.isPresent()) {
            Prestamo prestamo = prestamoOpt.get();
            usuario.devolverLibro(libro);
            prestamos.remove(prestamo);

            LocalDate hoy = LocalDate.now();
            if (hoy.isAfter(prestamo.fechaLimite)) {
                long diasRetraso = Duration.between(prestamo.fechaLimite.atStartOfDay(), hoy.atStartOfDay()).toDays();
                long multa = diasRetraso * 500;
                System.out.println("⚠️ El libro se devolvió con " + diasRetraso + 
                                   " días de retraso. Multa: $" + multa);
            } else {
                System.out.println("Devolución sin multa.");
            }
        } else {
            System.out.println("No existe un préstamo registrado de este libro.");
        }
    }

    // Mostrar libros disponibles
    public void mostrarLibrosDisponibles() {
        System.out.println(libros.size()+" Libros disponibles:");
        for (Libro l : libros) {
            if (l.disponible) {
                System.out.println(l);
            }
        }
    }

    // Mostrar usuarios
    public void mostrarUsuarios() {
        System.out.println(usuarios.size()+" Usuarios registrados:");
        for (Usuario u : usuarios) {
            u.mostrarDatos();
        }
    }

    // Mostrar historial
    public void mostrarHistorialPrestamos() {
        System.out.println("📖 Historial de préstamos:");
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }
}
