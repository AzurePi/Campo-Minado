package src.placar;

import exceptions.InvalidNameException;

import java.io.Serializable;

public abstract class Pontuacao implements Comparable<Pontuacao>, Serializable {
    private final String nome;
    private final int pontos;

    //Construtor -------------------------------------------------------------------------------------------------------
    public Pontuacao(String nome, int pontos) throws InvalidNameException {
        validaNome(nome);
        this.nome = nome;
        this.pontos = pontos;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    //Métodos ----------------------------------------------------------------------------------------------------------
    @Override
    public int compareTo(Pontuacao o) {
        Integer esse = this.pontos;
        Integer aquele = o.getPontos();
        return aquele.compareTo(esse);
    }

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
