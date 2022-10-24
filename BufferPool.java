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
    private int num;
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
        num = numberofbuff;
    }


    /**
     * 
     * @param file
     * @param offset
     * @return
     * @throws IOException
     */
    public Buffer getBufferAtOffset(RandomAccessFile file, int offset)
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
    public boolean insert() throws IOException {
        Buffer champ = new Buffer(accessbp, num);
        boolean flag = false;
        // check if the buffer is in the buffer pool
        if (list.getValue().equals(champ)) {
            return flag;
        }
        else if (this.max()) {
            // what do we do when its a max
            flush();
            num--;
        }
        else {
            list.insert(champ);
            num++;
            flag = true;

        }
        return flag;

    }


    /**
     * 
     * @return
     */
    public boolean max() {
        return list.length() == num;
    }


    /**
     * @return
     * @throws IOException
     * @throws NoSuchElementException
     */
    public int getindexat(int index) {
        for (int i = 0; i < list.length(); i++) {
            int offset = list.getValue().getOffset();
            if (index >= offset && index < offset + 1024) {
                return i;
            }
            list.getNext();
        }
        return -1;
    }


    /**
     * @return
     * @throws IOException
     * @throws NoSuchElementException
     */
    public Record getRecord(int indx) throws IOException {
        int i = getindexat(indx);
        if (i != -1) {
            Record found = list.getValue().getRecord(i);
            return found;
        }

        return null;

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
        if (list.getValue().isdirty()) {
            list.getValue().flush();
        }
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
     */
    public void setRecord(int sindex, Record record) {
        if (record != null) {
            int spotfound = this.getindexat(sindex);
            list.getValue().setRecord(spotfound, record);
        }
    }


    /**
     * 
     * @param pos1
     * @param pos2
     * @throws IOException
     * @throws NoSuchElementException
     */
    public void swap(int pos1, int pos2)
        throws NoSuchElementException,
        IOException {
        Record Temp = this.getRecord(pos1);
        this.setRecord(pos1, Temp);
        this.setRecord(pos2, Temp);

    }

}
