package src.placar;

import java.util.ArrayList;

public class Placar <P extends Pontuacao> {
    private ArrayList<P> pontuacoes;
    private int tamanho;

    //Construtor -------------------------------------------------------------------------------------------------------
    public Placar() {
        pontuacoes = new ArrayList<>();
        tamanho = 0;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public ArrayList<P> getPontuacoes() {
        return pontuacoes;
    }

    public void setPontuacoes(ArrayList<P> pontuacoes) {
        this.pontuacoes = pontuacoes;
        tamanho = pontuacoes.size();
    }

    public int getTamanho() {
        return tamanho;
    }

    //Métodos ----------------------------------------------------------------------------------------------------------
    public void printToFile() {
        /*
        TODO: aprender a usar arquivos
        armazenar as informações na pasta files
         */
    }
}
