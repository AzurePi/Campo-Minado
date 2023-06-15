package exceptions;

public class InvalidNameException extends Exception {
    public InvalidNameException(){
        super("Esse nome possui um caractere proibido.");
    }

}
