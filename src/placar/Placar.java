package src.placar;

import java.io.*;
import java.util.ArrayList;

public class Placar<P extends Pontuacao> {
    private final ArrayList<P> pontuacoes;
    String filename;

    //Construtor -------------------------------------------------------------------------------------------------------
    public Placar(String dificuldade) {
        pontuacoes = new ArrayList<>();

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

    //Métodos ----------------------------------------------------------------------------------------------------------
    public void add(P pontuacao) {
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
                    add(aux);
                    flag = true;
                } catch (EOFException e) {
                    flag = false; //se chegamos ao fim do arquivo, saímos do loop
                }
            } while (flag);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + filename + "não encontrado para leitura");
        } catch (ClassNotFoundException | IOException ignored) {
        }
        //printar();
    }

    public void printToFile() {
        int size = pontuacoes.size();

        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename));

            for (int i = 0; i < size; i++)
                writer.writeObject(pontuacoes.get(i));
            writer.close();
        } catch (IOException ignored) {
        }

    }

    public void printar() {
        for (int i = 0; i < pontuacoes.size(); i++)
            System.out.println(pontuacoes.get(i).getNome() + pontuacoes.get(i).getPontos());
    }
}
