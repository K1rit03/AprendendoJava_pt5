package br.com.aprendendojavaspringboot.cyberteather;


import br.com.aprendendojavaspringboot.cyberteather.service.ConsumoApi;
import br.com.aprendendojavaspringboot.cyberteather.service.ConverteDados;
import model.DadosSerie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CyberteatherApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CyberteatherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=vikings&apikey=a215246d");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor(json, DadosSerie.class);
		System.out.println(dados);

	}

	private DadosSerie conversor(String json, Class<DadosSerie> dadosSerieClass) {
		ConverteDados conversor = new ConverteDados(); // Crie uma instância da classe ConverteDados
		DadosSerie dados = conversor.obterDados(json, dadosSerieClass); // Chame o método obterDados
		return dados; // Retorne o objeto DadosSerie
	}

}
