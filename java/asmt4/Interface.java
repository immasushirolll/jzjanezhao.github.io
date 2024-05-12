import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Interface {
    public static void main(String[] args) throws DictionaryException {
        
        /* read in txt file and store information in ordered dictionary */
        String line;
        int type;
        String data;
        BSTDictionary dict = new BSTDictionary();
        Record record;
        Key key;

        // read in small.txt file
        // String filePath = "C:/1-School/Uni/Y4/CS2210/asmt4/small.txt";  //  VScode is already in the right folder
        String filePath = args[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // keep reading until there are no more lines in txt file
            while ((line = reader.readLine()) != null) {
                String label = line.toLowerCase();  // this is the first line
                line = reader.readLine();           // line now stores the next line
                Character charType = line.charAt(0);    // stores just the first character of that line
                if (charType == '-') {      // sound file
                    type = 3;
                    data = line.replace("-", "");
                }
                else if (charType == '+') { // music file
                    type = 4;
                    data = line.replace("+", "");
                }
                else if (charType == '*') { // voice file
                    type = 5;
                    data = line.replace("*", "");
                }
                else if (charType == '/') { //translation
                    type = 2;
                    data = line.replace("/", "");
                }
                else if (line.contains(".gif")) {   // animated image
                    type = 7;
                    data = line;
                }
                else if (line.contains(".jpg")) {   // image
                    type = 6;
                    data = line;
                }
                else if (line.contains(".html")) {  // webpage URL
                    type = 8;
                    data = line;
                }
                else {              // just a string description
                    type = 1;
                    data = line;
                }

                // make key to store as record with data
                key = new Key(label, type);
                record = new Record(key, data);

                // // checking for proper label, type, data, designations
                // System.out.println(record.getDataItem());
                // System.out.println(key.getLabel());
                // System.out.println(key.getType());

                // add to dicitonary
                dict.put(record);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        /* read user commands */
        StringReader keyboard = new StringReader();
        String command = "";
        while (!command.equals("exit")) {
            command = keyboard.read("Enter next command: ");
            String[] words = command.split(" ");     // split and store in array of words
            command = words[0];     // command is the first word
            
            // print data item, if not found print error message
            if (command.equals("define")) {
                type = 1;
                String label = words[1];    // label is the second word
                key = new Key(label, type);
                
                if (dict.get(key) == null) {
                    System.out.println("The word " + label + " is not in the ordered dictionary");
                }
                else {
                    System.out.println(dict.get(key).getDataItem());
                }
            }

            // if Record object d w Key (label, type) in dict, print data, else error message
            if (command.equals("translate")) {
                type = 2;
                String label = words[1];    // label is the second word
                key = new Key(label, type);

                if (dict.get(key) == null) {
                    System.out.println("There is no definition for the word " + label);
                }
                else {
                    System.out.println(dict.get(key).getDataItem());
                }
            }

            // for sound files
            if (command.equals("sound")) {
                type = 3;
                String label = words[1];    // label is the second word
                key = new Key(label, type);

                if (dict.get(key) == null) {
                    System.out.println("There is no sound file for " + label);
                }
                else {
                    try {
                        SoundPlayer player = new SoundPlayer();
                        player.play(dict.get(key).getDataItem());
                    }
                    catch (MultimediaException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            
            // for music files
            if (command.equals("play")) {
                type = 4;
                String label = words[1];    // label is the second word
                key = new Key(label, type);

                if (dict.get(key) == null) {
                    System.out.println("There is no music file for " + label);
                }
                else {
                    try {
                        SoundPlayer player = new SoundPlayer();
                        player.play(dict.get(key).getDataItem());
                    }
                    catch (MultimediaException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            // for voice files
            if (command.equals("say")) {
                type = 5;
                String label = words[1];    // label is the second word
                key = new Key(label, type);

                if (dict.get(key) == null) {
                    System.out.println("There is no voice file for " + label);
                }
                else {
                    try {
                        SoundPlayer player = new SoundPlayer();
                        player.play(dict.get(key).getDataItem());
                    }
                    catch (MultimediaException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            // for jpgs
            if (command.equals("show")) {
                type = 6;
                String label = words[1];    // label is the second word
                key = new Key(label, type);

                if (dict.get(key) == null) {
                    System.out.println("There is no image file for " + label);
                }
                else {
                    try {                
                        PictureViewer viewer = new PictureViewer();
                        viewer.show(dict.get(key).getDataItem());
                    }
                    catch (MultimediaException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            // for gifs
            if (command.equals("animate")) {
                type = 7;
                String label = words[1];    // label is the second word
                key = new Key(label, type);

                if (dict.get(key) == null) {
                    System.out.println("There is no animated image file for " + label);
                }
                else {
                    try {
                        PictureViewer viewer = new PictureViewer();
	                    viewer.show(dict.get(key).getDataItem());
                    }
                    catch (MultimediaException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            // for htmls
            if (command.equals("browse")) {
                type = 8;
                String label = words[1];    // label is the second word
                key = new Key(label, type);

                if (dict.get(key) == null) {
                    System.out.println("There is no webpage called " + label);
                }
                else {
                    ShowHTML browser = new ShowHTML();
                    browser.show(dict.get(key).getDataItem());
                }
            }

            // remove Record w key(w, k), error message if Record dne
            if (command.equals("delete")) {
                String typeString = words[2];
                type = Integer.parseInt(typeString);
                String label = words[1];
                key = new Key(label, type);
                try {
                    dict.remove(key);
                }
                catch (DictionaryException e) {
                    System.out.println("No record in the ordered dictionary has key ("+ label+" ," + typeString);
                }
            }

            // insert Record object((w,t),c), if record w key (w,t) exists print error
            if (command.equals("add")) {
                String typeString = words[2];
                type = Integer.parseInt(typeString);
                String label = words[1];
                key = new Key(label, type);
                data = words[3];
                record = new Record(key, data);
                try {
                    dict.put(record);
                } catch (DictionaryException e) {
                    System.out.println("A record with the given key (" + label + " ," + typeString + ") is already in the ordered dictionary.");
                }
            }

            // print all Record labels that start w prefix, if none then print error
            if (command.equals("list")) {
                String prefix = words[1];
                int counter = 0;
                key = new Key(prefix, 0);
                record = dict.successor(key);
                // use successor
                while (record != null && record.getKey().getLabel().startsWith(prefix)) {
                    System.out.println(record.getKey().getLabel());
                    record = dict.successor(record.getKey());
                    counter ++;
                }
                if (counter == 0) {
                    System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
                }
            }

            // print all attributes of Record w smallest Key
            if (command.equals("first")) {
                record = dict.smallest();
                key = record.getKey();
                String label = key.getLabel();
                type = key.getType();
                data = record.getDataItem();
                System.out.println(label+","+type+","+data);
            }

            // print all attriutes of Record w largest key
            if (command.equals("last")) {
                record = dict.largest();
                key = record.getKey();
                String label = key.getLabel();
                type = key.getType();
                data = record.getDataItem();
                System.out.println(label+","+type+","+data);
            }

            // if invalid command, print error message
            else {
                System.out.println("Invalid command.");
            }
        }
    }
}
