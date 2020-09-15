package filters;


/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.Collection;

/**
 * A filter that filters no file and returns them all.
 */
public class All extends Filter {


    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private All() {
    }


    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from an arraylist of files no files and returns the arraylist of files unchanged.
     *
     * @param files Arraylist of files to filter.
     * @return Filtered Arraylist of files.
     */
    public static Collection<File> FilterFiles(Collection<File> files) {
        return files;
    }

}
