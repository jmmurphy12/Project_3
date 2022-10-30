import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

// Remember to remove the package from all files!

// HONOR CODE GOES HERE

/**
 * This is where the main method will be (i.e. where the file being sorted is
 * called)
 * 
 * @author Amado Jain
 * @author Josh Murphy
 * @version 2022.10.30
 * 
 */
public class HeapSort {
    /**
     * This is the entry point of the application
     * 
     * @param args
     *            Command line arguments
     * @throws Exception
     *             if an error occurs
     */
    public static void main(String[] args) throws Exception {
        String fileone = args[0];
        int numberbuffer = Integer.valueOf(args[1]);
        String stats = args[2];
        File file = new File(fileone);
        RandomAccessFile access = new RandomAccessFile(file, "rw");
        int numRecords = (int)(file.length() / 4);
        BufferPool bp = new BufferPool(access, numberbuffer);

        MaxHeap max = new MaxHeap(bp, numRecords, numRecords);

        ByteFile bf = new ByteFile(fileone, numberbuffer);

        long start = System.currentTimeMillis();

        if (!bf.isSorted()) {
            max.Sort();
            bp.flushall();
        }

        long end = System.currentTimeMillis() - start;

        statfile(stats, bp.getCachehits(), bp.getCachemisses(), bp
            .getDiskReads(), bp.getDiskWrites(), end);

        access.read();

        for (int idex = 0; idex < access.length() / 4096; idex++) {
            access.seek(idex * 4096);
            byte[] recData = new byte[4];
            access.read(recData);
            Record newRec = new Record(recData);
            System.out.printf("%5d %5d\t", newRec.getKey(), newRec.getValue());
            if ((idex != 0) && ((idex + 1) % 8 == 0)) {
                System.out.println();
            }
        }
        access.close();
    }


    /**
     * Creates a statistic file
     * 
     * @param stats
     *            the name of the statistics file
     * @param cachehit
     *            the number of cache hits when performing sort
     * @param cachemiss
     *            the number of cache misses when performing sort
     * @param diskread
     *            the number of disk reads when performing sort
     * @param diskwrite
     *            the number of disk writes when performing sort
     * @param time
     *            the time it takes when performing sort
     * @throws IOException
     *             if file error occurs
     */
    public static void statfile(
        String stats,
        int cachehit,
        int cachemiss,
        int diskread,
        int diskwrite,
        long time)
        throws IOException {
        File statfile = new File(stats);
        if (!statfile.exists()) {
            if (!statfile.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter stat = new FileWriter(statfile, true);
        stat.write("------  STATS  ------\n");
        stat.write("File name: " + statfile + "\n");
        stat.write("Cache Hits: " + cachehit + "\n");
        stat.write("Cache Misses: " + cachemiss + "\n");
        stat.write("Disk Reads: " + diskread + "\n");
        stat.write("Disk Writes: " + diskwrite + "\n");
        stat.write("Time to sort: " + time + "\n");
        stat.close();
    }
}
