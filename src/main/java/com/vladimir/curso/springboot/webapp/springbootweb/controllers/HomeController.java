package com.vladimir.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"","/","/home"})
    public String home() {

        return "redirect:/details";  //reinicia el request, a una ruta existente dentro de un controlador u otro pero se pierden los datos/parametros dentro del request, CRUD
        // return "forward:/details";  // Se mantiene dentro de la misma http y no pierdes los parametros del request  
    }
    

}
