package ar.com.noaa.api.noaaocean.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

@Entity
@Table(name = "muestra")
public class Muestra {

    @Id
    @Column(name = "muestra_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer muestraId;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "boya_id", referencedColumnName = "boya_id")
    private Boya boya;
    

    @Column(name = "horario_muestra")
    private Date horarioMuestra;

    private String matricula;

    @Column(name = "longitud_actualb")
    private Double longitudActualBoya;
    

    @Column(name = "latitud_actualb")
    private Double latitudActualBoya;

    @Column(name = "altura_nvmar")
    private Double alturaAlNivelDelMar;

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

    public  Double getLongitudActualBoya() {
        return longitudActualBoya;
    }

    public void setLongitudActualBoya(Double longitudActualBoya) {
        this.longitudActualBoya = longitudActualBoya;
    }

    public Double getLatitudActualBoya() {
        return latitudActualBoya;
    }

    public void setLatitudActualBoya(Double latitudActualBoya) {
        this.latitudActualBoya = latitudActualBoya;
    }

    public Double getAlturaAlNivelDelMar() {
        return alturaAlNivelDelMar;
    }

    public void setAlturaAlNivelDelMar(Double alturaAlNivelDelMar) {
        this.alturaAlNivelDelMar = alturaAlNivelDelMar;
    }
    

    

    
}
