package src.placar;

import exceptions.InvalidNameException;

import java.io.Serializable;

public class PontuacaoDificil extends Pontuacao implements Serializable {
    public PontuacaoDificil(String nome, int pontos) throws InvalidNameException {
        super(nome, pontos);
    }
}
