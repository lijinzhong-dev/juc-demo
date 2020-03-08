package season_two.again_in_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone2 implements  Runnable{ //资源类

    Lock lock =new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 执行get()");
            set();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 执行set()");
        }finally {
            lock.unlock();
        }
    }
}

/**
 * 可重入锁（递归锁）演示:ReentrantLock是可重入锁
 */
public class ReentrantLockDemo {
     public static void main(String[] args) {
         Phone2 phone2 = new Phone2();

         Thread t1 = new Thread(phone2);
         Thread t2 = new Thread(phone2);
         t1.start();
         t2.start();
     }
}
