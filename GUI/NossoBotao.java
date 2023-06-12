package GUI;

import javax.swing.*;

public class NossoBotao extends JButton {
    private boolean foiClickado = false;

    private int i , j;

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

    public NossoBotao(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public boolean isFoiClickado() {
        return foiClickado;
    }

    public void setFoiClickado(boolean foiClickado) {
        this.foiClickado = foiClickado;
    }
}
