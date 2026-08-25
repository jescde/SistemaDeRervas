import java.util.ArrayList;
import java.util.List;

public class ServicioReserva {

    private final List<Reserva> reservas = new ArrayList<>();

    /**Este método nos permite añadir reservas**/
    public void anadirReserva(Reserva reserva){
        if (reserva == null){
            throw new IllegalArgumentException(
                    "La reserva no puede ser nula."
            );
        }

        comprobarReservas(reserva);

        reservas.add(reserva);
    }

    /**Con este método comprobamos que no haya duplicados de reservas ya existentes**/
    private void comprobarReservas(Reserva nuevaReserva){
        for (int i = 0; i < reservas.size(); i++){

            Reserva reservaExistente = reservas.get(i);

            if (reservaExistente.getIdReserva() == nuevaReserva.getIdReserva()){
                throw new IllegalArgumentException(
                        "No pueden existir dos reservas con el mismo identificador"
                );
            }
        }
    }

    /**Este método nos permite buscar una reserva por su identificador**/
    public Reserva buscarIdReserva(int idReserva){
        for (int i = 0; i <reservas.size(); i++){
            Reserva reservaExistente = reservas.get(i);

            if (reservaExistente.getIdReserva() == idReserva){
                return reservaExistente;
            }
        }

        throw new IllegalArgumentException(
                "No existe la reserva"
        );
    }

    /**Mediante este método vamos a listar las reservas que tenemos en la aplicación**/
    public List<Reserva> listarReservas(){

        return reservas;
    }

    /**Este método nos permite eliminar una reserva de nuestra elección**/
    public Reserva eliminarReserva(int id){
        for(int i = 0; i < reservas.size(); i++){

            Reserva reservaExistente = reservas.get(i);

            if (id == reservaExistente.getIdReserva()){
                reservas.remove(i);

                return reservaExistente;
            }
        }

        throw new IllegalArgumentException(
                "La reserva no existe."
        );
    }
}
