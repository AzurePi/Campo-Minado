package src.campoMinado;

/**
 * Um quadrado com um <code>Conteudo</code> que compõe um <code>Tabuleiro</code>.
 *
 * @param <T> O tipo de <code>Conteudo</code> contido no quadrado.
 */
public class Quadrado<T extends Conteudo> {
    private T conteudo;

    //Construtor -------------------------------------------------------------------------------------------------------
    /**
     * Instancia um novo <code>Quadrado</code>
     *
     * @param conteudo O <code>Conteudo</code>
     */
    public Quadrado(T conteudo) {
        this.conteudo = conteudo;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    /**
     * @return O <code>Conteudo</code> do <code>Quadrado</code>.
     */
    public T getConteudo() {
        return conteudo;
    }

    /**
     * Sobrescreve o conteúdo do <code>Quadrado</code>.
     *
     * @param conteudo O <code>Conteudo</code> do quadrado
     */
    public void setConteudo(T conteudo) {
        this.conteudo = conteudo;
    }
}
