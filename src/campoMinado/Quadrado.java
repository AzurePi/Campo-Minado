package src.campoMinado;

/**
 * Um quadrado com um <code>Conteudo</code> que compõe um <code>Tabuleiro</code>.
 *
 * @param <C> O tipo de <code>Conteudo</code> contido no quadrado.
 */
public class Quadrado<C extends Conteudo> {
    private final C conteudo;

    //Construtor -------------------------------------------------------------------------------------------------------

    /**
     * Instancia um novo <code>Quadrado</code>
     *
     * @param conteudo O <code>Conteudo</code>
     */
    public Quadrado(C conteudo) {
        this.conteudo = conteudo;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------

    /**
     * @return O <code>Conteudo</code> do <code>Quadrado</code>.
     */
    public C getConteudo() {
        return conteudo;
    }
}
