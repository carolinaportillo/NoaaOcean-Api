package ar.com.noaa.api.noaaocean.entities;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;


@Entity
@Table(name = "boya")
public class Boya{

@Id
@Column(name = "boya_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer boyaId;


@Column(name = "color_luz")
private String colorLuz;

private double longitud;

private double latitud;


@OneToMany(mappedBy = "boya", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JsonIgnore
private List<Muestra> muestras = new ArrayList();

public Integer getBoyaId(){
    return boyaId;
}

public void setBoyaId(Integer boyaId) {
    this.boyaId = boyaId;
}

public List<Muestra> getMuestras() {
    return muestras;
}

public void setMuestras(List<Muestra> muestras) {
    this.muestras = muestras;
}

public String getColorLuz() {
    return colorLuz;
}

public void setColorLuz(String colorLuz) {
    this.colorLuz = colorLuz;
}

public double getLongitud() {
    return longitud;
}

public void setLongitud(double longitud) {
    this.longitud = longitud;
}

public double getLatitud() {
    return latitud;
}

public void setLatitud(double latitud) {
    this.latitud = latitud;
}



//relacion bidireccional (?)
public void agregarMuestra(Muestra muestra){
    this.muestras.add(muestra);
    muestra.setBoya(this);
}
//llamar cuando cree la muestra 



    
}
