package ar.com.noaa.api.noaaocean.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "muestra")
public class Muestra {

    @Id
    @Column(name = "muestra_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer muestraId;


    @ManyToOne
    @JoinColumn(name = "boya_id", referencedColumnName = "boya_id")
    private Boya boya;
    

    @Column(name = "horario_muestra")
    private Date horarioMuestra;

    private String matricula;

    @Column(name = "longitud_actualb")
    private double longitudActualBoya;
    

    @Column(name = "latitud_actualb")
    private double latitudActualBoya;

    @Column(name = "altura_nvmar")
    private double alturaAlNivelDelMar;

    public Integer getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Integer muestraId) {
        this.muestraId = muestraId;
    }

    public Boya getBoya() {
        return boya;
    }

    public void setBoya(Boya boya) {
        this.boya = boya;
        this.boya.agregarMuestra(this); //RB establecida con el metodo creado en boya.java
    }

    public Date getHorarioMuestra() {
        return horarioMuestra;
    }

    public void setHorarioMuestra(Date horarioMuestra) {
        this.horarioMuestra = horarioMuestra;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public double getLongitudActualBoya() {
        return longitudActualBoya;
    }

    public void setLongitudActualBoya(double longitudActualBoya) {
        this.longitudActualBoya = longitudActualBoya;
    }

    public double getLatitudActualBoya() {
        return latitudActualBoya;
    }

    public void setLatitudActualBoya(double latitudActualBoya) {
        this.latitudActualBoya = latitudActualBoya;
    }

    public double getAlturaAlNivelDelMar() {
        return alturaAlNivelDelMar;
    }

    public void setAlturaAlNivelDelMar(double alturaAlNivelDelMar) {
        this.alturaAlNivelDelMar = alturaAlNivelDelMar;
    }
    

    

    
}
