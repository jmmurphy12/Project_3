import java.io.EOFException;
import java.io.FileNotFoundException;
import org.junit.Test;
import student.TestCase;
import student.testingsupport.PrintStreamWithHistory;

public class HeapSortTest extends TestCase {

    /**
     * An artificial test to get initial coverage for the
     * main method. Delete or modify this test.
     * 
     * @throws Exception
     */
    @Test
    public void testMain() throws Exception {
        HeapSort dum = new HeapSort();
        assertNotNull(dum);
// HeapSort.main(new String[3]);
// assertEquals(systemOut().getHistory(), "");
// ---------------Test the parse----------------
        String goingout = "  17 14365   \r\n" + "\r\n"
            + "    7 22820 10261  5233 20085  8765 \r\n" + "\r\n"
            + "    1  3213  3086 20444  5921  1216  9052  5240 12044 23160 15136 30909 18020  5744 21088  4657 \r\n"
            + "24136 17409 27020 30093 \r\n" + "\r\n"
            + "    0 13264   568 13206  1191 16924  1785 13287  2394 12538  2973  2674  3588 14607  4183 23785 \r\n"
            + " 4802  3929  5383 30882  6005 12806  6570 27904  7198 31848  7790  9280  8386 24415  8976 15579 \r\n"
            + " 9558 17099 10136 27774 10764 28524 11370 30380 11964  9504 12579 22807 13169 22042 13765 29036 \r\n"
            + "14333 22993 14946 26109 15549 22916 16133  8486 16711  8421 17306 29845 17904 24085 18511  4107 \r\n"
            + "19089 21764 19746  1020 20356 16572 20936 22694 21517 32059 22104 26860 22713 22635 23333 28688 \r\n"
            + "23954   851 24536 12118 25152  6553 25759  9295 26344 29246 26987 26925 27608  2460 28180 31338 \r\n"
            + "28774  6278 29397 12728 \r\n" + "F";
        String[] file = new String[] { "sampleblock.bin" };
        HeapSort.main(file);
        PrintStreamWithHistory out = systemOut();
        assertEquals(goingout, out.getHistory());

    }

}
