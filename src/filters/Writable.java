package filters;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Filters all files that are either writable or not to the user in the system.
 */
public class Writable extends Filter {

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* The files after they were filtered and ready to be returned.  */
    private Collection<File> returnFiles = new ArrayList<File>();

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton */
    private static final Writable writableFilter = new Writable();

    /* Singleton constructor */
    private Writable() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of Hidden.
     */
    public static final Writable instance() {
        return writableFilter;
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
    public Collection<File> filterFiles(Collection<File> files, boolean YesOrNo) {
        this.returnFiles = new ArrayList<File>();
        if (YesOrNo) {
            for (File singleFile : files) {
                if (singleFile.canWrite()) {
                    this.returnFiles.add(singleFile);
                }
            }
        }
        else {
            for (File singleFile : files) {
                if (!singleFile.canWrite()) {
                    this.returnFiles.add(singleFile);
                }
            }
        }
        return this.returnFiles;
    }
}
