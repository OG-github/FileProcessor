package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import filesprocessing.Type1Exception;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * The factory that decides what kind of filter to use. This factory will receive a command from
 * CommandFileProcessor and decide accordingly what kind of filtering to do. This factory preserves the
 * single-choice mechanism. This factory is a singleton.
 */
public class FilterFactory {

    /*----------------- CONSTANTS ------------------------------------------------------------------------------------*/

    /* The delimiter used in the command line */
    private static final String DELIMITER = "#";

    /* Numeric zero value to check correct values of sizing filters */
    private static final double ZERO_D = 0;

    /* Numeric value for the first word in the command line. The first word is the name of the filter */
    private static final int CMD_0 = 0;

    /* Numeric value for the second word in the command line. Either a numeric value for sizing or a YES or NO */
    private static final int CMD_1 = 1;

    /* Numeric value for the third word in the command line. Second numeric value for sizing */
    private static final int CMD_2 = 2;

    /* Filter for strictly greater than a certain size in kilobytes */
    private static final String GREATER_THAN = "greater_than";

    /* Filter for inclusive between certain sizes in kilobytes */
    private static final String BETWEEN = "between";

    /* Filter for strictly smaller than a certain size in kilobytes */
    private static final String SMALLER_THAN = "smaller_than";

    /* Filter for matching the file name excluding the path */
    private static final String FILE = "file";

    /* Filter for String contained in the file name excluding the path */
    private static final String CONTAINS = "contains";

    /* Filter for String prefix in the file name excluding the path */
    private static final String PREFIX = "prefix";

    /* Filter for String suffix in the file name excluding the path */
    private static final String SUFFIX = "suffix";

    /* Filter for files having writing permission (by current user) */
    private static final String WRITABLE = "writable";

    /* Filter for files having execution permission (by current user) */
    private static final String EXECUTABLE = "executable";

    /* Filter for hidden files */
    private static final String HIDDEN = "hidden";

    /* Filter for matching all files (not filtering at all) */
    private static final String ALL = "all";

    /* NOT condition for filters. (Filter opposite from condition) */
    private static final String NOT = "NOT";

    /* String negative condition for certain criteria */
    private static final String NO = "NO";

    /* String positive condition for certain criteria */
    private static final String YES = "YES";

    /* If Writable files are needed */
    private static final boolean WRITABLE_YES = true;

    /* If non-Writable files are needed */
    private static final boolean WRITABLE_NO = false;

    /* If Executable files are needed */
    private static final boolean EXECUTABLE_YES = true;

    /* If non-Executable files are needed */
    private static final boolean EXECUTABLE_NO = false;

    /* If Hidden files are needed */
    private static final boolean HIDDEN_YES = true;

    /* If non-Hidden files are needed */
    private static final boolean HIDDEN_NO = false;

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* The filtered returned files */
    private Collection<File> returnFiles = new ArrayList<File>();

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton instance */
    private static final FilterFactory factory = new FilterFactory();

    /* Singleton constructor */
    private FilterFactory() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of FilterFactory
     */
    public static final FilterFactory instance() {
        return factory;
    }

    /*----------------- Methods / Functions --------------------------------------------------------------------------*/

    /*----------------- Main Filter function ----------------------------------*/

    /**
     * This method will determine which kind of filter will be used and will return the filtered files
     * accordingly. The method receives a command and files. If an unrecognized filter was found the method
     * will throw Type1Exception to be handled in CommandFileProcessor. The method enters only valid
     * parameters to the filters it's calling, meaning checking the parameters from the command line is
     * done here.
     *
     * @param files   The files to be filtered.
     * @param command The relevant command line.
     * @return A Collection of filtered files.
     * @throws Type1Exception If an error in the command line was found. (will continue on default filter in
     *                        CommandFileProcessor).
     */
    public Collection<File> filter(Collection<File> files, String command) throws Type1Exception {
        this.returnFiles = new ArrayList<File>();
        String[] commandLine = command.split(DELIMITER); // split the cmd line into word by delimiter
        switch (commandLine[CMD_0]) {

            case GREATER_THAN:
                this.greaterThan(files, commandLine);
                break;

            case BETWEEN:
                this.between(files, commandLine);
                break;

            case SMALLER_THAN:
                this.smallerThan(files, commandLine);
                break;

            case FILE:
                String name = commandLine[CMD_1]; // name to search
                this.returnFiles = FileName.FilterFiles(files, name);
                break;

            case CONTAINS:
                String contain = commandLine[CMD_1]; // phrase to search
                this.returnFiles = Contains.FilterFiles(files, contain);
                break;

            case PREFIX:
                String pre = commandLine[CMD_1]; // prefix to search
                this.returnFiles = Prefix.FilterFiles(files, pre);
                break;

            case SUFFIX:
                String suff = commandLine[CMD_1]; // suffix to search
                this.returnFiles = Suffix.FilterFiles(files, suff);
                break;

            case WRITABLE:
                this.writable(files, commandLine);
                break;

            case EXECUTABLE:
                this.executable(files, commandLine);
                break;

            case HIDDEN:
                this.hidden(files, commandLine);
                break;

            case ALL:
                this.returnFiles = All.FilterFiles(files);
                break;

            default:
                throw new Type1Exception(); // throw error if not recognizable
        }

        Collection<String> cmdLinesArrList = Arrays.asList(commandLine);
        if (cmdLinesArrList.contains(NOT)) {
            this.returnFiles = Not.FilterFiles(files, this.returnFiles);
        }

        return new ArrayList<>(this.returnFiles); // shallow copy with new ArrayList
    }

    /*----------------- Helper Functions ----------------------------------*/

    /**
     * Helper to the Filter function to help filter greater than.
     *
     * @param files       The files to be filtered.
     * @param commandLine The relevant command line.
     * @throws Type1Exception If an error in the command line was found.
     */
    private void greaterThan(Collection<File> files, String[] commandLine) throws Type1Exception {
        double greater = Double.parseDouble(commandLine[CMD_1]); // parsing string to double
        if (greater < ZERO_D) {
            throw new Type1Exception();
        } // check valid input
        this.returnFiles = GreaterThan.FilterFiles(files, greater);
    }

    /**
     * Helper to the Filter function to help filter between.
     *
     * @param files       The files to be filtered.
     * @param commandLine The relevant command line.
     * @throws Type1Exception If an error in the command line was found.
     */
    private void between(Collection<File> files, String[] commandLine) throws Type1Exception {
        double lower = Double.parseDouble(commandLine[CMD_1]); // parsing string to double
        double upper = Double.parseDouble(commandLine[CMD_2]);
        if (lower < ZERO_D || upper < ZERO_D || lower > upper) { // correct values for between
            throw new Type1Exception();
        } // check valid input
        this.returnFiles = Between.FilterFiles(files, lower, upper);
    }

    /**
     * Helper to the Filter function to help filter smaller than.
     *
     * @param files       The files to be filtered.
     * @param commandLine The relevant command line.
     * @throws Type1Exception If an error in the command line was found.
     */
    private void smallerThan(Collection<File> files, String[] commandLine) throws Type1Exception {
        double smaller = Double.parseDouble(commandLine[CMD_1]); // parsing string to double
        if (smaller < ZERO_D) {
            throw new Type1Exception();
        }
        this.returnFiles = SmallerThan.FilterFiles(files, smaller);
    }

    /**
     * Helper to the Filter function to help filter writable.
     *
     * @param files       The files to be filtered.
     * @param commandLine The relevant command line.
     * @throws Type1Exception If an error in the command line was found.
     */
    private void writable(Collection<File> files, String[] commandLine) throws Type1Exception {
        String YesNoW = commandLine[CMD_1];
        if (YesNoW.equals(YES)) {
            this.returnFiles = Writable.FilterFiles(files, WRITABLE_YES); // writable
        }
        else if (YesNoW.equals(NO)) {
            this.returnFiles = Writable.FilterFiles(files, WRITABLE_NO); // non-writable
        }
        else { // invalid YES NO
            throw new Type1Exception();
        }
    }

    /**
     * Helper to the Filter function to help filter executable.
     *
     * @param files       The files to be filtered.
     * @param commandLine The relevant command line.
     * @throws Type1Exception If an error in the command line was found.
     */
    private void executable(Collection<File> files, String[] commandLine) throws Type1Exception {
        String YesNoE = commandLine[CMD_1];
        if (YesNoE.equals(YES)) {
            this.returnFiles = Executable.FilterFiles(files, EXECUTABLE_YES); // executable
        }
        else if (YesNoE.equals(NO)) {
            this.returnFiles = Executable.FilterFiles(files, EXECUTABLE_NO); // non-executable
        }
        else { // invalid YES NO
            throw new Type1Exception();
        }
    }

    /**
     * Helper to the Filter function to help filter hidden.
     *
     * @param files       The files to be filtered.
     * @param commandLine The relevant command line.
     * @throws Type1Exception If an error in the command line was found.
     */
    private void hidden(Collection<File> files, String[] commandLine) throws Type1Exception {
        String YesNoH = commandLine[CMD_1];
        if (YesNoH.equals(YES)) {
            this.returnFiles = Hidden.FilterFiles(files, HIDDEN_YES); // hidden
        }
        else if (YesNoH.equals(NO)) {
            this.returnFiles = Hidden.FilterFiles(files, HIDDEN_NO); // non-hidden
        }
        else {
            throw new Type1Exception();
        }
    }
}
