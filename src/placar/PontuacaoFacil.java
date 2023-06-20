package src.placar;

import exceptions.InvalidNameException;

import java.io.Serializable;

public class PontuacaoFacil extends Pontuacao implements Serializable {
    public PontuacaoFacil(String nome, int pontos) throws InvalidNameException {
        super(nome, pontos);
    }
}
