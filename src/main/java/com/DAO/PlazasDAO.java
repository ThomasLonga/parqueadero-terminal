package com.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.TO.Plazas;

public interface PlazasDAO extends CrudRepository<Plazas, Integer>{
    
    @Query(value = "SELECT * FROM plazas WHERE estado='libre' AND tipo= ?1", nativeQuery=true)
    List<Plazas> plazasLibresxTipo(String tipo);
}
