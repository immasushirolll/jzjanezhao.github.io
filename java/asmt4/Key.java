/* Key of data items in internal nodes of BST implementing ordered dictionary */
public class Key {
    private String label;
    private int type;

    // constructor
    public Key(String theLabel, int theType) {
        label = theLabel.toLowerCase();     // convert to all lowercase
        type = theType;
    }
    // return label
    public String getLabel() {
        return this.label;
    }
    // return type
    public int getType() {
        return this.type;
    }
    // return 0 if Key = k, -1 if Key < k, 1 if otherwise
    public int compareTo(Key k) {
        if (this.label.equals(k.label) && this.type == k.type) {
            return 0;
        }
        if (this.label.compareTo(k.label) < 0 || (this.label == k.label && this.type < k.type)) {
            return -1;
        }
        else {
            return 1;
        }
    } 
}

// testing compareTo(Key k)

    // public static void main(String[] args) {
    //     Key A = new Key("car", 2);
    //     Key B = new Key("car", 2);
    //     Key C = new Key("car", 3);
    //     Key D = new Key("house", 1);
    //     int a = A.compareTo(B);
    //     int b = A.compareTo(C);
    //     int c = A.compareTo(D);
    //     System.out.println(a);
    //     System.out.println(b);
    //     System.out.println(c);
    // }
