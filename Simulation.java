//Simulation.java
//Edward Lee
//Merges the Queue and the Job classes into a simulation of lines and work
import java.io.*;
import java.util.Scanner;
import java.lang.Math;

public class Simulation{
   //Opens the input and output files and reads the jobs to be done.
   public static void main(String[] args) throws IOException{
      Job[] unsorted = new Job[32];
      int i = 0;
      int j = 0;
      int k = 0;
      int l = 0;
      int totalWait = 0;
      int maxWait = 0;
      double averageWait = 0;
      int numJobs = 0;
      int time=0;
      Job mem;
      int jobsProcessed = 0;
      boolean updated = false;
      if(args.length<1){
         System.out.println("Must give exactly one input file.");
         System.exit(1);
      }
      Scanner input  = new Scanner(new File(args[0]));
      PrintWriter report = new PrintWriter(new FileWriter(args[0]+".rpt"));
      PrintWriter trace = new PrintWriter(new FileWriter(args[0]+".trc"));
      Queue SerpentineLine = new Queue();
      while(input.hasNextLine()){
         Job A;
         A = getJob(input);
         unsorted[numJobs] = A;
         numJobs++;
      }
      //Sorts the jobs from the input file and places them in the main Queue.
      if(numJobs>1){
         for(i=0; i<numJobs-1; i++){
            for(j=i+1; j<numJobs; j++){
               if(unsorted[i].getArrival()>unsorted[j].getArrival()){
                  mem = unsorted[i];
                  unsorted[i] = unsorted[j];
                  unsorted[j] = mem;
               }
            }
         }
      }
      for(i=0; i<numJobs; i++){
         SerpentineLine.enqueue((Job)unsorted[i]);
      }
      //Adds headers for the output files
      report.println("Report file: " + args[0] + ".rpt");
      report.println(numJobs + (numJobs==1?" Job:":" Jobs:"));
      report.println(SerpentineLine);
      trace.println("Trace file: " + args[0] + ".trc");
      trace.println(numJobs + (numJobs==1?" Job:":" Jobs:"));
      trace.println(SerpentineLine);
      report.println();
      trace.println();
      report.println("**********************************");
      //A series of nested loops that does the main work of the simulation
      for(i=1; i<numJobs; i++){
         time = 0;
         trace.println("***********************************");
         trace.println(i + (i==1?" processor:":" processors:"));
         trace.println("***********************************");
         trace.println("time=0");
         trace.println("0: " + SerpentineLine);
         Queue[] processors = new Queue[i];
         for(l=0; l<i; l++){
            processors[l] = new Queue();
            trace.println((l+1)+": ");
         }
         trace.println();
         while(jobsProcessed<numJobs){
            updated = false;
            while(((Job)SerpentineLine.peek()).getArrival()==time){
               mem = (Job)SerpentineLine.dequeue();
               processors[bestline(processors)].enqueue(mem);
               updated = true;
            }
            for(k=0; k<processors.length; k++){
               try{
                  mem = (Job)processors[k].peek();
                  if(mem.getFinish()==time){
                     mem = (Job)processors[k].dequeue();
                     SerpentineLine.enqueue(mem);
                     jobsProcessed++;
                     updated = true;
                  }
                  mem = (Job)processors[k].peek();
                  if((processors[k].length()>0)&&(mem.getFinish()==-1)){
                     ((Job)processors[k].peek()).computeFinishTime(time);
                  }
               }catch (QueueEmptyException e){
               }
            }
            time++;
            if(updated==true){
               trace.println("time=" + (time-1));
               trace.println("0: " + SerpentineLine);
               for(j=0; j<processors.length; j++){
                  trace.println(j+1 + ": " + processors[j]);
               }
               trace.println();
            }
         }
         mem = (Job)SerpentineLine.peek();
         maxWait = mem.getWaitTime();
         totalWait = 0;
         for(j=0; j<numJobs; j++){
            mem = (Job)SerpentineLine.dequeue();
            if(mem.getWaitTime()>maxWait){
               maxWait = mem.getWaitTime();
            }
            totalWait = totalWait + mem.getWaitTime();
            mem.resetFinishTime();
            SerpentineLine.enqueue(mem);
         }
         averageWait = (double)totalWait/(double)numJobs;
         report.println(i + (i==1?" processor: ":" processors: ") + "totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + Math.round(averageWait*100.0)/100.0);
         jobsProcessed = 0;
      }
   //Close the input and output files
   input.close();
   report.close();
   trace.close();
   }
   //Returns the index of the best Array to join
   public static int bestline(Queue[] Q){
      int length = Q[0].length();
      int i = 0;
        int result = 0;
      for(i=0; i<Q.length; i++){
         if(length>Q[i].length()){
            length = Q[i].length();
            result = i;
         }
      }
      return result;
   }
   //Returns the next Job in the Scanner
   public static Job getJob(Scanner in){
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }
}
