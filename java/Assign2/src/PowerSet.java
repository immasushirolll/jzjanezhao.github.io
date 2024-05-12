import java.lang.Math;
import java.lang.reflect.Array;

public class PowerSet<T> {
    private Set<T> set;   // private variable
    
    /* generates Power Set from T array of elements */
    public PowerSet(T[] elements) {     // constructor
        int n = elements.length;    // gets number of elements in T array
        //set = (Set<T>) Array.newInstance(Set.class, (int) Math.pow(2,n));
        Set<T> set  = new Set<T>();
        // for loop iterates from 0 to n
        for (int i = 0; i < Math.pow(2, n); i++) {
            // turn into binary and pad with 0s
            String bString = String.format("%"+n+"s", Integer.toBinaryString(i)).replace(' ', '0');
            // create Set
            Set<T> tmpSet = new Set<T>(); // this is a linked list

            // for loop iterates from 0 to n
            for (int j = 0; j < bString.length(); j++) {
                if (bString.charAt(j) == '1') {
                    tmpSet.add(elements[j]);    // put element at index j into tmpSet linked list  
                }
            }
            // put tmpSet into the array, set
            set.add((T)tmpSet);     // ith element of set = tmpSet
        }
    }
    
    /* gives number of items in array (number of sets in Power Set) */
    public int getLength() {
        return (set.getLength());
    }
    public Set<T> getSet(int i){
        return(Set<T>)set.getElement(i);
    }
    public static void main(String[] args){
        Character[] carr = new Character[] {'a', 'b', 'c'};
        PowerSet<Character> cps = new PowerSet<Character>(carr);
        for (int i = 0; i < cps.getLength(); i++){
            System.out.println(cps.getSet(i));
        }
    }
}