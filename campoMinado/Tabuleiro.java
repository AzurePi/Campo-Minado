package campoMinado;

import java.util.ArrayList;
import java.util.Random;

public class Tabuleiro {
    //Atributos  -------------------------------------------------------------------------------------------------------
    private int tamanho;
    private ArrayList<ArrayList<Quadrado<?>>> board;

    //Construtor -------------------------------------------------------------------------------------------------------
    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;

        board = new ArrayList<>();

        //Relaciona cada elemento do array "linha" com outro array "Coluna"
        for (int i = 0; i < tamanho; i++)
            board.add(new ArrayList<>());

        iniciaTabuleiro();
        sorteiaMinas();
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public ArrayList<ArrayList<Quadrado<?>>> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<ArrayList<Quadrado<?>>> board) {
        this.board = board;
    }

    //Métodos ----------------------------------------------------------------------------------------------------------
    public void iniciaTabuleiro() {
        //preenche o tabuleiro com objetos Vazios
        for (int i = 0; i < tamanho; i++) {
            ArrayList<Quadrado<?>> linha = board.get(i); //pegamos a linha i
            for (int j = 0; j < tamanho; j++) {
                linha.add(j, new Quadrado<>(new Vazio())); //adicionamos um quadrado Vazio na coluna j da linha i
                //board.get(i).add(j, new Quadrado<Vazio>());
            }
        }
    }

    public void sorteiaMinas() {
        int bombas;
        boolean sorteado;
        int linha, coluna;
        Random random = new Random();

        switch (tamanho) {
            case 5 -> bombas = 7;
            case 7 -> bombas = 20;
            case 12 -> bombas = 50;
            default -> bombas = 0;
        }

        //para cada bomba que deve estar no tabuleiro
        for (int i = 0; i < bombas; i++) {
            //sorteamos uma coordenada
            do {
                linha = random.nextInt(tamanho);
                coluna = random.nextInt(tamanho);

                sorteado = board.get(linha).get(coluna).getConteudo() instanceof Bomba;
                //instanceof retorna o boleano que controla o laço
                //Note que se não for do tipo bomba, o boleano retornado é falso
            } while (sorteado);

            board.get(linha).add(coluna, new Quadrado<>(new Bomba()));
            preencheNumerado(linha, coluna);
        }
    }

    public void preencheNumerado(int linha, int coluna) {
        for (int i = linha - 1; i <= linha + 1; i++) {
            for (int j = coluna - 1; j <= coluna + 1; j++) {
                if (coordenadaValida(i, j) && !(board.get(i).get(j).getConteudo() instanceof Bomba)) {
                    Conteudo aux = board.get(i).get(j).getConteudo();
                    if (aux instanceof Vazio) {
                        board.get(i).add(j, new Quadrado<>(new Numerado(1)));
                    } else if (aux instanceof Numerado) {
                        ((Numerado) aux).setDica(((Numerado) aux).getDica() + 1);
                        //(Numerado) board.get(i).get(j).getConteudo();
                    }
                }
            }
        }
    }

    private boolean coordenadaValida(int i, int j) {
        return (i >= 0 && i < tamanho && j >= 0 && j < tamanho);
    }
}
