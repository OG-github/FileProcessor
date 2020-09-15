package orders;

import java.io.File;
import java.util.ArrayList;


/**
 * This order takes the files and reverses their order.
 */
public class Reverse extends Order {
    private static Reverse REVERSEOrder = new Reverse();


    private Reverse() {
    }

    public static Reverse instance(){ return REVERSEOrder;}

    public static ArrayList<File> OrderFiles(ArrayList<File> files){
        ArrayList<File> returnFiles= new ArrayList<File>();
        for (int i = files.size() - 1; i >= 0; i--){
            returnFiles.add(files.get(i));
        }
        return returnFiles;
    }
}
