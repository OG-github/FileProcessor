package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters all files that are either hidden or not to the user in the system.
 */
public class Hidden extends Filter {

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* The files after they were filtered and ready to be returned.  */
    private Collection<File> returnFiles = new ArrayList<File>();

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton */
    private static final Hidden hiddenFilter = new Hidden();

    /* Singleton constructor */
    private Hidden() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of Hidden.
     */
    public static final Hidden instance() {
        return hiddenFilter;
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
    public Collection<File> filterFiles(Collection<File> files, boolean YesOrNo) {
        this.returnFiles = new ArrayList<File>();
        if (YesOrNo) {
            for (File singleFile : files) {
                if (singleFile.isHidden()) {
                    this.returnFiles.add(singleFile);
                }
            }
        }
        else {
            for (File singleFile : files) {
                if (!singleFile.isHidden()) {
                    this.returnFiles.add(singleFile);
                }
            }
        }
        return this.returnFiles;
    }
}
