package season_two.volatile_demo;

import java.util.concurrent.TimeUnit;

class DataDemo{
    int a = 0;
    boolean flag=false;

    public void method01(){
        a =1;
        flag=true;

        System.out.println("method01 ---a="+a+" ,flag="+flag);
    }
    public void method02(){
        if (flag){
            a = a+5;
            System.out.println("method02 ---a="+a+" ,flag="+flag);
        }
    }
}
/**
 * volatile禁止指令重排验证
 */
public class ReSortSeqDemo {

     public static void main(String[] args) throws InterruptedException {
         DataDemo dataDemo = new DataDemo();
         new Thread(()->{
             dataDemo.method02();
         },"B").start();

         TimeUnit.SECONDS.sleep(3);

         new Thread(()->{
             dataDemo.method01();
         },"A").start();

     }
}
