package GUI;

import javax.swing.*;

public class Frame {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame f = new JFrame("Campo Minado");
        TelaInicialGUI inicial = new TelaInicialGUI();
        TabuleiroGUI tabuleiro = new TabuleiroGUI(5);

        inicial.setVisible(true);
        tabuleiro.setVisible(false);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(580, 600);
        f.setVisible(true);

        f.add(inicial);
    }
}
