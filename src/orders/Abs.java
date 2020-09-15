package orders;

/*----------------- IMPORTS ------------------------------------------------------------------------------------------*/

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * Orders files by name in an ascending order (case-sensitive). This method overrides compare implementation of
 * comparator to sort the files respectively.
 */
public class Abs extends Order {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Abs() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     *
     * @param files Collection of files to filter.
     * @return Ordered Collection of files.
     */
    public static Collection<File> OrderFiles(Collection<File> files) {
        Collection<File> returnFiles = new ArrayList<File>();
        File[] filesArray = files.toArray(new File[files.size()]);
        Arrays.sort(filesArray, new Comparator<File>() {
            public int compare(File file1, File file2) { // define compare
                return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
            }
        });
        returnFiles.addAll(Arrays.asList(filesArray));
        return returnFiles;
    }
}
