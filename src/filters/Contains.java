package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters all files that contain a certain name/string (case sensitive) within their name without the file path.
 */
public class Contains extends Filter {

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* The files after they were filtered and ready to be returned.  */
    private Collection<File> returnFiles = new ArrayList<File>();

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton */
    private static final Contains containsFilter = new Contains();

    /* Singleton constructor */
    private Contains() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of Contains.
     */
    public static final Contains instance() {
        return containsFilter;
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that don't contain a certain String within their name
     * (excluding file path).
     *
     * @param files  Collection of files to filter.
     * @param phrase The String phrase to match within files' names.
     * @return Filtered Collection of files.
     */
    public Collection<File> filterFiles(Collection<File> files, String phrase) {
        this.returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            if (singleFile.getName().contains(phrase)) {
                this.returnFiles.add(singleFile);
            }
        }
        return this.returnFiles;
    }
}
