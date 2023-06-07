package GUI;

import javax.swing.*;

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

        ImageIcon iconeBomba = new ImageIcon("GUI/assets/noun-bomb-238911.png");
        f.setIconImage(iconeBomba.getImage());

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(580, 600);

        f.add(new MainPanel());
        f.setVisible(true);
    }
}
