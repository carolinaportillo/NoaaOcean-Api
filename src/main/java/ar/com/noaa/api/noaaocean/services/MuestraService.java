package ar.com.noaa.api.noaaocean.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.noaa.api.noaaocean.entities.Boya;
import ar.com.noaa.api.noaaocean.entities.Muestra;
import ar.com.noaa.api.noaaocean.repos.MuestraRepository;

@Service
public class MuestraService{

    @Autowired
    public BoyaService serviceBoya;

    @Autowired
    public MuestraRepository repo;

    public Muestra registrarMuestra(Integer boyaId, Date horario, String matricula, Double latitud, Double longitud,
    Double alturaNivelDelMar) {
        //Instanciar una nueva muestra
        Muestra muestra = new Muestra();
        //seteo los datos 
        Boya boya = serviceBoya.obtenerBoyaPorId(boyaId);
        muestra.setBoya(boya);
        muestra.setHorarioMuestra(horario);
        muestra.setMatricula(matricula);
        muestra.setLatitudActualBoya(latitud);
        muestra.setLongitudActualBoya(longitud);
        muestra.setAlturaAlNivelDelMar(alturaNivelDelMar);
        
        //calculo el color segun el nivel del mar
        if(alturaNivelDelMar > -50 && alturaNivelDelMar < 50){

            boya.setColorLuz("AMARILLO");

        } if (alturaNivelDelMar > -100 && alturaNivelDelMar < 100){
            boya.setColorLuz("ROJO");
        }
        else {
            boya.setColorLuz("VERDE");
        }
        //guardo la muestra en la bd
        return repo.save(muestra);
    
    }



    
}
