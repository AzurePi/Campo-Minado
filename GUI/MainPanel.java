package GUI;


import javax.swing.*;
import java.awt.*;

//frame principal, dentro do qual se alteram os conteúdos (JPanel) exibidos
public class MainPanel extends JPanel {
    private final String MENUINICIAL;
    private final String TABULEIRO;
    private final String PLACAR;
    private final CardLayout layout; //https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html
    private final JPanel telas;

    public MainPanel() {
        layout = new CardLayout();
        telas = new JPanel(layout);

        JPanel menuInicial = new TelaInicialGUI();
        JPanel tabuleiro = new TabuleiroGUI(5);
        JPanel placar = new PlacarGUI();

        MENUINICIAL = "JPanel do menu inicial";
        TABULEIRO = "JPanel com o tabuleiro do jogo";
        PLACAR = "JPanel com o placar";

        telas.add(menuInicial, MENUINICIAL);
        telas.add(tabuleiro, TABULEIRO);
        telas.add(placar, PLACAR);

        this.setVisible(true);
        layout.show(telas, MENUINICIAL);
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
