package com.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.TO.Facturas;

public interface FacturasDAO extends CrudRepository<Facturas, Integer>{
    @Query(value = "SELECT * FROM facturas where fechaSalida is null", nativeQuery=true)
    List<Facturas> listarVehiculosParqueados();

    @Query(value = "SELECT * FROM facturas where fechaSalida is null AND placa= ?1", nativeQuery=true)
    Facturas buscarVehiculoParqueado(String placa);
}
