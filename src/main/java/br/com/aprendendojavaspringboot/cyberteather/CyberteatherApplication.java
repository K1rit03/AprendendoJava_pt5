package br.com.aprendendojavaspringboot.cyberteather;


import br.com.aprendendojavaspringboot.cyberteather.principal.Principal;
import br.com.aprendendojavaspringboot.cyberteather.service.ConsumoApi;
import br.com.aprendendojavaspringboot.cyberteather.service.ConverteDados;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CyberteatherApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CyberteatherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();

	}
}
