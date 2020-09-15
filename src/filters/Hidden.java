package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters all files that are either hidden or not to the user in the system.
 */
public class Hidden extends Filter {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Hidden() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that are either hidden from the user in the system or all
     * the non-hidden files, depending on the boolean argument this function receives.
     *
     * @param files   Collection of files to filter.
     * @param YesOrNo True (Yes) for returned hidden files; False (No) for returned non-hidden files.
     * @return Filtered Collection of files.
     */
    public static Collection<File> FilterFiles(Collection<File> files, boolean YesOrNo) {
        Collection<File> returnFiles = new ArrayList<File>();
        if (YesOrNo) {
            for (File singleFile : files) {
                if (singleFile.isHidden()) {
                    returnFiles.add(singleFile);
                }
            }
        }
        else {
            for (File singleFile : files) {
                if (!singleFile.isHidden()) {
                    returnFiles.add(singleFile);
                }
            }
        }
        return returnFiles;
    }
}
