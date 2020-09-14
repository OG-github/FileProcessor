package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters all the files that begin with a certain string/name (String prefix).
 */
public class Prefix extends Filter {

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* The files after they were filtered and ready to be returned.  */
    private Collection<File> returnFiles = new ArrayList<File>();

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton */
    private static final Prefix prefixFilter = new Prefix();

    /* Singleton constructor */
    private Prefix() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of Prefix.
     */
    public static final Prefix instance() {
        return prefixFilter;
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that their name does not start with a certain String.
     *
     * @param files        Collection of files to filter.
     * @param prefixString String of the prefix the files should have.
     * @return Filtered Collection of files.
     */
    public Collection<File> filterFiles(Collection<File> files, String prefixString) {
        this.returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            if (singleFile.getName().startsWith(prefixString)) {
                this.returnFiles.add(singleFile);
            }
        }
        return this.returnFiles;
    }
}
