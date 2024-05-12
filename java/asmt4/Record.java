/* Records to be stored in internal nodes of BST */
public class Record {
    private Key theKey;
    private String data;

    // constructor
    public Record(Key k, String theData) {
        this.theKey = k;
        this.data = theData;
    }
    // return key object
    public Key getKey() {
        return this.theKey;
    }
    // return data
    public String getDataItem() {
        return this.data;
    }
}
