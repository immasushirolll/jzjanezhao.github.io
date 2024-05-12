
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
    // private instance variables
    private DLinkedNode<T> front;   // 1st node
    private DLinkedNode<T> rear;    // last node
    private int count;      // number of nodes

    public DLPriorityQueue() {  // constructor
        // create empty priority queue
        front = null;
        rear = null;
        count = 0;
    }

    public void add(T dataItem, double priority) {
        DLinkedNode<T> newNode = new DLinkedNode<T>(dataItem, priority);
        // if list empty, insert at front
        if (count == 0) {
            front = newNode;
            rear = front;
            count++;
        }
        // if list not empty, insert in front of 1st priority bigger than this priority
        else {
            count++;
            // for loop looks for 1st priority bigger than this priority
            DLinkedNode<T> pointer = front;
            for (; pointer != null && (double) pointer.getPriority() < (double) newNode.getPriority(); pointer = pointer.getNext());
            
                // if the 1st node is bigger, then it is smallest priority, insert at front
                if (pointer == front) {
                    pointer.setPrev(newNode);
                    newNode.setNext(pointer);
                    front = newNode;                
                }
                // if larger priority not found, it is biggest priority, insert at end
                else if (pointer == null) {
                    newNode.setPrev(rear);
                    rear.setNext(newNode);
                    rear = newNode;
                }
                // if found in middle, insert in front of pointer
                else {
                    pointer.getPrev().setNext(newNode);
                    newNode.setPrev(pointer.getPrev());
                    pointer.setPrev(newNode);
                    newNode.setNext(pointer);
                }
            }
        }   

    public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
        // changes priority of given dataItem to newPriority
        DLinkedNode<T> pointer;
        for (pointer = front; pointer != null; pointer = pointer.getNext()) {
            if (pointer.getDataItem().equals(dataItem)) {
                // removing the only node
                if (pointer.getNext() == null && pointer.getPrev() == null) {
                    pointer.setPriority(newPriority);
                    return;
                }
                // removing from back
                else if (pointer.getNext() == null) {
                    pointer.getPrev().setNext(null);
                    rear = pointer.getPrev();
                    this.add(dataItem, newPriority);
                    return;
                }
                // removing from front
                else if (pointer.getPrev() == null) {
                    pointer.getNext().setPrev(null);
                    front = pointer.getNext();
                    this.add(dataItem, newPriority);
                    return;
                }
                else {
                    pointer.getPrev().setNext(pointer.getNext());
                    pointer.getNext().setPrev(pointer.getPrev()); 
                    pointer.setPriority(-100.0);
                    this.removeMin();
                    this.add(dataItem, newPriority);
                    return;
                }
            }
        }
        throw new InvalidElementException("dataitem not in queue");
    }

    public T removeMin() throws EmptyPriorityQueueException {
        DLinkedNode<T> pointer = front;
        // if queue not empty, remove and return item at front
        if (count != 0) {    
            front = front.getNext();
            count -= 1;
            return pointer.getDataItem();
        }
        else {
            throw new EmptyPriorityQueueException("queue is empty");
        }
    }

    // returns true if queue is empty
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    // returns number of elements in queue
    public int size() {
        return count;
    }

    // returns string representation of queue
    public String toString() {
        String qString = "";
        DLinkedNode<T> pointer = front;
        for (; pointer != null; pointer = pointer.getNext()) {
            qString += pointer.getDataItem().toString();
        }
        return qString;
    }

    // returns rear node
    public DLinkedNode<T> getRear() {
        return rear;
    }
}