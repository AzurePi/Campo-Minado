package campoMinado;

public class Quadrado <T extends Conteudo>{
    private T conteudo;

    public Quadrado(T conteudo) {
        this.conteudo = conteudo;
    }

    public T getConteudo() {
        return conteudo;
    }

    public void setConteudo(T conteudo) {
        this.conteudo = conteudo;
    }
}
