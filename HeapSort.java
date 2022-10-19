import java.io.File;
import java.io.RandomAccessFile;

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
    @SuppressWarnings({ "unused", "rawtypes", "unchecked" })
    public static void main(String[] args) throws Exception {
        String fileone = args[0];
        // -------------------------------------
// String numberbuffer = args[1];
// String stats = args[2];
// ------------------------------------
        File file = new File(fileone);
        RandomAccessFile access = new RandomAccessFile(file, "rw");
        try {
            access.read();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        BufferPool bp = new BufferPool(fileone);
        MaxHeap max = new MaxHeap(args, 0, 0);
        access.close();

    }
}
