package ar.edu.unahur.obj2.excepciones.helicopteros;

public class HeliCivil extends Helicoptero {

    public HeliCivil(Double combustible, Double capacidadTanque) {
        super(combustible, capacidadTanque);
    }

    @Override
    protected void prepararVuelo() {
        registrarEnBitacora("Pasajeros y equipajes verificados. Listo para despegar.");
    }

    @Override
    protected void finalizarVuelo(Double kilometraje) {
        registrarEnBitacora("Vuelo civil completado." + kilometraje);
    }

}
