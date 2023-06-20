package src.placar;

import exceptions.InvalidNameException;

/**
 * Tipo de <code>Pontuacao</code> média.
 */
public class PontuacaoMedio extends Pontuacao {
    public PontuacaoMedio(String nome, int pontos) throws InvalidNameException {
        super(nome, pontos);
    }
}
