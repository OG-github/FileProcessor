package filesprocessing;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.*;
import java.util.*;

/**
 * Main method is here.
 * This processor receives a source directory with files, and a text file of commands on the processing,
 * and prints the name of the files according to the filtering and ordering specified in the command file.
 * This class uses 1 main function to process the files and the command file. It catches Type2Exceptions
 * that cannot be handled and prints respectively what is needed.
 */
public class DirectoryProcessor {

    /*----------------- CONSTANTS ------------------------------------------------------------------------------------*/

    /* Number of arguments Main takes. */
    private static final int NUM_OF_ARGS = 2;

    /* Number of the argument in args array representing the source directory. */
    private static final int NUM_SRC_DIR_ARG = 0;

    /* Number of the argument in args array representing the command file. */
    private static final int NUM_CMD_FILE_ARG = 1;

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* A text file with each line detailing how to process the source directory printing */
    private File commandFile;

    /* The source directory from which the files are filtered, ordered and printed */
    private File sourceDir;

    /* The files contained in the source directory */
    private Collection<File> sourceFiles;

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Builds a program to print all the files in the specific directory according to lines of text in the
     * command file
     *
     * @param source  The source directory from which the files will be taken and processed
     * @param command A text file containing instruction on processing (filtering, ordering)
     */
    public DirectoryProcessor(File source, File command) {
        this.sourceFiles = new ArrayList<File>();
        this.commandFile = command;
        this.sourceDir = source;
        File[] arrayFiles = sourceDir.listFiles();
        for (File file : arrayFiles) {
            this.sourceFiles.add(file);
        }
    }

    /*----------------- Methods / Functions --------------------------------------------------------------------------*/

    /**
     * A method that takes only files in the directory and not sub-directory files. (In the future can add
     * a twin method to take all the files if needs change).
     */
    public void GetOnlyLocalFiles() {
        Collection<File> notDirectories = new ArrayList<File>();
        for (File file : this.sourceFiles) {
            if (!file.isDirectory()) {
                notDirectories.add(file);
            }
        }
        this.sourceFiles = notDirectories;
    }

    /**
     * Process the source directory
     */
    public void processSource() {
        try { // process files
            CommandFileProcessor.instance().process(this.commandFile, this.sourceFiles);
        }
        catch (Type2ExceptionBadSectionName err) {
            err.printERRORMSG();
            return;
        }
        catch (Type2ExceptionIO err) {
            err.printERRORMSG();
            return;
        }
        catch (Type2Exception err) {
            return;
        }
    }

    /*----------------- Main -----------------------------------------------------------------------------------------*/

    /**
     * Runs the program using input of directory and command text file from user.
     *
     * @param args First is the source dir, second is the command file
     */
    public static void main(String[] args) {
        if (args.length > NUM_OF_ARGS) { // check not too many args
            Type2ExceptionBadArgs err = new Type2ExceptionBadArgs();
            err.printERRORMSG();
            return;
        }
        File source = new File(args[NUM_SRC_DIR_ARG]);
        File command = new File(args[NUM_CMD_FILE_ARG]);
        DirectoryProcessor dirProcessor = new DirectoryProcessor(source, command);
        dirProcessor.GetOnlyLocalFiles(); // get local files
        dirProcessor.processSource(); // filter and sort source dir
    }

}
