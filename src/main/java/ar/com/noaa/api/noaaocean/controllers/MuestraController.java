package ar.com.noaa.api.noaaocean.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.noaa.api.noaaocean.entities.Muestra;
import ar.com.noaa.api.noaaocean.models.request.InfoMuestraNueva;
import ar.com.noaa.api.noaaocean.models.response.ColorMuestraResponse;
import ar.com.noaa.api.noaaocean.models.response.GenericResponse;
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


    @DeleteMapping("/muestras/{id}")
    public ResponseEntity<GenericResponse> eliminarMuestraPorId(@PathVariable Integer id){
        GenericResponse respuesta = new GenericResponse();

        service.eliminarMuestraYResetearLuzDeBoya(id);
        
        respuesta.isOk = true;
        respuesta.message = "La muestra se ha eliminado correctamente, la luz de boya ahora es AZUL";
        
        return ResponseEntity.ok(respuesta);
    }

   /* GET /muestras/colores/{color} : que devuelva la lista de muestras de un color en el siguiente 
   formato JSON Array:
    [{
   “boyaId”: 1232,
   “horario”: “2020-08-05T20:20:10”,
   “alturaNivelDelMar”: 29
    },
    {
    “boyaId”: 124,
    “horario”: “2020-08-01T20:22:10”,
    “alturaNivelDelMar”: 55
    }]*/

    @GetMapping("/muestras/colores/{color}")
    public ResponseEntity<List<ColorMuestraResponse>> traerMuestrasPorColor(@PathVariable String color){
        
        return ResponseEntity.ok(service.obtenerListaDeMuestrasPorColor(color));
    }


}
