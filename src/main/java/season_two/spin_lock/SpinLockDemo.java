package season_two.spin_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 题目：实现一个自旋锁
 * 自旋锁的好处：循环比较获取直到成功为止，没有类似wait的阻塞方法.
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，B线程随后进来发现
 * 当前有线程持有锁，不是null，所以只能通过自旋锁等待，直到A线程释放锁后，B线程才能抢到。
 */
public class SpinLockDemo {
    //定义原子引用线程
    AtomicReference<Thread> atomicReference =new AtomicReference<>();

    public  void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t 线程进来...");

        //atomicReference中的线程不为空时，会一直循环等待
        while (!atomicReference.compareAndSet(null,thread )){

        }
    }
    public  void myUnLock(){
        Thread thread = Thread.currentThread();
        boolean b = atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+"\t 执行UnLock..." +b);
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();
            //让线程暂停4秒为了使让线程A持有锁保持一段时间
            try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnLock();//释放
        },"A").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(()->{
            spinLockDemo.myLock();
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnLock();//释放
        },"B").start();


    }
}
