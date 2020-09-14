package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters a scope of files sizes (inclusive) in kilobytes.
 */
public class Between extends Filter {

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* The files after they were filtered and ready to be returned.  */
    private Collection<File> returnFiles = new ArrayList<File>();

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton */
    private static final Between betweenFilter = new Between();

    /* Singleton constructor */
    private Between() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of Between.
     */
    public static final Between instance() {
        return betweenFilter;
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
    public Collection<File> filterFiles(Collection<File> files, double lower, double higher) {
        this.returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            double fileSize = (double) (singleFile.length() / Filter.KILO); // convert to kbytes
            if (fileSize >= lower && fileSize <= higher) {
                this.returnFiles.add(singleFile);
            }
        }
        return this.returnFiles;
    }
}
