package src.placar;

import java.io.*;
import java.util.ArrayList;

public class Placar<P extends Pontuacao> {
    private ArrayList<P> pontuacoes;
    String filename;

    //Construtor -------------------------------------------------------------------------------------------------------
    public Placar(String dificuldade) {
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

    /**
     * Adiciona uma nova pontuação ao placar, e organiza todas elas em ordem decrecente.
     *
     * @param pontuacao a <code>Pontuacao</code> sendo adiconada
     */
    public void add(P pontuacao) {
        pontuacoes.add(pontuacao);
        pontuacoes.sort(null);
    }

    public void readFromFile() {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filename));
            pontuacoes = (ArrayList<P>) reader.readObject();
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + filename + "não encontrado para leitura");
        } catch (ClassNotFoundException | IOException ignored) {
        }

        if (pontuacoes == null)
            pontuacoes = new ArrayList<>();
    }

    public void printToFile() {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename));
            writer.writeObject(pontuacoes);
            writer.close();
        } catch (IOException ignored) {
        }
    }

    public void printar() {
        for (P pontuacoe : pontuacoes) System.out.println(pontuacoe.getNome() + pontuacoe.getPontos());
    }
}
