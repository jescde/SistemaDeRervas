# SistemaDeRervas

Aplicación de consola desarrollada en Java para gestionar reservas de habitaciones de hotel.

El proyecto está actualmente en desarrollo y tiene como objetivo aplicar conceptos fundamentales de Java y programación orientada a objetos mediante un caso práctico: modelado de entidades, relaciones entre objetos, encapsulación, enumeraciones, validaciones, excepciones, manejo de fechas y cálculo de importes monetarios.

Estado del proyecto

En desarrollo.

Actualmente está implementado el modelo principal de dominio y varias reglas de negocio relacionadas con la creación de reservas.

La aplicación todavía no dispone de toda la lógica necesaria para realizar, consultar y cancelar reservas desde el menú de consola.

Funcionalidades implementadas
Modelado de clientes.
Modelado de habitaciones.
Tipos de habitación mediante enum.
Precio por noche asociado al tipo de habitación.
Capacidad máxima asociada al tipo de habitación.
Creación del modelo Reserva.
Validación del número de huéspedes.
Validación de fechas de entrada y salida.
Cálculo automático del importe de la estancia.
Uso de BigDecimal para representar valores monetarios.
Uso de excepciones para impedir la creación de reservas con datos inválidos.
Estructura actual

Una Reserva está asociada a un Cliente y una Habitacion.

Cada Habitacion tiene asociado un TipoHabitacion, que determina actualmente su precio por noche y su capacidad máxima.

Modelo de dominio
Cliente

Representa al cliente que realiza una reserva.

Atributos actuales:

private int idCliente;
private String nombre;
private String apellido;
private String correo;

Los datos personales pueden modificarse mediante setters, mientras que el identificador del cliente se utiliza para identificarlo dentro del sistema.

TipoHabitacion

TipoHabitacion es un enum que define los tipos de habitación disponibles.

Actualmente existen:

Tipo	Precio/noche	Capacidad
INDIVIDUAL	50,00 €	1
DOBLE	90,00 €	2
SUITE	120,00 €	4

Implementación actual:

public enum TipoHabitacion {

    INDIVIDUAL(new BigDecimal("50.00"), 1),
    DOBLE(new BigDecimal("90.00"), 2),
    SUITE(new BigDecimal("120.00"), 4);

    private BigDecimal precio;
    private int capacidad;

    TipoHabitacion(BigDecimal precio, int capacidad) {
        this.precio = precio;
        this.capacidad = capacidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getCapacidad() {
        return capacidad;
    }
}

Se utiliza BigDecimal en lugar de double para trabajar con importes monetarios con mayor precisión.

Habitacion

Representa una habitación concreta del hotel.

Actualmente contiene:

private int idHabitacion;
private TipoHabitacion tipoHabitacion;

La capacidad y el precio no se almacenan directamente en Habitacion, ya que pueden obtenerse a través de su TipoHabitacion.

Por ejemplo:

habitacion.getTipoHabitacion().getPrecio();

permite obtener el precio por noche.

Y:

habitacion.getTipoHabitacion().getCapacidad();

permite consultar su capacidad máxima.

Esto evita duplicar información dentro del modelo.

Reserva

Representa una reserva realizada por un cliente sobre una habitación.

Actualmente contiene:

private final int idReserva;
private LocalDate fechaEntrada;
private LocalDate fechaSalida;
private int numHuesped;
private Cliente cliente;
private Habitacion habitacion;
private BigDecimal importe;

La reserva relaciona los principales elementos del sistema:

Reglas de negocio

La clase Reserva contiene actualmente varias validaciones internas.

Validación del número de huéspedes

Una reserva no puede contener un número de huéspedes superior a la capacidad de la habitación.

if (numHuesped > habitacion.getTipoHabitacion().getCapacidad()) {
    throw new IllegalArgumentException(
        "El número de huéspedes es superior a la capacidad de la habitación."
    );
}

Tampoco se permiten valores iguales o inferiores a cero:

if (numHuesped <= 0) {
    throw new IllegalArgumentException(
        "El número de huéspedes indicado no es válido."
    );
}

Estas validaciones evitan que pueda existir una Reserva con un estado inválido.

Validación de fechas

La fecha de salida debe ser posterior a la fecha de entrada.

Se rechazan:

fechaSalida < fechaEntrada

y:

fechaSalida == fechaEntrada

La comprobación utiliza LocalDate:

if (fechaSalida.isBefore(fechaEntrada)
        || fechaEntrada.isEqual(fechaSalida)) {

    throw new IllegalArgumentException(
        "Las fechas indicadas no son válidas."
    );
}

Por tanto, una reserva debe tener como mínimo una noche de duración.

Cálculo del importe

El importe se calcula automáticamente a partir de:

Número de noches × Precio por noche

El número de noches se obtiene utilizando:

ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);

El precio por noche procede del tipo de habitación:

habitacion
    .getTipoHabitacion()
    .getPrecio();

Finalmente se realiza la operación mediante BigDecimal:

private BigDecimal importeTotalEstancia(
        LocalDate fechaEntrada,
        LocalDate fechaSalida) {

    long diasEstancia =
            ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);

    BigDecimal bdDiasEstancia =
            BigDecimal.valueOf(diasEstancia);

    BigDecimal precioEstancia =
            bdDiasEstancia.multiply(
                    habitacion.getTipoHabitacion().getPrecio()
            );

    return precioEstancia;
}

Por ejemplo:

Tipo de habitación: DOBLE
Precio por noche:    90,00 €
Entrada:              20/08/2026
Salida:               24/08/2026

Duración:             4 noches

4 × 90,00 € = 360,00 €

El importe resultante queda almacenado dentro de la propia Reserva.

Esto permite conservar el precio correspondiente al momento en el que se creó la reserva.

Tecnologías utilizadas
Java
Programación Orientada a Objetos
LocalDate
ChronoUnit
BigDecimal
Excepciones con IllegalArgumentException
Enumeraciones (enum)

Actualmente el proyecto no utiliza frameworks externos.

Conceptos aplicados

Durante el desarrollo se están trabajando especialmente los siguientes conceptos:

Clases y objetos
Constructores
Encapsulación
Getters y setters
Relaciones entre objetos
Enumeraciones
Atributos final
Tipos primitivos frente a objetos
Excepciones
Validación de datos
Reglas de negocio
Manejo de fechas
Operaciones monetarias con BigDecimal
Separación de responsabilidades
Próximos pasos

El proyecto todavía se encuentra en una fase inicial/intermedia.

Las siguientes funcionalidades previstas son:

Completar y revisar la clase Reserva.
Implementar la gestión de habitaciones disponibles.
Implementar almacenamiento de reservas.
Crear ReservaService.
Implementar creación de reservas.
Comprobar disponibilidad de habitaciones según fechas.
Consultar reservas.
Cancelar reservas.
Conectar la lógica de negocio con el menú de consola.
Gestionar las excepciones desde la interfaz de consola.
Añadir persistencia mediante fichero o base de datos.
Incorporar pruebas.

La arquitectura prevista evolucionará aproximadamente hacia:

Main deberá encargarse principalmente de la interacción con el usuario, mientras que la lógica relacionada con las reservas se trasladará progresivamente a servicios y otras clases especializadas.

Objetivo del proyecto

El objetivo principal no es únicamente construir un sistema de reservas funcional, sino utilizar el proyecto para profundizar progresivamente en el desarrollo de aplicaciones con Java.

La primera versión será una aplicación de consola.

A medida que evolucione el proyecto se podrán incorporar conceptos como persistencia, arquitectura por capas, bases de datos, testing y separación entre lógica de negocio e interfaz.

Versión actual: prototipo / aplicación de consola en desarrollo.
