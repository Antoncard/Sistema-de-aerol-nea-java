# Sistema-de-aerol-nea-java
Proyector final de estructura de datos

✈️ Sistema de Gestión de Aerolínea
Proyecto final – Estructura de Datos (Java – Consola)
👨‍🎓 Autores

Antonio Cardona

Santiago Cruz

Jeisson Guarín

Docente: Diego Alejandro Franco
Asignatura: Estructura de Datos

📘 Descripción del Proyecto

Este proyecto implementa un sistema de gestión de aerolínea por consola en Java, utilizando las estructuras de datos vistas en clase:

Listas (ArrayList)

Colas (Queue – LinkedList)

Pilas (Stack)

Recursividad lineal

El sistema permite registrar vuelos, administrar pasajeros, manejar listas de espera, deshacer operaciones y generar reportes mediante métodos recursivos.

🧩 Estructura del Proyecto
📁 Clases principales
Clase	Descripción
Pasajero.java	Representa a un pasajero (id, nombre, documento, nacionalidad).
Vuelo.java	Maneja información del vuelo, pasajeros confirmados y lista de espera.
OperacionHistorial.java	Registra operaciones para ser revertidas con una pila.
SistemaAereolinea.java	Contiene el menú, lógica principal y métodos recursivos.
Main.java	Punto de entrada: instancia el sistema y lo ejecuta.
🔧 Estructuras de Datos Utilizadas
📍 1. Listas (ArrayList)

Usos:

Lista global de vuelos

Pasajeros confirmados

Por qué: acceso por índice, facilidad para recorrerlas y aplicar recursividad.

📍 2. Colas (Queue – LinkedList)

Usos:

Lista de espera por vuelo

Comportamiento:

add() → ingresa al final

poll() → atiende al primero (FIFO)

Ejemplo: si el vuelo está lleno, nuevos pasajeros pasan a la cola; al liberar un cupo, el primero en espera sube automáticamente.

📍 3. Pilas (Stack)

Usos:

Historial de operaciones para deshacer cambios

Operaciones:

push() → agregar operación

pop() → revertir última acción (LIFO)

Operaciones almacenadas:

Reserva

Cancelación

Agregado a lista de espera

Pase de espera a confirmado

🔁 Recursividad Implementada

El programa incluye 4 métodos recursivos lineales:

Método	Propósito
buscarVueloRecursivo()	Busca un vuelo por código recorriendo la lista recursivamente.
contarPasajerosRec()	Cuenta pasajeros confirmados en un vuelo.
contarDestinoRec()	Suma pasajeros de todos los vuelos con un destino específico.
buscarPasajeroRec()	Busca un pasajero en todos los vuelos y pasajeros.
📜 Funcionalidades del Sistema
🧭 Menú (11 opciones):

Registrar vuelo

Listar vuelos

Buscar vuelo

Registrar pasajero

Cancelar reserva

Mostrar pasajeros confirmados

Mostrar lista de espera

Deshacer última operación

Ver historial completo

Reportes recursivos

Salir

🔒 Validaciones

Código de vuelo único

Capacidad > 0

Manejo de errores en entradas

Revisiones de listas vacías

Limpieza correcta del Scanner

🧪 Pruebas Realizadas
✔️ Vuelos completos

El tercer pasajero pasa a lista de espera correctamente.

✔️ Movimiento automático desde lista de espera

Un pasajero de la cola sube al cancelarse una reserva.

✔️ Deshacer operaciones

Funciona incluso cuando se deben revertir múltiples acciones.

✔️ Métodos recursivos

Todos devuelven resultados correctos en escenarios variados.

✔️ Validaciones

Detecta errores de usuario y evita datos inválidos.

⚠️ Dificultades y Soluciones
1. Limpieza del Scanner

Solución: sc.nextLine() después de cada nextInt().

2. Deshacer incompleto

Solución: Registrar dos operaciones: cancelación y pase de espera.

3. Recursividad incompleta

Solución: Manejo de dos índices (vuelo y pasajero).

4. IDs duplicados

Solución: ID = tamaño de lista + 1.

🚀 Mejoras Implementadas

Búsqueda rápida por código

Historial completo sin perder datos

Validaciones adicionales

Mejor manejo de excepciones

Mensajes más descriptivos

Verificación de listas vacías

🏁 Conclusión
Logros

Implementación correcta de listas, colas, pilas y recursividad

Funcionalidades del proyecto totalmente cubiertas

Sistema estable y validado mediante pruebas

Interfaz de consola clara y amigable

Aprendizajes

Uso eficiente de estructuras dinámicas

Aplicación práctica de FIFO (colas) y LIFO (pilas)

Importancia del caso base en recursividad

Manejo de flujos reales como los de una aerolínea

Posibles mejoras futuras:

Guardar datos en archivos

Manejo de fechas

Precios y pagos

Filtros avanzados de búsqueda

Estadísticas generales de ocupación

▶️ Instrucciones de Uso
🔧 Compilación
javac Pasajero.java
javac Vuelo.java
javac OperacionHistorial.java
javac SistemaAereolinea.java
javac Main.java

🚀 Ejecución
java Main

🔄 Flujo recomendado

Registrar vuelos

Registrar pasajeros

Visualizar listas

Cancelar reservas

Probar el sistema de deshacer

Revisar historial

Ejecutar reportes recursivos
