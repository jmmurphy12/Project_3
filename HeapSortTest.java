import java.io.EOFException;

import java.io.FileNotFoundException;
import org.junit.Test;
import student.TestCase;
import student.testingsupport.PrintStreamWithHistory;

public class HeapSortTest extends TestCase {
    private String[] file;
    private ByteFile bF;

    /**
     * 
     */
    public void setUp() {
        bF = new ByteFile("sampleBlock1.bin", 1);
        file = new String[] { "sampleBlock1.bin", "1", "sampleoutput" };

    }


    /**
     * An artificial test to get initial coverage for the
     * main method. Delete or modify this test.
     * 
     * @throws Exception
     */
    @Test
    public void testMain() {
        String goingout = " 17 14365 ";
        try {
            HeapSort.main(file);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PrintStreamWithHistory out = systemOut();
        assertEquals(goingout, out.getHistory());

    }


    /**
     * Test the main
     * 
     * @throws Exception
     */
    @Test
    public void testsimple() throws Exception {
        String[] file = new String[] { "sampleBlock1.bin", "1",
            "sampleoutput" };
        HeapSort.main(file);
        ByteFile bF = new ByteFile("sampleBlock1.bin", 1);
        assertTrue(bF.isSorted());

    }

}
