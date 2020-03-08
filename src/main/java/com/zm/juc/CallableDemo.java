package com.zm.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//创建线程方式一：继承Thread
class MyThread1 extends  Thread{
    //完成线程真正功能的代码在run()方法中
    public void run() {

    }
}

//创建线程方式二：实现 Runnable 接口
class MyThread2 implements   Runnable{

    @Override
    public void run() {

    }
}

/**
 * 创建线程方式三：实现 Callable 接口，
 * 接口Callable<V>中的泛型，用来确定call()方法的返回值类型.
 * 查看接口Callable<V>源码，可以看到该接口有@FunctionalInterface注解，
 * 说明该接口是函数式接口，故可以使用lambda表达式，注：@FunctionalInterface是在jdk1.8才有的
 */
class MyThread3 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {

        System.out.println("**********call********");
        return 1024;
    }
}


/**
 * 老版本创建线程常用的两种方式：
 *  1.继承Thread类
 *  2.实现Runnable接口
 *
 * 但是大厂面试常考第3种创建线程的方式，本案例介绍：Callable，用于创建有返回值的线程
 *  Callable接口类似于Runnable
 *
 * 当然创建线程还可以通过方式四：使用线程池
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * FutureTask(Callable<V> callable)
         * 创建一个 FutureTask ，它将在运行时执行给定的 Callable 。
         * 注：下面的FutureTask<Integer>中的泛型Integer，可以根据我们
         * 实现Callable接口call方法返回值类型而定
         */
        FutureTask<Integer> futureTask =new FutureTask(new MyThread3());

        //执行，会执行实现了Callable<V>接口中覆盖的call()
        new Thread (futureTask,"线程名称").start();

        //获取实现了Callable<V>接口中覆盖的call()方法的返回值
        Integer num = futureTask.get();
        System.out.println("----------------------"+num);
    }
}
