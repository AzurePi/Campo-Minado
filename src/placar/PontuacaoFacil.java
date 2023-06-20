package src.placar;

import exceptions.InvalidNameException;

/**
 * Tipo de <code>Pontuacao</code> fácil.
 */
public class PontuacaoFacil extends Pontuacao {
    public PontuacaoFacil(String nome, int pontos) throws InvalidNameException {
        super(nome, pontos);
    }
}
