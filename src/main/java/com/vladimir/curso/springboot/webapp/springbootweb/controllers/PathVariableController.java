package com.vladimir.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladimir.curso.springboot.webapp.springbootweb.models.User;
import com.vladimir.curso.springboot.webapp.springbootweb.models.dto.ParamDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/var")
public class PathVariableController {
    
    
    @Value("${config.code}") //Esto es para realizar cambios internos, en este caso, la configuracion del control
    private Long code;

    @Value("${config.username}")
    private String username;

    @Value("${config.listOfValues}")
    private List<String> listOfValues; //private String[] listOfValues;   funciona igual pero mejor usar List<>
    
    @Value("#{ '${config.listOfValues}'.toUpperCase().split(',')}") //# Lenguaje de expresion, trabajandolo de manera manual. Las comillas simples '' indica que es un string, por lo que se pueden usar los metodos de string
    private List<String> valueList;
    
    @Value("#{ '${config.listOfValues}'.toUpperCase()}") // Le quitamos el split para que se mantenga como string y usamos uppercase
    private String valueString;

    // @Value("${config.message}")  Esto lo inyectamos directamente en el metodo
    // private String message;

    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valuesMap;
    
    @Value("#{${config.valuesMap}.product}") //Como es un objeto, se puede ingresar a la llave
    private String product;

    @Value("#{${config.valuesMap}.price}") //Como es un objeto, se puede ingresar a la llave
    private String price;
    
    @Autowired   //busca algun componenrte de spring que este almacenado en su contenedor
    private Environment environment; //Similar a @Value

    @GetMapping("/baz/{message}") //Esta es la ruta variable
    public ParamDto baz(@PathVariable String message) {
        
        ParamDto param = new ParamDto();
        param.setMessage(message);
        return param;
    }
    
    //Con multiples pathvariables
    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {

        Map<String, Object> json = new HashMap<>(); 
        json.put("product", product);
        json.put("id", id);
        return json;
    }

    //Enviando datos a la base de datos
    @PostMapping("/create") //cuando es post se ejecuta con la ruta base
    public User create(@RequestBody User user) {
        user.setName(user.getName().toUpperCase());
        return user;
    }
    
    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.message}") String message) {

        Map<String, Object> json = new HashMap<>();
        json.put("code", code);
        json.put("username", username);
        json.put("message", message);
        json.put("message2", environment.getProperty("config.message")); 
        json.put("code2", Integer.valueOf(environment.getProperty("config.code")));          //Lo devuelve como String por lo que habria que hacer la conversion 
        json.put("code3", environment.getProperty("config.code", Long.class));          //Otra manera de convertir a entero  
        json.put("listOfValues", listOfValues);
        json.put("valueList", valueList);
        json.put("valueString", valueString);
        json.put("valuesMap", valuesMap);
        json.put("product", product);
        json.put("price", price);

        return json;
    }
    
}
