import java.io.IOException;

public interface BpInterface {
    
    public Record getBpRecord(int indx) throws IOException;


    public void setRecord(int index, Record record)  throws IOException;
}
