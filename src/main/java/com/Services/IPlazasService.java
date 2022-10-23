package com.Services;
import java.util.List;
import com.TO.Plazas;

public interface IPlazasService {
    public Plazas guardar(Plazas plaza);

    public void eliminar(int idPlaza);

    public List<Plazas> listarPlazas();

    public Plazas buscarPlaza(int idPlaza);
    
    
}
