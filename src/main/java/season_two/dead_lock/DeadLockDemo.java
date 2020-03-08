package season_two.dead_lock;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements  Runnable{//资源类

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockA+"\t 尝试获得："+lockB);
            //暂停2秒钟,仅仅为了演示方便和业务无关
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockB+"\t 尝试获得："+lockA);
            }
        }
    }
}

/**
 * 死锁演示
 */
public class DeadLockDemo {
     public static void main(String[] args) {
            String lockA="lockA";
            String lockB="lockB";

            //线程1
            new Thread(new HoldLockThread(lockA,lockB),"A").start();
            //线程2
            new Thread(new HoldLockThread(lockB,lockA),"B").start();
      }
}

