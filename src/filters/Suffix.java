package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters in all the files that end with a certain string/name (String suffix).
 */
public class Suffix extends Filter {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Suffix() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that their name does not end with a certain String.
     *
     * @param files        Collection of files to filter.
     * @param suffixString String of the suffix the files should have.
     * @return Filtered Collection of files.
     */
    public static Collection<File> FilterFiles(Collection<File> files, String suffixString) {
        Collection<File> returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            if (singleFile.getName().endsWith(suffixString)) {
                returnFiles.add(singleFile);
            }
        }
        return returnFiles;
    }
}
