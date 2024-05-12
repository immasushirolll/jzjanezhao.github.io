
public class Set<T> {
    
    private LinearNode<T> setStart; // front of linked list

    public Set() {  // constructor
        setStart = null;
    }
    
    /*Create and add new node containing given element to linked list*/
    public void add(T element) {
        // create node containing given element
        LinearNode<T> newNode = new LinearNode<T> (element);

        // adding node to empty list
        if (setStart == null) {
            setStart = new LinearNode<T>();
            setStart.setNext(newNode);
        }
        // adding node to list with other nodes
        else {
            newNode.setNext(setStart.getNext());
            setStart.setNext(newNode);
        }      
    }            
    
    /*gets number of items in linked list*/
    public int getLength() {
        LinearNode<T> current;      // current is a node

        if (setStart == null) {     // checks if list is empty, returns 0 if true
            return 0;
        }
        else {                      // if not empty, iterates through list and counts elements
            current = setStart.getNext();
            int counter = 0;

            while ((current != null)) {
                counter += 1;
                current = current.getNext();    // goes to next element in list
            }
            return counter;
        }
    }
    
    /*gets element at ith node */
    public T getElement(int i) {
        LinearNode<T> current;
        current = setStart;
        int counter = -1;   // initialize counter

        while (current != null) {   
            if (counter == i) {
                return current.getElement();
            }
            counter += 1;
            current = current.getNext();
        }
        return null;    // returns null if list is empty or if element does not exist in list
    }
    
    /* indicates whether a given element is stored in the linked list */
    public boolean contains(T element) {
        LinearNode<T> current;
        current = setStart;

        while (current != null) {   // while list isn't empty, and element is found, return true
            if (String.valueOf(current.getElement()).equals(String.valueOf(element))) { // convert elements to string and compare them
                return true;
            }
            current = current.getNext();
        }
        return false;    // returns null if list is empty or if element does not exist in list
    }
    
    /*returns string containing each element in Set */
    public String toString() {
        String string = new String();   // initialize string

        LinearNode<T> current;      // current is a node

        if (setStart == null) {     // if list is empty, returns string (which is empty)
            return string;
        }
        else {                      // iterates through list, converts elements to a string
            current = setStart.getNext();
            while ((current != null)) {
                String tmpString = String.valueOf(current.getElement());
                string = string + tmpString + " ";
                current = current.getNext();    // goes to next element in list
            }
            return string;
        }
    }
}