import java.io.File;
import java.io.IOException;

public class BufferPool {
    private String fileUsed;
    private LinkedList<Buffer> list;

    public BufferPool(String filename) {
        fileUsed = filename;
        list = new LinkedList<Buffer>();
    }


    public Buffer getBufferAtOffset(String file, int offset)
        throws IOException {
        Buffer newBuffer = new Buffer(file, offset);
        return newBuffer;
    }
}
