package ba.unsa.etf.rpr.exceptions;

/**
 * A Class that represents use-defined exception
 *
 * @author Anida Nezovic
 */
public class MovieException extends Exception {
    public MovieException(String msg, Exception e) {
        super(msg, e);
    }

    public MovieException(String msg) {
        super(msg);
    }
}
