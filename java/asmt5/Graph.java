import java.util.*;

public class Graph implements GraphADT {
    private ArrayList<LinkedList<GraphEdge>> adjList;
    private int numNodes = 0;    // number of nodes
    private GraphNode[] nodes;

    // use adjacency list, create empty graph w n nodes, no edges, name of node is n-1
    public Graph(int n) {
        adjList = new ArrayList<LinkedList<GraphEdge>>();
        this.nodes = new GraphNode[n];
        this.numNodes = n;
        for (int i = 0; i < n; i++) {   // adds a new list n times
            nodes[i] = new GraphNode(i);
            adjList.add(new LinkedList<GraphEdge>());
        }
    }

    // adds edge connecting nodes u and v, throw if node dne or edge already exists
    public void insertEdge(GraphNode u, GraphNode v, int edgeType, String label) throws GraphException {
        if (u == null || v == null) throw new GraphException("node(s) null");
        // u and v not in graph if its name is bigger than amount of nodes in graph
        if (u.getName() >= numNodes || v.getName() >= numNodes || u.getName() < 0 || v.getName() < 0) {
            System.out.println("T-T" + numNodes + " " + u.getName() + " " + v.getName());
            throw new GraphException("Node(s) do(es) not exist.");
        }
        
        LinkedList<GraphEdge> uList = adjList.get(u.getName());     // list of edges for node u
        LinkedList<GraphEdge> vList = adjList.get(v.getName());     // list of edges for node u

        for (int i = 0; i < uList.size() ; i++) {     // look through linked list for edge that's (u, v) or (v, u)   
            if ((uList.get(i).firstEndpoint() == u && uList.get(i).secondEndpoint() == v) ||
                (uList.get(i).firstEndpoint() == v && uList.get(i).secondEndpoint() == u)) {
                    throw new GraphException("Edge already exists.");
            }
        }
        for (int i = 0; i < vList.size() ; i++) {       
            if ((vList.get(i).firstEndpoint() == u && vList.get(i).secondEndpoint() == v) ||
                (vList.get(i).firstEndpoint() == v && vList.get(i).secondEndpoint() == u)) {
                    throw new GraphException("Edge already exists.");
            }
        }
 
        GraphEdge newEdge = new GraphEdge(u, v, edgeType, label);

        adjList.get(u.getName()).add(newEdge); // add as an edge coming off of node u
        adjList.get(v.getName()).add(newEdge); // add as an edge coming off of node v
    }

    // return node w name u, throw if not found
    public GraphNode getNode(int u) throws GraphException {
        if (u >= numNodes || u < 0) {    // u not in graph because counter is # of nodes in graph
            throw new GraphException("Node does not exist.");
        }
        return nodes[u];
    }

    // return iterator storing edges incident on u, return null if 0 edges, throw if u dne
    public Iterator incidentEdges(GraphNode u) throws GraphException {
        // check if u exists
        if (u.getName() >= numNodes || u.getName() < 0) {
            throw new GraphException("Node(s) do(es) not exist.");
        }   // assume it exists from here on
        
        // check if u has edges
        if (adjList.get(u.getName()).size() == 0) return null;

        Iterator<GraphEdge> iter = adjList.get(u.getName()).iterator();
        return iter;
    }

    // return edge connecting u, v, throw if edge dne or u, v, dne in graph
    public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
        if (u == null || v == null) {throw new GraphException("node null");}
        // u and v not in graph if its name is bigger than amount of nodes in graph
        if (u.getName() >= numNodes || v.getName() >= numNodes || u.getName() < 0 || v.getName() < 0) {
            throw new GraphException("Node(s) do(es) not exist.");
        }        
        
        LinkedList<GraphEdge> uList = adjList.get(u.getName());     // list of edges for node u

        for (int i = 0; i < uList.size() ; i++) {     // look through linked list for edge that's (u, v) or (v, u)   
            if (((uList.get(i).firstEndpoint() == u && uList.get(i).secondEndpoint() == v)) || 
                ((uList.get(i).firstEndpoint() == v && uList.get(i).secondEndpoint() == u))) {
                    return uList.get(i);
            }
        }
        throw new GraphException("Edge does not exist.");
    }

    // return true if u, v adjacent, false otherwise, throw if u, v dne
    // nodes are adjacent if there exists an edge connecting them
    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
        if (u == null || v == null) {throw new GraphException("node null");}
        // u and v not in graph if its name is bigger than amount of nodes in graph
        if (u.getName() >= numNodes || v.getName() >= numNodes || u.getName() < 0 || v.getName() < 0) {
            throw new GraphException("Node(s) do(es) not exist.");
        }        
        
        LinkedList<GraphEdge> uList = adjList.get(u.getName());     // list of edges for node u

        for (int i = 0; i < uList.size() ; i++) {     // look through linked list for edge that's (u, v) or (v, u)   
            if (((uList.get(i).firstEndpoint() == u && uList.get(i).secondEndpoint() == v)) || 
                ((uList.get(i).firstEndpoint() == v && uList.get(i).secondEndpoint() == u))) {
                    return true;
            }
        }
        return false;
    }
}
