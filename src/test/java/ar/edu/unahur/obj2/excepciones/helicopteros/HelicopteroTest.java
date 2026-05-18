package ar.edu.unahur.obj2.excepciones.helicopteros;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.excepciones.excepciones.*;

public class HelicopteroTest {

    @Test
    void dadoCombustibleNegativo_cuandoSeConstruyeHelicopteroCivil_entoncesLanzaEstadoInvalidoException(){
        assertThrows(EstadoInvalidoException.class, () -> 
        new HeliCivil(-10.0, 200.0)
        );
    }

    @Test
    void dadoCombustibleNegativo_cuandoSeConstruyeHelicopteroMilitar_entoncesLanzaEstadoInvalidoException(){
        assertThrows(EstadoInvalidoException.class, () -> 
        new HeliMilitar(-10.0, 200.0)
        );
    }


}
