package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters in all the files that end with a certain string/name (String suffix).
 */
public class Suffix extends Filter {

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* The files after they were filtered and ready to be returned.  */
    private Collection<File> returnFiles = new ArrayList<File>();

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton */
    private static final Suffix suffixFilter = new Suffix();

    /* Singleton constructor */
    private Suffix() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of Suffix.
     */
    public static final Suffix instance() {
        return suffixFilter;
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that their name does not end with a certain String.
     *
     * @param files        Collection of files to filter.
     * @param suffixString String of the suffix the files should have.
     * @return Filtered Collection of files.
     */
    public Collection<File> filterFiles(Collection<File> files, String suffixString) {
        this.returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            if (singleFile.getName().endsWith(suffixString)) {
                this.returnFiles.add(singleFile);
            }
        }
        return this.returnFiles;
    }
}
