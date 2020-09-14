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
public class NOT extends Filter {

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* The files after they were filtered and ready to be returned.  */
    private Collection<File> returnFiles = new ArrayList<File>();

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton */
    private static final NOT NOTFilter = new NOT();

    /* Singleton constructor */
    private NOT() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of NOT.
     */
    public static final NOT instance() {
        return NOTFilter;
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
    public Collection<File> filterFiles(Collection<File> allFiles, Collection<File> filteredFiles) {
        this.returnFiles = new ArrayList<File>();
        for (File singleFile : allFiles) {
            if (!filteredFiles.contains(singleFile)) {
                this.returnFiles.add(singleFile);
            }
        }
        return this.returnFiles;
    }

}
