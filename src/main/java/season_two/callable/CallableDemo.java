package season_two.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

/**
 * implements Callable<V>
 * 其中V是要实现的方法call()返回值的类型
 */
class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"***线程进入");
        return 1024;
    }
}

/**
 * 第3种获取多线程的方式
 */
public class CallableDemo {
     public static void main(String[] args) throws ExecutionException, InterruptedException {
         //FutureTask泛型最好和Callable接口的泛型一致
         FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread2());
         FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new MyThread2());//第二个FutureTask
         new Thread(futureTask, "A").start();
         //new Thread(futureTask, "B").start();
         new Thread(futureTask2, "B").start();

         /**
          * 判断实现callable接口复写的call方法是否执行完毕
          * futureTask.isDone()为false表示没有执行完毕，true表示执行完毕
          * 没有执行完毕就一直循环等待,当执行完毕后，则在通过futureTask.get()获取返回值
          */
         while (!futureTask.isDone()){

         }

         /**
          * 获取实现Callable接口线程的返回值,futureTask.get()一般放到最后面,不然会阻塞main线程
          */
         Integer result = futureTask.get();//一般放到最后面,不然会阻塞main线程
         System.out.println("线程的返回值："+result);
     }
}
