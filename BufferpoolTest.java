
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

    /**
     * 
     * Test the getBufferatoffset method
     */
    public void testgetBufferAtOffset() {

    }


    /**
     * test the insert method
     * 
     * @throws IOException
     */
    public void testinsert() throws IOException {
// file = new ByteFile("sample", 3);
// file.writeRandomRecords();
// access = new RandomAccessFile("sample", "rw");
// bpool = new BufferPool(access, 3);
// // Buffer buffer = new Buffer(access, 0);
// bpool.insert();
// assertEquals(3, bpool.getlength());
    }


    /**
     * test the get record method
     * 
     * @throws IOException
     */
    public void testgetRecord() throws IOException {
        file = new ByteFile("sample", 3);
        file.writeRandomRecords();
        access = new RandomAccessFile("sample", "rw");
        bpool = new BufferPool(access, 3);
        // --------------general test-----------------------------------
        byte[] bb = new byte[4096];
        access.read(bb);
        Record[] rec = Record.toRecArray(bb);
        System.out.print(rec[1].toString());
        //System.out.print(bpool.getRecord(1).toString());
        assertEquals(bpool.getRecord(1).toString(), rec[1].toString());
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
    public void testsetRecord() throws IOException {
//        file = new ByteFile("sampleBlock3.bin", 3);
//        file.writeRandomRecords();
//        access = new RandomAccessFile("sampleBlock3.bin", "rw");
//        bpool = new BufferPool(access, 3);
//        // --------------set record-----------------------------------
//        byte[] bb = new byte[4096];
//        access.read(bb);
//        Record record = new Record(5, 5);
//        bpool.setRecord(2, record);
//        assertEquals(bpool.getRecord(2).toString(), record.toString());
    }

}
