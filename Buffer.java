import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * A buffer that store 4096 bytes of data
 * 
 * @author Amado Jain
 * @author Josh Murphy
 * @version 2022.10.30
 * 
 */
public class Buffer {
    private byte[] basicBuffer;
    private boolean dirtybit;
    private RandomAccessFile access;
    private int blockNum;
    private Record[] records;

    /**
     * The constructor for the buffer class
     * 
     * @param fileUsed
     *            the file where the data is coming from
     * @param offset
     *            the block number of the buffer
     * @throws IOException
     *             if a file error occurs
     */
    public Buffer(RandomAccessFile file, int offsetVal) throws IOException {
        basicBuffer = new byte[4096];
        access = file;
        blockNum = offsetVal;
        access.seek(offsetVal * 4096);
        access.read(basicBuffer);
        records = setRecordArray();
        dirtybit = false;
    }


    /**
     * Creates a record array from the byte array
     * 
     * @return
     *         a record array that represents the byte array
     */
    public Record[] setRecordArray() {
        return Record.toRecArray(basicBuffer);
    }


    /**
     * Checks to see if an index in the whole file is in the buffer
     * 
     * @param index
     *            the location in the file as a whole
     * @return
     *         true if location is in the buffer false if not
     */
    public boolean inBuffer(int index) {
        if (index / 1024 == blockNum) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Gets the record in the buffer
     * 
     * @param index
     *            the record location in the file as a whole
     * @return
     *         the record found in the buffer
     * @throws IOException
     *             if a file error occurs
     */
    public Record getRecord(int index) throws IOException {
        if (inBuffer(index)) {
            int bufferIndex = (index % 1024);
            return records[bufferIndex];
        }
        else {
            return null;
        }

    }


    /**
     * Writes data in the buffer back to the file
     * 
     * @throws IOException
     *             if a file error occurs
     */
    public void flush() throws IOException {
        if (isdirty()) {
            access.seek(blockNum * 4096);
            access.write(basicBuffer);
        }
        dirtybit = false;
    }


    /**
     * Checks if a buffer is dirty (has been modified)
     * 
     * @return
     *         true if it is dirty, false if not
     */
    public boolean isdirty() {
        return dirtybit;
    }


    /**
     * Sets a buffer to dirty
     */
    public void makeDirty() {
        dirtybit = true;
    }


    /**
     * Gets the byte array of the buffer
     * 
     * @return
     *         the byte array of the buffer
     */
    public byte[] toBuffarray() {
        return basicBuffer;
    }


    /**
     * Gets the block number of the buffer
     * 
     * @return
     *         the block number of the buffer
     */
    public int getBlockNum() {
        return blockNum;
    }

}
