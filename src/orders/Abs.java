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

    private static Abs absOrder = new Abs();

    private Collection<File> returnFiles = new ArrayList<File>();

    private Abs() {
    }

    public static Abs instance() {
        return absOrder;
    }

    public Collection<File> orderFiles(Collection<File> files) {
        returnFiles.clear();
        File[] filesArray = files.toArray(new File[files.size()]);
        Arrays.sort(filesArray, new Comparator<File>() {
            public int compare(File file1, File file2) { // define compare
                return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
            }
        });
        this.returnFiles.addAll(Arrays.asList(filesArray));
        return this.returnFiles;
    }
}
