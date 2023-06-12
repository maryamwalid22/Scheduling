/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schedualing_lab;

import java.util.*;

/**
 *
 * @author user
 */
public class ClsProcess implements Comparable<ClsProcess>,Cloneable{

    
    String Name;
    int BurstTime;
    int ArrivalTime;
    int WaitTime;
       
    

    /**
     * Search the Prcs list to find the process with name ParmName
     * @param Prcs List of processes
     * @param ParmName Name of the process to find
     * @return The process if exists and null if not exists
     */
    static ClsProcess Find(List<ClsProcess> Prcs, String ParmName) {
        for(ClsProcess Proc:Prcs)
            if(Proc.Name.equals(ParmName))
                return Proc;
        return null;
    }
    
    /**
     * Set statistics of all processes in Prcs to zero
     * @param Prcs 
     */
    static void SetAllStatisticsZero(List<ClsProcess> Prcs) {
        for(ClsProcess Proc:Prcs)
            Proc.SetStatisticsZero();
    }
    
    /**
     * Set the process statistics fields to zero
     */
    private void SetStatisticsZero() {
        WaitTime=0;
    }

    /**
     * 
     * @param ProcessNumber 
     */
    public ClsProcess(int ProcessNumber) {
        Name="P"+ProcessNumber;
    }
    
    boolean ReadProcess()
    {
        Scanner in = new Scanner(System.in);
        System.out.println(Name +": ");
        System.out.print("Enter BurstTime (0 for exit): ");
        BurstTime=in.nextInt();
        if(BurstTime==0)
            return false;
        System.out.print("Enter ArrivalTime: ");
        ArrivalTime=in.nextInt();
        return true;
        
    }
    void PrintProcess()
    {
//        System.out.println(Name +": ");
//        System.out.println("\tBurst Time: "+BurstTime);
//        System.out.println("\tArrival Time: "+ArrivalTime);
//        System.out.println("\tWait Time: "+WaitTime);
        System.out.println(this);
    }

    @Override
    public int compareTo(ClsProcess t) {
        if(this.ArrivalTime<t.ArrivalTime)
            return -1;
        else if(this.ArrivalTime==t.ArrivalTime)
            return 0;
        else
            return 1;
    }
    
   
    @Override
    public String toString(){
        return Name +": "
                +"\n\tBurst Time: "+BurstTime
                +"\n\tArrival Time: "+ArrivalTime
                +"\n\tWait Time: "+WaitTime+"\n";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
	 return super.clone();
    }
}

//compare to SJF
class ArrivalBurstComparator implements Comparator<ClsProcess>, Cloneable {
    @Override
    public int compare(ClsProcess a, ClsProcess b) {
        if(a.ArrivalTime<b.ArrivalTime)
            return -1;
        else if(a.ArrivalTime==b.ArrivalTime)
            if(a.BurstTime<b.BurstTime)
                return -1;
            else if(a.BurstTime==b.BurstTime)
                return 0;
            else
                return 1;
        else
            return 1;
    }
}