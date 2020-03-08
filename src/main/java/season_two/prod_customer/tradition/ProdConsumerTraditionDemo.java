package season_two.prod_customer.tradition;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
class ShareData{
    private int num = 0;

    Lock lock =new ReentrantLock();

    private Condition condition= lock.newCondition();


    public  void addOne(){
        lock.lock();
        try {
            //判断
            while (this.num!=0){
                condition.await();
            }
            //干活
            num++;
            System.out.println(Thread.currentThread().getName()+"\t "+num);
            //通知
            condition.signalAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void decrOne(){
        lock.lock();
        try {
            //判断
            while (this.num==0){
                condition.await();
            }
            //干活
            num--;
            System.out.println(Thread.currentThread().getName()+"\t "+num);
            //通知
            condition.signalAll();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
/**
 * 传统消费者生产者:
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1一个减1，来5轮。
 */
public class ProdConsumerTraditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i=1;i<=5;i++){
               shareData.addOne();
            }
        },"A").start();

        new Thread(()->{
            for (int i=1;i<=5;i++){
               shareData.decrOne();
            }
        },"B").start();
    }
}

