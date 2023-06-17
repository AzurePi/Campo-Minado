package src.campoMinado;

/**
 * Tipo de conteúdo oculto numerado.
 */
public class Numerado extends Conteudo {
    private int dica;

    //Construtor -------------------------------------------------------------------------------------------------------
    /**
     * Instancia um novo <code>Numerado</code>.
     *
     * @param dica O número oculto nesse <code>Conteudo</code>
     */
    public Numerado(int dica) {
        this.dica = dica;
    }

    //Setters & Getters ------------------------------------------------------------------------------------------------
    /**
     * @return A dica em <code>Numerado</code>.
     */
    public int getDica() {
        return dica;
    }

    /**
     * Sobrescreve a dica no objeto <code>Numerado</code>.
     *
     * @param dica A dica em <code>Numerado</code>
     */
    public void setDica(int dica) {
        this.dica = dica;
    }

    //Métodos ----------------------------------------------------------------------------------------------------------

    /**
     * @return Um inteiro que codifica o que está oculto, a dica em <code>Numerado</code>.
     */
    @Override
    public int revelar() {
        return dica;
    }
}
