package orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This orders files by their type (last instance of .). If files have the same type they will be sorted by
 * name.
 */
public class Type extends Order {
    private static Type typeOrder = new Type();

    private ArrayList<File> returnFiles= new ArrayList<File>();

    private Type(){
    }

    public static Type instance(){ return typeOrder;}


    public ArrayList<File> orderFiles(ArrayList<File> files) {
        returnFiles.clear();
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
                } else if (stringDot1 == -1) { // only string2 has an extension, so string1 goes first
                    return -1;
                } else { // only string1 has an extension, so vice versa
                    return 1;
                }
            }
        });
        returnFiles.addAll(Arrays.asList(filesArray));
        return returnFiles;
    }
}
