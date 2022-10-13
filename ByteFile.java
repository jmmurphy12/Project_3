/**
 * Basic handling of binary data files.
 * Uses a single byte array as a buffer for disc operations
 * Assumes that Records are composed of a short key, and
 * a short value.
 * 
 * @author CS Staff
 * @version 2022 Oct 10
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.Random;
import student.TestableRandom;

public class ByteFile {

    File theFile;
    int numBlocks;
    public long seed;

    public static int RECORDS_PER_BLOCK = 1024;
    public static int BYTES_PER_BLOCK = RECORDS_PER_BLOCK
        * Record.SIZE_IN_BYTES; // 4096

    public ByteFile(String filename, int numBlocks){
        theFile = new File(filename);
        this.numBlocks = numBlocks;
        this.seed = -1;
    }


    // checks if a file of records is sorted or not
    public boolean isSorted() throws IOException {
        byte[] basicBuffer = new byte[BYTES_PER_BLOCK];

        RandomAccessFile raf = new RandomAccessFile(theFile, "r");
        try {
            short currKey = Short.MIN_VALUE;

            for (int block = 0; block < numBlocks; block++) {
                raf.read(basicBuffer);
                // ^^^ the slow operation! Buffer helps here.

                Record[] recsInBlock = Record.toRecArray(basicBuffer);
                for (int rec = 0; rec < RECORDS_PER_BLOCK; rec++) {
                    short nextKey = recsInBlock[rec].getKey();
                    if (currKey > nextKey) {
                        raf.close();
                        return false;
                    }
                    else {
                        currKey = nextKey;
                    }
                }
            }
        }
        finally {
            raf.close();
        }
        return true;
    }
    
    
    // creates a file of randomly generated records
    public void writeRandomRecords() throws IOException {
        Random rng = new TestableRandom();
        if (seed != -1) {
            rng.setSeed(seed);
        }

        byte[] basicBuffer = new byte[BYTES_PER_BLOCK];
        ByteBuffer bb = ByteBuffer.wrap(basicBuffer);
        
        theFile.delete();
        // Deletes whatever content was there! This is important for if
        // you try using a file that already has lots of data, and you
        // don't reach the end of it. That old data would still be there
        // otherwise!

        RandomAccessFile raf = new RandomAccessFile(theFile, "rw");
        try {
            for (int block = 0; block < numBlocks; block++) {
                for (int rec = 0; rec < RECORDS_PER_BLOCK; rec++) {
                    short key = (short)rng.nextInt(Record.KEY_MAXIMUM);
                    short val = (short)rng.nextInt(Short.MAX_VALUE);
                    // puts the data in the basicBuffer...
                    bb.putShort(key);
                    bb.putShort(val);
                }
                raf.write(bb.array());
                // ^^^ the slow, costly operation!!! Good thing we use buffer
                bb.clear(); // resets the position of the buffer in array
            }
        }
        finally {
            raf.close();
        }
    }

}
