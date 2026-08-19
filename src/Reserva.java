import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private final int idReserva;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private int numHuesped;
    private Cliente cliente;
    private Habitacion habitacion;
    private BigDecimal importe;

    public Reserva(int idReserva, LocalDate fechaEntrada, LocalDate fechaSalida, int numHuesped, Cliente cliente, Habitacion habitacion){
        this.idReserva = idReserva;

        validarFechasReserva(fechaEntrada, fechaSalida);

        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.cliente = cliente;
        this.habitacion = habitacion;

        validarNumHuesped(numHuesped);

        this.numHuesped = numHuesped;
        this.importe = importeTotalEstancia(fechaEntrada, fechaSalida);
    }

    public int getIdReserva() {
        return idReserva;
    }

    public int getNumHuesped() {
        return numHuesped;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    // ↓↓↓ REGLAS DE NEGOCIO ↓↓↓

    /**Este método nos permite validar si el número de huespedes cumple con la capacidad de la habitación o si
     es un número correcto superior a cero**/
    private void validarNumHuesped (int numHuesped){ // Es privado porque otras clases no deben acceder a él
        if (numHuesped > habitacion.getTipoHabitacion().getCapacidad()){
            throw new IllegalArgumentException(
                    "El número de huspedes es superior a la capacidad de la habitación."
            );
        }

        if (numHuesped <= 0){
            throw new IllegalArgumentException(
                    "El número de huespedes indicados no es válido."
            );
        }
    }

    /**Este métdodo nos comprobar que las fechas de entrada y salida sean correctas entre sí**/
    private void validarFechasReserva(LocalDate fechaEntrada, LocalDate fechaSalida){
        if (fechaSalida.isBefore(fechaEntrada) || fechaEntrada.isEqual(fechaSalida)){
            throw new IllegalArgumentException(
                    "Las fechas indicadas no son válidas."
            );
        }
    }

    /**Método para calcular el importe total de la estancia en base a los días que se hospedará el huesped**/
    private BigDecimal importeTotalEstancia(LocalDate fechaEntrada, LocalDate fechaSalida){
        BigDecimal precioEstancia;
        long diasEstancia; // Calculamos los días que permanecerá el huesped

        diasEstancia = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);

        BigDecimal bdDiasEstancia = BigDecimal.valueOf(diasEstancia); // Para operar la variable diasEstancia con importeTotalEstancia hay que convertir la variable long a tipo BigDecimal

        precioEstancia = bdDiasEstancia.multiply(habitacion.getTipoHabitacion().getPrecio()); // Se usan los métodos de la clase BigDeciaml para operar

        return precioEstancia;
    }
}
