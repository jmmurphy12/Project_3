import java.io.File;
import java.io.FileNotFoundException;
// import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class Buffer {
    private byte[] basicBuffer;
    private boolean dirtybit;
    public Record rec;
    public Buffer bpool;
    public LinkedList<Buffer> list;
    private RandomAccessFile accesson;
    private int index;

    /**
     * 
     * @param fileUsed
     * @param offset
     * @throws IOException
     */
    public Buffer(RandomAccessFile access, int startindex) throws IOException {
        accesson = access;
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
            accesson.seek(index);
            accesson.write(basicBuffer);
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
        return basicBuffer;
    }


    /**
     * 
     * @return
     */
    public int getOffset() {
        return index;
    }

}
