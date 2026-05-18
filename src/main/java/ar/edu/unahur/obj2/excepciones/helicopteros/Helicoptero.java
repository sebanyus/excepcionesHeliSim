package ar.edu.unahur.obj2.excepciones.helicopteros;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.excepciones.excepciones.*;
import ar.edu.unahur.obj2.excepciones.modos.*;

public abstract class Helicoptero {
    private Double combustible;
    private Double kilometraje = 0.0;
    private final Double capacidadTanque;
    private final List <String> bitacora = new ArrayList<>();
    private IModoVuelo modoVuelo;

    public Helicoptero(Double combustible, Double capacidadTanque) {
        
        if (combustible < 0) {
            throw new EstadoInvalidoException(
                "No se puede inicializar un helicoptero con combustible negativo" + "valor recibido: " + combustible
            );
        }
        
        this.combustible = combustible;
        this.capacidadTanque = capacidadTanque;
        this.modoVuelo = new ModoEficiente();

    }

    public Double volar(Double distancia, Boolean volarHastaDondeSePueda) {
        validarEstadoParaDespegue();
        prepararVuelo();
        Double tiempoEstimado = ejecutarVuelo(distancia, volarHastaDondeSePueda);
        finalizarVuelo(kilometraje);

        return tiempoEstimado;
    }

    protected Double ejecutarVuelo(Double distancia, Boolean volarHastaDondeSePueda) {
        Double combustibleNecesario = modoVuelo.calcularCombustibleNecesario(distancia);
        Boolean usarReserva = consumeReserva(combustibleNecesario);

        if (combustibleNecesario > this.combustible) {
            if (volarHastaDondeSePueda) {
                return volarParcialmente();
            }
            throw new EstadoInvalidoException("No alcanza el combustible");
        }

        this.combustible -= combustibleNecesario;
        kilometraje += distancia;

        Double tiempoEstimado = distancia / modoVuelo.getVelocidadMaximaKmH();

        if (usarReserva) {
            throw new UsoDeReservaException(getReserva());
        }

        return tiempoEstimado;
    }

    private Double volarParcialmente() {
        Double distanciaRecorrida = combustible * modoVuelo.getRendimientoKmPorLitro();
        kilometraje += distanciaRecorrida;
        combustible = 0.0;
        registrarEnBitacora("Vuelo parcial " + distanciaRecorrida + " km recorridos hasta agotar combustible");

        return distanciaRecorrida / modoVuelo.getVelocidadMaximaKmH();
    }

    protected void validarEstadoParaDespegue() {
        if (combustible <= 0.0) {
            throw new EstadoInvalidoException("Combustible Insuficiente");
        }
    }

    private Boolean consumeReserva(Double combustibleNecesario) {
        return (this.combustible - combustibleNecesario) < getReserva();
    }

    private double getReserva() {
        return capacidadTanque * 0.1;
    }

    protected void registrarEnBitacora(String mensaje) {
        bitacora.add(mensaje);
    }

    public void setModoVuelo(IModoVuelo modoVuelo) {
        this.modoVuelo = modoVuelo;
    }

    protected abstract void prepararVuelo();

    protected abstract void finalizarVuelo(Double kilometraje);

    public Double intentarVolar(Double distanciaKm) {
        try {
            volar(distanciaKm, false); 
            return distanciaKm;
        } catch (EstadoInvalidoException e) {
            System.out.println("Gestion Silencionsa, no pudo volar por combustible insuficiente.");
            return 0.0;
        }
    }

    public Double getCombustible() {
        return combustible;
    }

    public Double getKilometraje() {
        return kilometraje;
    }

    public Double getCapacidadTanque() {
        return capacidadTanque;
    }

    public List<String> getBitacora() {
        return bitacora;
    }

    public IModoVuelo getModoVuelo() {
        return modoVuelo;
    }

}
