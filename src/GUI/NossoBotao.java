package src.GUI;

import javax.swing.*;

public class NossoBotao extends JButton {
    private boolean foiClickado;
    private int i , j;

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

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public boolean isFoiClickado() {
        return foiClickado;
    }

    public void setFoiClickado(boolean foiClickado) {
        this.foiClickado = foiClickado;
    }
}
