package com.Ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Services.ConfiguracionService;
import com.TO.Configuracion;

@Controller
public class ConfiguracionCtrl {

    @Autowired
    private ConfiguracionService configuracionService;
    
    /* METODOS DE LA PÁGINA HTML */

    // INICIO
    @GetMapping("/configuracion")
    public String inicio(Model model) {
        model.addAttribute("titulo", "Configuracion");

        iniciar(model);
        return "Configuracion";
    }

    private void iniciar(Model model) {
        Configuracion configuracion = configuracionService.buscarConfiguracion();
        model.addAttribute("configuracion", configuracion);
    }

    // GUARDAR
    @PostMapping("/configuracion/guardar")
    public String guardar(Configuracion configuracion) {
        configuracionService.guardar(configuracion);
        return "redirect:/configuracion";
    }

    /* METODOS DE POSTMAN */
    @PostMapping("/configuracion/guardarUsr")
    public ResponseEntity<Configuracion> guardarConfig(@RequestBody Configuracion configuracion) {
        return new ResponseEntity<>(configuracionService.guardar(configuracion), HttpStatus.OK);
    }

    

}
