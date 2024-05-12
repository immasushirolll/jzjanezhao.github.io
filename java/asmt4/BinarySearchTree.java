public class BinarySearchTree {
    private BSTNode root;

    // constructor
    public BinarySearchTree() {
        this.root = new BSTNode(null);
    }
    // return root node of this BST
    public BSTNode getRoot() {
        return root;
    }
    /* return node storing key k
     * return r if key not in tree w/ root r
     */
    public BSTNode get(BSTNode r, Key k) {
        if (r.isLeaf()) {
            return r;
        }
        else {
            if (r.getRecord().getKey().compareTo(k) == 0) {
                return r;
            }
            else if (r.getRecord().getKey().compareTo(k) < 0) {
                return get(r.getRightChild(), k);
            }
            else {
                return get(r.getLeftChild(), k);
            }
        }
    }
    // add record d to BST w root r, throw exception if d already in tree
    public void insert(BSTNode r, Record d) throws DictionaryException {
        BSTNode p = new BSTNode(null);
        p = get(r, d.getKey());
        if (p.isLeaf() == false) {
            throw new DictionaryException("Record already in tree.");
        }
        else if (p.isLeaf()) {
            p.setRecord(d);
            p.setLeftChild(new BSTNode(null));
            p.setRightChild(new BSTNode(null));
            p.getRightChild().setParent(p);
            p.getLeftChild().setParent(p);
        }
        else {
            if (r.getRecord().getKey().compareTo(d.getKey()) < 0) {
                insert(r.getRightChild(), d);
            }
            else {
                insert(r.getLeftChild(), d);
            }
        }
    }
    // delete node w key k from tree root r, throw exception if k not in tree
    public void remove(BSTNode r, Key k) throws DictionaryException {
        BSTNode p = new BSTNode(null);
        p = get(r, k);
        if (p.isLeaf()) {
            throw new DictionaryException("Key not in tree.");
        }
        else {
            if (p.getLeftChild().isLeaf() || p.getRightChild().isLeaf()) {
                BSTNode otherChild;
                if (p.getLeftChild().isLeaf()) {
                    otherChild = p.getRightChild();
                }
                else {
                    otherChild = p.getLeftChild();
                }
                BSTNode pParent = p.getParent();
                if (pParent != null) {
                    p.setRecord(otherChild.getRecord());
                    p.setLeftChild(otherChild.getLeftChild());
                    p.setRightChild(otherChild.getRightChild());
                }
                else {
                    root = otherChild;
                    otherChild.setParent(null);
                }
            }
            else {
                BSTNode s = smallest(p.getRightChild());
                p.setRecord(s.getRecord());
                remove(s, s.getRecord().getKey());
            }
        }
    }
    // return node storing successor of given key in tree root r, dne ret null
    public BSTNode successor(BSTNode r, Key k) {
        BSTNode p = get(r, k);
        if (!(p.getRightChild().isLeaf())) {
            return smallest(p.getRightChild());
        }
        else {
            p  = p.getParent();
            while (p != null && p.getRecord().getKey().compareTo(k) < 0) {
                p = p.getParent();
            }
            return p;
        }
    }
    // return node storing predecessor of given key in tree w root r, dne ret null
    public BSTNode predecessor(BSTNode r, Key k) {
        BSTNode p = get(r, k);
        if (!(p.getLeftChild().isLeaf())) {
            return largest(p.getLeftChild());
        }
        else {
            p = p.getParent();
            while (p != null && p.getRecord().getKey().compareTo(k) > 0) {
                p = p.getParent();
            }
            return p;
        }
    }
    // return node w smallest key in tree w root r
    public BSTNode smallest(BSTNode r) {
        if (r == null) {
            return null;
        }
        else {
            BSTNode p = r;
            while (!p.isLeaf()) {
                p = p.getLeftChild();
            }
            return p.getParent();
        }
    }
    // return node w largest key in tree w root r
    public BSTNode largest(BSTNode r) {
        if (r == null) {
            return null;
        }
        else {
            BSTNode p = r;
            while (!p.isLeaf()) {
                p = p.getRightChild();
            }
            return p.getParent();
        }
    }
}

// testing BST class

    // public static void main(String[] args) throws DictionaryException{
    //     Key key1 = new Key("1",0);
    //     Key key2 = new Key("2",0);
    //     Record record1 = new Record(key1, "");
    //     Record record2 = new Record(key2, "");
    //     BinarySearchTree test = new BinarySearchTree();
    //     test.insert(test.getRoot(), record1);
    //     test.insert(test.getRoot(), record2);
    //     System.out.println(test.getRoot().getRecord().getKey().getLabel());
    //     System.out.println(test.getRoot().getLeftChild().getRecord() + "      "+test.getRoot().getRightChild().getRecord().getKey().getLabel());
    // }
