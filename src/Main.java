package src;
/*
Bomb by icon 54 from <a href="https://thenounproject.com/browse/icons/term/bomb/" target="_blank" title="Bomb Icons">Noun Project</a>
Flag by Elliott Counts from <a href="https://thenounproject.com/browse/icons/term/flag/" target="_blank" title="Flag Icons">Noun Project</a>
*/

import src.GUI.MainPanel;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main {
    public static JFrame f;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        f = new JFrame("Campo Minado");

        MainPanel panel = new MainPanel();

        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch (UnsupportedLookAndFeelException | ClassNotFoundException | IllegalAccessException |
                InstantiationException e){
            throw new RuntimeException(e);
        }

        ImageIcon iconeBomba = new ImageIcon("src/GUI/assets/noun-bomb-238911.png");
        f.setIconImage(iconeBomba.getImage());

        f.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                panel.getPlacar().salvar();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(580, 600);
        f.setResizable(false);

        f.add(panel);
        f.pack();
        f.setVisible(true);
    }
}
