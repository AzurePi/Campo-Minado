package src.campoMinado;

/**
 * Tipo de conteúdo oculto bomba.
 */
public class Bomba extends Conteudo {
    //Métodos ----------------------------------------------------------------------------------------------------------
    @Override
    public int revelar() {
        return -1;
    }
}
