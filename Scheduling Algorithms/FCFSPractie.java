import java.util.Scanner;

public class FCFSPractie {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n;
        System.out.println("Enter the number of processes ");
        n=sc.nextInt();
        int at[]=new int[n];
        int bt[]=new int[n];
        int ct[]=new int[n];
        int tat[]=new int[n];
        int wt[]=new int[n];
        int pid[]=new int[n];

        float AWT=0,ATAT=0;
        int temp;
        for(int i=0;i<n;i++){
            System.out.println("enter the arrival time of process "+(i+1));
            at[i]=sc.nextInt();
            System.out.println("Enter the burst time of process "+(i+1));
            bt[i]=sc.nextInt();
            pid[i]=i+1;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n-(i+1);j++){
                if(at[j]>at[j+1]){
                    temp=at[j];
                    at[j]=at[j+1];
                    at[j+1]=temp;

                    temp=bt[j];
                    bt[j]=bt[j+1];
                    bt[j+1]=temp;


                    temp=pid[j];
                    pid[j]=pid[j+1];
                    pid[j+1]=temp;
                }
            }
        }

        for(int i=0;i<n;i++){
            if(i==0){
                ct[i]=at[i]+bt[i];
            }else{
                if(at[i]>ct[i-1]){
                    ct[i]=at[i]+bt[i];
                }
                else{
                    ct[i]=ct[i-1]+bt[i];
                }
                
            }
                tat[i]=ct[i]-at[i];
                wt[i]=tat[i]-bt[i];
                AWT+=wt[i];
                ATAT+=tat[i];
        }
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            if(ct[i]>max) max=ct[i];
        }
        for(int i=0;i<n;i++){
            if(at[i]<min) min=at[i];
        } 
        int res=max-min;

        System.out.println("\npid  arrival  brust  complete turn waiting");
for(int  i = 0 ; i< n;  i++)
{
System.out.println(pid[i] + "  \t " + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t"  + wt[i] ) ;
}
        System.out.println("Average waiting time : "+(AWT/n));
        System.out.println("Average turn around time : "+(ATAT/n));
        System.out.println("throughput is: "+n+"/"+res);
        sc.close();
    }
}
