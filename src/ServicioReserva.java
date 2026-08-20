import java.util.ArrayList;
import java.util.List;

public class ServicioReserva {

    private final List<Reserva> reservas = new ArrayList<>();

    public void anadirReserva(Reserva reserva){
        if (reserva == null){
            throw new IllegalArgumentException(
                    "La reserva no pueden ser nula."
            );
        }

        reservas.add(reserva);

    }
}
