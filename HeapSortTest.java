import org.junit.Test;
import student.TestCase;
import student.testingsupport.PrintStreamWithHistory;

public class HeapSortTest extends TestCase {
    private String[] file;
    private ByteFile bF;
    private String[] file2;
    private String[] file3;
    private String[] file4;

    /**
     * 
     */
    public void setUp() {
        bF = new ByteFile("sampleBlock1.bin", 1);
        file = new String[] { "sampleBlock1.bin", "1", "sampleoutput" };
        file2 = new String[] { "sampleBlock3.bin", "3", "sampleoutput2" };
        file3 = new String[] { "sampleBlock10.bin", "10", "sampleoutput3" };
        file4 = new String[] { "sampleBlock50.bin", "50", "sampleoutput4" };

    }


    /**
     * An artificial test to get initial coverage for the
     * main method. Delete or modify this test.
     * 
     * @throws Exception
     */
    @Test
    public void testMain() {
        String goingout = "17 14365";
        try {
            HeapSort.main(file);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PrintStreamWithHistory out = systemOut();
        assertEquals(goingout, out.getHistory().trim());

    }

    /**
     * Test the main
     * 
     * @throws Exception
     */
    @Test
    public void testmain3() throws Exception {
//        String goingout = "7 22820  10261  5233 20085  8765";
//        
//        try {
//            HeapSort.main(file2);
//        }
//        catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        PrintStreamWithHistory out = systemOut();
//        assertEquals(3, file2.length);
//        assertEquals(goingout, out.getHistory().trim());

    }
    
    
    /**
     * Test the main
     * 
     * @throws Exception
     */
    @Test
    public void testmain10() throws Exception {
//        String goingout = "7 22820  10261  5233 20085  8765";
//        try {
//            HeapSort.main(file3);
//        }
//        catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        PrintStreamWithHistory out = systemOut();
//        assertEquals(goingout, out.getHistory().trim());

    }
    
    
    public void test50Blocks() {
//        String goingout = "7 22820  10261  5233 20085  8765";
//        try {
//            HeapSort.main(file4);
//        }
//        catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        PrintStreamWithHistory out = systemOut();
//        assertEquals(goingout, out.getHistory().trim());
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