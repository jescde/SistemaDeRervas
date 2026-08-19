public class Habitacion {
    private int idHabitacion;
    private TipoHabitacion tipoHabitacion;

    public Habitacion(int idHabitacion, TipoHabitacion tipoHabitacion){
        this.idHabitacion = idHabitacion;
        this.tipoHabitacion = tipoHabitacion;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }
}
