package com.Ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.Services.PlazasService;
import com.TO.Plazas;
import java.util.List;

@Controller
public class PlazasCtrl {

    @Autowired
    private PlazasService plazasService;

    /* METODOS PARA LA PÁGINA HTML */ 
    @GetMapping("/plazas")
    public String iniciar(Model model) {
        inicio(model);
        
        return "Plazas";
    }

    public void inicio(Model model) {
        model.addAttribute("titulo", "Plazas");
        var plazas = plazasService.listarPlazas();
        model.addAttribute("plazas", plazas);

        if(model.getAttribute("plaza") == null) {
            Plazas plaza = new Plazas();
            model.addAttribute("plaza", plaza);
        }
    }

    @PostMapping("/plazas/guardar")
    public String guardar(Plazas plaza){
        plazasService.guardar(plaza);
        return "redirect:/plazas";
    }

    @GetMapping("/plazas/editar/{idPlazas}")
    public String editar(Plazas plaza, Model model){
        plaza = plazasService.buscarPlaza(plaza.getIdPlazas());
        model.addAttribute("plaza", plaza);
        inicio(model);
        return "Plazas";
    }

    @GetMapping("/plazas/eliminar/{idPlazas}")
    public String eliminar(Plazas plazas){
        plazasService.eliminar(plazas.getIdPlazas());
        return "redirect:/plazas";
    }
    
    /* METODOS PARA LA POSTMAN */
    @GetMapping("/plazas/listarPlazas")
    public ResponseEntity<List<Plazas>> listarPlaza() {
        return new ResponseEntity<>(plazasService.listarPlazas(), HttpStatus.OK);
    }

    @PostMapping("/plazas/registrar")
    public ResponseEntity<Plazas> guardarPlaza(@RequestBody Plazas plaza){
        return new ResponseEntity<>(plazasService.guardar(plaza), HttpStatus.OK);
    }

    @DeleteMapping(value="/plazas/eliminarPsn/{id}")
    public ResponseEntity<String> eliminarPlaza(@PathVariable int idPlaza){
        Plazas obj = plazasService.buscarPlaza(idPlaza);
        if(obj != null){
            plazasService.eliminar(idPlaza);
            return new ResponseEntity<>("elemento eliminado", HttpStatus.OK);
        }else
            return new ResponseEntity<>("id no encontrado", HttpStatus.INTERNAL_SERVER_ERROR);
        
    }

}
