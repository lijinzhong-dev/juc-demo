package com.zm.juc;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{//资源类

    private int number =30;

    //ReentrantLock是 可重入锁
    Lock lock=new ReentrantLock();

    public  void sale(){

        lock.lock();//加锁
        try {
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+"\t 卖出第["+(number--)+"]张票,\t 还剩下["+number+"]张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();//释放锁
        }
    }
}


/**
 * 题目：3个售票员  卖   30张票
 *
 * 笔记：如何编写企业级的代码
 * 固定的编程套路 + 模板是什么？
 * 1.在高内聚低耦合的前提下：线程     操作   资源类
 *
 * 复习线程状态：
 *  1.NEW               新建状态
 *  2.RUNNABLE          运行状态
 *  3.BLOCKED           阻塞状态
 *  4.WAITING           等待状态
 *  5.TIMED_WAITING     等待状态(指定等待时间)
 *  6.TERMINATED        终止状态
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) {//主线程，一切程序的入口

        //资源类，代表票资源
        Ticket ticket = new Ticket();



       /* //线程，代表A售票员
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i <= 40; i++){
                    ticket.sale();//调用资源
                }
            }
        }, "A售票员").start();

        //线程，代表B售票员
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i <= 40; i++){
                    ticket.sale();//调用资源
                }
            }
        }, "B售票员").start();

        //线程，代表C售票员
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i <= 40; i++){
                    ticket.sale();//调用资源
                }
            }
        }, "C售票员").start();
*/

       //上面创建3个线程代码比较繁琐，可以使用Lambda表达式简化
        new Thread(() -> {for (int i=1;i <= 40; i++){ticket.sale();}},"A售票员").start();
        new Thread(() -> {for (int i=1;i <= 40; i++){ticket.sale();}},"B售票员").start();
        new Thread(() -> {for (int i=1;i <= 40; i++){ticket.sale();}},"C售票员").start();

        new Thread(() -> {for (int i=1;i <= 40; i++){ticket.sale();}},"D售票员").start();
    }
}
