/*
Bomb by icon 54 from <a href="https://thenounproject.com/browse/icons/term/bomb/" target="_blank" title="Bomb Icons">Noun Project</a>
 */

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicialGUI extends JPanel implements ActionListener {
    JButton botaoJogar;
    JButton botaoRecordes;

    public TelaInicialGUI() {
        JPanel painelTitulo = new JPanel();

        ImageIcon iconeBomba = new ImageIcon("GUI/assets/noun-bomb-238911.svg");
        //JLabel icone = new JLabel(iconeBomba);
        JLabel titulo = new JLabel("Campo Minado", iconeBomba, SwingConstants.CENTER);
        painelTitulo.add(titulo);

        JPanel painelMenu = new JPanel();

        botaoJogar = new JButton("Jogar");
        botaoJogar.addActionListener(this);

        botaoRecordes = new JButton("Recordes");
        botaoRecordes.addActionListener(this);

        painelMenu.add(botaoJogar);
        painelMenu.add(botaoRecordes);

        this.add(painelTitulo);
        this.add(painelMenu);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        MainPanel parent = (MainPanel) this.getParent();
        CardLayout layout = parent.getLayout();
        JPanel telas = parent.getTelas();

        if (source == botaoJogar)
            layout.show(telas, parent.getTABULEIRO());
        else if (source == botaoRecordes)
            layout.show(telas, parent.getPLACAR());
    }
}
