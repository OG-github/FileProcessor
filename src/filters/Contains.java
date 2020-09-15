package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters all files that contain a certain name/string (case sensitive) within their name without the file path.
 */
public class Contains extends Filter {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Contains() {
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
    public static Collection<File> FilterFiles(Collection<File> files, String phrase) {
        Collection<File> returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            if (singleFile.getName().contains(phrase)) {
                returnFiles.add(singleFile);
            }
        }
        return returnFiles;
    }
}
