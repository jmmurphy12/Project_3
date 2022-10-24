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
    private int index;

    /**
     * 
     * @param fileUsed
     * @param offset
     * @throws IOException
     */
    public Buffer(RandomAccessFile file, int startindex) throws IOException {
        basicBuffer = new byte[4096];
        access = file;
        index = startindex;
        access.seek(startindex);
        access.read(basicBuffer, 0, 4096);
        dirtybit = false;
    }


    /**
     * 
     * @return
     * @throws IOException
     */
    public Record getRecord(int index) throws IOException {
        index = index * 4;
        byte[] byteon = new byte[4];
        for (int i = index; i < index + 4; i++) {
            byteon[i - index] = basicBuffer[i];
        }
        return new Record(byteon);
    }


    /**
     * 
     */
    public void setRecord(int sindex, Record rectwo) {
        for (int i = index; i < index + 4; i++) {
            byte[] BytesSet = rectwo.getBytes();
            basicBuffer[(sindex * 4) + i] = BytesSet[i];
        }

        dirtybit = true;
    }


    /**
     * @param file
     * @throws IOException
     * 
     */
    public void flush() throws IOException {
        if (isdirty()) {
            access.seek(index);
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


    /**
     * 
     * @return
     */
    public byte[] toBuffarray() {
        return this.basicBuffer;
    }


    /**
     * 
     * @return
     */
    public int getOffset() {
        return index;
    }

}
