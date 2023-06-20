package exceptions;

/**
 * Exceção jogada quando um nome é inválido em determinado contexto.
 */
public class InvalidNameException extends Exception {
    public InvalidNameException(String msg) {
        super(msg);
    }
}
