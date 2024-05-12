import javax.xml.catalog.Catalog;
import java.io.IOException;;

public class FindShortestPath {
    public static void main(String[] args) throws Exception {
        try {
            if (args.length < 1) {
                throw new Exception("No input file specified");
            }
            String dungeonFileName = args[0];
            
            // create empty priority queue
            DLPriorityQueue pQueue = new DLPriorityQueue<>();

            // get starting chamber from provided class Dungeon
            Dungeon dungeon = new Dungeon(dungeonFileName);
            Hexagon start = dungeon.getStart();

            // add starting chamber to priority queue w priority 0, mark as enqueued
            pQueue.add(start, 0.0);
            start.markEnqueued();

            // while priority queue not empty and exit not found, perform following
            while (!pQueue.isEmpty() && dungeon.getExit() == null) {
                
                // remove chamber w smallest priority, mark dequeued
                Hexagon current = (Hexagon) pQueue.removeMin();
                current.markDequeued();
                
                // if current is exit, exit loop
                if (current.isExit()) {
                    break;
                }
                
                /* for the current chamber, if it doesn't have a dragon, neighbours don't have dragons, 
                 * and neighbours aren't null, a wall, and are not dequeued
                 */ 
                for (int i = 0; i < 6; i++) {
                    Hexagon neighbour = ((Hexagon) pQueue.removeMin()).getNeighbour(i);
                    if (!current.isDragon() && !current.getNeighbour(i).isDragon() && neighbour != null && !neighbour.isWall() && !neighbour.isMarkedDequeued()) {
                        
                        // calculate distance D
                        int D = 1 + current.getDistanceToStart();
                        
                        // if distance of neighbour to intial > D, set distance of neighbour to initial chamber to D
                        if (neighbour.getDistanceToStart() > D) {
                            neighbour.setDistanceToStart(D);

                            // set current as predecessor of neighbour
                            neighbour.setPredecessor(current);

                            /* if neighbour is enqueued, update priority of neighbour to newPriority = distance from
                             * from neighbour to initial + distance from neighbour to exit
                             */
                            double newPriority = 0.0;
                            if (neighbour.isMarkedEnqueued()) {
                                newPriority = neighbour.getDistanceToStart() + neighbour.getDistanceToExit(dungeon);
                                pQueue.updatePriority(neighbour, newPriority);
                            }
                            /* otherwise add this to queue and mark as enqueued */
                            else {
                                pQueue.add(neighbour, newPriority);
                                neighbour.markEnqueued();
                            }
                        }
                    }
                }   
            }
        }
        
        catch (IOException e1) {
            System.out.println("error reading file");
        }
        catch (InvalidDungeonCharacterException e2) {
            System.out.println("invalid dungeon");
        }
        catch (EmptyPriorityQueueException e3) {
            System.out.println("priority queue is empty");
        }
        catch (InvalidElementException e4) {
            System.out.println("element is invalid");
        }
        catch (InvalidNeighbourIndexException e5) {
            System.out.println("neighbour index is invalid");
        }
    }
}
