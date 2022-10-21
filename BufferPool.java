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
    private String fileUsed;
    private LinkedList<Buffer> list;
    // private Record record;
    private RandomAccessFile accessone;
    private int num;
    private Buffer buff;

    /**
     * 
     * @param access
     * @throws IOException
     */
    public BufferPool(RandomAccessFile access, int numberofbuff)
        throws IOException {
        list = new LinkedList<Buffer>();
        accessone = access;
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
    public boolean inserthelper() throws IOException {
        Buffer champ = new Buffer(accessone, num);
        boolean flag = false;
        // check if the buffer is in the buffer pool
        if (list.getValue().equals(champ)) {
            return flag;
        }
        else if (this.max()) {
            // if it flush does it insert;
            flush();
        }
        else {
            list.LRU(champ);
            flag = true;

        }
        return flag;

    }

// /**
// *
// * @throws IOException
// *
// */
// public boolean insert() throws IOException {
// // false if it already in the buffer pool
// // True if its we had to insert it
// return false;
// }


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
            Record found = buff.getRecord(i);
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
        // I need to work on this
        if (list.getValue().isdirty()) {
            list.getValue().flush();
// inserthelper();
        }
        // write the byte back to the file if its dirty
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
    public void setRecord(int index, Record rec) {
        // I need to work on this.
        //
        if (rec != null) {
            int spotfound = this.getindexat(index);
            list.getValue().setRecord(spotfound, rec);
        }
        // when record is not there
        // dirty bit needs to be changed
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
