package br.com.savio.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.savio.cursomc.domain.Categoria;
import br.com.savio.cursomc.repositories.CategoriaRepository;


@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	@Autowired
	CategoriaRepository catRpository; 
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "informatica"); 
		Categoria cat2 = new Categoria(null, "escritorio"); 

		catRpository.saveAll(Arrays.asList(cat1, cat2));
	}

}
