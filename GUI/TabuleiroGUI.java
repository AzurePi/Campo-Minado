package GUI;

import campoMinado.Tabuleiro;

import javax.swing.*;

public class TabuleiroGUI extends JPanel {
    Tabuleiro t;

    public TabuleiroGUI(int tamanho) {
        t = new Tabuleiro(tamanho);

        JTable campo = new JTable(tamanho, tamanho);

        for (int i = 0; i < tamanho * tamanho; i++) {
            JButton b = new JButton();
            b.addActionListener(new apertarBotao());
            campo.add(b, i);
        }

        this.add(campo);
    }
}
