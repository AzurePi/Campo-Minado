package src.GUI;

import javax.swing.*;
import java.awt.*;

//frame principal, dentro do qual se alteram os conteúdos (JPanel) exibidos
public class MainPanel extends JPanel {
    TelaInicialGUI menuInicial;
    TabuleiroGUI tabuleiro;
    PlacarGUI placar;
    private final String MENUINICIAL;
    private final String TABULEIRO;
    private final String PLACAR;
    private final CardLayout layout; //https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
    private final JPanel telas;

    //Construtor -------------------------------------------------------------------------------------------------------
    public MainPanel() {
        layout = new CardLayout();
        telas = new JPanel(layout);

        menuInicial = new TelaInicialGUI();
        tabuleiro = new TabuleiroGUI();
        placar = new PlacarGUI();

        menuInicial.setVisible(true);
        tabuleiro.setVisible(true);
        placar.setVisible(true);

        MENUINICIAL = "JPanel do menu inicial";
        TABULEIRO = "JPanel com o tabuleiro do jogo";
        PLACAR = "JPanel com o src.placar";

        telas.add(menuInicial, MENUINICIAL);
        telas.add(tabuleiro, TABULEIRO);
        telas.add(placar, PLACAR);
        telas.setVisible(true);

        this.setVisible(true);
        this.add(telas);
        layout.show(telas, MENUINICIAL);
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public TelaInicialGUI getMenuInicial() {
        return menuInicial;
    }

    public void setMenuInicial(TelaInicialGUI menuInicial) {
        this.menuInicial = menuInicial;
    }

    public TabuleiroGUI getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(TabuleiroGUI tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public PlacarGUI getPlacar() {
        return placar;
    }

    public void setPlacar(PlacarGUI placar) {
        this.placar = placar;
    }

    @Override
    public CardLayout getLayout() {
        return layout;
    }

    public JPanel getTelas() {
        return telas;
    }

    public String getMENUINICIAL() {
        return MENUINICIAL;
    }

    public String getTABULEIRO() {
        return TABULEIRO;
    }

    public String getPLACAR() {
        return PLACAR;
    }
}
