package br.com.aprendendojavaspringboot.cyberteather.principal;

import model.Episodio;
import br.com.aprendendojavaspringboot.cyberteather.service.ConsumoApi;
import br.com.aprendendojavaspringboot.cyberteather.service.ConverteDados;
import model.DadosEpisodios;
import model.DadosSerie;
import model.DadosTemporada;
import org.springframework.cglib.core.Local;

import java.nio.DoubleBuffer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

        for (int i = 1; i <= dados.totalTemporadas(); i++) { // Use <= para incluir a última temporada
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + APIKEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

        temporadas.forEach(System.out::println);



        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));


        List<DadosEpisodios> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());


//        System.out.println("\nTop 10 episodios");
//        dadosEpisodios.stream()
//                        .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                        .peek(e -> System.out.println("Primeiro Filtro(N/A" + e))
//
//                        .sorted(Comparator.comparing(DadosEpisodios::avaliacao).reversed())
//                        .peek(e -> System.out.println("Ordenação " + e))
//                        .limit(10)
//                        .peek(e -> System.out.println("Limitação " + e))
//                        .map(e -> e.titulo().toUpperCase())
//                        .peek(e -> System.out.println("Mapeando "  + e))
//                        .forEach(System.out::println);





        List<Episodio> episodios = temporadas.stream()
                .flatMap(t-> t.episodios().stream()
                        .map(d-> new Episodio(t.temporada(), d))
                ).collect(Collectors.toList());
        episodios.forEach(System.out::println);

//        System.out.println("Digite o nome ou um trecho de algum episodio: ");
//
//        var trechoTitulo = leitura.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
//                .findFirst();
//        if(episodioBuscado.isPresent()){
//            System.out.println("Episodio encontrado!");
//            System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
//        }else{
//            System.out.println("Episodio não encontrado");
//        }


//        System.out.println("A partir de que ano voce deseja ver os episodios :");
//        var ano = leitura.nextInt();
//        leitura.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano,1,1);
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodios.stream()
//                .filter(e -> e.getDataDeLancamento() != null && e.getDataDeLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada" + e.getTemporada() +
//                                "Episodio" + e.getTitulo() +
//                                "Data Lançamento: " + e.getDataDeLancamento().format(formatador)
//                ));
        Map<Integer , Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
        System.out.println("Media:" + est.getAverage());
        System.out.println("Melhor episodio: " + est.getMax());
        System.out.println("Pior episodio: " + est.getMin());
        System.out.println("Quantidade avaliado: " + est.getCount());




    }

    private DadosSerie conversor(String json, Class<DadosSerie> dadosSerieClass) {
        ConverteDados conversor = new ConverteDados(); // Crie uma instância da classe ConverteDados
        DadosSerie dados = conversor.obterDados(json, dadosSerieClass); // Chame o método obterDados
        return dados; // Retorne o objeto DadosSerie
    }
}
