/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedualing_lab;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Haider
 */
public class SJF {
float AverageWaitTime;
   float AvgturnArouTime;
    int Turnaround;
    
    
    void Run(List<ClsProcess> Prcs)
    {
        Collections.sort(Prcs,new ArrivalBurstComparator());

        int CurTime=0;
        AverageWaitTime=0;
        for(ClsProcess Proc:Prcs)
        {
            if(CurTime<Proc.ArrivalTime) // Handle idle gap if any
                CurTime=Proc.ArrivalTime;
            Proc.WaitTime=CurTime-Proc.ArrivalTime;
            AverageWaitTime+=Proc.WaitTime;
            CurTime+=Proc.BurstTime;
            Turnaround=Proc.BurstTime-Proc.WaitTime;
             //AvgturnArouTime+=AverageWaitTime;
                        AvgturnArouTime=Proc.BurstTime+Proc.WaitTime;

        }
        AverageWaitTime/=Prcs.size();
          //AvgturnArouTime=AverageWaitTime/Turnaround;
    }
    
    void PrintResults()
    {

        System.out.println("Average Wait Time: "+AverageWaitTime);
         // System.out.println("Turnaround Time: "+Turnaround);

        System.out.println("Average Turnaroun Time: " +AvgturnArouTime);
        
        
    }
}
