package com.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DAO.PersonasDAO;
import com.TO.Personas;

@Service
public class PersonasService implements IPersonasService{

    @Autowired
    PersonasDAO personaDao;

    @Override
    @Transactional
    public Personas guardar(Personas persona) {
        return personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(int idPersona) {
        personaDao.deleteById(idPersona);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personas> listarpersonas() {
        return (List<Personas>) personaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Personas buscarPersona(int idPersona) {
        return personaDao.findById(idPersona).orElse(null);
    }
    
}
