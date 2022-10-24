import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * @author amado
 *
 */
public class BufferTest extends student.TestCase {
    private byte[] bufferarray;
    private RandomAccessFile file;
    private Buffer buff;

    /**
     * @throws IOException
     * 
     */
    public void setup() throws IOException {
        bufferarray = new byte[12];
        // ------------the next 4 lines are junky----------
        String[] sfile = new String[] { "inputforbuffer" };
        File scanner = new File(sfile[0]);
        file = new RandomAccessFile(scanner, "");

    }


    /**
     * 
     * Test the constructor of buffer
     * 
     * @throws IOException
     */
    public void testConstructor() throws IOException {
        buff = new Buffer(file, 1);
    }


    /**
     * 
     * Test the get Record method
     * 
     * @throws IOException
     */
    public void testGetRecord() throws IOException {
        buff.getRecord(0);
    }


    /**
     * Test the set Record method
     */
    public void testSetRecord() {
        byte[] nbyte = new byte[5];
        Record n = new Record(nbyte);
        buff.setRecord(0, n);
    }


    /**
     * Test the flush method
     * 
     * @throws IOException
     * 
     */
    public void testflush() throws IOException {
        buff.flush();
    }


    /**
     * Test the isdirty method
     */
    public void testisdirty() {
        assertFalse(buff.isdirty());
    }


    /**
     * Test the to buffer array
     */
    public void testtoBuffarray() {
        assertEquals(buff.toBuffarray(), bufferarray);
    }


    /**
     * Test the getoffset method
     */
    public void testGetOffset() {
        assertEquals(buff.getOffset(), 1);
    }

}
