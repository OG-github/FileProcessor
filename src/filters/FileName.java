package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters all files that have the same exact name (case sensitive) as a given String (file path not included, but
 * file ending is).
 */
public class FileName extends Filter {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private FileName() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that don't match a certain String name (case sensitive)
     * excluding the file path.
     *
     * @param files Collection of files to filter.
     * @param name  String name to match the files' names.
     * @return Filtered Collection of files.
     */
    public static Collection<File> FilterFiles(Collection<File> files, String name) {
        Collection<File> returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            if (singleFile.getName().equals(name)) {
                returnFiles.add(singleFile);
            }
        }
        return returnFiles;
    }
}
