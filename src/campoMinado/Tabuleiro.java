package src.campoMinado;

import java.util.ArrayList;
import java.util.Random;

/**
 * Um tabuleiro de campo minado composto por uma matriz de <code>Quadrado</code>.
 */
public class Tabuleiro {
    private final int tamanho;
    private final int nBombas;
    private ArrayList<ArrayList<Quadrado<?>>> board;

    /**
     * Instancia um novo <code>Tabuleiro</code>.
     *
     * @param tamanho O tamanho do tabuleiro
     */
//Construtor -------------------------------------------------------------------------------------------------------
    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;

        //Relaciona cada elemento ("linha") do array com outro array ("coluna")
        board = new ArrayList<>();
        for (int i = 0; i < tamanho; i++)
            board.add(new ArrayList<>());

        switch (tamanho) {
            case 5 -> nBombas = 6;
            case 7 -> nBombas = 15;
            case 11 -> nBombas = 45;
            default -> nBombas = 0;
        }

        iniciaTabuleiro();
        sorteiaMinas();
    }

    /**
     * @return O tamanho do tabuleiro
     */
    //Setters & Getters ------------------------------------------------------------------------------------------------
    public int getTamanho() {
        return tamanho;
    }

    /**
     * @return O número de bombas no tabuleiro
     */
    public int getnBombas() {
        return nBombas;
    }

    /**
     * @return O tabuleiro
     */
    public ArrayList<ArrayList<Quadrado<?>>> getBoard() {
        return board;
    }

    /**
     * Sets board.
     *
     * @param board the board
     */
    public void setBoard(ArrayList<ArrayList<Quadrado<?>>> board) {
        this.board = board;
    }

    /**
     * Inicia tabuleiro.
     */
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

    /**
     * Sorteia minas.
     */
    public void sorteiaMinas() {
        boolean sorteado;
        int linha, coluna;
        Random random = new Random();

        //para cada bomba que deve estar no tabuleiro
        for (int i = 0; i < nBombas; i++) {
            //sorteamos uma coordenada
            do {
                linha = random.nextInt(tamanho);
                coluna = random.nextInt(tamanho);

                sorteado = board.get(linha).get(coluna).getConteudo() instanceof Bomba;
                //instanceof retorna o boleano que controla o laço
                //Note que se não for do tipo bomba, o boleano retornado é falso
            } while (sorteado);

            board.get(linha).set(coluna, new Quadrado<>(new Bomba()));
            preencheDicas(linha, coluna);
        }
    }

    /**
     * Preenche dicas.
     *
     * @param linha  the linha
     * @param coluna the coluna
     */
    public void preencheDicas(int linha, int coluna) {
        for (int i = linha - 1; i <= linha + 1; i++) {
            for (int j = coluna - 1; j <= coluna + 1; j++) {
                if (coordenadaValida(i, j, tamanho)) {
                    Conteudo aux = board.get(i).get(j).getConteudo();

                    if (aux instanceof Vazio)
                        board.get(i).set(j, new Quadrado<>(new Numerado(1)));
                    else if (aux instanceof Numerado)
                        ((Numerado) aux).setDica(((Numerado) aux).getDica() + 1);
                }
            }
        }
    }

    /**
     * Coordenada valida boolean.
     *
     * @param i       the
     * @param j       the j
     * @param tamanho the tamanho
     * @return the boolean
     */
    public static boolean coordenadaValida(int i, int j, int tamanho) {
        return (i >= 0 && i < tamanho && j >= 0 && j < tamanho);
    }

    /**
     * Imprimir.
     */
    public void imprimir() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++)
                System.out.printf(board.get(i).get(j).getConteudo().revelar() + " ");
            System.out.printf("\n");
        }
    }
}
