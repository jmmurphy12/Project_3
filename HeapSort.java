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
        int numberbuffer = Integer.valueOf(args[1]);
// String stats = args[2];
// ------------------------------------
        File file = new File(fileone);
        RandomAccessFile access = new RandomAccessFile(file, "rw");
        int numRecords = (int)(file.length() / 4);
        BufferPool bp = new BufferPool(access, numberbuffer);
        MaxHeap max = new MaxHeap(bp, numRecords, numberbuffer);
        // access.read();
        bp.flushall();
        for (int idex = 0; idex < numRecords / 1024; idex++) {
            // get first record out of block and print shit
        }
        access.close();
        

    }


    /**
     * This is the entry point of the application
     * 
     * @param args
     *            Command line arguments
     * @throws Exception
     */
    public static void sort(String args) throws Exception {
// "%5d %5d\t",\

    }
}
