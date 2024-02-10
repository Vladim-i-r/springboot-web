package com.vladimir.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vladimir.curso.springboot.webapp.springbootweb.models.dto.ParamDto;
import com.vladimir.curso.springboot.webapp.springbootweb.models.dto.ParamMixDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/params")
public class RequestParamsController {
    
    //Usando la anotacion RequestParam con un solo parametro y sus distintos argumentos iniciales
    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false, defaultValue = "Hola que tal") String message) { // recibe un parameteo de la URL, /api/params/foo?message=Hola
        ParamDto param = new ParamDto();
        param.setMessage(message);
        //param.setMessage(message!=null? message: "Hola");  // Si es distinto de null, pasamos el message, si no el Hola. Para mas facil, se agrega desde el argumento
        return param;
    }
    
    //Esta es la manera cuando hay mas de 1 dato y diferentes
    @GetMapping("/bar")
    public ParamMixDto bar(@RequestParam String text, @RequestParam Integer code) {  // se envia de todo excepto objetos, eso ya se usa json en el requestbody
        ParamMixDto params = new ParamMixDto();                                       /// localhost:8080/api/params/bar?text=Hola&code=3
        params.setMessage(text);
        params.setCode(code);
        return params;
    }
    
    // Otra alternativa para inyectar los datos del http request sin el argumentos requestparam es el siguiente
    @GetMapping("/request")
    public ParamMixDto request(HttpServletRequest request) {
        Integer code = 10;
        try {
            code = Integer.parseInt(request.getParameter("code"));  //Usando el request, este lo convierte de forma automatica desde el argumento
        } catch (NumberFormatException e) {
            
        }
        
        ParamMixDto params = new ParamMixDto();
        params.setCode(code);
        params.setMessage(request.getParameter("message"));
        return params;
    }
    
    
    
}
