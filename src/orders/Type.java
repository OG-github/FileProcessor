package orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * This class orders files by their type (last instance of '.'). If files have the same type they will be sorted by
 * name.
 */
public class Type extends Order {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Type() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Orders the files by making a comparator. Will sort by type, if 2 files are the same type will sort
     * by name. When sorting by name, all upper case names will be prioritized (ascending order) and will
     * show before the lower case starting files.
     *
     * @param files Collection of files to order.
     * @return Ordered Collection of files.
     */
    public static Collection<File> OrderFiles(Collection<File> files) {
        Collection<File> returnFiles = new ArrayList<File>();
        File[] filesArray = files.toArray(new File[files.size()]);
        Arrays.sort(filesArray, new Comparator<File>() {
            public int compare(File file1, File file2) { // define compare
                String string1 = file1.getAbsolutePath();
                String string2 = file2.getAbsolutePath();
                final int stringDot1 = string1.lastIndexOf('.'); // get the last dot
                final int stringDot2 = string2.lastIndexOf('.');
                if ((stringDot1 == -1) == (stringDot2 == -1)) { // both or neither
                    string1 = string1.substring(stringDot1 + 1); // get the string after the last dot
                    string2 = string2.substring(stringDot2 + 1); // (1 index ahead from stringDot#)
                    return string1.compareTo(string2);
                }
                else if (stringDot1 == -1) { // only string2 has an extension, so string1 goes first
                    return -1;
                }
                else { // only string1 has an extension, so vice versa
                    return 1;
                }
            }
        });
        returnFiles.addAll(Arrays.asList(filesArray));
        return returnFiles;
    }
}
