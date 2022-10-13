import java.io.File;

// Remember to remove the package from all files!

// HONOR CODE GOES HERE


/**
 * External HeapsSort starter kit.  
 * 
 * @author {your PID}
 */
public class HeapSort {

    /**
     * This is the entry point of the application
     * 
     * @param args
     *            Command line arguments
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        String file = args[0];
        BufferPool bp = new BufferPool(file);
    }
}
