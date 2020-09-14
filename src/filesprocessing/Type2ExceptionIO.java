package filesprocessing;

/**
 * A problem accessing the command file occurred. This file is unusable for the program.
 */
public class Type2ExceptionIO extends Type2Exception {
    private final String ERRORMSG = "ERROR: <Problem accessing the command file> \n";

    public Type2ExceptionIO() {
    }

    public void printERRORMSG() {
        System.err.println(ERRORMSG);
    }
}
