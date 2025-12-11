import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

// Clase principal que maneja el sistema de aerolinea
public class SistemaAereolinea {

    // Atributos del sistema
    private List<Vuelo> listaVuelos;              // Lista de todos los vuelos
    private Stack<OperacionHistorial> historial;  // Pila para el historial de operaciones
    private Scanner sc;                           // Scanner para leer entrada

    // Constructor del sistema
    public SistemaAereolinea() {
        listaVuelos = new ArrayList<>();
        historial = new Stack<>();
        sc = new Scanner(System.in);
    }

    // Metodo que inicia el sistema y muestra el menu principal
    public void iniciar() {
        int opcion;
        do {
            // Muestra el menu
            System.out.println("\n===== SISTEMA DE AEROLINEA =====");
            System.out.println("1. Registrar vuelo");
            System.out.println("2. Listar vuelos");
            System.out.println("3. Buscar vuelo por codigo");
            System.out.println("4. Registrar pasajero en vuelo");
            System.out.println("5. Cancelar reserva");
            System.out.println("6. Mostrar pasajeros de un vuelo");
            System.out.println("7. Mostrar lista de espera");
            System.out.println("8. Deshacer ultima operacion");
            System.out.println("9. Ver historial");
            System.out.println("10. Reportes recursivos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine();  // Limpiar buffer

                // Ejecuta la opcion seleccionada
                switch (opcion) {
                    case 1: registrarVuelo(); break;
                    case 2: listarVuelos(); break;
                    case 3: buscarVueloPorCodigo(); break;
                    case 4: registrarPasajeroEnVuelo(); break;
                    case 5: cancelarReserva(); break;
                    case 6: mostrarPasajerosDeVuelo(); break;
                    case 7: mostrarListaEspera(); break;
                    case 8: deshacerOperacion(); break;
                    case 9: verHistorial(); break;
                    case 10: menuReportes(); break;
                    case 0: System.out.println("Saliendo..."); break;
                    default: System.out.println("Opcion invalida");
                }
            } catch (Exception e) {
                // Manejo de errores cuando se ingresa texto en lugar de numero
                System.out.println("Error: Ingrese un numero valido");
                sc.nextLine();
                opcion = -1;
            }

        } while (opcion != 0);
    }

    // Registra un nuevo vuelo en el sistema
    private void registrarVuelo() {
        System.out.print("Codigo de vuelo: ");
        String codigo = sc.nextLine();

        // Validacion: codigo no vacio
        if (codigo.trim().isEmpty()) {
            System.out.println("Error: El codigo no puede estar vacio");
            return;
        }

        // Validacion: codigo no duplicado
        if (buscarVueloRecursivo(codigo, 0) != null) {
            System.out.println("Error: Ya existe un vuelo con ese codigo");
            return;
        }

        System.out.print("Origen: ");
        String origen = sc.nextLine();

        System.out.print("Destino: ");
        String destino = sc.nextLine();

        System.out.print("Capacidad maxima: ");
        int capacidad = sc.nextInt();
        sc.nextLine();

        // Validacion: capacidad positiva
        if (capacidad <= 0) {
            System.out.println("Error: La capacidad debe ser mayor a 0");
            return;
        }

        // Crea y agrega el vuelo
        Vuelo v = new Vuelo(codigo, origen, destino, capacidad);
        listaVuelos.add(v);

        System.out.println("Vuelo registrado exitosamente");
    }

    // Muestra todos los vuelos registrados
    private void listarVuelos() {
        if (listaVuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados");
            return;
        }

        System.out.println("\n===== LISTA DE VUELOS =====");
        for (Vuelo v : listaVuelos) {
            System.out.println(v);
        }
    }

    // Busca y muestra un vuelo por su codigo
    private void buscarVueloPorCodigo() {
        System.out.print("Ingrese codigo del vuelo: ");
        String codigo = sc.nextLine();

        Vuelo v = buscarVueloRecursivo(codigo, 0);

        if (v == null) {
            System.out.println("Vuelo no encontrado");
        } else {
            System.out.println("Vuelo encontrado:");
            System.out.println(v);
        }
    }

    /*
     * Busca un vuelo por codigo de forma recursiva
     * CASO BASE: Si indice es mayor o igual al tamanio de la lista retorna null
     * CASO RECURSIVO: Si encuentra el codigo lo retorna, sino busca en indice mas uno
     */
    private Vuelo buscarVueloRecursivo(String codigo, int indice) {
        // Caso base: llegamos al final sin encontrar
        if (indice >= listaVuelos.size()) {
            return null;
        }

        // Caso base: encontramos el vuelo
        if (listaVuelos.get(indice).getCodigoVuelo().equalsIgnoreCase(codigo)) {
            return listaVuelos.get(indice);
        }

        // Caso recursivo: buscar en el siguiente indice
        return buscarVueloRecursivo(codigo, indice + 1);
    }

    // Registra un pasajero en un vuelo o en lista de espera
    private void registrarPasajeroEnVuelo() {
        listarVuelos();
        System.out.print("Codigo del vuelo: ");
        String codigo = sc.nextLine();

        // Busca el vuelo
        Vuelo vuelo = buscarVueloRecursivo(codigo, 0);
        if (vuelo == null) {
            System.out.println("Vuelo no encontrado");
            return;
        }

        // Lee datos del pasajero
        System.out.print("Nombre del pasajero: ");
        String nombre = sc.nextLine();

        System.out.print("Documento: ");
        String documento = sc.nextLine();

        System.out.print("Nacionalidad: ");
        String nacionalidad = sc.nextLine();

        // Crea el pasajero
        Pasajero p = new Pasajero(vuelo.getListaPasajeros().size() + 1, nombre, documento, nacionalidad);

        // Verifica si hay cupo o va a lista de espera
        if (vuelo.hayCupo()) {
            vuelo.agregarPasajero(p);
            historial.push(new OperacionHistorial("RESERVA", p, vuelo));
            System.out.println("Pasajero agregado al vuelo");
        } else {
            vuelo.agregarPasajeroListaEspera(p);
            historial.push(new OperacionHistorial("LISTA_ESPERA", p, vuelo));
            System.out.println("Vuelo lleno. Pasajero agregado a lista de espera");
        }
    }

    // Muestra los pasajeros confirmados de un vuelo
    private void mostrarPasajerosDeVuelo() {
        listarVuelos();
        System.out.print("Codigo del vuelo: ");
        String codigo = sc.nextLine();

        Vuelo vuelo = buscarVueloRecursivo(codigo, 0);
        if (vuelo == null) {
            System.out.println("Vuelo no encontrado");
            return;
        }

        System.out.println("\n===== PASAJEROS CONFIRMADOS =====");
        if (vuelo.getListaPasajeros().isEmpty()) {
            System.out.println("No hay pasajeros confirmados en este vuelo");
        } else {
            for (Pasajero p : vuelo.getListaPasajeros()) {
                System.out.println(p);
            }
        }
    }

    // Muestra la lista de espera de un vuelo
    private void mostrarListaEspera() {
        listarVuelos();
        System.out.print("Codigo del vuelo: ");
        String codigo = sc.nextLine();

        Vuelo vuelo = buscarVueloRecursivo(codigo, 0);
        if (vuelo == null) {
            System.out.println("Vuelo no encontrado");
            return;
        }

        System.out.println("\n===== LISTA DE ESPERA =====");
        if (vuelo.getListaEspera().isEmpty()) {
            System.out.println("No hay pasajeros en lista de espera");
        } else {
            for (Pasajero p : vuelo.getListaEspera()) {
                System.out.println(p);
            }
        }
    }

    // Cancela la reserva de un pasajero y promueve al siguiente de la lista de espera
    private void cancelarReserva() {
        listarVuelos();
        System.out.print("Codigo del vuelo: ");
        String codigo = sc.nextLine();

        Vuelo vuelo = buscarVueloRecursivo(codigo, 0);
        if (vuelo == null) {
            System.out.println("Vuelo no encontrado");
            return;
        }

        System.out.print("Documento del pasajero a cancelar: ");
        String doc = sc.nextLine();

        // Busca el pasajero a cancelar
        Pasajero eliminado = null;
        for (Pasajero p : vuelo.getListaPasajeros()) {
            if (p.getDocumento().equalsIgnoreCase(doc)) {
                eliminado = p;
                break;
            }
        }

        if (eliminado == null) {
            System.out.println("Pasajero no encontrado en este vuelo");
            return;
        }

        // Elimina el pasajero y registra en historial
        vuelo.getListaPasajeros().remove(eliminado);
        historial.push(new OperacionHistorial("CANCELACION", eliminado, vuelo));

        // Si hay pasajeros en lista de espera, promueve al primero
        if (!vuelo.getListaEspera().isEmpty()) {
            Pasajero siguiente = vuelo.pasarDesdeEspera();
            vuelo.agregarPasajero(siguiente);
            historial.push(new OperacionHistorial("PASAR_DE_ESPERA", siguiente, vuelo));
            System.out.println("Pasajero de lista de espera promovido: " + siguiente.getNombre());
        }

        System.out.println("Reserva cancelada");
    }

    // Deshace la ultima operacion realizada usando la pila de historial
    private void deshacerOperacion() {
        if (historial.isEmpty()) {
            System.out.println("No hay operaciones para deshacer");
            return;
        }

        // Saca la ultima operacion de la pila
        OperacionHistorial op = historial.pop();
        Vuelo vuelo = op.getVuelo();
        Pasajero pasajero = op.getPasajero();

        System.out.println("Deshaciendo: " + op);

        // Revierte la operacion segun su tipo
        if (op.getTipoOperacion().equals("RESERVA")) {
            vuelo.getListaPasajeros().remove(pasajero);
            System.out.println("Reserva deshecha");
        }
        else if (op.getTipoOperacion().equals("LISTA_ESPERA")) {
            vuelo.getListaEspera().remove(pasajero);
            System.out.println("Eliminado de lista de espera");
        }
        else if (op.getTipoOperacion().equals("CANCELACION")) {
            vuelo.agregarPasajero(pasajero);
            System.out.println("Cancelacion revertida");
        }
        else if (op.getTipoOperacion().equals("PASAR_DE_ESPERA")) {
            vuelo.getListaPasajeros().remove(pasajero);
            vuelo.agregarPasajeroListaEspera(pasajero);
            System.out.println("Movimiento revertido");
        }
    }

    // Muestra el historial de operaciones realizadas
    private void verHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No hay operaciones en el historial");
            return;
        }

        System.out.println("\n===== HISTORIAL DE OPERACIONES =====");

        // Crea una copia temporal para no modificar la pila original
        Stack<OperacionHistorial> temp = new Stack<>();
        temp.addAll(historial);

        // Muestra las operaciones de la mas reciente a la mas antigua
        int num = temp.size();
        while (!temp.isEmpty()) {
            System.out.println(num + ". " + temp.pop());
            num--;
        }
    }

    // Muestra el menu de reportes recursivos
    private void menuReportes() {
        System.out.println("\n===== REPORTES =====");
        System.out.println("1. Contar pasajeros en un vuelo");
        System.out.println("2. Contar pasajeros por destino");
        System.out.println("3. Buscar pasajero por documento");
        System.out.print("Seleccione una opcion: ");
        int opc = sc.nextInt();
        sc.nextLine();

        switch (opc) {
            case 1: reporteContarPasajerosVuelo(); break;
            case 2: reportePasajerosPorDestino(); break;
            case 3: reporteBuscarPasajeroDocumento(); break;
            default: System.out.println("Opcion invalida");
        }
    }

    /*
     * Cuenta recursivamente el total de pasajeros en una lista
     * CASO BASE: Si indice es igual al tamanio de la lista retorna 0
     * CASO RECURSIVO: Suma 1 y llama recursivamente con indice mas uno
     */
    private int contarPasajerosRec(List<Pasajero> lista, int indice) {
        // Caso base: llegamos al final de la lista
        if (indice == lista.size()) {
            return 0;
        }
        // Caso recursivo: contar este pasajero mas los siguientes
        return 1 + contarPasajerosRec(lista, indice + 1);
    }

    // Genera reporte de total de pasajeros en un vuelo usando recursividad
    private void reporteContarPasajerosVuelo() {
        listarVuelos();
        System.out.print("Codigo de vuelo: ");
        String codigo = sc.nextLine();

        Vuelo v = buscarVueloRecursivo(codigo, 0);
        if (v == null) {
            System.out.println("Vuelo no encontrado");
            return;
        }

        int total = contarPasajerosRec(v.getListaPasajeros(), 0);
        System.out.println("Total pasajeros recursivo: " + total);
    }

    /*
     * Cuenta recursivamente pasajeros que viajan a un destino dado
     * CASO BASE: Si indice es igual al tamanio de la lista de vuelos retorna 0
     * CASO RECURSIVO: Si el vuelo coincide con el destino suma sus pasajeros
     *                 y llama recursivamente con indice mas uno
     */
    private int contarDestinoRec(String destino, int indice) {
        // Caso base: llegamos al final de la lista de vuelos
        if (indice == listaVuelos.size()) {
            return 0;
        }

        Vuelo v = listaVuelos.get(indice);
        int suma = 0;

        // Si el destino coincide, contamos los pasajeros de ese vuelo
        if (v.getDestino().equalsIgnoreCase(destino)) {
            suma = v.getListaPasajeros().size();
        }

        // Caso recursivo: sumar los pasajeros de este vuelo mas los siguientes
        return suma + contarDestinoRec(destino, indice + 1);
    }

    // Genera reporte de pasajeros por destino usando recursividad
    private void reportePasajerosPorDestino() {
        System.out.print("Destino: ");
        String destino = sc.nextLine();

        int total = contarDestinoRec(destino, 0);
        System.out.println("Total pasajeros hacia " + destino + ": " + total);
    }

    /*
     * Busca recursivamente un pasajero por documento en todos los vuelos
     * CASO BASE: Si vueloIndex es igual al tamanio de la lista retorna false
     * CASO RECURSIVO: Si encuentra el documento retorna true, sino busca en
     *                 el siguiente pasajero o siguiente vuelo
     */
    private boolean buscarPasajeroRec(String doc, int vueloIndex, int pasajeroIndex) {
        // Caso base: llegamos al final de todos los vuelos
        if (vueloIndex == listaVuelos.size()) {
            return false;
        }

        Vuelo v = listaVuelos.get(vueloIndex);

        // Si terminamos de revisar todos los pasajeros de este vuelo
        // pasamos al siguiente vuelo
        if (pasajeroIndex >= v.getListaPasajeros().size()) {
            return buscarPasajeroRec(doc, vueloIndex + 1, 0);
        }

        // Caso base: encontramos el pasajero
        if (v.getListaPasajeros().get(pasajeroIndex).getDocumento().equalsIgnoreCase(doc)) {
            System.out.println("Pasajero encontrado en vuelo " + v.getCodigoVuelo());
            return true;
        }

        // Caso recursivo: buscar en el siguiente pasajero
        return buscarPasajeroRec(doc, vueloIndex, pasajeroIndex + 1);
    }

    // Genera reporte de busqueda de pasajero por documento usando recursividad
    private void reporteBuscarPasajeroDocumento() {
        System.out.print("Documento: ");
        String doc = sc.nextLine();

        if (!buscarPasajeroRec(doc, 0, 0)) {
            System.out.println("Pasajero no encontrado");
        }
    }
}