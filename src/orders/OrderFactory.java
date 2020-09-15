package orders;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import filesprocessing.Type1Exception;
import filesprocessing.Type2Exception;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * The factory that decides what kind of ordering to use. This factory will receive a command from
 * CommandFileProcessor and decide accordingly what kind of ordering to do. This factory preserves the
 * single-choice mechanism. This factory is a singleton.
 */
public class OrderFactory {

    /*----------------- CONSTANTS ------------------------------------------------------------------------------------*/

    /* The singleton instance */
    private static OrderFactory factory = new OrderFactory();

    /* The ordered returned files */
    private Collection<File> returnFiles = new ArrayList<File>();

    /* String for the word FILTER that can be found in the command line we receive. */
    private static final String FILTER = "FILTER";

    /* String for the word REVERSE that can be found in the command line to reverse the order of the files. */
    private static final String REVERSE = "REVERSE";

    /* Order command for ordering files by name in an ascending order (case-sensitive). */
    private static final String ABS = "abs";

    /* Order command for ordering files by their type (first instace of '.'). */
    private static final String SIZE = "size";

    /* Order command for ordering files by their size (kilobytes). */
    private static final String TYPE = "type";

    /* The delimiter used in the command line */
    private final String DELIMITER = "#";

    /* Numeric value for the first word in the command line. The first word is the name of the filter */
    private static final int CMD_0 = 0;

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* singleton constructor */
    private OrderFactory() {
    }

    /* Singleton constructor */
    public static OrderFactory instance() {
        return factory;
    }

    /*----------------- Methods / Functions --------------------------------------------------------------------------*/

    /*----------------- Main Filter function ----------------------------------*/

    /**
     * This method will determine which kind of ordering will be used and will return the ordered files
     * accordingly. The method receives a command and files. If an unrecognized order was found the method
     * will throw Type1Exception to be handled in CommandFileProcessor. The method enters only valid
     * parameters to the order it's calling, meaning checking the parameters from the command line is
     * done here.
     *
     * @param files   The files to be ordered
     * @param command The relevant command line
     * @return A Collection of ordered files
     * @throws Type1Exception If an error in the command line was found. (will continue on default order in
     *                        CommandFileProcessor.
     */
    public Collection<File> orderFiles(Collection<File> files, String command) throws Type1Exception, Type2Exception {
        this.returnFiles = new ArrayList<File>();
        if (command == null || command.isEmpty() || command.equals(FILTER)) {
            this.returnFiles = Abs.OrderFiles(files);
            return this.returnFiles;
        }
        String[] commandLine = command.split(DELIMITER); // split by delimiter
        switch (commandLine[CMD_0]) {
            case ABS:
                this.returnFiles = Abs.OrderFiles(files);
                break;
            case SIZE:
                this.returnFiles = Size.OrderFiles(files);
                break;
            case TYPE:
                this.returnFiles = Type.OrderFiles(files);
                break;
            default:
                throw new Type1Exception();

        }
        if (Arrays.asList(commandLine).contains(REVERSE)) {
            this.returnFiles = Reverse.OrderFiles(this.returnFiles);
        }
        return this.returnFiles;
    }
}
