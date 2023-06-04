package campoMinado;

public class Numerado extends Conteudo {
    private int dica;

    //Construtor -------------------------------------------------------------------------------------------------------
    public Numerado(int dica) {
        this.dica = dica;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public int getDica() {
        return dica;
    }

    public void setDica(int dica) {
        this.dica = dica;
    }

    //Métodos ----------------------------------------------------------------------------------------------------------
    @Override
    public int revelar() {
        return 0;
    }
}
