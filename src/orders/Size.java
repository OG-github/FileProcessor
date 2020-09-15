package orders;

import java.io.File;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Comparator;


/**
 * This order takes a list of files and sorts them by size. If 2 files have the same size they will be
 * sorted according to name (ascending order).
 */
public class Size extends Order {

    private static Size sizeOrder = new Size();

    private ArrayList<File> returnFiles = new ArrayList<File>();

    private Size() {
    }

    public static Size instance() {
        return sizeOrder;
    }

    /**
     * Orders the files by making a comparator. Will sort by size, if 2 files are the same size will sort
     * by name. When sorting by name, all upper case names will be prioritized (ascending order) and will
     * show before the lower case starting files.
     *
     * @param files The files to order
     * @return The files in a sorted manner
     */
    public static ArrayList<File> OrderFiles(ArrayList<File> files) {
        returnFiles.clear();
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
