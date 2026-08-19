import java.math.BigDecimal;

public enum TipoHabitacion {
    INDIVIDUAL (new BigDecimal("50.00"), 1),
    DOBLE (new BigDecimal("90.00"), 2),
    SUITE (new BigDecimal("120.00"), 4);

    private final BigDecimal precio;
    private final int capacidad;

    TipoHabitacion (BigDecimal precio, int capacidad){
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
