
import java.nio.ByteBuffer;

/**
 * This class keeps track records in the byte file
 * 
 * @author Amado Jain
 * @author Josh Murphy
 * @version 2022.10.30
 * 
 */
public class Record implements Comparable<Record> {

    /**
     * The size of each record object
     */
    public static final int SIZE_IN_BYTES = 4;

    /**
     * Location of the key
     */
    public static final int BYTE_INDEX_KEY = 0;

    /**
     * The location of the value
     */
    public static final int BYTE_INDEX_VALUE = 2;

    /**
     * The maximum key value
     */
    public static final int KEY_MAXIMUM = 30000;

    // This tiny ByteBuffer holds both the key and value as bytes
    private ByteBuffer bb;

    /**
     * makes a record and its backing ByteBuffer, useful for testing
     * 
     * @param key
     *            the key of the record
     * @param val
     *            the value of the record
     */
    public Record(short key, short val) {
        bb = ByteBuffer.allocate(SIZE_IN_BYTES);
        bb.putShort(BYTE_INDEX_KEY, key);
        bb.putShort(BYTE_INDEX_VALUE, val);

    }


    /**
     * Gets the key of the record
     * 
     * @return
     *         the key of the record
     */
    public short getKey() {
        return bb.getShort(BYTE_INDEX_KEY);
    }


    /**
     * Gets the value of the record
     * 
     * @return
     *         the value of the record
     */
    public short getValue() {
        return bb.getShort(BYTE_INDEX_VALUE);
    }


    /**
     * makes quick testing even easier
     * 
     * @param key
     *            the key of the record
     * @param val
     *            the value of the record
     */
    public Record(int key, int val) {
        this((short)key, (short)val);
    }


    /**
     * Constructs using a given byte array. Does NOT copy but refers
     * 
     * @param bytes
     *            the bytes of the record
     */
    public Record(byte[] bytes) {
        bb = ByteBuffer.wrap(bytes);
    }


    /**
     * Makes a whole array of records that are backed by the given byte array
     * Caution: Changing the array will change records and vice versa!
     * 
     * @param binaryData
     *            the byte array for the record
     * @return
     *         the record array
     */
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


    /**
     * Constructs using a given byte buffer. Does NOT copy but refers
     * 
     * @param bb
     *            the byte buffer for the record
     */
    private Record(ByteBuffer bb) {
        this.bb = bb;
    }


    /**
     * copies the contents of another record. This is a DEEP copy.
     * 
     * @param other
     *            the record its being set to
     */
    public void setTo(Record other) {
        bb.putShort(BYTE_INDEX_KEY, other.getKey());
        bb.putShort(BYTE_INDEX_VALUE, other.getValue());
    }


    /**
     * Compares one record to another
     * 
     * @param o
     *            the other record being compared
     * @return
     *         the comparison results
     */
    @Override
    public int compareTo(Record o) {
        return Short.compare(this.getKey(), o.getKey());
    }


    /**
     * A nice overview of the Record's contents.
     * 
     * @return
     *         the string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("Record: (");
        sb.append(this.getKey());
        sb.append(", ");
        sb.append(this.getValue());
        sb.append(")");
        return sb.toString();
    }

}
