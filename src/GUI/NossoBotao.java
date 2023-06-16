package src.GUI;

import javax.swing.*;

public class NossoBotao extends JButton {
    private boolean foiClickado;
    private final int i, j;

    //Construtor -------------------------------------------------------------------------------------------------------
    public NossoBotao(int i, int j) {
        foiClickado = false;
        this.i = i;
        this.j = j;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public boolean isFoiClickado() {
        return foiClickado;
    }

    public void setFoiClickado(boolean foiClickado) {
        this.foiClickado = foiClickado;
    }
}
