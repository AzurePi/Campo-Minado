package src.placar;

import java.io.*;
import java.util.ArrayList;

/**
 * Um placar, composto por uma lista de <code>Pontuacao</code> do tipo determinado por <code>P</code> e um nome de arquivo.
 *
 * @param <P> tipo que extenda <code>Pontuacao</code>
 */
public class Placar<P extends Pontuacao> {
    private ArrayList<P> pontuacoes;
    String filename;

    //Construtor -------------------------------------------------------------------------------------------------------

    /**
     * Instancia um novo placar da dificuldade especificada. Tenta realizar uma leitura de informações de <code>pontuacoes</code> do arquivo correspondente à dificuldade.
     *
     * @param dificuldade descrição da dificuldade utilizada para o placar
     */
    public Placar(String dificuldade) {
        switch (dificuldade) {
            case "Fácil" -> filename = "src/placar/files/placarFacil.dat";
            case "Médio" -> filename = "src/placar/files/placarMedio.dat";
            case "Difícil" -> filename = "src/placar/files/placarDificil.dat";
        }
        readFromFile(); //tenta ler do arquivo, caso já existam informações do placar em um arquivo
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------

    /**
     * @return a <code>ArrayList</code> que contém todas as pontuações do tipo <code>P</code> especificado.
     */
    public ArrayList<P> getPontuacoes() {
        return pontuacoes;
    }

    //Métodos ----------------------------------------------------------------------------------------------------------

    /**
     * Adiciona uma nova pontuação ao placar, e organiza todas elas em ordem decrescente.
     *
     * @param pontuacao a <code>Pontuacao</code> sendo adicionada
     */
    public void add(P pontuacao) {
        pontuacoes.add(pontuacao);
        pontuacoes.sort(null);
    }

    /**
     * Lê informações da <code>ArrayList</code> <code>pontuacoes</code> de um arquivo.
     */
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

    /**
     * Armazena as informações da <code>ArrayList</code> <code>pontuacoes</code> em um arquivo.
     */
    public void printToFile() {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename));
            writer.writeObject(pontuacoes);
            writer.close();
        } catch (IOException ignored) {
        }
    }
}
