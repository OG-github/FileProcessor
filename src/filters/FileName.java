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

    /*----------------- Fields ---------------------------------------------------------------------------------------*/

    /* The files after they were filtered and ready to be returned.  */
    private Collection<File> returnFiles = new ArrayList<File>();

    /*----------------- Singleton ------------------------------------------------------------------------------------*/

    /* The singleton */
    private static final FileName fileFilter = new FileName();

    /* Singleton constructor */
    private FileName() {
    }

    /**
     * The only way to access the 1 instance of this class.
     *
     * @return The singleton of FileName.
     */
    public static final FileName instance() {
        return fileFilter;
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
    public Collection<File> filterFiles(Collection<File> files, String name) {
        this.returnFiles = new ArrayList<File>();
        for (File singleFile : files) {
            if (singleFile.getName().equals(name)) {
                this.returnFiles.add(singleFile);
            }
        }
        return this.returnFiles;
    }
}
