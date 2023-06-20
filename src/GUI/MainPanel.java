package src.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * O painel principal (extende <code>JPanel</code>) utilizado no Campo Minado. Dentro dele, se alternam através de um CardLayout os painéis <code>menuInicial</code>, <code>tabuleiro</code> e <code>placar</code>.
 */
public class MainPanel extends JPanel {
    private final MenuInicialGUI menuInicial;
    private final TabuleiroGUI tabuleiro;
    private final PlacarGUI placar;
    public final static String MENU_INICIAL = "JPanel do menu inicial";
    public final static String TABULEIRO = "JPanel com o tabuleiro do jogo";
    public final static String PLACAR = "JPanel com o placar";
    private final CardLayout layout;
    private final JPanel telas;

    //Construtor -------------------------------------------------------------------------------------------------------

    /**
     * Constrói um novo <code>MainPanel</code>.
     */
    public MainPanel() {
        layout = new CardLayout();
        telas = new JPanel(layout);

        menuInicial = new MenuInicialGUI();
        tabuleiro = new TabuleiroGUI();
        placar = new PlacarGUI();

        menuInicial.setVisible(true);
        tabuleiro.setVisible(true);
        placar.setVisible(true);

        telas.add(menuInicial, MENU_INICIAL);
        telas.add(tabuleiro, TABULEIRO);
        telas.add(placar, PLACAR);
        telas.setVisible(true);

        this.setVisible(true);
        this.add(telas);
        layout.show(telas, MENU_INICIAL);
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------

    /**
     * @return O painel onde se encontra o menu inicial
     */
    public MenuInicialGUI getMenuInicial() {
        return menuInicial;
    }

    /**
     * @return O painel onde se encontra o tabuleiro
     */
    public TabuleiroGUI getTabuleiro() {
        return tabuleiro;
    }

    /**
     * @return O painel onde se encontra o placar
     */
    public PlacarGUI getPlacar() {
        return placar;
    }

    @Override
    public CardLayout getLayout() {
        return layout;
    }

    /**
     * @return O painel que contém os painíis que se alternam por meio do <code>CardLayout</code>
     */
    public JPanel getTelas() {
        return telas;
    }
}
