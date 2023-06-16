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
    private final CardLayout LAYOUT; //https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
    private final JPanel TELAS;

    //Construtor -------------------------------------------------------------------------------------------------------
    public MainPanel() {
        LAYOUT = new CardLayout();
        TELAS = new JPanel(LAYOUT);

        menuInicial = new TelaInicialGUI();
        tabuleiro = new TabuleiroGUI();
        placar = new PlacarGUI();

        menuInicial.setVisible(true);
        tabuleiro.setVisible(true);
        placar.setVisible(true);

        MENUINICIAL = "JPanel do menu inicial";
        TABULEIRO = "JPanel com o tabuleiro do jogo";
        PLACAR = "JPanel com o placar";

        TELAS.add(menuInicial, MENUINICIAL);
        TELAS.add(tabuleiro, TABULEIRO);
        TELAS.add(placar, PLACAR);
        TELAS.setVisible(true);

        this.setVisible(true);
        this.add(TELAS);
        LAYOUT.show(TELAS, MENUINICIAL);
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
        return LAYOUT;
    }

    public JPanel getTELAS() {
        return TELAS;
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
