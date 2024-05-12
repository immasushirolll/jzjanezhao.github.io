public class GraphEdge {
    private GraphNode firstEp, secondEp;
    private int edgeType;
    private String edgeLabel;
    
    // constructor for a door in the maze
    public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
        firstEp = u;
        secondEp = v;
        edgeType = type;
        edgeLabel = label;
    }

    // returns first endpoint
    public GraphNode firstEndpoint() {
        return firstEp;
    }

    // returns second endpoint
    public GraphNode secondEndpoint() {
        return secondEp;
    }

    // returns type of edge
    public int getType() {
        return edgeType;
    }

    // set type of edge
    public void setType(int newType) {
        edgeType = newType;
    }

    // return label of edge
    public String getLabel() {
        return edgeLabel;
    }

    // set label of edge
    public void setLabel(String newLabel) {
        edgeLabel = newLabel;
    }
}
