
import java.io.IOException;
import java.io.RandomAccessFile;
import student.TestCase;

/**
 * 
 * @author amado
 *
 */
public class BufferpoolTest extends TestCase {
    private ByteFile file;
    private RandomAccessFile access;
    private BufferPool bpool;

    public void setUp() throws IOException {
        file = new ByteFile("sample", 3);
        file.writeRandomRecords();
        access = new RandomAccessFile("sample", "rw");
        bpool = new BufferPool(access, 3);
    }


    /**
     * 
     * Test the getBufferatoffset method
     * 
     * @throws IOException
     */
    public void testGetRecords() throws IOException {
        byte[] stuff = new byte[12288];
        access.seek(0);
        access.read(stuff);
        Record[] records = Record.toRecArray(stuff);

        assertEquals(records[12].toString(), bpool.getBpRecord(12).toString());
        assertEquals(records[12].toString(), bpool.getBpRecord(12).toString());
        assertEquals(records[3000].toString(), bpool.getBpRecord(3000)
            .toString());

    }


    /**
     * test the insert method
     * 
     * @throws IOException
     */
    public void testinsert() throws IOException {
        Buffer buffer = new Buffer(access, 0);
        bpool.insert(buffer);
        assertEquals(1, bpool.getlength());
        Buffer buffer1 = new Buffer(access, 1);
        bpool.insert(buffer1);
        assertEquals(2, bpool.getlength());
        Buffer buffer2 = new Buffer(access, 2);
        bpool.insert(buffer2);
        assertEquals(3, bpool.getlength());
        Buffer buffer3 = new Buffer(access, 3);
        bpool.insert(buffer3);
        assertEquals(3, bpool.getlength());
    }


    /**
     * test the get record method
     * 
     * @throws IOException
     */
    public void testgetRecord() throws IOException {
        byte[] bb = new byte[4096];
        access.read(bb);
        Record[] rec = Record.toRecArray(bb);
        // System.out.print(rec[1].toString());
        // System.out.print(bpool.getRecord(1).toString());
        assertEquals(bpool.getBpRecord(1).toString(), rec[1].toString());
        access.close();
        // ---------------test when full--------------------------------

    }


    /**
     * test the flush method
     * 
     */
    public void testflush() {
        
    }


    /**
     * Test the setRecord method
     * 
     * @throws IOException
     */
    public void testSetRecord() throws IOException {
        byte[] bb = new byte[4096];
        access.read(bb);
        Record record = new Record(5, 5);
        bpool.setRecord(2, record);
        assertEquals(bpool.getBpRecord(2).toString(), record.toString());
    }

}
