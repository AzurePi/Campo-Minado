package src.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * O painel (extende <code>JPanel</code>) do menu inicial do campo minado. <br>Implementa <code>ActionListener</code> para gerenciar as ações de seus próprios botões.
 */
public class MenuInicialGUI extends JPanel implements ActionListener {
    private final JRadioButton facil;
    private final JRadioButton medio;
    private final JRadioButton dificil;
    private final JButton botaoJogar;
    private final JButton botaoRecordes;

    //Construtor -------------------------------------------------------------------------------------------------------

    /**
     * Instantiates a new Menu inicial gui.
     */
    public MenuInicialGUI() {
        ImageIcon iconeBomba = new ImageIcon("src/GUI/assets/noun-bomb-238911.png");
        JLabel labelIcone = new JLabel(iconeBomba);

        JPanel painelLateral = new JPanel();
        painelLateral.setLayout(new BoxLayout(painelLateral, BoxLayout.Y_AXIS));

        JLabel labelTitulo = new JLabel("Campo Minado", SwingConstants.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.BOLD, 50));

        JPanel painelMenu = new JPanel();
        painelMenu.setLayout(new BoxLayout(painelMenu, BoxLayout.X_AXIS));

        JPanel painelDificuldades = new JPanel();
        painelDificuldades.setLayout(new BoxLayout(painelDificuldades, BoxLayout.Y_AXIS));

        ButtonGroup dificuldades = new ButtonGroup();

        facil = new JRadioButton("Fácil", true);
        medio = new JRadioButton("Médio", false);
        dificil = new JRadioButton("Difícil", false);

        dificuldades.add(facil);
        dificuldades.add(medio);
        dificuldades.add(dificil);

        painelDificuldades.add(facil);
        painelDificuldades.add(medio);
        painelDificuldades.add(dificil);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));

        botaoJogar = new JButton("Jogar");
        botaoJogar.addActionListener(this);

        botaoRecordes = new JButton("Recordes");
        botaoRecordes.addActionListener(this);

        painelBotoes.add(botaoJogar);
        painelBotoes.add(Box.createRigidArea(new Dimension(40, 1)));
        painelBotoes.add(botaoRecordes);

        painelMenu.add(painelDificuldades);
        painelMenu.add(Box.createRigidArea(new Dimension(40, 1)));
        painelMenu.add(painelBotoes);

        painelLateral.add(labelTitulo);
        painelLateral.add(Box.createRigidArea(new Dimension(1, 300)));
        painelLateral.add(painelMenu);

        this.add(labelIcone);
        this.add(painelLateral);
        this.setVisible(true);
    }

    //Métodos ----------------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        MainPanel parent = (MainPanel) this.getParent().getParent();
        CardLayout layout = parent.getLayout();
        JPanel telas = parent.getTelas();

        if (source == botaoJogar) {
            if (facil.isSelected())
                parent.getTabuleiro().inicializar(5);
            else if (medio.isSelected())
                parent.getTabuleiro().inicializar(7);
            else if (dificil.isSelected())
                parent.getTabuleiro().inicializar(11);

            layout.show(telas, MainPanel.TABULEIRO); //mostra o tabuleiro na tela
        } else if (source == botaoRecordes)
            layout.show(telas, MainPanel.PLACAR);
    }
}
