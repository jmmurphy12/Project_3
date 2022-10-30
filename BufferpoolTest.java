
import java.io.IOException;
import java.io.RandomAccessFile;
import student.TestCase;

/**
 * Tests the BufferPool class
 * 
 * @author Amado Jain
 * @author Josh Murphy
 * @version 2022.10.30
 * 
 */
public class BufferpoolTest extends TestCase {
    private ByteFile file;
    private RandomAccessFile access;
    private BufferPool bpool;

    /**
     * The set up for the test class
     */
    public void setUp() throws IOException {
        file = new ByteFile("sampleblock1", 3);
        file.writeRandomRecords();
        access = new RandomAccessFile("sampleblock1.bin", "rw");
        bpool = new BufferPool(access, 3);
    }


    /**
     * Tests if input into the file is correct
     * 
     * @throws IOException
     *             if a file error occurs
     */
    public void testCorrectInput() throws IOException {
// System.out.println(bpool.getNewBufferAtOffset(access, 0).getRecord(0));
// System.out.println(bpool.getNewBufferAtOffset(access, 0).getRecord(1));
// System.out.println(bpool.getNewBufferAtOffset(access, 0).getRecord(2));
// System.out.println(bpool.getNewBufferAtOffset(access, 0).getRecord(3));
// System.out.println(bpool.getNewBufferAtOffset(access, 0).getRecord(4));
    }


    /**
     * Test the getBufferatoffset method
     * 
     * @throws IOException
     *             if a file error occurs
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
     * Tests if the GetBufferPool method behaves properly
     * 
     * @throws IOException
     *             if a file error occurs
     */
    public void testGetBufferPool() throws IOException {
        assertNull(bpool.getBufferAtOffset(0));
        assertNull(bpool.getBufferAtOffset(1));
        bpool.insert(bpool.getNewBufferAtOffset(access, 0));
        assertFalse(null == bpool.getBufferAtOffset(0));
        bpool.insert(bpool.getNewBufferAtOffset(access, 4));
        bpool.insert(bpool.getNewBufferAtOffset(access, 7));

        assertFalse(null == bpool.getBufferAtOffset(4));
        assertFalse(null == bpool.getBufferAtOffset(7));
    }


    /**
     * Tests if the GetBpRecord behaves properly
     * 
     * @throws IOException
     *             if a file error occurs
     */
    public void testGetBpRecord() throws IOException {
        // assertEquals("Record: (8985, 26660)",
        // bpool.getBpRecord(0).toString());
        // assertEquals("Record: (21847, 25879)",
        // bpool.getBpRecord(1).toString());
        // assertEquals("Record: (24254, 26046)",
        // bpool.getBpRecord(2).toString());
        // assertEquals("Record: (8985, 26660)",
        // bpool.getBpRecord(0).toString());
    }


    /**
     * test the insert method
     * 
     * @throws IOException
     *             if a file error occurs
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
     *             if a file error occurs
     */
    public void testgetRecord() throws IOException {
        byte[] bb = new byte[4096];
        access.read(bb);
        Record[] rec = Record.toRecArray(bb);
        // System.out.print(rec[1].toString());
        // System.out.print(bpool.getRecord(1).toString());
        assertEquals(bpool.getBpRecord(1).toString(), rec[1].toString());
        // ---------------test when full--------------------------------

    }


    /**
     * Test the setRecord method
     * 
     * @throws IOException
     *             if a file error occurs
     */
    public void testSetRecord() throws IOException {
        byte[] bb = new byte[4096];
        access.read(bb);
        Record record = new Record(5, 5);
        System.out.println(bpool.getBpRecord(2).toString());
        bpool.setRecord(2, record);
        System.out.println(bpool.getBpRecord(2).toString());
        byte[] b = bpool.getBufferAtOffset(0).toBuffarray();
        System.out.println(b[10]);
        assertEquals(bpool.getBpRecord(2).toString(), record.toString());

        Record record1 = new Record(70, 35);
        bpool.setRecord(2000, record1);
        assertEquals(bpool.getBpRecord(2000).toString(), record1.toString());

        Record record2 = new Record(74, 38);
        bpool.setRecord(2000, record2);
        assertEquals(bpool.getBpRecord(2000).toString(), record2.toString());
    }


    /**
     * Tests if the flushAll method behaves properly
     * 
     * @throws IOException
     *             if a file error occurs
     */
    public void testFlushAll() throws IOException {
        Buffer buffer = new Buffer(access, 0);
        bpool.insert(buffer);

        Buffer buffer1 = new Buffer(access, 1);
        bpool.insert(buffer1);

        Buffer buffer2 = new Buffer(access, 2);
        bpool.insert(buffer2);

        Buffer buffer3 = new Buffer(access, 3);
        bpool.insert(buffer3);

        bpool.flushall();

        assertEquals(0, bpool.getlength());
    }

}
