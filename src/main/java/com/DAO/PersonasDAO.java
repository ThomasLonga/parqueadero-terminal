package com.DAO;

import org.springframework.data.repository.CrudRepository;

import com.TO.Personas;

public interface PersonasDAO extends CrudRepository<Personas, Integer>{
    
}
