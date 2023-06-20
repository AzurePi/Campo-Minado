package src.placar;

import java.io.*;
import java.util.ArrayList;

public class Placar<P extends Pontuacao> {
    private final ArrayList<P> pontuacoes;
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

        readFromFile(); //tenta ler do arquivo, caso já existam informações do placar em um arquivo
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public ArrayList<P> getPontuacoes() {
        return pontuacoes;
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

            boolean flag;
            do {
                try {
                    aux = (P) reader.readObject();
                    addPontuacao(aux);
                    flag = true;
                } catch (EOFException e) {
                    flag = false; //se chegamos ao fim do arquivo, saímos do loop
                }
            } while (flag);
            reader.close();
        } catch (ClassNotFoundException | IOException ignored) {
        }
    }

    public void printToFile() {
        if (tamanho > 0) {
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
