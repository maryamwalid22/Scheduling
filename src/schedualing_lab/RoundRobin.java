/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedualing_lab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Haider
 */
public class RoundRobin {
    float AverageWaitTime;
    int Quantum;
    float AvgturnArouTime;
    int Turnaround;
    
    RoundRobin(int ParmQuantum) {
        Quantum=ParmQuantum;
    }
    
    void Run(List<ClsProcess> Prcs)
    {
        if(Prcs.isEmpty())
            return;
        
        ClsProcess.SetAllStatisticsZero(Prcs); // Set statistics(like wait time) to zero
        
        Collections.sort(Prcs);
        
        List<ClsProcess> PrcsQueue=new ArrayList<>(); // Creating a new list to be manipulated as queue
        
        int CurTime=0;      // Used to know the current moment of schedualing
        AverageWaitTime=0;
        ClsProcess CurrentProc=null; // refers to the current process under running
        int ProcessIndex=0;      // Used to know the Last process not inserted to the queue
        
        try {        
            // Move the first process to the ready queue
            PrcsQueue.add((ClsProcess) Prcs.get(ProcessIndex).clone()); // Copy the process to the ready Queue
            CurTime=PrcsQueue.get(ProcessIndex).ArrivalTime;   // Set the current time to the first 
                                                    // process arrival time
            ProcessIndex++;
            
            while((Prcs.size()<ProcessIndex) || !PrcsQueue.isEmpty()  // Loop until original list parsed completely
                    || CurrentProc!=null)                             // and queue list is empty
                                                                      // and no current Process
            {
                // Add all Processes that arrived before current time to the queue
                for( ; ProcessIndex< Prcs.size();ProcessIndex++)
                {
                    ClsProcess Proc=Prcs.get(ProcessIndex);
                    if(Proc.ArrivalTime<=CurTime )
                    {
                        PrcsQueue.add((ClsProcess) Proc.clone()); // Copy the process to the ready Queue
                    }
                    else
                        // if this process is not arrived before current time then stop the iteration  
                        // and continue schedualing
                        break;
                }
                
                // If Current process has remaining burst time then add it to the queue
                if(CurrentProc!=null)
                {
                    PrcsQueue.add(CurrentProc);
                    
                }
                // if Ready Queue is empty then increment current time and loop
                if(PrcsQueue.isEmpty())
                {
                    CurTime++;
                    continue;
                }
                // Choose the first process in the Ready Queue
                CurrentProc=PrcsQueue.get(0);
                PrcsQueue.remove(0);

                // Execute
                CurrentProc.WaitTime+=CurTime-CurrentProc.ArrivalTime; 
                if(CurrentProc.BurstTime<=Quantum){
                    CurTime+=CurrentProc.BurstTime;
                    // Update original process with the new wait time
                    ClsProcess.Find(Prcs, CurrentProc.Name).WaitTime=CurrentProc.WaitTime;
                    CurrentProc=null;       // No need to track the process
                                            // It completed its work
                }
                else
                {
                    CurrentProc.BurstTime-=Quantum;
                    CurTime+=Quantum;
                    CurrentProc.ArrivalTime=CurTime; // pretend that this process is 
                                                     // arrived this time to calculate the wait
                                                     // time correctlly
             Turnaround=CurrentProc.WaitTime-CurrentProc.BurstTime;
             
                }
            }
            
            for(ClsProcess Proc:Prcs)
                AverageWaitTime+=Proc.WaitTime;
            AverageWaitTime/=Prcs.size();
            AvgturnArouTime+=AverageWaitTime;
            AvgturnArouTime=AverageWaitTime/Turnaround;
            
        } catch (CloneNotSupportedException ex) {
            System.out.println("Error: Failed. The clone is not supported");
        }
    }
    
    void PrintResults()
    {

        System.out.println("Average Wait Time: "+AverageWaitTime);
        
    }
}
