package com.zm.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{//资源类

    /**
     * 标志位，根据该属性的值来决定线程按顺序执行，如：
     * number =1  执行线程A
     * number =2  执行线程B
     * number =3  执行线程C
     */
    private int number =1;

    //获取锁
    Lock lock =new ReentrantLock();

    //一把锁配3把钥匙
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public  void print5(){
        lock.lock();
        try {
            //1 判断
            while (number !=1){
                c1.await();
            }

            //2 干活
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName() +"\t" +i);
            }

            //3 通知
            number =2;//修改标志位，一定要在c2.signal();之前
            c2.signal(); //通知第二个线程
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void print10(){
        lock.lock();
        try {
            //1 判断
            while (number !=2){
                c2.await();
            }

            //2 干活
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName() +"\t" +i);
            }

            //3 通知
            number =3;//修改标志位，一定要在c3.signal();之前
            c3.signal(); //通知第3个线程
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void print15(){
        lock.lock();
        try {
            //1 判断
            while (number !=3){
                c3.await();
            }

            //2 干活
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName() +"\t" +i);
            }

            //3 通知
            number =1;//修改标志位，一定要在c1.signal();之前
            c1.signal(); //通知第1个线程
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}

/**
 * 该案例主要用来展现Lock相对于synchronized来说能够使用多个线程之间按顺序执行。
 *
 * 案例实现目标：
 *  多个线程之间按顺序调用，实现：线程A --> 线程B --> 线程C
 *  3个线程启动后，要求如下：
 *      线程A打印5次、线程B打印10次、线程C打印15次
 *      接着
 *      线程A打印5次、线程B打印10次、线程C打印15次
 *      ...
 *      进行10轮
 */
public class ConditionDemo {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

            new Thread (()->{
                for (int i = 1; i <=10 ; i++) {
                    shareData.print5();
                }
            },"线程A").start();

            new Thread (()->{
                for (int i = 1; i <=10 ; i++) {
                    shareData.print10();
                }
            },"线程B").start();

            new Thread (()->{
                for (int i = 1; i <=10 ; i++) {
                    shareData.print15();
                }
            },"线程C").start();
        }
}
