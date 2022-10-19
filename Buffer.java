import java.io.File;
import java.io.FileNotFoundException;
// import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Buffer {
    private byte[] basicBuffer;
    private boolean dirtybit;
    public Record rec;
    public Buffer bpool;
    public LinkedList<Buffer> list;
    private RandomAccessFile raf;
// private int startindex;
// private int endindex;
    /**
     * 
     * @param fileUsed
     * @param offset
     * @throws IOException
     */
    public Buffer(String fileUsed, int offset) throws IOException {
        File file = new File(fileUsed);
        raf = new RandomAccessFile(file, "rw");
        basicBuffer = new byte[4096];
        raf.read(basicBuffer, offset, 4096);
        raf.close();
        // -----------------------------------------
        dirtybit = false;
        // have to initialize the record
        // to make change to the dirty bits to update

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
        sindex = sindex * 4;
        byte[] byteon = new byte[4];
        for (int i = sindex; i < sindex + 4; i++) {
            basicBuffer[i] = byteon[i - sindex];
        }
        dirtybit = true;

    }


    /**
     * @param file
     * @throws IOException 
     * 
     */
    public void flush(String file) throws IOException {
        list.removelast();
        if (list.getValue().isdirty()) {
            raf.write(basicBuffer);;
        }
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

}
