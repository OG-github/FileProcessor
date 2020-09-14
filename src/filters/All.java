package filters;


/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.Collection;

/**
 * A filter that filters no file and returns them all.
 */
public class All extends Filter {


    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton */
    private static final All allFilter = new All();

    /* Singleton constructor */
    private All() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of All.
     */
    public static final All instance() {
        return allFilter;
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from an arraylist of files no files and returns the arraylist of files unchanged.
     *
     * @param files Arraylist of files to filter.
     * @return Filtered Arraylist of files.
     */
    public Collection<File> filterFiles(Collection<File> files) {
        return files;
    }

}
