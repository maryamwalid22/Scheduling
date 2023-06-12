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
public class Schedualing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean Exit=false;
        Scanner in = new Scanner(System.in);
        int Choice;
        int Quantum=3;
        List<ClsProcess> Prcs=new ArrayList<>();
        
        while(!Exit)
        {
            System.out.println("1- Enter Processes");
            System.out.println("2- Print Processes");
            System.out.println("3- Execute FCFS");
            System.out.println("4- Execute SJF");
            System.out.println("5- Execute Round Robin");
            System.out.println("98- Change Quantum");
            System.out.println("99- Clear List");
            System.out.println("0- Exit");
            System.out.print("Enter Your Choice:");
            Choice = in.nextInt();
            if(Choice==0)
                Exit=true;
            else if(Choice==99)
            {
                Prcs.clear(); //=new ArrayList<>();
            }
            else if(Choice==98)
            {
                System.out.print("Enter Quantum (Current="+Quantum+":");
                Quantum = in.nextInt();
            }
            else if(Choice==1)
            {
                // Enter Processes
                int i=Prcs.size();
                
                while(true)
                {
                    i++;
                    ClsProcess P=new ClsProcess(i);
                    
                    if(P.ReadProcess()==false)
                        break;
                    Prcs.add(P);
                    
                }
            }
            else if(Choice==2)
            {
                // Print Processes
                for(ClsProcess Proc:Prcs)
                {
                    Proc.PrintProcess();
                }
            }
            else if(Choice==3)
            {
                //FCFS
                FCFS Obj=new FCFS();
                Obj.Run(Prcs);
                Obj.PrintResults();
            }
            else if(Choice==4)
            {
                //SJF
                SJF Obj=new SJF();
                Obj.Run(Prcs);
                Obj.PrintResults();
            }
            else if(Choice==5)
            {
                //Round Robin
                RoundRobin Obj=new RoundRobin(Quantum);
                Obj.Run(Prcs);
                Obj.PrintResults();
            }
        }
    }
}
