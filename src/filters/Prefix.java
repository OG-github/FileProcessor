package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters all the files that begin with a certain string/name (String prefix).
 */
public class Prefix extends Filter {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Prefix() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that their name does not start with a certain String.
     *
     * @param files        Collection of files to filter.
     * @param prefixString String of the prefix the files should have.
     * @return Filtered Collection of files.
     */
    public static Collection<File> FilterFiles(Collection<File> files, String prefixString) {
        Collection<File> returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            if (singleFile.getName().startsWith(prefixString)) {
                returnFiles.add(singleFile);
            }
        }
        return returnFiles;
    }
}
