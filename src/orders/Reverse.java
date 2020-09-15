package orders;

import java.io.File;
import java.util.ArrayList;


/**
 * This class takes files and reverses their order.
 */
public class Reverse extends Order {

    /*----------------- Constructors ---------------------------------------------------------------------------------*/

    /**
     * Generic constructor.
     */
    private Reverse() {
    }

    /*----------------- Main Method ----------------------------------------------------------------------------------*/

    /**
     * Orders files in reverse from their original Order.
     *
     * @param files Collection of files to order.
     * @return Ordered Collection of files.
     */
    public static ArrayList<File> OrderFiles(ArrayList<File> files) {
        ArrayList<File> returnFiles = new ArrayList<File>();
        for (int i = files.size() - 1; i >= 0; i--) {
            returnFiles.add(files.get(i));
        }
        return returnFiles;
    }
}
