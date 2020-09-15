package orders;

import java.io.File;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Comparator;


/**
 * This class takes a list of files and sorts them by size. If 2 files have the same size they will be
 * sorted according to name (ascending order).
 */
public class Size extends Order {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Size() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Orders the files by making a comparator. Will sort by size, if 2 files are the same size will sort
     * by name. When sorting by name, all upper case names will be prioritized (ascending order) and will
     * show before the lower case starting files.
     *
     * @param files Collection of files to order.
     * @return Ordered Collection of files.
     */
    public static ArrayList<File> OrderFiles(ArrayList<File> files) {
        ArrayList<File> returnFiles = new ArrayList<File>();
        File[] arrayFiles = new File[files.size()];
        arrayFiles = files.toArray(arrayFiles);
        Arrays.sort(arrayFiles, new Comparator<File>() {
            public int compare(File file1, File file2) { // override compare
                if (file1.length() < file2.length()) {
                    return -1;
                }
                else if (file1.length() > file2.length()) {
                    return 1;
                }
                else { // if same size
                    String string1 = file1.getAbsolutePath();
                    String string2 = file2.getAbsolutePath();
                    final int stringDot1 = string1.lastIndexOf("\\"); // get the last path
                    final int stringDot2 = string2.lastIndexOf("\\");
                    string1 = string1.substring(stringDot1 + 1); // get the string after the last path (name)
                    string2 = string2.substring(stringDot2 + 1);
                    boolean ifChar1Upper = Character.isUpperCase(string1.charAt(0));
                    boolean ifChar2Upper = Character.isUpperCase(string2.charAt(0));
                    if (ifChar1Upper && !ifChar2Upper) {
                        return 1;
                    }
                    else if (!ifChar1Upper && ifChar2Upper) {
                        return -1;
                    }
                    else {
                        return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
                    }
                }
            }
        });
        returnFiles.addAll(Arrays.asList(arrayFiles));
        return returnFiles;
    }
}
