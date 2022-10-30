import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.NoSuchElementException;

/**
 * The buffer pool class which holds all the buffers
 * 
 * @author Amado Jain
 * @author Josh Murphy
 * @version 2022.10.30
 * 
 */
public class BufferPool implements BpInterface {
    private LinkedList<Buffer> list;
    private RandomAccessFile accessbp;
    private int nM;
    private int cachehits;
    private int cachemisses;
    private int diskReads;
    private int diskWrites;

    /**
     * The constructor for the buffer pool class
     * 
     * @param access
     * @param numberofbuff
     * @throws IOException
     */
    public BufferPool(RandomAccessFile access, int numberofbuff)
        throws IOException {
        list = new LinkedList<Buffer>();
        accessbp = access;
        nM = numberofbuff;

        setCachehits(0);
        setCachemisses(0);
        setDiskReads(0);
        setDiskWrites(0);
    }


    /**
     * Creates a new buffer using the file
     * 
     * @param file
     *            the file where all the data is coming from
     * @param offset
     *            the block number of the buffer being created
     * @return
     *         the buffer created
     * @throws IOException
     *             if a file error occurs
     */
    public Buffer getNewBufferAtOffset(RandomAccessFile file, int offset)
        throws IOException {
        cachemisses++;
        Buffer newBuffer = new Buffer(file, offset);
        diskReads++;
        return newBuffer;
    }


    /**
     * 
     * if it doesn't have the record check if its max
     * else insert the buffer into the buffer pool
     * if the record equals the buffer
     * 
     * @throws IOException
     * 
     */
    public void insert(Buffer b) throws IOException {
        if (list.length() < nM) {
            list.addtoFront(b);
        }
        else {
            flush();
            list.addtoFront(b);
        }
    }


    /**
     * Gets a buffer already in the buffer pool with a certain block number
     * 
     * @param offset
     *            the block number of the buffer trying to be found
     * @return
     *         the buffer with that block number in the buffer pool
     */
    public Buffer getBufferAtOffset(int offset) {
        list.moveToStart();
        for (int i = 0; i < list.length(); i++) {
            if (list.getValue().getBlockNum() == offset) {
                return list.getValue();
            }
            list.next();
        }
        return null;
    }


    /**
     * Gets a record from the buffer pool
     *
     * @param indx
     *            the location of the record in the file
     * @return
     *         the record found
     * @throws IOException
     *             if a file error occurs
     */
    public Record getBpRecord(int indx) throws IOException {
        int offsetValue = indx / 1024;

        if (null == getBufferAtOffset(offsetValue)) {
            insert(getNewBufferAtOffset(accessbp, offsetValue));
        }
        else {
            cachehits++;
        }

        list.LRU(getBufferAtOffset(offsetValue));
        return getBufferAtOffset(offsetValue).getRecord(indx);
    }


    /**
     * Gets the length (size) of the buffer pool
     * 
     * @return
     *         the length (size) of the buffer pool
     */
    public int getlength() {
        return list.length();

    }


    /**
     * Writes buffer back to the file
     * 
     * @param file
     *            the file being written to
     * @throws IOException
     *             if a file error occurs
     * 
     */
    public void flush() throws IOException {
        diskWrites++;
        Buffer flushedBuffer = list.removelast();
        flushedBuffer.flush();
    }


    /**
     * Writes all buffers in the buffer pool back to the file
     * 
     * @throws IOException
     *             if a file error occurs
     */
    public void flushall() throws IOException {
        list.moveToStart();
        while (list.length() > 0) {
            flush();
        }
    }


    /**
     * A setter method in the buffer pool for the records
     * 
     * @param rec
     *            The record that is passed to the method
     * @throws IOException
     */
    public void setRecord(int index, Record record) throws IOException {
        int offsetValue = index / 1024;

        if (null == getBufferAtOffset(offsetValue)) {
            insert(getNewBufferAtOffset(accessbp, offsetValue));
        }
        else {
            cachehits++;
        }

        getBufferAtOffset(offsetValue).getRecord(index).setTo(record);
        list.LRU(getBufferAtOffset(offsetValue));
        getBufferAtOffset(offsetValue).makeDirty();

    }


    /**
     * Gets the amount of disk reads when performing the sort method
     * 
     * @return
     *         the number of disk reads when performing the sort method
     */
    public int getDiskReads() {
        return diskReads;
    }


    /**
     * Sets the amount of disk reads
     * 
     * @param diskReads
     *            the number of disk reads being set
     */
    public void setDiskReads(int diskReads) {
        this.diskReads = diskReads;
    }


    /**
     * Gets the amount of cache misses when performing the sort method
     * 
     * @return
     *         the number of cache misses when performing the sort method
     */
    public int getCachemisses() {
        return cachemisses;
    }


    /**
     * Sets the amount of cache misses
     * 
     * @param cachemisses
     *            the amount of cache misses being set
     */
    public void setCachemisses(int cachemisses) {
        this.cachemisses = cachemisses;
    }


    /**
     * Gets the amount of cache hits when performing the sort method
     * 
     * @return
     *         the number of cache hits when performing the sort method
     */
    public int getCachehits() {
        return cachehits;
    }


    /**
     * Sets the amount of cache hits
     * 
     * @param cachehits
     *            the amount of cache hits being set
     */
    public void setCachehits(int cachehits) {
        this.cachehits = cachehits;
    }


    /**
     * Gets the amount of disk writes when performing the sort method
     * 
     * @return
     *         the number of disk writes when performing the sort method
     */
    public int getDiskWrites() {
        return diskWrites;
    }


    /**
     * Sets the amount of disk writes
     * 
     * @param diskWrites
     *            the amount of disk writes being set
     */
    public void setDiskWrites(int diskWrites) {
        this.diskWrites = diskWrites;
    }

}
