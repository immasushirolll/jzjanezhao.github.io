import java.util.LinkedList;

/* Implements public methods in DictionaryADT */

public class HashDictionary implements DictionaryADT { 
    public int capacity;
    public int counter;
    private LinkedList<Data>[] table;

    /* constructor, returns empty dictionary of specified size */
    public HashDictionary(int size) {
        capacity = size;
        counter = 0;
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<Data>();
        }
    }
    
    /* takes config and returns the index for hash table */
    private int hashFunction(String board, int x, int M) {
        int val = (int) board.charAt(board.length()-1);
        for (int i = board.length() - 2; i >= 0; i--) {
            val = (val*x + (int) board.charAt(i)) % M;
        }
        return val;
    }

    /* return score using the config from dictionary
     * return -1 if config not in dictionary
     */
    public int get(String config) {
        int pos = hashFunction(config, 43, capacity);
        LinkedList<Data> chain = table[pos];
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).getConfiguration().equals(config)) return chain.get(i).getScore();
        }
        return -1;
    }

    /* add record to dictionary
     * throw DictionaryException if record.getConfiguration() in dictionary
     * return 1 if adding record causes collision
     * return 0 if successfully added with no collisions
     */
    public int put(Data record) throws DictionaryException {
        //for (int i = 0; i < chain.size(); i++) {
        //    if (chain.get(i).getConfiguration() == record.getConfiguration()) throw new DictionaryException();
        //}
        if (this.get(record.getConfiguration()) != -1) throw new DictionaryException();
        // take Data, hash it, get position to put in dictionary, put it in
        int pos = hashFunction(record.getConfiguration(), 43, capacity);
        LinkedList<Data> chain = table[pos];
        if (chain.size() != 0) {
            chain.add(record);
            counter ++;
            return 1;
        }
        else {
            chain.add(record);
            counter ++;
            return 0;
        }
    }

    /* removes record using the config from dictionary
     * throw DictionaryException if no record in hash table stores config
     */
    public void remove(String config) throws DictionaryException {
        int pos = hashFunction(config, 43, capacity);
        LinkedList<Data> chain = table[pos];
        for (int i = 0; i < chain.size(); i++) {
            if (chain.get(i).getConfiguration().equals(config)) {
                chain.remove(i);
                counter --;
                return;
            }
        }
        throw new DictionaryException();
    }

    /* return number of Data objects in dictionary */
    public int numRecords() {
        return counter;
    }
}
