import java.util.ArrayList;

public class Usuario {
    String nombre;
    String idUSuario;
    ArrayList<Object> librosPrestados = new ArrayList<>();

    public Usuario(String nombre,String idUSuario,ArrayList<Object> librosPrestados){
        this.nombre=nombre;
        this.idUSuario=idUSuario;
        this.librosPrestados=librosPrestados;
    }

    public void mostrarDatos(){

        System.out.println("Nombre del usuario: "+nombre+"\nId del usuario: "+idUSuario);
        for(int i=0 ; i<librosPrestados.size() ; i++){
            System.out.println(librosPrestados.get(i));
        }
    }
    public void agregarPrestamo(Libro libro){
        if (!libro.disponible) {
            if (librosPrestados.contains(libro)) {
                System.out.println("El libro seleccionado ya está en tu lista de libros prestados");
            }else{
                System.out.println("El libro seleccionado no está disponible");
            }
            
        }else{
            librosPrestados.add(libro);
            System.out.println("El libro ha sido agregado con éxito");         
            libro.cambiarDisponibilidad();   
        }
    }
    public void devolverLibro(Libro libro) {
        if (librosPrestados.contains(libro)) {
            librosPrestados.remove(libro);
            libro.cambiarDisponibilidad();
            System.out.println("El libro '" + libro.titulo + "' ha sido devuelto correctamente");
        } else {
            System.out.println("El libro '" + libro.titulo + "' no estaba en la lista de los libros prestados a "+nombre);
        }
    }
}
