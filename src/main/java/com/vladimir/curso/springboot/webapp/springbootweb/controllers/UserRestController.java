package com.vladimir.curso.springboot.webapp.springbootweb.controllers;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vladimir.curso.springboot.webapp.springbootweb.models.User;
import com.vladimir.curso.springboot.webapp.springbootweb.models.dto.UserDto;


@RestController                                     //Este contempla 2 handlers, puede ser @Controller pero en el metodo se debe agregar @RespondeBody
@RequestMapping("/api")  //Coloca una ruta base
public class UserRestController {

    @GetMapping("/details-dto")                           
    public UserDto detailsDto(){ // Se crea esta clase para compartir de manera segura informacion con el FrontEnd
        UserDto userDto = new UserDto();
        User user = new User("Vladimir","Lopez");
        userDto.setUser(user);
        userDto.setTitle("Hola Mundo Spring Boot");
        return userDto;  
    }

    @GetMapping("/list")    
    public List<User> list() {
        User user = new User("Vladimir","Lopez");
        User user2 = new User("Andres","Doe");
        User user3 = new User("John","Doe");

        List<User> users =  Arrays.asList(user, user2, user3); // Clase helper  

        /*List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        users.add(user3);*/

        return users;
    }


    @GetMapping("/details-map")                                 //Aqui debajo se agregaria el @ResponseBody
    public Map<String, Object> detailsMap(){
        User user = new User("Vladimir","Lopez");
        Map<String, Object> body = new HashMap<>();   //Formato para devolver un tipo JSON                                   
        body.put("title", "Hola Mundo Spring Boot"); 
        body.put("user", user);
        /*body.put("name", "Vladimir");
        body.put("lastname", "Lopez");*/
        return body;  //Aqui se manda llamar a ahora al objeto body y no a la plantilla
    }

}
