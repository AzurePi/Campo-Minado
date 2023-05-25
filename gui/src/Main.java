import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame f = new JFrame("Campo Minado");
        f.setMinimumSize(new Dimension(600, 600));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        JPanel inicial = new JPanel();
        inicial.setBorder(BorderFactory.createDashedBorder(Color.BLACK));

        JLabel bemVindo = new JLabel("Bem vindo!");

        JPanel selecao = new JPanel();

        JLabel label = new JLabel("Tamanho do tabuleiro: ");
        String[] tamanhos = {"5x5", "10x10", "15x15"};
        JComboBox<String> tamanhoCombo = new JComboBox<>(tamanhos);
        tamanhoCombo.setSelectedIndex(0);

        selecao.add(label);
        selecao.add(tamanhoCombo);

        inicial.add(bemVindo);
        inicial.add(selecao);
        f.add(inicial);


    }
}
