package com.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DAO.FacturasDAO;
import com.TO.Facturas;

@Service
public class FacturasService implements IFacturasService{

    @Autowired
    FacturasDAO facturaDao;

    @Override
    @Transactional
    public Facturas guardar(Facturas factura) {
        return facturaDao.save(factura);
    }

    @Override
    @Transactional
    public void eliminar(int idFactura) {
        facturaDao.deleteById(idFactura);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Facturas> listarFacturas() {
        return (List<Facturas>) facturaDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Facturas> listarVehiculosParqueados() {
        return (List<Facturas>) facturaDao.listarVehiculosParqueados();
    }

    @Transactional(readOnly = true)
    public Facturas buscarVehiculoParqueado(String placa) {
        return facturaDao.buscarVehiculoParqueado(placa);
    }

    @Override
    @Transactional(readOnly = true)
    public Facturas buscarFactura(int idFactura) {
        return facturaDao.findById(idFactura).orElse(null);
    }
    
}
