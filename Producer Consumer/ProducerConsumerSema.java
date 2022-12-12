import java.util.concurrent.Semaphore;

class Queue{
    int item;
    Semaphore cons=new Semaphore(0);
    Semaphore prod=new Semaphore(1);

    void get(){
        try {
            cons.acquire();
        } catch (Exception e) {
           e.printStackTrace();
        }

        System.out.println("Consumer consumed item "+item);

        prod.release(); //to notify
    }

    void put(int item){
        try {
            prod.acquire();
        } catch (Exception e) {
           e.printStackTrace();
        }

        this.item=item;
        System.out.println("Producer Produced item "+item);

        cons.release(); //to notify
    }
}

class Producer implements Runnable{

    Queue q;
    Producer( Queue q){
        this.q=q;
        new Thread(this, "Producer").start();
    }
    public void run(){
        for(int i=0;i<5;i++){
            q.put(i);
        }
    }
}

class Consumer implements Runnable{
    Queue q;
    Consumer(Queue q){
        this.q=q;
        new Thread(this,"Consumer").start();
    }
    public void run(){
        for(int i=0;i<5;i++){
            q.get();
        }
    }
}

public class ProducerConsumerSolution {
    public static void main(String[] args) {
        Queue q=new Queue(); //create empty buffer
        new Consumer(q); // start con thread
        new Producer(q); // start prod thread
    }
}
