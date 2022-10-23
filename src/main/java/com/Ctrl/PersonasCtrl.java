package com.Ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.Services.PersonasService;
import com.TO.Personas;
import java.util.List;

@Controller
public class PersonasCtrl {

    @Autowired
    private PersonasService personasService;

    /* METODOS PARA LA PÁGINA HTML */ 
    @GetMapping("/personas")
    public String iniciar(Model model) {
        inicio(model);
        
        return "Personas";
    }

    public void inicio(Model model) {
        model.addAttribute("titulo", "Personas");
        var personas = personasService.listarpersonas();
        model.addAttribute("personas", personas);

        if(model.getAttribute("persona") == null) {
            Personas persona = new Personas();
            model.addAttribute("persona", persona);
        }
    }

    @PostMapping("/personas/guardar")
    public String guardar(Personas persona){
        /*if(errores.hasErrors()){
            model.addAttribute("persona", persona);
            inicio(model);
            return "Personas";
        }*/
        personasService.guardar(persona);
        return "redirect:/personas";
    }

    @GetMapping("/personas/editar/{idPersonas}")
    public String editar(Personas persona, Model model){
        persona = personasService.buscarPersona(persona.getIdPersonas());
        model.addAttribute("persona", persona);
        inicio(model);
        return "Personas";
    }

    @GetMapping("/personas/eliminar/{idPersonas}")
    public String eliminar(Personas personas){
        personasService.eliminar(personas.getIdPersonas());
        return "redirect:/personas";
    }
    
    /* METODOS PARA LA POSTMAN */
    @GetMapping("/personas/listarPersonas")
    public ResponseEntity<List<Personas>> listarPersona() {
        return new ResponseEntity<>(personasService.listarpersonas(), HttpStatus.OK);
    }

    @PostMapping("/personas/registrar")
    public ResponseEntity<Personas> guardarPersona(@RequestBody Personas persona){
        return new ResponseEntity<>(personasService.guardar(persona), HttpStatus.OK);
    }

    @DeleteMapping(value="/personas/eliminarPsn/{id}")
    public ResponseEntity<String> eliminarPersona(@PathVariable int idPersona){
        Personas obj = personasService.buscarPersona(idPersona);
        if(obj != null){
            personasService.eliminar(idPersona);
            return new ResponseEntity<>("elemento eliminado", HttpStatus.OK);
        }else
            return new ResponseEntity<>("id no encontrado", HttpStatus.INTERNAL_SERVER_ERROR);
        
    }

}
