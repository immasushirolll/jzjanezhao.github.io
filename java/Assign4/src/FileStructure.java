import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.AsyncBoxView.ChildLocator;

public class FileStructure {
    // instance variable
    private NLNode<FileObject> root;

    // recursive helper method to create tree
    private void makeTree(NLNode<FileObject> node) {
        // base case
        if (node.getData().isFile()) return;

        // recursive case
        FileObject file = node.getData();  // make FileObject
        Iterator<FileObject> iter = file.directoryFiles(); // make iterator
        // get # children inside current file object
        int remainingChild = file.numFilesInDirectory();
        // while (i < # children)
        while (remainingChild > 0) {
            remainingChild --;      // decrement counter
            FileObject child = iter.next();
            NLNode<FileObject> tmp = new NLNode<FileObject>(child, node);  // create new node object
            node.addChild(tmp);
            makeTree(tmp);
        }
    }

    public FileStructure(String fileObjectName) throws FileObjectException {
        // constructor
        FileObject file = new FileObject(fileObjectName);   // make new file
        root = new NLNode<>(file, null);    // no parent bc it's root of tree
        if (file.isDirectory()) makeTree(root);     // if file is in there, make tree from that file
    }

    // returns root
    public NLNode<FileObject> getRoot() {
        return root;
    }

    /* recursive helper method */
    private ArrayList<String> addContainer(NLNode<FileObject> r, String type) {
        ArrayList<String> list = new ArrayList<String>();   // create new array list container
        
        FileObject f = r.getData();   // take fileobject out of node and store it
        String rType = "";
        String name = "";

        if (f.isFile()) {
            name = f.getLongName();     // get absolute path from this fileobject
            String[] split = name.split("\\.", 2);   // split absolute file path by period, second piece is type
            rType = "." + split[1];
            // base case: r represents file
            if (rType.equals(type)) {
                list.add(name);    // if filetype of r matches type, add to list
            }
        }

        // recursive case: r represents folder
        else {
            Iterator<FileObject> iter = f.directoryFiles();
            int remainingChild = f.numFilesInDirectory();
            while (remainingChild > 0) {
                remainingChild --;
                FileObject child = iter.next();
                NLNode<FileObject> tmp = new NLNode<FileObject>(child, r);
                ArrayList<String> childList = addContainer(tmp, type);
                for (String s:childList) {      // iterating through childList and adding to main list
                    list.add(s);
                }
            }
        }
        return list;
    }

    /* Returns iterator with names of all files of specified type 
     * represented by nodes of this FileStructure
     */
    public Iterator<String> filesOfType(String type) {
        ArrayList<String> container = addContainer(root, type);
        Iterator<String> iterator = container.iterator();
        return iterator;
    }

    /* recursive helper method to get file path */
    private String recursiveSearch(NLNode<FileObject> currentNode, String wantedName) {
        FileObject f = currentNode.getData();   // take fileobject out of node and store it
        //check if match
        String path = f.getLongName();     // get absolute path from this fileobject
        String[] split = path.split("/", 0);   // split absolute file path by /
        String name = split[split.length -1];
        //base case: found the wanted name
        if (name.equals(wantedName)) {
            return path;
        }
        //begin search
        Iterator<FileObject> iter = f.directoryFiles();
        int remainingChild = f.numFilesInDirectory();
        while (remainingChild > 0) {
            remainingChild --;
            FileObject child = iter.next();
            NLNode<FileObject> tmp = new NLNode<FileObject>(child, currentNode);
            String childValue = recursiveSearch(tmp, wantedName);
            if (!childValue.isEmpty()){
                return childValue;
            }
        }
        return new String();
        
    }

    /* look for files with specified name inside this FileStructure, 
    return absolute path */
    public String findFile(String name) {
        String path = recursiveSearch(root, name);
        return path;
    }
}
