import java.io.IOException;

/**
 * Interface for BufferPool mainly used for testing purposes
 * 
 * @author Amado Jain
 * @author Josh Murphy
 * @version 2022.10.30
 * 
 */
public interface BpInterface {

    /**
     * Gets a record from the buffer pool
     * 
     * @param indx
     *            the location in the file
     * @return
     *         the record "got"
     * @throws IOException
     *             A problem with the file occurs
     */
    public Record getBpRecord(int indx) throws IOException;


    /**
     * Sets a record to another in the file
     * 
     * @param index
     *            the record location being set
     * @param record
     *            the new record value after being set
     * @throws IOException
     *             if a file error occurs
     */
    public void setRecord(int index, Record record) throws IOException;
}
