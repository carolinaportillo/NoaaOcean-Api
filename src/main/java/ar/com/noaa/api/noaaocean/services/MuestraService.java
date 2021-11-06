package ar.com.noaa.api.noaaocean.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.noaa.api.noaaocean.entities.Boya;
import ar.com.noaa.api.noaaocean.entities.Muestra;
import ar.com.noaa.api.noaaocean.models.response.ColorMuestraResponse;
import ar.com.noaa.api.noaaocean.repos.BoyaRepository;
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

    public List<Muestra> obtenerMuestrasPorBoyaId(Integer idBoya){

        //buscar boya por id
        Boya boya = serviceBoya.obtenerBoyaPorId(idBoya);
        //retornar la lista de muestras de esa boya
        return (boya.getMuestras());
    }
    

    public void eliminarMuestraYResetearLuzDeBoya(Integer id){
       //buscar boya por muestra id
        Muestra muestra = repo.findByMuestraId(id);
        //actualizo el color de la boya de ESA muestra
        Boya boya = muestra.getBoya();
        boya.setColorLuz("AZUL");
        //elimino la muestra en la bd
        repo.deleteById(id);
    }


    /*public Serie buscarSerieV2(String nombreABuscar) {
        Serie serieBuscada = null;
        //En este caso se recorre TODO(aunque haya 1000 series)
        //y al final se devuelve si encontr una serie.
        //en el caso anterior, se devuelve una vez encontrada.
        //ej si hay 1000 series pero esta en la posicion 3, hace 3 vueltas
        for (Serie serie : this.series) {
            if (serie.getNombre().equals(nombreABuscar))
                serieBuscada = serie;
        }
        return serieBuscada;
        /*if (serieBuscada == null)
            return null;
        else
            return serieBuscada;*/


    /*public List<Empleada> obtenerSueldosActualesNoStream() {
        List<Empleada> listaEmpleadas = new ArrayList<>();

        for (Categoria categoria : this.traerCategorias()) {
            for (Empleada empleada : categoria.getEmpleadas()) {
                listaEmpleadas.add(empleada);
            }
        }

        return listaEmpleadas;
    }*/


    public List<ColorMuestraResponse> obtenerListaDeMuestrasPorColor(String color){
        
        List<ColorMuestraResponse> muestrasPorColor = new ArrayList();
        

        for(Muestra muestra : repo.findAll()){

            ColorMuestraResponse muestraByColor = new ColorMuestraResponse();

            if(muestra.getBoya().getColorLuz().equals(color)){

                muestraByColor.boyaId = muestra.getBoya().getBoyaId();
                muestraByColor.horario = muestra.getHorarioMuestra();
                muestraByColor.alturaNivelDelMar = muestra.getAlturaAlNivelDelMar();
                
                muestrasPorColor.add(muestraByColor);
            }

        }

        return muestrasPorColor;

    }



    
}
