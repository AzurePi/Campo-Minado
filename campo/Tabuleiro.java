import java.util.ArrayList;

public class Tabuleiro {
    int tamanho;
    ArrayList<ArrayList<Quadrado>> board = new ArrayList<>();

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;

        for (int i = 0; i < tamanho; i++) {
            ArrayList<Quadrado> linha = new ArrayList<>();
            for (int j = 0; j < tamanho; j++) {
                //decide se o novo quadrado é vazio ou com bomba
                //depois de tudo pronto, adicionamos números
            }
            board.add(linha);
        }

    }


}
