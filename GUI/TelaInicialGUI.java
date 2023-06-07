/*
Bomb by icon 54 from <a href="https://thenounproject.com/browse/icons/term/bomb/" target="_blank" title="Bomb Icons">Noun Project</a>
*/

package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicialGUI extends JPanel implements ActionListener {
    ButtonGroup dificuldades;
    JRadioButton facil, medio, dificil;
    JButton botaoJogar;
    JButton botaoRecordes;

    public TelaInicialGUI() {
        JPanel painelTitulo = new JPanel();

        ImageIcon iconeBomba = new ImageIcon("GUI/assets/noun-bomb-238911.png");
        JLabel titulo = new JLabel("Campo Minado", iconeBomba, SwingConstants.CENTER);
        painelTitulo.add(titulo);

        JPanel painelMenu = new JPanel();

        JPanel painelDificuldades = new JPanel();

        dificuldades = new ButtonGroup();

        facil = new JRadioButton("Fácil", true);
        medio = new JRadioButton("Médio", false);
        dificil = new JRadioButton("Difícil", false);

        dificuldades.add(facil);
        dificuldades.add(medio);
        dificuldades.add(dificil);

        painelDificuldades.add(facil);
        painelDificuldades.add(medio);
        painelDificuldades.add(dificil);

        botaoJogar = new JButton("Jogar");
        botaoJogar.addActionListener(this);

        botaoRecordes = new JButton("Recordes");
        botaoRecordes.addActionListener(this);

        painelMenu.add(painelDificuldades);
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

        if (source == botaoJogar){
            if(facil.isSelected())
                parent.getTabuleiro().inicializar(5);
            else if(medio.isSelected())
                parent.getTabuleiro().inicializar(7);
            else if(dificil.isSelected())
                parent.getTabuleiro().inicializar(12);

            layout.show(telas, parent.getTABULEIRO()); //mostra o tabuleiro na tela
        }

        else if (source == botaoRecordes)
            layout.show(telas, parent.getPLACAR());
    }
}
