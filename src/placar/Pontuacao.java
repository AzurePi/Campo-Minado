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
        if (caracteresInvalidos.isEmpty())
            setCaracteresInvalidos();

        if (validaNome(nome))
            this.nome = nome;
        else
            throw new InvalidNameException();
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

    private static boolean validaNome(String nome) {
        for (int i = 0; i < nome.length(); i++) {
            if (caracteresInvalidos.contains(nome.charAt(i)))
                return false;
        }
        return true;
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
