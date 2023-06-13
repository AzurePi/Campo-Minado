package src.placar;

import exceptions.InvalidNameException;

import java.util.ArrayList;

public class Placar {
    private ArrayList<Pontuacao> pontuacoes;
    private int tamanho;

    //Construtor -------------------------------------------------------------------------------------------------------
    public Placar() {
        pontuacoes = new ArrayList<>();
        tamanho = 0;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public ArrayList<Pontuacao> getPontuacoes() {
        return pontuacoes;
    }

    public void setPontuacoes(ArrayList<Pontuacao> pontuacoes) {
        this.pontuacoes = pontuacoes;
        tamanho = pontuacoes.size();
    }

    public int getTamanho() {
        return tamanho;
    }

    //Métodos ----------------------------------------------------------------------------------------------------------
    public void addPontuacao(Pontuacao pontuacao) {
        pontuacoes.add(pontuacao);
        pontuacoes.sort(null);
        tamanho++;
    }

    public void addPontuacao(String nome, float pontos) throws InvalidNameException {
        pontuacoes.add(new Pontuacao(nome, pontos));
        pontuacoes.sort(null);
        tamanho++;
    }

    public void printToFile() {
        /*
        TODO: aprender a usar arquivos
        armazenar as informações na pasta files
         */
    }
}
