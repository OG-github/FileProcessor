package filesprocessing;

/**
 * A bad filter format was found in the command file that did not match the criteria.
 */
public class Type2ExceptionBadSectionName extends Type2Exception {
    private final String ERRORMSG = "ERROR: <Bad section name found, please check command line> \n";

    public Type2ExceptionBadSectionName() {
    }

    public void printERRORMSG() {
        System.err.println(ERRORMSG);
    }
}
