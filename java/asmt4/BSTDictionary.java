public class BSTDictionary implements BSTDictionaryADT {
    
    private BinarySearchTree dict;

    // constructor
    public BSTDictionary() {
        this.dict = new BinarySearchTree();
    }
    /* Returns the Record object with the Key as k, or it returns null if such 
       a Record is not in the dictionary. */
    public Record get (Key k) {
        BSTNode node = dict.get(dict.getRoot(), k);
        return node.getRecord();
    }

    /* Inserts the Record d into the ordered dictionary. It throws a DictionaryException 
      if a Record with the same Key attribute as d is already in the dictionary. */
    public void put (Record d) throws DictionaryException {
        dict.insert(dict.getRoot(), d);
    }
   
       /*  Removes the Record with the same Key attribute as k from the dictionary. It throws a 
           DictionaryException if such a Record is not in the dictionary. */
    public void remove (Key k) throws DictionaryException { 
        dict.remove(dict.getRoot(), k);
    }
   
       /* Returns the successor of k (the Record from the ordered dictionary 
          with smallest key larger than k); it returns null if the given key has
          no successor. Note that the given key k DOES NOT need to be in the dictionary. */
    public Record successor (Key k) {
        BSTNode succRecord = dict.successor(dict.getRoot(), k);
        if (succRecord == null) {
            return null;
        }
        else {
            return succRecord.getRecord();
        }
    }
   
       /* Returns the predecessor of k (the Record from the ordered dictionary 
          with largest key smaller than k; it returns null if the given key has 
          no predecessor. Note that the given key k DOES NOT need to be in the dictionary.  */
    public Record predecessor (Key k) {
        BSTNode predRecord = dict.predecessor(dict.getRoot(), k);
        if (predRecord == null) {
            return null;
        }
        else {
            return predRecord.getRecord();
        }
    }
   
       /* Returns the Record with smallest key in the ordered dictionary. 
          Returns null if the dictionary is empty.  */
    public Record smallest () {
        BSTNode smallNode = dict.smallest(dict.getRoot());
        if (dict.getRoot().getRecord() == null) {
            return null;
        }
        else {
            return smallNode.getRecord();
        }
    }
   
       /* Returns the Record with largest key in the ordered dictionary. 
          Returns null if the dictionary is empty.  */
    public Record largest () {
        BSTNode largeNode = dict.largest(dict.getRoot());
        if (dict.getRoot().getRecord() == null) {
            return null;
        }
        else {
            return largeNode.getRecord();
        }
    }  
}
