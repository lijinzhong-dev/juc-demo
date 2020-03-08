package season_two.prod_customer.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    //高并发下需要加上volatile
    private volatile boolean FLAG = true;//默认开启，进行生产+消费

    //多线程下不要写i++ 或++i 为了安全使用原子型，如AtomicInteger
    private  AtomicInteger atomicInteger =new AtomicInteger();

    BlockingQueue<String> blockingQueue =null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    //生产
    public  void  myProd() throws InterruptedException {
        String data=null;
        boolean retValue;
        while (FLAG){
            data =atomicInteger.incrementAndGet()+"";
            retValue= blockingQueue.offer(data,2 , TimeUnit.SECONDS);//将数据放到阻塞队列中
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入数据："+data+"成功！");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入数据："+data+"失败！");
            }
            TimeUnit.SECONDS.sleep(1);//为了演示方便，与业务无关
        }
        System.out.println(Thread.currentThread().getName()+"\t 停止插入数据，此时FLAG=false，生产动作结束！");
    }

    public  void  myConsumer()throws InterruptedException{

        String result=null;
        while (FLAG){
            result=blockingQueue.poll(2l, TimeUnit.SECONDS);
            if(null== result || result.equalsIgnoreCase("")){
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t 超时，消费退出！");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 消费数据："+result+"成功！");
            }
        }
    }
    public void stop(){
        this.FLAG=false;
    }
}
/**
 * 阻塞队列消费者生产者
 */
public class ProdConsumerBlockQueueDemo {
     public static void main(String[] args) throws InterruptedException {
         MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

         new Thread(()->{
             System.out.println(Thread.currentThread().getName()+"\t 启动,开始生产");
             try {
                 myResource.myProd();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         },"Prod").start();

         new Thread(()->{
             System.out.println(Thread.currentThread().getName()+"\t 消费启动,开始消费");
             try {
                 myResource.myConsumer();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         },"Consumer").start();

         TimeUnit.SECONDS.sleep(5);
         System.out.println("5秒后，叫停！活动结束");
         myResource.stop();
     }
}
