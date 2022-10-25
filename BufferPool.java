import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.NoSuchElementException;

/**
 * 
 * @author amado
 *
 */
public class BufferPool {
// private Buffer buff;
    private LinkedList<Buffer> list;
    private RandomAccessFile accessbp;
    static private int NUM;
// private int cachehits;
// private int cachemisses;

    /**
     * 
     * @param access
     * @throws IOException
     */
    public BufferPool(RandomAccessFile access, int numberofbuff)
        throws IOException {
        list = new LinkedList<Buffer>();
        accessbp = access;
        NUM = numberofbuff;
    }


    /**
     * 
     * @param file
     * @param offset
     * @return
     * @throws IOException
     */
    public Buffer getNewBufferAtOffset(RandomAccessFile file, int offset)
        throws IOException {
        Buffer newBuffer = new Buffer(file, offset);
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
        if (list.length() < NUM) {
            list.addtoFront(b);
        }
        else {
            flush();
            list.addtoFront(b);
        }
    }


    public Buffer getBufferAtOffset(int offset) {
        for (int i = 0; i < list.length(); i++) {
            if (list.getValue().getOffset() == offset) {
                return list.getValue();
            }
        }
        return null;
    }


    /**
     * @return
     * @throws IOException
     * @throws NoSuchElementException
     */
    public Record getBpRecord(int indx) throws IOException {
        int offsetValue = indx / 1024;

        if (null == getBufferAtOffset(offsetValue)) {
            insert(getNewBufferAtOffset(accessbp, offsetValue));
            return getBufferAtOffset(offsetValue).getRecord(indx);
        }
        else {
            list.LRU(getBufferAtOffset(offsetValue));
            return getBufferAtOffset(offsetValue).getRecord(indx);
        }
    }


    /**
     * 
     */
    public int getlength() {
        return list.length();

    }


    /**
     * @param file
     * @throws IOException
     * 
     */
    public void flush() throws IOException {
        Buffer flushedBuffer = list.removelast();
        flushedBuffer.flush();
    }


    /**
     * @throws IOException
     * 
     */
    public void flushall() throws IOException {
        for (int i = 0; i < list.length(); i++) {
            flush();
        }
    }


    /**
     * A setter method in the buffer pool for the
     * 
     * @param rec
     *            The record that is passed to the method
     * @throws IOException
     */
    public void setRecord(int index, Record record) throws IOException {
        int offsetValue = index / 1024;

        if (null == getBufferAtOffset(offsetValue)) {
            insert(getNewBufferAtOffset(accessbp, offsetValue));
            getBufferAtOffset(offsetValue).getRecord(index).setTo(record);
            list.LRU(getBufferAtOffset(offsetValue));
            getBufferAtOffset(offsetValue).isdirty();
        }
        else {
            getBufferAtOffset(offsetValue).getRecord(index).setTo(record);
            list.LRU(getBufferAtOffset(offsetValue));
            getBufferAtOffset(offsetValue).isdirty();
        }

    }

}
