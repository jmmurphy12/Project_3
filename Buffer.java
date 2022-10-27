import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * @author amado
 *
 */
public class Buffer {
    private byte[] basicBuffer;
    private boolean dirtybit;
    private RandomAccessFile access;
    private int blockNum;
    private Record[] records;

    /**
     * 
     * @param fileUsed
     * @param offset
     * @throws IOException
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
    

    public Record[] setRecordArray() {
        return Record.toRecArray(basicBuffer);
    }


    public boolean inBuffer(int index) {
        if (index / 1024 == blockNum) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * 
     * @return
     * @throws IOException
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
     * @param file
     * @throws IOException
     * 
     */
    public void flush() throws IOException {
        if (isdirty()) {
            access.seek(blockNum * 4096);
            access.write(basicBuffer);
        }
        dirtybit = false;
    }


    /**
     * 
     * @return
     */
    public boolean isdirty() {
        return dirtybit;
    }


    public void makeDirty() {
        dirtybit = true;
    }


    /**
     * 
     * @return
     */
    public byte[] toBuffarray() {
        return basicBuffer;
    }


    /**
     * 
     * @return
     */
    public int getBlockNum() {
        return blockNum;
    }

}
