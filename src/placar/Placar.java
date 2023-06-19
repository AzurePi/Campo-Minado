package src.placar;

import java.io.*;
import java.util.ArrayList;

public class Placar<P extends Pontuacao> {
    private ArrayList<P> pontuacoes;
    private int tamanho;
    String filename;

    //Construtor -------------------------------------------------------------------------------------------------------
    public Placar(String dificuldade) {
        pontuacoes = new ArrayList<>();
        tamanho = 0;

        switch (dificuldade) {
            case "Fácil" -> filename = "src/placar/files/placarFacil.dat";
            case "Médio" -> filename = "src/placar/files/placarMedio.dat";
            case "Difícil" -> filename = "src/placar/files/placarDificil.dat";
        }

        readFromFile();
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
    public void addPontuacao(P pontuacao) {
        pontuacoes.add(pontuacao);
        pontuacoes.sort(null);
    }

    public void readFromFile() {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filename));
            P aux;

            while (true) {
                try {
                    aux = (P) reader.readObject();
                    addPontuacao(aux);
                } catch (EOFException e) {
                    break;
                }
            }
            reader.close();
        } catch (ClassNotFoundException | IOException ignored) {
        }
    }

    public void printToFile() {
        if(tamanho > 0){
            try {
                ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename));

                for (int i = 0; i < tamanho; i++)
                    writer.writeObject(pontuacoes.get(i));
                writer.close();
            } catch (IOException ignored) {
            }
        }
    }
}
