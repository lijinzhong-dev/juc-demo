package season_two.aba;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 演示ABA问题及ABA解决方案
 */
public class ABADemo {
    static AtomicReference<Integer>  atomicReference=new AtomicReference<>(100);
    //指定初始值，并指定时间戳（类似于版本号）
    static AtomicStampedReference<Integer>  atomicStampedReference=new AtomicStampedReference<>(100,1);

     public static void main(String[] args) {
       System.out.println("===========以下是ABA问题的产生===============");

       new Thread(()->{
           atomicReference.compareAndSet(100,101 );
           atomicReference.compareAndSet(101,100 );
       },"t1").start();

       new Thread(()->{
           //线程睡1秒
           try {TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
           System.out.println(atomicReference.compareAndSet(100,2019 ) +"\t"+ atomicReference.get());;
       },"t2").start();

         try {TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

         System.out.println("===========以下是ABA问题的解决===============");
         new Thread(()->{
             //获取数据的初始版本号
             int stamp = atomicStampedReference.getStamp();
             System.out.println(Thread.currentThread().getName()+"\t第1次版本号:"+stamp);
             //让线程停止的为了让下面的线程t4也拿到数据的初始版本号：1
             try {TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
             //指定期望值、修改值、期望版本号、修改版本号
             atomicStampedReference.compareAndSet(100,101 , 1, atomicStampedReference.getStamp()+1);
             System.out.println(Thread.currentThread().getName()+"\t第2次版本号:"+atomicStampedReference.getStamp());

             atomicStampedReference.compareAndSet(101,100 , 2, atomicStampedReference.getStamp()+1);
             System.out.println(Thread.currentThread().getName()+"\t第3次版本号:"+atomicStampedReference.getStamp());

         },"t3").start();

         new Thread(()->{
             //获取数据的初始版本号
             int stamp = atomicStampedReference.getStamp();
             System.out.println(Thread.currentThread().getName()+"\t第1次版本号:"+stamp);
             //此处让t4线程停止3秒钟，是为了让t3线程完成ABA操作
             try {TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
             System.out.println(atomicStampedReference.compareAndSet(100,2019 , stamp, stamp+1)+"\t"+atomicStampedReference.getReference());
         },"t4").start();
      }
}
