package campoMinado;

public class Numerado extends Conteudo{
    private int dica;

    public int getDica() {
        return dica;
    }

    public void setDica(int dica) {
        this.dica = dica;
    }

    public Numerado(int dica) {
        this.dica = dica;
    }

    @Override
    public int revelar() {
        return 0;
    }
}
