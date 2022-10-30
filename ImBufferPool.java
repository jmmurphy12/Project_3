/**
 * A fake buffer pool used for testing purposes
 * 
 * @author Amado Jain
 * @author Josh Murphy
 * @version 2022.10.30
 * 
 */
public class ImBufferPool implements BpInterface {
    private Record[] records;

    /**
     * The constructor for the class
     * 
     * @param size
     *            the amount of records being stored
     */
    public ImBufferPool(int size) {
        records = new Record[size];
    }


    /**
     * Gets a record in the fake buffer pool
     * 
     * @param indx
     *            the location of the record
     * @return
     *         the record in the fake buffer pool
     */
    public Record getBpRecord(int indx) {
        return records[indx];
    }


    /**
     * Sets the record in the fake buffer pool
     * 
     * @param index
     *            the location of the record being set
     * @param record
     *            the data the record is now being changed to
     */
    public void setRecord(int index, Record record) {
        records[index] = record;
    }


    /**
     * Displays the fake buffer in the form of a string
     * 
     * @return
     *         the data of the fake buffer in the form of a string
     */
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int idex = 0; idex < records.length; idex++) {
            s.append(records[idex].toString());
            s.append("\n");
        }
        return s.toString();
    }

}
