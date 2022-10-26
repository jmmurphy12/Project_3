
public class ImBufferPool implements BpInterface {
    private Record[] records;

    public ImBufferPool(int size) {
        records = new Record[size];
    }


    public Record getBpRecord(int indx) {
        return records[indx];
    }


    public void setRecord(int index, Record record) {
        records[index] = record;
    }


    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int idex = 0; idex < records.length; idex++) {
            s.append(records[idex].toString());
            s.append("\n");
        }
        return s.toString();
    }

}
