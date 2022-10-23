package com.Services;
import java.util.List;
import com.TO.Facturas;

public interface IFacturasService {
    public Facturas guardar(Facturas factura);

    public void eliminar(int idFactura);

    public List<Facturas> listarFacturas();

    public Facturas buscarFactura(int idFactura);
}
