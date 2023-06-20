package src.GUI;

import javax.swing.*;

/**
 * Extensão de <code>JButton</code> com informações de se já foi clicado e suas coordenadas em uma matriz.
 */
public class BotaoCampoMinado extends JButton {
    private boolean foiClickado;
    private final int i, j;

    //Construtor -------------------------------------------------------------------------------------------------------

    /**
     * Instancia um novo <code>BotaoCampoMinado</code> com as coordenadas especificadas. Esse botão se incia como não clicado.
     *
     * @param i a linha do botão
     * @param j a coluna do botão
     */
    public BotaoCampoMinado(int i, int j) {
        foiClickado = false;
        this.i = i;
        this.j = j;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------

    /**
     * @return a linha do botão
     */
    public int getI() {
        return i;
    }

    /**
     * @return a coluna do botão
     */
    public int getJ() {
        return j;
    }

    /**
     * @return se o botão já foi clicado ou não
     */
    public boolean isFoiClickado() {
        return foiClickado;
    }

    /**
     * @param foiClickado se o botão foi clicado ou não
     */
    public void setFoiClickado(boolean foiClickado) {
        this.foiClickado = foiClickado;
    }
}
