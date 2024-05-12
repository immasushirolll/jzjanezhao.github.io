import java.util.Scanner;

public class TownLinkedList {   
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in);
      TownNode headTown = null;
      TownNode currTown = null;
      TownNode lastTown = null;
      int count;
      int inputValue;
      int i;
      
      count = scnr.nextInt();
   
      headTown = new TownNode(count);
      lastTown = headTown;

      for (i = 0; i < count; ++i) {
         inputValue = scnr.nextInt();
      
         currTown = new TownNode(inputValue);
      
         lastTown.insertAfter(currTown);
         lastTown = currTown;
      }

      /* Your code goes here */
      for (i = 0; i < count + 1; i++) {
         int val = scnr.nextInt();
         if (val >= 10) 
            System.out.println(val + " is plenty of neighbors.");
      }       
   }
}

class TownNode {
   private int neighborsVal;
   private TownNode nextNodeRef;

   public TownNode(int neighborsInit) {
      this.neighborsVal = neighborsInit;
      this.nextNodeRef = null;
   }

   public void insertAfter(TownNode nodeLoc) {
      TownNode tmpNext = null;

      tmpNext = this.nextNodeRef;
      this.nextNodeRef = nodeLoc;
      nodeLoc.nextNodeRef = tmpNext;
   }

   public TownNode getNext() {
      return this.nextNodeRef;
   }

   public int getNodeData() {
      return this.neighborsVal;
   }
}