package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters a scope of files sizes (inclusive) in kilobytes.
 */
public class Between extends Filter {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Between() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that their file size in kilobytes is not within the
     * inclusive range that is given between two doubles.
     *
     * @param files  Collection of files to filter.
     * @param lower  Lower inclusive limit for file size in kilobytes.
     * @param higher Upper inclusive limit for file size in kilobytes.
     * @return Filtered Collection of files.
     */
    public static Collection<File> FilterFiles(Collection<File> files, double lower, double higher) {
        Collection<File> returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            double fileSize = (double) (singleFile.length() / Filter.KILO); // convert to kbytes
            if (fileSize >= lower && fileSize <= higher) {
                returnFiles.add(singleFile);
            }
        }
        return returnFiles;
    }
}
