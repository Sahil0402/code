import java.util.Scanner;

public class SRTFPractice {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n;
        
        System.out.println("Enter the number of processes");
        n=sc.nextInt();
        int at[]=new int[n];
        int bt[]=new int[n];
        int ct[]=new int[n];
        int tat[]=new int[n];
        int wt[]=new int[n];
        int pid[]=new int[n];
        int f[]=new int[n];
        int k[]=new int[n];

        for(int i=0;i<n;i++){
            System.out.println("enter the arrival time of process "+(i+1));
            at[i]=sc.nextInt();
            System.out.println("Enter the burst time of process "+(i+1));
            bt[i]=sc.nextInt();
            k[i]=bt[i];
            f[i]=0;
            pid[i]=i+1;
        }
        int tot=0,st=0;
        float AWT=0,ATAT=0;
        while(true){
            int c=n,min=999;
            if(tot==n) break;

            for(int i=0;i<n;i++){
                if(at[i]<=st && f[i]==0 && bt[i]<min){
                    min=bt[i];
                    c=i;
                }
            }

            if(c==n){
                st++;
            }else{
                bt[c]--;
                st++;
                if(bt[c]==0){
                    ct[c]=st;
                   f[c]=1;
                   tot++;
                }
            }
        }

        for(int i=0;i<n;i++){
            tat[i]=ct[i]-at[i];
            wt[i]=tat[i]-k[i];
            ATAT+=tat[i];
            AWT+=wt[i];
        }
        System.out.println("\n PID     arrival     Burst     Completion     Turn Around     Waiting");
        for(int i=0;i<n;i++){
            System.out.println(pid[i] +"\t"+ at[i]+"\t"+ k[i] +"\t"+ ct[i] +"\t"+ tat[i] +"\t"+ wt[i]);
        }
        System.out.println("Average waiting time : "+(AWT/n));
        System.out.println("Average turn around time : "+(ATAT/n));
        sc.close();
    }
}
