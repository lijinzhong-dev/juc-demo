package com.zm.juc;

class  AirCondition2{//资源类

    private int number = 0;


    public synchronized void increment() throws Exception{

        /**
         * 特别要注意：只要涉及到线程横向的通信和互访调用，
         * 对于wait() 、notifyAll()、notify()必须使用while进行重新判断，千万不能使用if做判断！
         */
        //1.判断
        while(number !=0){
            this.wait();
        }

        //2.干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t "+number);

        //3.通知
        this.notifyAll();//唤醒其他线程去消费
    }

    public synchronized void decrement() throws Exception{

        //1.判断
        while(number ==0){
            this.wait();
        }
        //切记 不能用if判断，否则程序会出错！，因为中断和虚假唤醒是可能存在的，必须用while重新判断
        /*if(number ==0){
            this.wait();
        }*/

        //2.干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t "+number);

        //3.通知
        this.notifyAll();
    }
}

/**
 * 题目：现在4个线程:2个生产、2个消费，操作初始值为0的一个变量
 * 实现目标：2个线程对该变量变量加1，2个线程对该变量减1，实现交替操作，并操作10轮。
 *
 * 1. 高内聚低耦合前提下，线程操作资源类
 * 2. 判断 + 干活 +通知
 * 3. 防止线程的虚假唤醒，只要有wait,就需要while对其进行判断，而不能使用if
 */
public class ProdConsumerDemo02 {
    public static void main(String[] args) throws InterruptedException {

        AirCondition2 airCondition = new AirCondition2();

        new Thread (()->{
            try {
                for (int i = 0; i < 10; i++) {
                    airCondition.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread (()->{
            try {
                for (int i = 0; i < 10; i++) {
                    airCondition.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            },"B").start();

        new Thread (()->{
            try {
                for (int i = 0; i < 10; i++) {
                    airCondition.increment();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"C").start();

        new Thread (()->{
            try {
                for (int i = 0; i < 10; i++) {
                    airCondition.decrement();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"D").start();
    }


}
