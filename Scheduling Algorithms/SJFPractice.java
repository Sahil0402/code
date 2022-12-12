import java.util.Scanner;

public class SJFPractice {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n,at[],bt[],ct[],tat[],wt[],pid[],f[];
        System.out.println("Enter the no of processes : ");
        n=sc.nextInt();
        at=new int[n];
        bt=new int[n];
        ct=new int[n];
        tat=new int[n];
        wt=new int[n];
        pid=new int[n];
        f=new int[n];

        int st=0,tot=0;
        float AWT=0,ATAT=0;

        for(int i=0;i<n;i++){
            System.out.println("Enter the arrival time of process "+(i+1));
            at[i]=sc.nextInt();
            System.out.println("Enter the burst time of Process "+(i+1));
            bt[i]=sc.nextInt();
            pid[i]=i+1;
            f[i]=0;
        }
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
            }
            else{
                ct[c]=st+bt[c];
                st+=bt[c];
                tat[c]=ct[c]-at[c];
                wt[c]=tat[c]-bt[c];
                f[c]=1;
                tot++;
            }

        }

        System.out.println("\n PID     Burst     Completion     Turn Around     Waiting");
        for(int i=0;i<n;i++){
            AWT+=wt[i];
            ATAT+=tat[i];
            System.out.println(pid[i]+"\t\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+tat[i]+"\t\t"+wt[i]);
        }
        System.out.println("Average waiting time : "+(AWT/n));
        System.out.println("Average turn around time : "+(ATAT/n));
        sc.close();


    }
}
