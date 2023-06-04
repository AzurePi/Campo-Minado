package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicialGUI extends JFrame implements ActionListener {
    JButton jogar = new JButton("Jogar");
    JButton recordes = new JButton("Recordes");

    public TelaInicialGUI() {
        //adicionar menu de opções

        jogar.addActionListener(this);
        recordes.addActionListener(this);

        this.add(jogar);
        this.add(recordes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source == jogar) {
            //troca pra tela do jogo
        } else if (source == recordes) {
            //torca pra tela dos recordes
        }

    }
}
