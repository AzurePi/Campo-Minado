package src;
/*
Bomb by icon 54 from <a href="https://thenounproject.com/browse/icons/term/bomb/" target="_blank" title="Bomb Icons">Noun Project</a>
Flag by Elliott Counts from <a href="https://thenounproject.com/browse/icons/term/flag/" target="_blank" title="Flag Icons">Noun Project</a>
*/

import src.GUI.MainPanel;

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
        f.pack();
        f.setVisible(true);
    }
}
