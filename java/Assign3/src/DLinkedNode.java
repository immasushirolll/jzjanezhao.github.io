
public class DLinkedNode<T> {
    // instance variables
    private T dataItem;
    private double priority;
    private DLinkedNode<T> next;
    private DLinkedNode<T> prev;
    public char[] getPriority;

    // getter and setters
    public DLinkedNode(T data, double prio) {
        // stores given dataItem and priority
        dataItem = data;
        priority = prio;
    }
    public DLinkedNode() {
        // create empty node w null dataItem and zero priority
        dataItem = null;
        priority = 0.0;
    }
    public double getPriority() {
        return priority;
    }
    public T getDataItem() {
        return dataItem;
    }
    public DLinkedNode<T> getNext() {
        return next;
    }
    public DLinkedNode<T> getPrev() {
        return prev;
    }
    public void setDataItem(T dataItem) {
        this.dataItem = dataItem;
    }
    public void setNext(DLinkedNode<T> next) {
        this.next = next;
    }
    public void setPrev(DLinkedNode<T> prev) {
        this.prev = prev;
    }
    public void setPriority(double priority) {
        this.priority = priority;
    }
}
