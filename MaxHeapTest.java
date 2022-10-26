import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.NoSuchElementException;
import java.util.Scanner;
import student.TestCase;

public class MaxHeapTest extends student.TestCase {
    private ImBufferPool fakeBuf;
    private BufferPool rBuff;
    private MaxHeap testFakeHeap;
    private ByteFile byteF;
    private RandomAccessFile d;
    private int fileLength;
    private MaxHeap testRealHeap;

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
        
        byteF = new ByteFile("sampleBlock1.bin", 10);
        byteF.writeRandomRecords();
        d = new RandomAccessFile("sampleBlock1.bin", "rw");
        fileLength = (int)(d.length() / 4);
        rBuff = new BufferPool(d, 3);
        
        testRealHeap = new MaxHeap(rBuff, fileLength, fileLength);

    }
    
    
    public void testFakeHeap() throws NoSuchElementException, IOException {
        System.out.println(fakeBuf.toString());
        testFakeHeap.buildHeap();
        System.out.println(fakeBuf.toString());
        testFakeHeap.Sort();
        System.out.println(fakeBuf.toString());
        
    }
    
    
    public void testRealHeap() throws NoSuchElementException, IOException {
        testRealHeap.buildHeap();
        testRealHeap.Sort();
        rBuff.flushall();
        assertTrue(byteF.isSorted());
    }

}
