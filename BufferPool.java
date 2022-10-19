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
    private Record record;
    private RandomAccessFile access;
    /**
     * 
     * @param filename
     * @throws FileNotFoundException
     */
    public BufferPool(String filename) throws FileNotFoundException {
        fileUsed = filename;
        list = new LinkedList<Buffer>();
        access = new RandomAccessFile(filename, "rw");
    }

    /**
     * 
     * @param file
     * @param offset
     * @return
     * @throws IOException
     */
    public Buffer getBufferAtOffset(String file, int offset)
        throws IOException {
        Buffer newBuffer = new Buffer(file, offset);
        return newBuffer;
    }


    /**
     * 
     * @throws IOException
     * 
     */
    public Buffer insert(Buffer buff) throws IOException {
        // need to work on this
        // check if the bpool is full
        if (this.max()) {
            // do flush if it is full
            flush(fileUsed);
        }
        else {
            // insert to buffer to the bpool
            list.insert(buff);
        }
        return null;

    }


    /**
     * 
     * @return
     */
    public boolean max() {
        return list.length() == 20;
    }


    /**
     * @return
     * @throws IOException
     * @throws NoSuchElementException
     */
    public Record getRecord(int index)
        throws NoSuchElementException,
        IOException {
        list.moveToPos(index);
        return list.getValue().getRecord(index);
        // this just calling the get record from the buffer class since the
        // record value is inside the individual buffer array
    }


    /**
     * @param file
     * @throws IOException
     * 
     */
    public void flush(String file) throws IOException {
        list.removelast();
        if (list.getValue().isdirty()) {
            access.writeBytes(file);
        }
        // write the byte back to the file if its dirty
    }


    /**
     * A setter method in the buffer pool for the
     * 
     * @param rec
     *            The record that is passed to the method
     */
    public void setRecord(int index, Record rec) {
        list.moveToPos(index);
        list.getValue().setRecord(index, rec);
        // this just calling the set record from the buffer class since the
        // record value is inside the individual buffer array
    }
}
