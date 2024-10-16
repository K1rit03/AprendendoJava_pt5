package br.com.aprendendojavaspringboot.cyberteather.principal;

import br.com.aprendendojavaspringboot.cyberteather.service.ConsumoApi;
import br.com.aprendendojavaspringboot.cyberteather.service.ConverteDados;
import model.DadosSerie;
import model.DadosTemporada;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    ConverteDados conversor = new ConverteDados();

    private final String ENDERECO ="https://www.omdbapi.com/?t=";
    private final String APIKEY = "&apikey=a215246d";


    public void exibeMenu(){
        System.out.println("Digite o nome da serie");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dados = conversor(json, DadosSerie.class);
        System.out.println(dados);
        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i< dados.totalTemporadas(); i++){
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + APIKEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);

		}
		temporadas.forEach(System.out::println);


    }

    private DadosSerie conversor(String json, Class<DadosSerie> dadosSerieClass) {
        ConverteDados conversor = new ConverteDados(); // Crie uma instância da classe ConverteDados
        DadosSerie dados = conversor.obterDados(json, dadosSerieClass); // Chame o método obterDados
        return dados; // Retorne o objeto DadosSerie
    }
}
