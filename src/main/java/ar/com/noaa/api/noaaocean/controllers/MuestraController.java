package ar.com.noaa.api.noaaocean.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.noaa.api.noaaocean.entities.Muestra;
import ar.com.noaa.api.noaaocean.models.request.InfoMuestraNueva;
import ar.com.noaa.api.noaaocean.models.response.MuestraResponse;
import ar.com.noaa.api.noaaocean.services.MuestraService;

@RestController
public class MuestraController{
    

    @Autowired
    private MuestraService service;
    

    @PostMapping("/muestras")
    public ResponseEntity<MuestraResponse> registrarNuevaMuestra(@RequestBody InfoMuestraNueva infoMuestra){
        
        MuestraResponse respuesta = new MuestraResponse();
        //crear una muestra nueva
        //setearle todos los datos iniciales
        Muestra muestra = service.registrarMuestra(infoMuestra.boyaId, infoMuestra.horario, infoMuestra.matricula, infoMuestra.latitud,
        infoMuestra.longitud, infoMuestra.alturaNivelDelMar );
        
        respuesta.id = muestra.getMuestraId();
        respuesta.color = muestra.getBoya().getColorLuz();

        return ResponseEntity.ok(respuesta);
    }
    
    //retorna la lista de muestras de una boya en especifico 
    @GetMapping("/muestras/boyas/{idBoya}")
    public ResponseEntity<List<Muestra>> obtenerMuestrasDeBoya(@PathVariable Integer idBoya){
        
        return ResponseEntity.ok(service.obtenerMuestrasPorBoyaId(idBoya));
    }
    

}
