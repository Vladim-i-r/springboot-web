package com.vladimir.curso.springboot.webapp.springbootweb;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({                                 							//Este funciona para agregar mas rutas de configuraciones
	@PropertySource(value="classpath:values.properties", encoding = "UTF-8") //Este sirve para cambiar la ruta de configuraciones, ademas del application.properties
})														//Se agrega el encodign para permitir caracteres especiales
public class ValuesConfig {                 //Para no ensuciar la clase principal, se crea una clase hermana dentro del mismo repo

}
