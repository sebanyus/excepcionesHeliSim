package ar.edu.unahur.obj2.excepciones.helicopteros;

import ar.edu.unahur.obj2.excepciones.excepciones.MisionAbortadaException;
import ar.edu.unahur.obj2.excepciones.modos.*;

public class HeliMilitar extends Helicoptero {

    private static final Double minimoAgresivo = 20.0; 

    public HeliMilitar(Double combustible, Double capacidadTanque) {
        super(combustible, capacidadTanque);
    }

    @Override
    protected void prepararVuelo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'prepararVuelo'");
    }

    @Override
    protected void finalizarVuelo(Double kilometraje) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finalizarVuelo'");
    }

    @Override
    protected void validarEstadoParaDespegue() {
        super.validarEstadoParaDespegue();
        Boolean esAgresivo = getModoVuelo() instanceof ModoAgresivo;
        Boolean combustibleBajo = getCombustible() < minimoAgresivo;

        if (esAgresivo && combustibleBajo) {
            throw new MisionAbortadaException(
                "Modo Agresivo actrivo con solo " + getCombustible()
                + " litros. Se requiere al menos " + minimoAgresivo
                + " litos para operar en este modo."
            );
        }
    }



}
