
import java.nio.ByteBuffer;

public class Record implements Comparable<Record> {

    public static final int SIZE_IN_BYTES = 4;
    public static final int BYTE_INDEX_KEY = 0;
    public static final int BYTE_INDEX_VALUE = 2;
    public static final int KEY_MAXIMUM = 30000;

    // This tiny ByteBuffer holds both the key and value as bytes
    private ByteBuffer bb;

    // makes a record and its backing ByteBuffer, useful for testing
    public Record(short key, short val) {
        bb = ByteBuffer.allocate(SIZE_IN_BYTES);
        bb.putShort(BYTE_INDEX_KEY, key);
        bb.putShort(BYTE_INDEX_VALUE, val);
    }


    public byte[] getBytes() {
        byte[] bytes = new byte[4];
        for (int idex = 0; idex < 4; idex++) {
           bytes[idex] = bb.get(idex);
        }
        
        return bytes;
    }


    public short getKey() {
        return bb.getShort(BYTE_INDEX_KEY);
    }


    public short getValue() {
        return bb.getShort(BYTE_INDEX_VALUE);
    }


    // makes quick testing even easier
    public Record(int key, int val) {
        this((short)key, (short)val);
    }


    // Constructs using a given byte array. Does NOT copy but refers
    public Record(byte[] bytes) {
        bb = ByteBuffer.wrap(bytes);
    }


    // Makes a whole array of records that are backed by the given byte array
    // Caution: Changing the array will change records and vice versa!
    public static Record[] toRecArray(byte[] binaryData) {
        int numRecs = binaryData.length / SIZE_IN_BYTES;
        Record[] recs = new Record[numRecs];
        for (int i = 0; i < recs.length; i++) {
            int byteOffset = i * SIZE_IN_BYTES;
            ByteBuffer bb = ByteBuffer.wrap(binaryData, byteOffset,
                SIZE_IN_BYTES);
            recs[i] = new Record(bb.slice());
        }
        return recs;
    }


    // Constructs using a given byte buffer. Does NOT copy but refers
    private Record(ByteBuffer bb) {
        this.bb = bb;
    }


    // copies the contents of another record. This is a DEEP copy.
    public void setTo(Record other) {
        bb.putShort(BYTE_INDEX_KEY, other.getKey());
        bb.putShort(BYTE_INDEX_VALUE, other.getValue());
    }


    @Override
    public int compareTo(Record o) {
        return Short.compare(this.getKey(), o.getKey());
    }


    // A nice overview of the Record's contents.
    public String toString() {
        StringBuilder sb = new StringBuilder("Record: (");
        sb.append(this.getKey());
        sb.append(", ");
        sb.append(this.getValue());
        sb.append(")");
        return sb.toString();
    }

}
