package cl.mineduc.sidep.parvuloapi.exceptions;

public class ParvuloException extends RuntimeException {

    public ParvuloException() {
        super();
    }

    public ParvuloException(String message) {
        super(message);
    }

    public ParvuloException(String message, Throwable cause) {
        super(message, cause);
    }

}
