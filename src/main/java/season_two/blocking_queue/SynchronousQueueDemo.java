package season_two.blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue演示
 */
public class SynchronousQueueDemo {
     public static void main(String[] args) {

         BlockingQueue<String> blockingQueue  = new SynchronousQueue<>();

         new Thread(()->{
             try {
                 System.out.println(Thread.currentThread().getName()+"\t put 1");
                 blockingQueue.put("1");
                 System.out.println(Thread.currentThread().getName()+"\t put 2");
                 blockingQueue.put("2");
                 System.out.println(Thread.currentThread().getName()+"\t put 3");
                 blockingQueue.put("3");
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         },"A").start();

         new Thread(()->{
             try {
                 //此处暂停是为了演示在没有移除元素的时候，上面添加元素是不能成功的
                 TimeUnit.SECONDS.sleep(5);
                 System.out.println(Thread.currentThread().getName()+" \t take "+blockingQueue.take());
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         },"B").start();

     }

}
