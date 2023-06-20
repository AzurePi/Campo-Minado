package src.placar;

import exceptions.InvalidNameException;

/**
 * Tipo de <code>Pontuacao</code> difícil.
 */
public class PontuacaoDificil extends Pontuacao {
    public PontuacaoDificil(String nome, int pontos) throws InvalidNameException {
        super(nome, pontos);
    }
}
