
public class OperacionHistorial {

    // Atributos de la operacion
    private String tipoOperacion;  // Tipo: RESERVA, CANCELACION, etc
    private Pasajero pasajero;     // Pasajero involucrado
    private Vuelo vuelo;           // Vuelo involucrado

    // Constructor
    public OperacionHistorial(String tipoOperacion, Pasajero pasajero, Vuelo vuelo) {
        this.tipoOperacion = tipoOperacion;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
    }

    // Retorna el tipo de operacion
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    // Retorna el pasajero
    public Pasajero getPasajero() {
        return pasajero;
    }

    // Retorna el vuelo
    public Vuelo getVuelo() {
        return vuelo;
    }

    // Convierte la operacion a texto
    @Override
    public String toString() {
        return tipoOperacion + " | Pasajero: " + pasajero.getNombre() + " | Vuelo: " + vuelo.getCodigoVuelo();
    }
}
