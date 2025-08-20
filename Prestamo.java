import java.time.LocalDate;

public class Prestamo {
    Libro libro;
    Usuario usuario;
    LocalDate fechaInicio;
    LocalDate fechaLimite;

    public Prestamo(Libro libro, Usuario usuario, LocalDate fechaInicio, LocalDate fechaLimite) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaInicio = fechaInicio;
        this.fechaLimite = fechaLimite;
    }

    @Override
    public String toString() {
        return "Préstamo: " + libro.titulo + " a " + usuario.nombre +
               " | Inicio: " + fechaInicio + " | Límite: " + fechaLimite;
    }
}
