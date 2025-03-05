package com.egg.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.servicios.AutorServicio;

import java.util.logging.*;

@Controller
@RequestMapping("/autor") // localhost:8080/autor
public class AutorControlador {

    @GetMapping("/registrar") // localhost:8080/autor/registrar
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {
        try {
            AutorServicio.crearAutor(nombre); // llamo a mi servicio para persistir
        } catch (MiException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autor_form.html";
        }
        return "index.html";

    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelMap) {
        try {
            AutorServicio.crearAutor(nombre); // llamo a mi servicio para persistir
            modelMap.addAttribute("exito", "Autor creado con éxito!");
        } catch (Exception ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelMap.addAttribute("error", "Ocurrió un error");

            return "autor_form.html";
        }
        return "index.html";
    }

}
