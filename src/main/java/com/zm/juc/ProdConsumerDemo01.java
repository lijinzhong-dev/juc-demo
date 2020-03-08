package com.zm.juc;


class  AirCondition{//资源类

    private int number = 0;


    public synchronized void increment() throws Exception{
        //1.判断
        if(number !=0){ //当number不等于0时，停止下来，不要生产了
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
        if(number ==0){
            this.wait();
        }

        //2.干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t "+number);

        //3.通知
       this.notifyAll();
    }
}

/**
 * 题目：现在两个线程 ，操作初始值为0的一个变量
 * 实现目标：一个线程对该变量变量加1，一个线程对该变量减1，实现交替操作，并操作10轮。
 *
 * 1. 高内聚低耦合前提下，线程操作资源类
 * 2. 判断 + 干活 +通知
 *
 * 注：两个线程操作一个变量，本案例表面上没有问题存在的（其实潜在很大问题，应该使用while进行多次判断，而不能使用if进行一次判断）
 *   那么4个线程操作一个变量呢，会出现很明显问题，参见ProdConsumerDemo02
 */
public class ProdConsumerDemo01 {
    public static void main(String[] args) throws InterruptedException {

        AirCondition airCondition = new AirCondition();

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
    }
}
