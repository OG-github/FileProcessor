package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Filters all files that can be executed by the user or all the files that are non-executable by the user.
 */
public class Executable extends Filter {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Executable() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Filters out from a Collection of files all the files that either are executable by the user in the system or
     * not, depending on the boolean argument this function receives.
     *
     * @param files   Collection of files to filter.
     * @param YesOrNo True (Yes) for returned executable files; False (No) for returned non-executable files.
     * @return Filtered Collection of files.
     */
    public static Collection<File> FilterFiles(Collection<File> files, boolean YesOrNo) {
        Collection<File> returnFiles = new ArrayList<File>();
        if (YesOrNo) {
            for (File singleFile : files) {
                if (singleFile.canExecute()) {
                    returnFiles.add(singleFile);
                }
            }
        }
        else {
            for (File singleFile : files) {
                if (!singleFile.canExecute()) {
                    returnFiles.add(singleFile);
                }
            }
        }
        return returnFiles;
    }
}
