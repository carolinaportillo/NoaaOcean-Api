package ar.com.noaa.api.noaaocean.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.noaa.api.noaaocean.entities.Boya;
import ar.com.noaa.api.noaaocean.repos.BoyaRepository;

@Service
public class BoyaService{

    @Autowired
    private BoyaRepository repo;



    public void crear(Boya boya){
        repo.save(boya);
    }



    public Boya crearBoya(double longitudInstalacion, double latitudInstalacion){
        Boya boya =  new Boya();
        boya.setLatitud(latitudInstalacion);
        boya.setLongitud(longitudInstalacion);
        return repo.save(boya);
    }
        
    

    
}
