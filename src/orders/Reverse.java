package orders;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;


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
    public static Collection<File> OrderFiles(Collection<File> files) {
        Collection<File> returnFiles = new ArrayList<File>();
        ArrayList<File> arrFiles = new ArrayList<>(files); // arrlist from files
        for (int i = arrFiles.size() - 1; i >= 0; i--) {
            returnFiles.add(arrFiles.get(i));
        }
        return returnFiles;
    }
}
