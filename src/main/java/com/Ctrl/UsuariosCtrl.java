package com.Ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Services.PersonasService;
import com.Services.UsuariosService;
import com.TO.Usuarios;
import java.util.List;

@Controller
public class UsuariosCtrl {

    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private PersonasService personasService;

    /* METODOS DE LA PÁGINA HTML */

    // INICIO
    @GetMapping("/usuarios")
    public String inicio(Model model) {
        model.addAttribute("titulo", "Usuarios");

        iniciar(model);
        return "Usuarios";
    }

    private void iniciar(Model model) {

        if (model.getAttribute("usuario") == null) {
            Usuarios usuario = new Usuarios();
            model.addAttribute("usuario", usuario);
        }

        var personas = personasService.listarpersonas();
        model.addAttribute("personas", personas);

        var usuarios = usuariosService.listarusuarios();
        model.addAttribute("usuarios", usuarios);
    }

    // GUARDAR
    @PostMapping("/usuarios/guardar")
    public String guardar(Usuarios usuario) {
        usuariosService.guardar(usuario);
        return "redirect:/usuarios";
    }

    // EDITAR
    @GetMapping("/usuarios/editar/{idUsuarios}")
    public String editar(Usuarios usuario, Model model) {
        usuario = usuariosService.buscarUsuario(usuario.getIdUsuarios());
        model.addAttribute("usuario", usuario);
        iniciar(model);
        return "Usuarios";
    }

    // ELIMINAR
    @GetMapping("/usuarios/eliminar/{idUsuarios}")
    public String eliminar(Usuarios usuario) {
        usuariosService.eliminar(usuario.getIdUsuarios());
        return "redirect:/usuarios";
    }

    /* METODOS DE POSTMAN */
    @GetMapping("/usuarios/listarUsr")
    public ResponseEntity<List<Usuarios>> listarUsr() {
        return new ResponseEntity<>(usuariosService.listarusuarios(), HttpStatus.OK);
    }

    @PostMapping("/usuarios/guardarUsr")
    public ResponseEntity<Usuarios> guardarUsr(@RequestBody Usuarios usuario) {
        return new ResponseEntity<>(usuariosService.guardar(usuario), HttpStatus.OK);
    }

    @DeleteMapping(value = "/usuarios/eliminarUsr/{id}")
    public ResponseEntity<String> eliminarUsr(@PathVariable int idUsuario) {
        Usuarios obj = usuariosService.buscarUsuario(idUsuario);
        if (obj != null) {
            usuariosService.eliminar(idUsuario);
            return new ResponseEntity<>("elemento eliminado", HttpStatus.OK);
        } else
            return new ResponseEntity<>("id no encontrado", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
