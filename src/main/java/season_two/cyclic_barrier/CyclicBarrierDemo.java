package season_two.cyclic_barrier;

import season_two.count_down_latch.CountryEnum;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 演示CyclicBarrier类，
 *  当阻塞线程的数量到达某一个数值时，才会让这些线程开始干活
 */
public class CyclicBarrierDemo {
     public static  void main(String[] args)  throws  Exception{

         /**
          *  API中CyclicBarrier的构造函数
          * 创建一个新的 CyclicBarrier ，当给定数量的线程（线程）等待时，它将跳闸，
          * 当屏障跳闸时执行给定的屏障动作，由最后一个进入屏障的线程执行。
          */
         //CyclicBarrier(int parties, Runnable barrierAction)

         //该行代码的两个参数分别表示：当阻塞的线程数量到达7个时，才执行System.out.println("****召唤神龙")语句
         CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{System.out.println("****召唤神龙");});

         for (int i=1;i<=7;i++){
             final  int tempInt =i;
             new Thread(()->{
                 System.out.println(Thread.currentThread().getName()+"\t 收集到第"+tempInt+"龙珠!");
                 try {
                     cyclicBarrier.await();//累加直到上面指定的7个
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }, String.valueOf(i)).start();
         }


     }
}
