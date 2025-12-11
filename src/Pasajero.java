
public class Pasajero {

    // Atributos del pasajero
    private int id;
    private String nombre;
    private String documento;
    private String nacionalidad;

    // Constructor
    public Pasajero(int id, String nombre, String documento, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.nacionalidad = nacionalidad;
    }

    // Retorna el nombre del pasajero
    public String getNombre() {
        return nombre;
    }

    // Retorna el documento del pasajero
    public String getDocumento() {
        return documento;
    }

    // Convierte el pasajero a texto
    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Documento: " + documento + " | Nacionalidad: " + nacionalidad;
    }
}