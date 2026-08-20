import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private final int idReserva;
    private final  LocalDate fechaEntrada;
    private final LocalDate fechaSalida;
    private final int numHuespedes;
    private final Cliente cliente;
    private final Habitacion habitacion;
    private final BigDecimal importe;

    public Reserva(int idReserva, LocalDate fechaEntrada, LocalDate fechaSalida, int numHuespedes, Cliente cliente, Habitacion habitacion){
        this.idReserva = idReserva;

        validarFechasReserva(fechaEntrada, fechaSalida);

        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;

        validarDatosReserva(cliente, habitacion);

        this.cliente = cliente;
        this.habitacion = habitacion;

        validarNumHuespedes(numHuespedes);

        this.numHuespedes = numHuespedes;
        this.importe = importeTotalEstancia();
    }

    public int getIdReserva() {
        return idReserva;
    }

    public int getNumHuespedes() {
        return numHuespedes;
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

    /**Con esté método podremos saber los días que van a permanecer los huespedes**/
    private long calcularDiasEstancia(){
        long duracionEstancia;

        duracionEstancia = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);

        return duracionEstancia;
    }

    // ↓↓↓ REGLAS DE NEGOCIO ↓↓↓

    /**Este método nos permite comprobar que ninguno de los datos necesarios se queda como null**/
    private void validarDatosReserva(Cliente cliente, Habitacion habitacion){
        if(cliente == null){
            throw new IllegalArgumentException(
                    "El campo cliente no puede quedar vacío."
            );
        }

        if(habitacion == null){
            throw new IllegalArgumentException(
                    "El campo habitación no puede quedar vacío. "
            );
        }
    }

    /**Este método nos permite validar si el número de huespedes cumple con la capacidad de la habitación o si
     es un número correcto superior a cero**/
    private void validarNumHuespedes(int numHuespedes){ // Es privado porque otras clases no deben acceder a él
        if (numHuespedes > habitacion.getTipoHabitacion().getCapacidad()){
            throw new IllegalArgumentException(
                    "El número de huspedes es superior a la capacidad de la habitación."
            );
        }

        if (numHuespedes <= 0){
            throw new IllegalArgumentException(
                    "El número de huespedes indicados no es válido."
            );
        }
    }

    /**Este métdodo nos permite comprobar que las fechas de entrada y salida sean correctas entre sí y no queden sin rellenar**/
    private void validarFechasReserva(LocalDate fechaEntrada, LocalDate fechaSalida){
        if(fechaEntrada == null || fechaSalida == null){
            throw new IllegalArgumentException(
                    "Los campos fecha de entrada y fecha de salida no pueden quedar vacíos."
            );
        }

        if (fechaSalida.isBefore(fechaEntrada) || fechaEntrada.isEqual(fechaSalida)){
            throw new IllegalArgumentException(
                    "Las fechas indicadas no son válidas."
            );
        }
    }

    /**Método para calcular el importe total de la estancia en base a los días que se hospedará el huesped**/
    private BigDecimal importeTotalEstancia(){
        BigDecimal precioEstancia;
        long duracionEstancia;

        duracionEstancia = calcularDiasEstancia();// Calculamos los días que permanecerá el huesped

        BigDecimal bdDiasEstancia = BigDecimal.valueOf(duracionEstancia); // Para operar la variable diasEstancia con importeTotalEstancia hay que convertir la variable long a tipo BigDecimal

        precioEstancia = bdDiasEstancia.multiply(habitacion.getTipoHabitacion().getPrecio()); // Se usan los métodos de la clase BigDeciaml para operar

        return precioEstancia;
    }
}
