package src.campoMinado;

public class Quadrado<T extends Conteudo> {
    private T conteudo;

    //Construtor -------------------------------------------------------------------------------------------------------
    public Quadrado(T conteudo) {
        this.conteudo = conteudo;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    public T getConteudo() {
        return conteudo;
    }

    public void setConteudo(T conteudo) {
        this.conteudo = conteudo;
    }
}
