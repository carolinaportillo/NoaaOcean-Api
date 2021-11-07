package ar.com.noaa.api.noaaocean.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.noaa.api.noaaocean.entities.Boya;
import ar.com.noaa.api.noaaocean.models.request.EstadoBoyaRequest;
import org.springframework.web.bind.annotation.RequestBody;
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
        respuesta.message = "La boya se ha creado correctamente";
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


    @PutMapping("/boyas/{id}")
    public ResponseEntity<GenericResponse> actualizarBoyaPorId(@PathVariable Integer id, @RequestBody EstadoBoyaRequest nuevoColorBoya){

        GenericResponse respuesta = new GenericResponse();
        //buscar la boya por id
        Boya boya = service.obtenerBoyaPorId(id);
        //actualizar el nuevo estado a "rojo"
        boya.setColorLuz(nuevoColorBoya.color);
        //guardar el cambio en la bd
        service.actualizarBoya(boya);
        //devolver el status
        respuesta.isOk = true;
        respuesta.message = "El color de la boya se ha actualizado correctamente";
        respuesta.id = boya.getBoyaId();
        
        return ResponseEntity.ok(respuesta);
    }
        
        
    
}
