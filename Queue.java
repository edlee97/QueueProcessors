//Queue.java
//Creates the Queue ADT for use in Simulation
public class Queue implements QueueInterface{
   //Creates the private Linked List elements of the Queue
   private class Spot{
      Object obj;
      Spot next;
      Spot(Object x){
         obj = x;
         next = null;
      }
   }
   private Spot front;
   private int queueLength;
   //Creator for the Queue
   public Queue(){
      front = null;
      queueLength = 0;
   }
   //Returns a boolean representing whether or not the Queue is empty
   public boolean isEmpty(){
      return(queueLength==0);
   }
   //Returns an int, representing the length of the queue
   public int length(){
      return queueLength;
   }
   //Adds a Spot to the end of the queue (and also, the object it carries)
   public void enqueue(Object newItem){
      Spot J = front;
      if(queueLength>0){
         while(J.next!=null){
            J=J.next;
         }
         J.next = new Spot(newItem);
         queueLength++;
         return;
      }else{
         front = new Spot(newItem);
         queueLength++;
      }
   }
   //Returns and deletes the item in the front of the Queue
   public Object dequeue() throws QueueEmptyException{
      if(queueLength==0){
         throw new QueueEmptyException("Queue is currently empty.");
      }
      Spot J = front;
      front = front.next;
      queueLength--;
      return J.obj;
   }
   //Returns the item in the front of the Queue
   public Object peek() throws QueueEmptyException{
      if(queueLength==0){
         throw new QueueEmptyException("Queue is currently empty.");
      }
      return front.obj;
   }
   //Erases all entries in this Queue, making it empty again
   public void dequeueAll() throws QueueEmptyException{
      if(queueLength==0){
         throw new QueueEmptyException("Queue is already empty.");
      }
      front = null;
      queueLength = 0;
   }
   //Provides the String representation of the Queue
   public String toString(){
      StringBuffer nah = new StringBuffer();
      Spot M = front;
      while (M!=null){
         nah.append(M.obj.toString() + " ");
         M = M.next;
      }
      return new String (nah);
   }
}
