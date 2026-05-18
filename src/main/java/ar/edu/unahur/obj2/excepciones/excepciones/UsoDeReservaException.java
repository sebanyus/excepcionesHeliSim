package ar.edu.unahur.obj2.excepciones.excepciones;

public class UsoDeReservaException extends RuntimeException {

    public UsoDeReservaException(Double reserva) {
        super("El vuelo uso la reserva de combustible: "
            + reserva
            + "litros. Recargar antes del próximo vuelo."
        );
    }
}
