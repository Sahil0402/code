import java.util.concurrent.Semaphore;

public class ReaderWriterProblemSolution {
    static Semaphore readLock=new Semaphore(1);
    static Semaphore writeLock=new Semaphore(1);
    volatile static int readCount=0;

    static class Read implements Runnable{
        @Override
        public void run() {
           try {
                //acquire
                readLock.acquire();
                synchronized(ReaderWriterProblemSolution.class){
                    readCount++;
                }
                if(readCount==1){
                    writeLock.acquire();
                }
                readLock.release();

                //reading

                System.out.println(Thread.currentThread().getName()+" is Reading");
                Thread.sleep(1500);
                System.out.println(Thread.currentThread().getName()+" finished reading");

                //releasing

                readLock.acquire();
                synchronized(ReaderWriterProblemSolution.class){
                    readCount--;
                }
                if(readCount==0){
                    writeLock.release();
                }
                readLock.release();


           } catch (Exception e) {
            e.printStackTrace();
           }
            
        }
    }

    static class Write implements Runnable{
        public void run(){
            try {
                writeLock.acquire();
                System.out.println(Thread.currentThread().getName()+" is writing");
                Thread.sleep(1500);
                System.out.println(Thread.currentThread().getName()+" finished writing");
                writeLock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        Read read=new Read();
        Write write=new Write();
        Thread t1=new Thread(read);
        t1.setName("Thread 1");
        Thread t2=new Thread(read);
        t2.setName("Thread 2");
        Thread t3=new Thread(write);
        t3.setName("Thread 3");
        Thread t4=new Thread(read);
        t4.setName("Thread 4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
