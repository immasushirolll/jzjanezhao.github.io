/* Node of BST */
public class BSTNode {
    private Record dictItem;
    private BSTNode left, right, parent = null;

    // constructor
    public BSTNode(Record item) {
        dictItem = item;
    }
    // return Record object in this node
    public Record getRecord() {
        return this.dictItem;
    }
    // store given record in this node
    public void setRecord(Record d) {
        this.dictItem = d;
    }
    // return left child
    public BSTNode getLeftChild() {
        return this.left;
    }
    // return right child
    public BSTNode getRightChild() {
        return this.right;
    }
    // return parent
    public BSTNode getParent() {
        return this.parent;
    }
    // set left child
    public void setLeftChild(BSTNode u) {
        left = u;
    }
    // set right child
    public void setRightChild(BSTNode u) {
        right = u;
    }
    // set parent
    public void setParent(BSTNode u) {
        parent = u;
    }
    // if node is leaf return true, false otherwise
    public boolean isLeaf() {
        if (this.getLeftChild() == null && this.getRightChild() == null) {
            return true;
        }
        else {
            return false;
        }
    }
}   
