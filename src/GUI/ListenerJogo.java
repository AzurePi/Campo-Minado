package src.GUI;

import src.campoMinado.Quadrado;
import src.campoMinado.Tabuleiro;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerJogo implements MouseListener {
    int tamanho;

    public ListenerJogo(){
        tamanho = TabuleiroGUI.getT().getTamanho();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!TabuleiroGUI.isCronometroIniciado()) {
            TabuleiroGUI.iniciarCronometro();
            TabuleiroGUI.setCronometroIniciado(true);
        }
        int button = e.getButton();
        NossoBotao source = (NossoBotao) e.getSource();

        //botão esquerdo -> revela o conteúdo
        //botão direito -> coloca uma bandeira
        if (button == 1) {
            TabuleiroGUI.incrementarCliques();

            int i = source.getI();
            int j = source.getJ();

            Quadrado<?> quadrado = TabuleiroGUI.getT().getBoard().get(i).get(j);
            int conteudo = quadrado.getConteudo().revelar();

            if (conteudo == -1) {
                abrirBombas();
                TabuleiroGUI.getCronometro().stop();
                TabuleiroGUI.encerrar();
            } else if (conteudo == 0)
                abrirVazio(i, j);
            else
                abrirNumerado(i, j);

        } else if (button == 3 && !source.isFoiClickado()) {
            source.setEnabled(!source.isEnabled());
            source.setDisabledIcon(TabuleiroGUI.getIconeBandeira());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void abrirBombas() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                int conteudo = TabuleiroGUI.getT().getBoard().get(i).get(j).getConteudo().revelar();
                if (conteudo == -1) {
                    TabuleiroGUI.getBotoes()[i][j].setEnabled(false);
                    TabuleiroGUI.getBotoes()[i][j].setDisabledIcon(TabuleiroGUI.getIconeBomba());
                    TabuleiroGUI.getBotoes()[i][j].setText("bomba");
                }
            }
        }
    }

    public void abrirVazio(int linha, int coluna) {
        NossoBotao botao = TabuleiroGUI.getBotoes()[linha][coluna];
        if (botao.isEnabled())
            botao.setEnabled(false);
        botao.setFoiClickado(true);

        for (int i = linha - 1; i <= linha + 1; i++) {
            for (int j = coluna - 1; j <= coluna + 1; j++) {
                if (Tabuleiro.coordenadaValida(i, j, tamanho)) {
                    int conteudo = TabuleiroGUI.getT().getBoard().get(i).get(j).getConteudo().revelar();
                    if (conteudo == 0 && TabuleiroGUI.getBotoes()[i][j].isEnabled())
                        abrirVazio(i, j);
                    else if (conteudo != -1)
                        abrirNumerado(i, j);
                }
            }
        }
    }

    private void abrirNumerado(int linha, int coluna) {
        NossoBotao numerado = TabuleiroGUI.getBotoes()[linha][coluna];
        numerado.setEnabled(false);
        numerado.setFoiClickado(true);

        int numero = TabuleiroGUI.getT().getBoard().get(linha).get(coluna).getConteudo().revelar();
        numerado.setText(String.valueOf(numero));
    }
}
