package orders;


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

    /* The singleton instance */
    private static OrderFactory factory = new OrderFactory();

    /* The ordered returned files */
    private Collection<File> returnFiles = new ArrayList<File>();

    /* singleton constructor */
    private OrderFactory() {
    }

    /* The delimiter used in the command line */
    private final String DELIMITER = "#";

    public static OrderFactory instance() {
        return factory;
    }

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
        returnFiles.clear();
        if (command == null || command.isEmpty() || command.equals("FILTER")) {
            returnFiles = Abs.instance().orderFiles(files);
            return returnFiles;
        }
        String[] commandLine = command.split(DELIMITER); // split by delimiter
        switch (commandLine[0]) {
            case "abs":
                returnFiles = Abs.instance().orderFiles(files);
                break;
            case "size":
                returnFiles = Size.instance().orderFiles(files);
                break;
            case "type":
                returnFiles = Type.instance().orderFiles(files);
                break;
            default:
                throw new Type1Exception();

        }
        if (Arrays.asList(commandLine).contains("REVERSE")) {
            this.returnFiles = Reverse.instance().orderFiles(this.returnFiles);
        }
        return this.returnFiles;
    }
}
