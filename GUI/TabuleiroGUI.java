package GUI;

import campoMinado.Tabuleiro;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabuleiroGUI extends JPanel {
    Tabuleiro t;

    public TabuleiroGUI(int tamanho) {
        t = new Tabuleiro(tamanho);

        JTable campo = new JTable(tamanho, tamanho);

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                BotaoTabuleiro b = new BotaoTabuleiro(new ImageIcon());

                //cria uma classe anônima que implementa BotaoTabuleiroListener
                b.addTableButtonListener(new BotaoTabuleiroListener() {
                    @Override
                    public void botaoClicado(int linha, int coluna) {
                        //verifica que tipo de conteúdo temos na matriz do campoMinado
                        //e age de acordo

                        //se é vazio, abre todos os vazios (abrir, nesse caso, envolve trocar a cor)
                        //se é número, troca a cor coloca a imagem do número no botão
                        //se é bomba, troca a cor, coloca a imagem da bomba, e mostra uma mensagem de fim de jogo
                    }
                });

                TableColumn col = new TableColumn();
                col.setCellRenderer(b);
                col.setCellEditor(b);
                campo.addColumn(col); //TODO: não tenho certeza se é assim que adicionamos as coisas na tabela
            }
        }

        this.add(campo);
    }

    private static class ListenerMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private static class ListenerJogo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
