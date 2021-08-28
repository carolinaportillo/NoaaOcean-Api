package ar.com.noaa.api.noaaocean.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.noaa.api.noaaocean.entities.Boya;


@Repository
public interface BoyaRepository extends JpaRepository<Boya, Integer> {

    Boya findByBoyaId(Integer id);
    
}
