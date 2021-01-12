package br.com.savio.cursomc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.savio.cursomc.services.S3Service;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service service;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		service.uploadFile("D:\\SAVIO\\Documents\\SAVIO\\SpringProjects\\docs-cursomc\\diagrama.png");
		
	}

}
