public class GraphNode {
    private int nodeName;
    private boolean nodeMark;

    // constructor, name is between 0 and n-1, n = # nodes in graph
    public GraphNode(int name) {
        nodeName = name;
        nodeMark = false;
    }

    // marks node
    public void mark(boolean mark) {
        nodeMark = mark;
    }

    // returns T/F for whether a node is marked
    public boolean isMarked() {
        return nodeMark;
    }

    // returns number representing node's name
    public int getName() {
        return nodeName;
    }
}

