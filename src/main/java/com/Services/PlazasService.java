package com.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DAO.PlazasDAO;
import com.TO.Plazas;

@Service
public class PlazasService implements IPlazasService{

    @Autowired
    PlazasDAO plazaDao;

    @Override
    @Transactional
    public Plazas guardar(Plazas plaza) {
        return plazaDao.save(plaza);
    }

    @Override
    @Transactional
    public void eliminar(int idPlaza) {
        plazaDao.deleteById(idPlaza);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Plazas> listarPlazas() {
        return (List<Plazas>) plazaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Plazas buscarPlaza(int idPlaza) {
        return plazaDao.findById(idPlaza).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Plazas> plazasLibresxTipo(String tipo) {
        return (List<Plazas>) plazaDao.plazasLibresxTipo(tipo);
    }
    
    
}
