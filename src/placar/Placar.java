package src.placar;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Placar<P extends Pontuacao> {
    private ArrayList<P> pontuacoes;
    private int tamanho;
    String filename;

    //Construtor -------------------------------------------------------------------------------------------------------
    public Placar() {
        pontuacoes = new ArrayList<>();
        tamanho = 0;

        filename = "pontuacao";

        Type tipo = this.getClass().getGenericSuperclass();
        if (tipo == PontuacaoFacil.class)
            filename += "Facil";
        else if (tipo == PontuacaoMedio.class)
            filename += "Medio";
        else
            filename += "Dificil";

        filename += ".dat";
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
            while (true) {
                try {
                    P aux = (P) reader.readObject();
                    addPontuacao((P) aux);
                } catch (EOFException e) {
                    break;
                }
            }
            reader.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void printToFile() {
        String filename = "pontuacao";
        filename += pontuacoes.get(0).getClass().getSimpleName();
        filename += ".dat";

        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filename));

            for (int i = 0; i < tamanho; i++)
                writer.writeObject(pontuacoes.get(i));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
        TODO: aprender a usar arquivos
        armazenar as informações na pasta files
         */
    }
}
