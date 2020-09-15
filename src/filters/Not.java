package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Filters all the files that were not filtered before. This class will receive two Collections, one for the original
 * files and one for the filtered files and the returned files will be the original list of files without the
 * filtered files. This filter essentially filters the NOT condition of any filter.
 */
public class Not extends Filter {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Not() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * This filter essentially filters the NOT condition of any filter. The function will receive a Collection of the
     * original files, and a list of the files we want to exclude. The returned files will be the original file
     * without the list of files we wanted to exclude.
     *
     * @param allFiles      Collection of the original file list.
     * @param filteredFiles The List of files we want to exclude.
     * @return Filtered Collection of files.
     */
    public static Collection<File> FilterFiles(Collection<File> allFiles, Collection<File> filteredFiles) {
        Collection<File> returnFiles = new ArrayList<File>();
        for (File singleFile : allFiles) {
            if (!filteredFiles.contains(singleFile)) {
                returnFiles.add(singleFile);
            }
        }
        return returnFiles;
    }

}
