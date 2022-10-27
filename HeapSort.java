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
    @SuppressWarnings({ "unused" })
    public static void main(String[] args) throws Exception {
        String fileone = args[0];
        int numberbuffer = Integer.valueOf(args[1]);
        String stats = args[2];
        File file = new File(fileone);
        RandomAccessFile access = new RandomAccessFile(file, "rw");
        int numRecords = (int)(file.length() / 4);
        BufferPool bp = new BufferPool(access, numberbuffer);
        long start = System.currentTimeMillis();
        MaxHeap max = new MaxHeap(bp, numRecords, numRecords);

        ByteFile bf = new ByteFile(fileone, numberbuffer);

        if (!bf.isSorted()) {
            max.Sort();
            bp.flushall();
        }

        access.read();
        long end = System.currentTimeMillis();

        for (int idex = 0; idex < numberbuffer; idex++) {
            access.seek(idex * 4096);
            byte[] recData = new byte[4];
            access.read(recData);
            Record newRec = new Record(recData);
// int key = access.readShort();
// //access.seek((idex * 4096) + 2);
// int value = access.readShort();
            System.out.printf("%5d %5d\t", newRec.getKey(), newRec.getValue());
        }
        access.close();
    }
}
