package ar.com.noaa.api.noaaocean.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.noaa.api.noaaocean.entities.Boya;
import ar.com.noaa.api.noaaocean.repos.BoyaRepository;

@Service
public class BoyaService{

    @Autowired
    private BoyaRepository repo;



    public Boya crearBoya(double longitudInstalacion, double latitudInstalacion){
        Boya boya =  new Boya();
        boya.setLatitud(latitudInstalacion);
        boya.setLongitud(longitudInstalacion);
        return repo.save(boya);
    }



    public List<Boya> obtenerBoyas(){
        return repo.findAll();
    }



    public Boya obtenerBoyaPorId(Integer id) {
        return repo.findByBoyaId(id);
    }
        

    public void actualizarBoya(Boya boya){
        repo.save(boya);
    }
    
    /*public void actualizarColorBoya(Integer id, String color){

       Boya boya = repo.findByBoyaId(id);
       boya.setColorLuz(color);
       repo.save(boya);
    }*/

    
}
