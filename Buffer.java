import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Buffer {
    private byte[] basicBuffer;
    
    public Buffer(String fileUsed, int offset) throws IOException {
        File file = new File(fileUsed);
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        basicBuffer = new byte[4096];
        
        raf.read(basicBuffer, offset, 4096);
        raf.close();
    }
}
