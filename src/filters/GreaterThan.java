package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters in files that are strictly above a certain size in kilobytes.
 */
public class GreaterThan extends Filter {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private GreaterThan() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that are not strictly above a certain size in kilobytes.
     *
     * @param files      Collection of files to filter.
     * @param sizeFilter Size in kilobytes.
     * @return Filtered Collection of files.
     */
    public static Collection<File> FilterFiles(Collection<File> files, double sizeFilter) {
        Collection<File> returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            if ((double) (singleFile.length() / Filter.KILO) > sizeFilter) { // turn the size to double kilobytes
                returnFiles.add(singleFile);
            }
        }
        return returnFiles;
    }
}
