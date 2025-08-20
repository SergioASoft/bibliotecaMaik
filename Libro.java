public class Libro {
    String titulo;
    String autor;
    String codigo;
    Boolean disponible;

    public Libro(String titulo,String autor,String codigo,Boolean disponible){
        this.titulo=titulo;
        this.autor=autor;
        this.codigo=codigo;
        this.disponible=disponible;
    }
    public void mostrarDatos(){

        System.out.println("Titulo del libro: "+titulo+"\nAutor del libro: "+autor+"\nCódigo: "+codigo+"");
        if (disponible) {
            System.out.println("Disponible");    
        }else{
            System.out.println("No disponible"); 
        }
    }

    public void cambiarDisponibilidad(){
        disponible = !disponible;
    }
    @Override
    
    public String toString() {
        return titulo + " - " + autor + " (" + codigo + ")";
    }

    // Igualdad por código (puedes cambiar criterio si lo deseas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return codigo.equals(libro.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
