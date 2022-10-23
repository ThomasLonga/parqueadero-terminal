package com.Services;
import com.TO.Configuracion;

public interface IConfiguracionService {
    public Configuracion guardar(Configuracion configuracion);
    public Configuracion buscarConfiguracion();
}
