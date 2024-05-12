import java.io.*;

public class TestDict {

    public static void main(String[] args) {
	final int TEXT = 2;
	BSTDictionary dictionary = new BSTDictionary();
	Record rec;
	Key key;

	String words[] = {"homework", "course", "class", "computer", "four"};
	String definitions[] = {"Very enjoyable work that students need to complete outside the classroom.",
				"A series of talks or lessons. For example, CS210.",
				"A set of students taught together,",
				"An electronic machine frequently used by Computer Science students.",
				"One more than three"};
	Key[] keys = new Key[5];
	Record[] records = new Record[5];
	
	boolean alltests = true;
	int test = -1;
	if (args.length > 0) {
		test = Integer.parseInt(args[0]);
		alltests = false;
	}
	
	for (int i = 0; i < 5; ++i) {
		keys[i] = new Key(words[i],TEXT);
		records[i] = new Record(keys[i],definitions[i]);
	}

	// Insert one word and then find it
	if (alltests || test == 1 || test == 3 || test == 6 || test == 7 || test == 8 || test == 9)
	try {
	    dictionary.put(records[0]);
	    rec = dictionary.get(keys[0]);
	    if ((rec.getDataItem()).compareTo(definitions[0]) == 0)
			System.out.println("Test 1 passed");
	    else System.out.println("Test 1 failed");
	}
	catch(Exception e) {
	    System.out.println("Test 1 failed");
	}

	// Try to find an inexistent word
	if (alltests || test == 2)
	try {
	    rec = dictionary.get(keys[1]);
	    if (rec == null) System.out.println("Test 2 passed");
	    else System.out.println("Test 2 failed");
	}
	catch(Exception e) {
	    System.out.println("Test 2 failed");
	}

	// Try to insert the same word again
	if (alltests || test == 3)
	try {
	    dictionary.put(records[0]);
	    System.out.println("Test 3 failed");
	}
	catch(DictionaryException e) {
	    System.out.println("Test 3 passed");
	}
	catch (Exception e) {
	    System.out.println("Test 3 failed");
	}

	// Insert and remove a word
	if (alltests || test == 4)
	try {
	    dictionary.put(records[1]);
	    dictionary.remove(keys[1]);
	    rec = dictionary.get(keys[1]);
	    if (rec == null) System.out.println("Test 4 passed");
	    else System.out.println("Test 4 failed");
	}
	catch(DictionaryException e) {
	    System.out.println("Test 4 failed");
	}

	// Remove a word not in the dictionary
	if (alltests || test == 5)
	try {
	    dictionary.remove(keys[3]);
	    System.out.println("Test 5 failed");
	}
	catch(DictionaryException e) {
	    System.out.println("Test 5 passed");
	}
	catch (Exception e) {
	    System.out.println("Test 5 failed");
	}

	// Insert 5 words in the dictionary and test the successor method
	if (alltests || test == 6 || test == 7 || test == 8 || test == 9)
	try {
	    dictionary.remove(keys[0]);
	    dictionary.put(records[1]);
	    dictionary.put(records[0]);
	    for (int i = 2; i < 5; ++i)
			dictionary.put(records[i]);

	    rec = dictionary.successor(keys[3]);
	    if ((rec.getKey().getLabel()).compareTo(words[1]) == 0)
			System.out.println("Test 6 passed");
	    else System.out.println("Test 6 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 6 failed");
	}

	// Test the successor method
	if (alltests || test == 7)
	try {
	    rec = dictionary.successor(keys[2]);
	    if ((rec.getKey().getLabel()).compareTo(words[3]) == 0)
			System.out.println("Test 7 passed");
	    else System.out.println("Test 7 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 7 failed");
	}

	//Test the predecessor method
	if (alltests || test == 8)
	try {
	    rec = dictionary.predecessor(keys[0]);
	    if ((rec.getKey().getLabel()).compareTo(words[4]) == 0)
			System.out.println("Test 8 passed");
	    else System.out.println("Test 8 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 8 failed");
	}

	// Test the predecessor method
	if (alltests || test == 9)
	try {
	    rec = dictionary.predecessor(keys[4]);
	    if ((rec.getKey().getLabel()).compareTo(words[1]) == 0)
			System.out.println("Test 9 passed");
	    else System.out.println("Test 9 failed");
	}
	catch (Exception e) {
	    System.out.println("Test 9 failed");
	}

        // Create a new empty dictionary

	dictionary = new BSTDictionary();
	}
}
