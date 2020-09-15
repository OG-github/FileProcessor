package filesprocessing;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import filters.*;
import orders.Abs;
import orders.OrderFactory;

/**
 * This is the main handler of the processing. This class calls 2 other major classes to help,
 * filterFactory and orderFactory. This class reads from the command line and calls the singletons of the
 * factories to filter and order the files it sends. While doing so this class also organizes the order of
 * printing and checks for errors that might occur. If major issues occur Type2Exception will be sent,
 * otherwise the class will just print warnings on the screen.
 */
public class CommandFileProcessor {

    /*----------------- CONSTANTS ------------------------------------------------------------------------------------*/

    /* Represents the ORDER command in command file. */
    private static final String ORDER = "ORDER";

    /* Represents the FILTER command in command file. */
    private static final String FILTER = "FILTER";

    /* Represents the warning message printed in the results. */
    private static final String WARNING = "Warning";

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* The files returned from the factories (filtering and ordering) */
    private Collection<File> returnFiles = new ArrayList<File>();

    /* A list containing all the warnings and files in the right order to print */
    private Collection<String> returnPrint = new ArrayList<String>();

    /* A flag to tell us which section was handled last */
    private String lastSection = ORDER;

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton */
    private static CommandFileProcessor processor = new CommandFileProcessor();

    /* Singleton constructor */
    private CommandFileProcessor() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of CommandFileProcessor
     */
    public static CommandFileProcessor instance() {
        return processor;
    }


    /*----------------- Methods / Functions --------------------------------------------------------------------------*/

    /**
     * The main process code. Reads lines from the command line and calls the factories of orders and
     * filters accordingly. The method keeps track of what section it handled last, and by so throws a
     * Type2 if a bad filter was found (or if unable to read the command file). After calling the
     * factories, the method catches Type1 if a bad sub-section was found in one of them.
     *
     * @param commands The command file
     * @param files    The files that need processing
     * @throws Type2Exception An exception we can't handle. (i.e. bad filters, unable to read command file)
     */
    public void process(File commands, Collection<File> files) throws Type2Exception {
        try (BufferedReader commandReader = new BufferedReader(new FileReader(commands))) {
            // initialize the values
            String line;
            this.initializeValues();
            boolean orderNoSubSecFlag = false; // flag if sub-section order was missing
            int lineCounter = 0; // line count for the warnings
            while ((line = commandReader.readLine()) != null) { // while line is not empty
                lineCounter++;
                if (this.lastSection.equals(ORDER)) {
                    if (line.equals(FILTER) || orderNoSubSecFlag) { // if last section was was missing order
                        try {
                            if (!orderNoSubSecFlag) { // if the line was already FILTER then reader moved next
                                line = commandReader.readLine();
                                lineCounter++;
                            }
                            orderNoSubSecFlag = false; // reset the flag
                            this.returnFiles = FilterFactory.instance().filter(files, line);// filter(single-choice)
                            this.lastSection = FILTER;

                        }
                        catch (Type1Exception err) { // bad command line for filter section
                            filterCommandError(files, err, lineCounter);
                        }
                    }
                    else {
                        throw new Type2ExceptionBadSectionName(); // if bad filter was found
                    }
                }
                else { // if need to check ORDER section (i.e. last section we checked was FILTER)
                    if (line.equals(ORDER)) {
                        try {
                            line = commandReader.readLine();
                            lineCounter++;
                            if (line != null && line.equals(FILTER)) {
                                orderNoSubSecFlag = true; // raise the flag if no sub sec for order
                            }
                            this.returnFiles = OrderFactory.instance().orderFiles(this.returnFiles, line); // order
                            this.lastSection = ORDER;
                            for (File file : this.returnFiles) {
                                this.returnPrint.add(file.getName()); // add all the files needed printing
                            }
                            this.returnFiles.clear();
                        }
                        catch (Type1Exception err) { // bad command line for order section
                            orderCommandError(err, lineCounter);
                        }
                    }
                    else {
                        throw new Type2ExceptionBadSectionName(); // "ORDER" line missing
                    }
                }
            } // main while loop
            if (lastSection.equals(FILTER)) {
                throw new Type2ExceptionBadSectionName(); // missing ORDER (last line was empty)
            }
        }
        catch (IOException io) { // if cant open or read the command file
            throw new Type2ExceptionIO();
        }
        this.printReturnList(); // print the processing
    }

    /*----------------- Helpers---------------------------------------------------------------------------------------*/

    /* Initializes the values for process method */
    private void initializeValues() {
        this.returnPrint.clear();
        this.returnFiles.clear();
        this.lastSection = ORDER; // pointer to which section we handled last
    }

    /* prints the returnList (i.e the the printing of files and warnings according to order) */
    private void printReturnList() {
        for (String msg : this.returnPrint) { // print accordingly
            if (msg.contains(WARNING)) {
                System.err.println(msg);
            }
            else {
                System.out.println(msg);
            }
        }
    }

    /* Helper function to catch an error if the filter command in the filter file was bad or with bad arguments. */
    private void filterCommandError(Collection<File> files, Type1Exception err, int lineCounter) {
        this.returnFiles = files;
        this.lastSection = FILTER;
        this.returnPrint.add(err.StringError() + lineCounter); // add err msg
    }

    /* Helper function to catch an error if the order command in the filter file was bad or with bad arguments. */    private void orderCommandError(Type1Exception err, int lineCounter){
        this.returnFiles = Abs.OrderFiles(this.returnFiles);
        this.lastSection = ORDER;
        this.returnPrint.add(err.StringError() + lineCounter); // add to err return print
        for (File file : this.returnFiles) {
            this.returnPrint.add(file.getName());
        }
        this.returnFiles.clear();
    }
}
