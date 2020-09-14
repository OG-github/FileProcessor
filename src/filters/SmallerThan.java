package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters in files that are strictly below a certain size in kilobytes.
 */
public class SmallerThan extends Filter {

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* The files after they were filtered and ready to be returned.  */
    private Collection<File> returnFiles = new ArrayList<File>();

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton */
    private static final SmallerThan smallerFilter = new SmallerThan();

    /* Singleton constructor */
    private SmallerThan() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of SmallerThan.
     */
    public static final SmallerThan instance() {
        return smallerFilter;
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that are not strictly below a certain size in kilobytes.
     *
     * @param files      Collection of files to filter.
     * @param sizeFilter Size in kilobytes.
     * @return Filtered Collection of files.
     */
    public Collection<File> filterFiles(Collection<File> files, double sizeFilter) {
        this.returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            if ((double) (singleFile.length() / Filter.KILO) < sizeFilter) { // turn the size to double kilobytes
                this.returnFiles.add(singleFile);
            }
        }
        return this.returnFiles;
    }
}
