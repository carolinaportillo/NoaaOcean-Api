package ar.com.noaa.api.noaaocean.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.noaa.api.noaaocean.entities.Boya;
import ar.com.noaa.api.noaaocean.models.request.InfoBoyaNueva;
import ar.com.noaa.api.noaaocean.models.response.GenericResponse;
import ar.com.noaa.api.noaaocean.services.BoyaService;

import org.springframework.web.bind.annotation.*;

@RestController
public class BoyaController {

    @Autowired
    private BoyaService service;

    @PostMapping("/boyas")
    public ResponseEntity<GenericResponse> postCrearBoya(@RequestBody InfoBoyaNueva datosInstalacion){

        GenericResponse respuesta = new GenericResponse();
        
        Boya boya = service.crearBoya(datosInstalacion.longitudInstalacion, datosInstalacion.latitudInstalacion);

        respuesta.isOk = true;
        respuesta.message = "Se creo correctamente";
        respuesta.id = boya.getBoyaId();

        return ResponseEntity.ok(respuesta);

    }



    @GetMapping("/boyas")
    public ResponseEntity<List<Boya>> getBoyas(){

        return ResponseEntity.ok(service.obtenerBoyas());
    }



    @GetMapping("/boyas/{id}")
    public ResponseEntity<Boya> getBoyaPorId(@PathVariable Integer id){

        return ResponseEntity.ok(service.obtenerBoyaPorId(id));
    }
        








    
   //crear boya
   //para crear una muestra si o si tengo que tener una boya
   //en el metodo crear muestra (primero tiene que haber una boya creada, istanciar una nueva muestra, traer el objeto boya por id Boya boya = serviceboya.fyndByBoyaid)
   //service boya buscar boya por id, parametro Integer id(repo.findByBoyaId)
   // llamo al metodo bd muestra.agregarMuestra
   // repo.save(muestra)
        
    
}
