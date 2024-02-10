package com.vladimir.curso.springboot.webapp.springbootweb.controllers;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.vladimir.curso.springboot.webapp.springbootweb.models.User;


@Controller 
public class UserController {

    @GetMapping("/details")  //Asigna un endpoint 
    public String details(Model model){ //Con model podemos instancias o mostrar a la vista   public String details(Map<String, Object> model)    SE PUEDE HACER CON MAP IGUAL   
        User user = new User("Vladimir","Lopez");        
        user.setEmail("vladimir@hotmail.com");                  
        model.addAttribute("title", "Hola Mundo Spring Boot");                               //model.put("title", "Hola Mundo Spring Boot");      SOLO QUE SERIA CON PUT  
        model.addAttribute("user", user);                               //model.put("title", "Hola Mundo Spring Boot");      SOLO QUE SERIA CON PUT  
        /*model.addAttribute("name", "Vladimir");
        model.addAttribute("lastname", "Lopez");*/
        return "details";  //Aqui se manda llamar a la plantilla de thymeleaf, solo por su nombre y no extension "html"
    }

    @GetMapping("/list")
    public String list(ModelMap model) {
        // En esta linea iba la lista de users, pero mejor se crea un metodo por aparte como el de abajo
        //model.addAttribute("users", users);
        model.addAttribute("title", "Listado de usuarios");
        return "list";
    }
    
    @ModelAttribute("users")   // Es el atributo que se manda a llamar en los html ${users.%%%} y es global, se puede llamar en otra plantilla, ejemplo details.html
    public List<User> usersModel(){
        List<User> users = Arrays.asList(
            new User("Prueba", "Gonzalez","glzpru@correo.com"),
            new User("Ursula", "Gutierrez", "usr@correo.com"), 
            new User("Ana", "Banana"), 
            new User("Javier", "Perez"));

        return users;
    }

}
