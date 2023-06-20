package src.placar;

import exceptions.InvalidNameException;

import java.io.Serializable;

public class PontuacaoMedio extends Pontuacao implements Serializable {
    public PontuacaoMedio(String nome, int pontos) throws InvalidNameException {
        super(nome, pontos);
    }
}
