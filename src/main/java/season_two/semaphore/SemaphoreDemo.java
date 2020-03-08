package season_two.semaphore;

import season_two.count_down_latch.CountryEnum;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位
        for (int i=1;i<=6;i++){//模拟6台汽车（6台汽车抢占3个停车位）
            new Thread(()->{
                try {
                    semaphore.acquire();//抢占车位
                    System.out.println(Thread.currentThread().getName()+"\t 抢占车位!");
                    //车停2秒钟
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"\t 停车2秒后离开车位!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放停车位
                }
            }, String.valueOf(i)).start();
        }
    }
}
