package filesprocessing;

/**
 * An exception that the program can handle with and continue. Catching Type1 will result in printing a
 * warning while running.
 */
public class Type1Exception extends Exception {
    private static final long serialVersionUID = 1L;

    public Type1Exception() {
    }

    public String StringError() {
        return "Warning in line ";
    }
}
