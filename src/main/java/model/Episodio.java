package model;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private String titulo;
    private Integer temporada;
    private Integer numeroDoEpisodio;
    private  double avaliacao;
    private LocalDate dataDeLancamento;

    public Episodio(Integer numeroTemporada, DadosEpisodios dadosEpisodios) {
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodios.titulo();
        this.numeroDoEpisodio = dadosEpisodios.episodio();
        try{
            this.avaliacao = Double.valueOf(dadosEpisodios.avaliacao());

        }catch (NumberFormatException ex){
            this.avaliacao = 0;
        }
        try{
            this.dataDeLancamento = LocalDate.parse(dadosEpisodios.dataDeLancamento());
        }catch (DateTimeParseException ex){
            this.dataDeLancamento = null;
        }



    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }

    public void setDataDeLancamento(LocalDate dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Integer getNumeroDoEpisodio() {
        return numeroDoEpisodio;
    }

    public void setNumeroDoEpisodio(Integer numeroDoEpisodio) {
        this.numeroDoEpisodio = numeroDoEpisodio;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", temporada=" + temporada +
                ", numeroDoEpisodio=" + numeroDoEpisodio +
                ", avaliacao=" + avaliacao +
                ", dataDeLancamento=" + dataDeLancamento ;
    }
}
