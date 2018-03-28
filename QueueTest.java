public class QueueTest{
   public static void main(String[]args){
      Queue TestQueue = new Queue();
      Job A = new Job(1, 1);
      Job B = new Job(2, 2);
      Job C = new Job(3, 3);
      Job D = new Job(4, 4);
      System.out.println("***********************************************");
      System.out.println("Initiating the QueueTest.");
      printLine();
      System.out.println("Testing isEmpty and length on an empty Queue.");
      System.out.println(TestQueue.isEmpty());
      System.out.println(TestQueue.length());
      if((TestQueue.isEmpty()==false) || !(TestQueue.length()==0)){
         bugfound();
      }
      pass();
      printLine();
      System.out.println("Testing isEmpty and length on populated Queue.");
      TestQueue.enqueue(A);
      TestQueue.enqueue(B);
      TestQueue.enqueue(C);
      TestQueue.enqueue(D);
      System.out.println(TestQueue.isEmpty());
      System.out.println(TestQueue.length());
      if((TestQueue.isEmpty()==true) || !(TestQueue.length()==4)){
         bugfound();
      }
      pass();
      printLine();
      System.out.println("Testing dequeue and peek.");
      System.out.println(TestQueue.peek());
      System.out.println(TestQueue.dequeue());
      System.out.println(TestQueue.peek());
      System.out.println(TestQueue.dequeue());
      if(!(TestQueue.length()==2)){
         bugfound();
      }
      pass();
      printLine();
      System.out.println("Testing dequeueAll.");
      TestQueue.dequeueAll();
      System.out.println(TestQueue.length());
      if(TestQueue.isEmpty()==false){
         bugfound();
      }
      pass();
      printLine();
      System.out.println("Testing toString.");
      TestQueue.enqueue(A);
      TestQueue.enqueue(B);
      TestQueue.enqueue(C);
      TestQueue.enqueue(D);
      System.out.println(TestQueue);
      System.out.println("All tests passed. Have a nice day.");
      System.out.println("***********************************************");
   }
   static void bugfound(){
      System.out.println("Error found. Exiting now.");
      System.exit(1);
   }
   static void printLine(){
      System.out.println("-----------------------------------------------");
   }
   static void pass(){
      System.out.println("Test passes.");
   }
}
