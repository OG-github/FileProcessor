package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters all files that are either writable or not to the user in the system.
 */
public class Writable extends Filter {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Writable() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that are either writable by the user in the system or all
     * the non-writable files, depending on the boolean argument this function receives.
     *
     * @param files   Collection of files to filter.
     * @param YesOrNo True (Yes) for returned writable files; False (No) for returned non-writable files.
     * @return Filtered Collection of files.
     */
    public static Collection<File> FilterFiles(Collection<File> files, boolean YesOrNo) {
        Collection<File> returnFiles = new ArrayList<File>();
        if (YesOrNo) {
            for (File singleFile : files) {
                if (singleFile.canWrite()) {
                    returnFiles.add(singleFile);
                }
            }
        }
        else {
            for (File singleFile : files) {
                if (!singleFile.canWrite()) {
                    returnFiles.add(singleFile);
                }
            }
        }
        return returnFiles;
    }
}
