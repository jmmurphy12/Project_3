import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.NoSuchElementException;
import student.TestCase;

/**
 * Tests the MaxHeap class
 * 
 * @author Amado Jain
 * @author Josh Murphy
 * @version 2022.10.30
 * 
 */
public class MaxHeapTest extends TestCase {
    private ImBufferPool fakeBuf;
    private BufferPool rBuff;
    private MaxHeap testFakeHeap;
    private ByteFile byteF;
    private RandomAccessFile d;
    private int fileLength;
    private MaxHeap testRealHeap;

    /**
     * The set up for the test class
     */
    public void setUp() throws NoSuchElementException, IOException {

        fakeBuf = new ImBufferPool(5);

        Record r1 = new Record(1, 1);
        Record r2 = new Record(2, 2);
        Record r3 = new Record(3, 3);
        Record r4 = new Record(4, 4);
        Record r5 = new Record(5, 5);

        fakeBuf.setRecord(1, r2);
        fakeBuf.setRecord(4, r5);
        fakeBuf.setRecord(2, r3);
        fakeBuf.setRecord(3, r4);
        fakeBuf.setRecord(0, r1);

        testFakeHeap = new MaxHeap(fakeBuf, 5, 5);

        byteF = new ByteFile("sample.bin", 10);
        byteF.writeRandomRecords();
        d = new RandomAccessFile("sample.bin", "rw");
        fileLength = (int)(d.length() / 4);
        rBuff = new BufferPool(d, 3);

        testRealHeap = new MaxHeap(rBuff, fileLength, fileLength);

    }


    /**
     * Tests the heap with the use of a fake buffer pool
     * 
     * @throws NoSuchElementException
     *             if an element doesn't exist
     * @throws IOException
     *             if an error occurs
     */
    public void testFakeHeap() throws NoSuchElementException, IOException {
        System.out.println(fakeBuf.toString());
        testFakeHeap.buildHeap();
        System.out.println(fakeBuf.toString());
        testFakeHeap.sort();
        System.out.println(fakeBuf.toString());
        MaxHeap testHeap = new MaxHeap(fakeBuf, 5, 5);
        System.out.println(fakeBuf.toString());
        testHeap.sort();
        System.out.println(fakeBuf.toString());

    }


    /**
     * Tests the heap with another fake buffer pool
     * 
     * @throws NoSuchElementException
     *             if an element doesn't exist
     * @throws IOException
     *             an error occurs
     */
    public void testFakeHeap2() throws NoSuchElementException, IOException {
        Record tr1 = new Record(8985, 26660);
        Record tr2 = new Record(21847, 25879);
        Record tr3 = new Record(24254, 26046);
        Record tr4 = new Record(24434, 27546);
        Record tr5 = new Record(19978, 14888);

        ImBufferPool fakeBuf1 = new ImBufferPool(5);

        fakeBuf1.setRecord(1, tr2);
        fakeBuf1.setRecord(4, tr5);
        fakeBuf1.setRecord(2, tr3);
        fakeBuf1.setRecord(3, tr4);
        fakeBuf1.setRecord(0, tr1);

        System.out.println(fakeBuf1.toString());
        testFakeHeap.buildHeap();
        System.out.println(fakeBuf1.toString());
        testFakeHeap.sort();
        System.out.println(fakeBuf1.toString());

    }


    /**
     * Tests the heap with a real buffer pool
     * 
     * @throws NoSuchElementException
     *             if an element doesn't exist
     * @throws IOException
     *             if an error occurs
     */
    public void testRealHeap() throws NoSuchElementException, IOException {
        testRealHeap.buildHeap();
        testRealHeap.sort();
        rBuff.flushall();
        assertTrue(byteF.isSorted());

    }

}
