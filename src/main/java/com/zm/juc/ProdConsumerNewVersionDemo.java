package com.zm.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class  AirCondition3{//资源类

    private int number = 0;

    /**
     * ReentrantLock 可重入、非公平的递归锁
     * 可重入锁：是指该锁能够被多人使用
     * 递归锁：是指锁中还有锁
     * 非公平锁：简单来说就是不按顺序来，允许加塞
     */
    private Lock lock=new ReentrantLock();

    //获取Condition对象，主要是为了获取它的await() 和 signal()
    private Condition condition= lock.newCondition();

    /**
     * 注意一点：wait()、notifyAll()、notify()是在synchronized存在的情况下
     * 匹配使用的，没有synchronized时，就不要使用wait()、notifyAll()、notify()
     */
    public  void increment() throws Exception{

        lock.lock();//加锁
        try {
            //1.判断
            while(number !=0){
                condition.await();
                //this.wait();
            }
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t "+number);

            //3.通知
            condition.signalAll();
            //this.notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void decrement() throws Exception{

        lock.lock();//加锁
        try {
            //1.判断
            while(number ==0){
                condition.await();
                //this.wait();
            }
            //2.干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t "+number);

            //3.通知
            condition.signalAll();
            //this.notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 *  基于JDK1.8编写的新版本多线程生产者和消费者案例----使用锁ReentrantLock
 */
public class ProdConsumerNewVersionDemo {



    public static void main(String[] args) {

        AirCondition3 airCondition3 = new  AirCondition3();

        new Thread (()->{
            try {
                for (int i = 0; i < 10; i++) {
                    airCondition3.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread (()->{
            try {
                for (int i = 0; i < 10; i++) {
                    airCondition3.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();

        new Thread (()->{
            try {
                for (int i = 0; i < 10; i++) {
                    airCondition3.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"C").start();

        new Thread (()->{
            try {
                for (int i = 0; i < 10; i++) {
                    airCondition3.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"D").start();
    }

}
