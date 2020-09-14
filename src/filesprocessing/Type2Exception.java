package filesprocessing;

/**
 * An exception the program can not recover from. Each Type2 that is being thrown will result in an error
 * and no processing of the file will be printed. Each sub-class carries the appropriate message to print.
 */
public abstract class Type2Exception extends Exception {
    private static final long serialVersionUID = 1L;

    public Type2Exception() {
    }

}
