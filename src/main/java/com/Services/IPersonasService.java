package com.Services;
import java.util.List;
import com.TO.Personas;

public interface IPersonasService {
    public Personas guardar(Personas persona);

    public void eliminar(int idPersona);

    public List<Personas> listarpersonas();

    public Personas buscarPersona(int idPersona);
}
