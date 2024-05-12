import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class NLNode<T> {
    // instance variables
    private NLNode<T> parent;   // parent of this node
    private ListNodes<NLNode<T>> children;  // list storing children
    private T data;     // data object stored in this node

    // constructors
    public NLNode() {
        parent = null;
        data = null;
        children = new ListNodes<NLNode<T>>();
    }
    public NLNode(T d, NLNode<T> p) {   // overloads constructor
        children = new ListNodes<NLNode<T>>();
        data = d;
        parent = p;
    }

    public void setParent(NLNode<T> p) {
        this.parent = p;
    }

    public NLNode<T> getParent() {
        return this.parent;
    }

    public void addChild(NLNode<T> newChild) {
        // adds given node newChild to list of children of this node
        this.children.add(newChild);
        // node newChild must have parent set to this node
        newChild.setParent(this);
    }

    public Iterator<NLNode<T>> getChildren() {
        // returns iterator containing children of this node
        Iterator<NLNode<T>> iterator = children.getList();
        return iterator;
    }

    public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter) {
        // return iterator containing children of this node sorted in order
        Iterator<NLNode<T>> iterator = children.sortedList(sorter);
        return iterator;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T d) {
        this.data = d;
    }
}
