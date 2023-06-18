package src.placar;

import exceptions.InvalidNameException;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class Pontuacao implements Comparable<Pontuacao>, Serializable {
    private String nome;
    private int pontos;
    private static final Set<Character> caracteresInvalidos = new HashSet<>();

    //Construtor -------------------------------------------------------------------------------------------------------
    public Pontuacao(String nome, int pontos) throws InvalidNameException {
        /*
        if (caracteresInvalidos.isEmpty())
            setCaracteresInvalidos();
        */
        validaNome(nome);
        this.nome = nome;
        this.pontos = pontos;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    //Métodos ----------------------------------------------------------------------------------------------------------
    @Override
    public int compareTo(Pontuacao o) {
        Integer esse = this.pontos;
        Integer aquele = o.getPontos();
        return esse.compareTo(aquele);
    }

    private static void validaNome(String nome) throws InvalidNameException {
        int length = nome.length();

        //é inválido se tem menos de 3 caracteres, ou mais de 12
        if (length > 12 || length < 3)
            throw new InvalidNameException("O nome no placar deve ter de 3 a 12 caracteres");

        //é inválido se não começa com uma letra
        if(!Character.isLetter(nome.charAt(0)))
            throw new InvalidNameException("O nome no placar deve começar com uma letra");

        //é inválido se possui caracteres que não são alfanuméricos
        if(!nome.matches("[a-zA-Z0-9]*$"))
            throw new InvalidNameException("O nome no placar deve conter apenas caracteres alfanuméricos");

        /*
        for (int i = 0; i < length; i++) {
            if (caracteresInvalidos.contains(nome.charAt(i)))
                return false;
        }
        */
    }

    private static void setCaracteresInvalidos() {
        caracteresInvalidos.add(' ');
        caracteresInvalidos.add('\\');
        caracteresInvalidos.add('/');
        caracteresInvalidos.add('!');
        caracteresInvalidos.add('?');
        caracteresInvalidos.add(',');
        caracteresInvalidos.add(';');
        caracteresInvalidos.add('`');
        caracteresInvalidos.add('´');
        caracteresInvalidos.add('~');
        caracteresInvalidos.add('^');
        caracteresInvalidos.add('+');
        caracteresInvalidos.add('=');
        caracteresInvalidos.add('*');
        caracteresInvalidos.add('°');
    }
}
