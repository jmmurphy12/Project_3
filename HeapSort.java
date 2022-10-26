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
        int numberbuffer = Integer.valueOf(args[1]);
        String stats = args[2];
        File file = new File(fileone);
        RandomAccessFile access = new RandomAccessFile(file, "rw");
        int numRecords = (int)(file.length() / 4);
        BufferPool bp = new BufferPool(access, numberbuffer);
        long start = System.currentTimeMillis();
        MaxHeap max = new MaxHeap(bp, numRecords, numberbuffer);
        access.read();
        long end = System.currentTimeMillis();

        for (int idex = 0; idex < numRecords / 1024; idex++) {
            access.seek(idex * 4096);
            int key = access.readUnsignedShort();
            access.seek((idex * 4096) + 2);
            int value = access.readUnsignedShort();
            System.out.printf("%5d %5d\t", key, value);
            if (idex != 0 && idex % 8 == 0) {
                System.out.println();
            }
        }
        access.close();
        bp.flushall();
    }
}
