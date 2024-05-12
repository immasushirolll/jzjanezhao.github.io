import java.util.*;
import java.io.*;

public class Maze {
    private Graph graph = null;
    private int S;  // scale factor
    private int A;  // width
    private int L;  // length
    private int k;  // amount of coins
    private int entrance;
    private int exit;
    
    // constructor, read input file, build maze, throw if input dne or input incorrect
    public Maze(String inputFile) throws MazeException {
        try {
            BufferedReader input = new BufferedReader(new FileReader(inputFile));

            input.readLine();  // scale factor not used, skip
            A = Integer.parseInt(input.readLine());
            L = Integer.parseInt(input.readLine());
            k = Integer.parseInt(input.readLine());

            graph = new Graph(A*L);

            String line = input.readLine();
            if (line.charAt(0) == 's') {
                entrance = 0;
            }
            if (line.charAt(0) == 'x') {
                exit = 0;
            }
            for (int i = 1; i < A; i++) {
                if (line.charAt(2*i) == 's') {
                    entrance = i;
                }
                else if (line.charAt(2*i) == 'x') {
                    exit = i;
                }

                int type = -1;
                if (line.charAt(2*i-1) == 'c') {
                    type = 0;
                }
                else if (line.charAt(2*i-1) >= '0' && 
                    line.charAt(2*i-1) <= '9') {
                    type = (int) line.charAt(2*i-1) - '0';
                }

                if (type != -1) graph.insertEdge(graph.getNode(i-1), graph.getNode(i), type, line);
            }

            for (int i = 1; i < L; i++) {
                line = input.readLine();
                if (line.charAt(0) == 'c') {
                    graph.insertEdge(graph.getNode(A*(i-1)),
                                     graph.getNode(A*i), 0,
                                     String.valueOf(line.charAt(0)));
                }
                else if (line.charAt(0) >= '0' && 
                         line.charAt(0) <= '9') {
                    graph.insertEdge(graph.getNode(A*(i-1)),
                                     graph.getNode(A*i), (int) line.charAt(0) - '0',
                                     String.valueOf(line.charAt(0)));
                }

                for (int j = 1; j < A; j++) {
                    int type = -1;
                    if (line.charAt(2*j) == 'c') {
                        type = 0;
                    }
                    else if (line.charAt(2*j) >= '0' &&
                             line.charAt(2*j) <= '9') {
                        type = line.charAt(2*j) - '0';
                    }
                    if (type != -1)  {
                        graph.insertEdge(graph.getNode(A*(i-1) + j),
                                         graph.getNode(A * i + j), type,
                                         String.valueOf(line.charAt(2*j)));
                    }
                }

                line = input.readLine();
                if (line.charAt(0) == 's') {
                    entrance = A*i;
                }
                else if (line.charAt(0) == 'x') {
                    exit = A*i;
                }
                for (int j = 1; j < A; j++) {
                    if (line.charAt(2*j) == 's') {
                        entrance = A*i+j;
                    }
                    else if (line.charAt(2*j) == 'x') {
                        exit = A*i+j;
                    }
                    int type = -1;
                    if (line.charAt(2*j-1) == 'c') {
                        type = 0;
                    }
                    else if (line.charAt(2*j-1) >= '0' &&
                             line.charAt(2*j-1) <= '9') {
                        type = line.charAt(2*j-1) - '0';

                    }

                    if (type != -1) {
                        graph.insertEdge(graph.getNode(A*i+j-1),
                                         graph.getNode(A*i+j), type,
                                         String.valueOf(line.charAt(2*j-1)));
                    }
                }

            }
            input.close();
        } catch (Exception e) {
            throw new MazeException("big sadge, problem reading file");
        }
    }

    // returns reference to Graph representing maze, throw if graph null
    public Graph getGraph() {
        return graph;
    }

    // returns Iterator w nodes of path from entrance to exit, return null if path dne
    public Iterator solve() {
        Stack<GraphNode> path = new Stack<GraphNode>();
        // System.out.println("help");
        try {
            if (!pathfind(path, graph.getNode(entrance), graph.getNode(exit), k)) {
                return null;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return path.iterator();
    }

    // depth-first search
    private boolean pathfind(Stack<GraphNode> path, GraphNode root, GraphNode exit, int coins) {
        root.mark(true);
        path.push(root);
        // System.out.println("current " + root.getName() + " coins: " + coins);

        if (root.equals(exit)) {
            return true;
        } else {
            try {
                Iterator<GraphEdge> checkEdge = graph.incidentEdges(root);
                while (checkEdge.hasNext()) {
                    GraphEdge sadge = checkEdge.next();
                    GraphNode whichOne = sadge.secondEndpoint();
                    if (root.equals(whichOne)) {
                        whichOne = sadge.firstEndpoint();
                    }
                    // System.out.println("checking edge " + whichOne.getName());

                    if (!whichOne.isMarked()) {
                        if (coins - sadge.getType() < 0) {
                            continue;
                        }
                        //System.out.println("node is not marked!");
                        if (pathfind(path, whichOne, exit, coins - sadge.getType())) return true;
                    }
                    // System.out.println("node is marked!");
                }
            } catch (Exception e) {
                return false;
            }

            path.pop().mark(false);
            return false;
        }
    }
}
