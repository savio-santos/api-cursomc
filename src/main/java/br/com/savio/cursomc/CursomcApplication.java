package br.com.savio.cursomc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = "br.com.savio.cursomc")
@EnableSwagger2
public class CursomcApplication  {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);

	}

}
