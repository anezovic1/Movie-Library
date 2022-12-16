package ba.unsa.etf.rpr.exceptions;

public class MovieException extends Exception {
    public MovieException(String msg, Exception e) {
        super(msg, e);
    }

    public MovieException(String msg) {
        super(msg);
    }
}
