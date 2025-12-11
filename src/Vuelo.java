import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Vuelo {

    // Atributos del vuelo
    private String codigoVuelo;
    private String origen;
    private String destino;
    private int capacidadMaxima;
    private List<Pasajero> listaPasajeros;  // Lista de pasajeros confirmados
    private Queue<Pasajero> listaEspera;    // Cola para lista de espera

    // Constructor
    public Vuelo(String codigoVuelo, String origen, String destino, int capacidadMaxima) {
        this.codigoVuelo = codigoVuelo;
        this.origen = origen;
        this.destino = destino;
        this.capacidadMaxima = capacidadMaxima;
        this.listaPasajeros = new ArrayList<>();
        this.listaEspera = new LinkedList<>();
    }

    // Retorna el codigo del vuelo
    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    // Retorna el destino del vuelo
    public String getDestino() {
        return destino;
    }

    // Retorna la lista de pasajeros confirmados
    public List<Pasajero> getListaPasajeros() {
        return listaPasajeros;
    }

    // Retorna la cola de espera
    public Queue<Pasajero> getListaEspera() {
        return listaEspera;
    }

    // Verifica si hay cupo disponible en el vuelo
    public boolean hayCupo() {
        return listaPasajeros.size() < capacidadMaxima;
    }

    // Agrega un pasajero a la lista confirmada
    public void agregarPasajero(Pasajero p) {
        listaPasajeros.add(p);
    }

    // Agrega un pasajero a la lista de espera
    public void agregarPasajeroListaEspera(Pasajero p) {
        listaEspera.add(p);
    }

    // Saca el primer pasajero de la lista de espera
    public Pasajero pasarDesdeEspera() {
        return listaEspera.poll();
    }

    // Convierte el vuelo a texto
    @Override
    public String toString() {
        return codigoVuelo + " | " + origen + " -> " + destino +
                " | Capacidad: " + capacidadMaxima +
                " | Ocupados: " + listaPasajeros.size() +
                " | En espera: " + listaEspera.size();
    }
}
