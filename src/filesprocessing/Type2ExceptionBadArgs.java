package filesprocessing;

/**
 * User put too many arguments in parameters.
 */
public class Type2ExceptionBadArgs extends Type2Exception {
    private final String ERRORMSG = "ERROR: <Too many arguments found, please check parameters> \n";

    public Type2ExceptionBadArgs() {
    }

    public void printERRORMSG() {
        System.err.println(ERRORMSG);
    }
}
