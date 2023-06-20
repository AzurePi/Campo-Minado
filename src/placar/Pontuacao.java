package src.placar;

import exceptions.InvalidNameException;

import java.io.Serializable;

/**
 * Classe abstrata de uma pontuação, caracterizada por um <code>nome</code> e <code>pontos</code>.
 */
public abstract class Pontuacao implements Comparable<Pontuacao>, Serializable {
    private final String nome;
    private final int pontos;

    //Construtor -------------------------------------------------------------------------------------------------------

    /**
     * Construtor abstrato de <code>Pontuacao</code>, utilizado por seus herdeiros.
     *
     * @param nome o nome associado à pontuação
     * @param pontos quantidade de pontos
     * @throws InvalidNameException m nome é considerado inválido se tem menos de 3 caracteres, ou mais de 12; se não começa com uma letra; ou se possui algum caractere alfanumérico.
     */
    public Pontuacao(String nome, int pontos) throws InvalidNameException {
        validaNome(nome);
        this.nome = nome;
        this.pontos = pontos;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------

    /**
     * @return o nome associado à pontuação
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return a quantidade de pontos
     */
    public int getPontos() {
        return pontos;
    }

    //Métodos ----------------------------------------------------------------------------------------------------------

    /**
     * Compara essa <code>Pontuacao</code> com outra <code>o</code> de acordo com seus valores <code>pontos</code>.
     *
     * @param o a <code>Pontuacao</code> sendo comparada
     * @return um inteiro negativo, zero, ou um inteiro positivo se essa <code>Pontuacao</code> é, respectivamente, maior, igual, ou menor que a outra
     */
    @Override
    public int compareTo(Pontuacao o) {
        Integer esse = this.pontos;
        Integer aquele = o.getPontos();
        return aquele.compareTo(esse);
    }

    /**
     * Verifica se um <code>nome</code> se enquadra nos padrões esperados.
     *
     * @param nome o nome sendo validado
     * @throws InvalidNameException um nome é considerado inválido se tem menos de 3 caracteres, ou mais de 12; se não começa com uma letra; ou se possui algum caractere alfanumérico.
     */
    private static void validaNome(String nome) throws InvalidNameException {
        int length = nome.length();

        //é inválido se tem menos de 3 caracteres, ou mais de 12
        if (length > 12 || length < 3)
            throw new InvalidNameException("O nome no placar deve ter de 3 a 12 caracteres");

        //é inválido se não começa com uma letra
        if (!Character.isLetter(nome.charAt(0)))
            throw new InvalidNameException("O nome no placar deve começar com uma letra");

        //é inválido se possui caracteres que não são alfanuméricos
        if (!nome.matches("[a-zA-Z0-9]*$"))
            throw new InvalidNameException("O nome no placar deve conter apenas caracteres alfanuméricos");
    }
}
