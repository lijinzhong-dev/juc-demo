package season_two.volatile_demo;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class  MyData{
    //    int number = 0;
    volatile int number = 0;

    public  void addTo60(){
        this.number = 60;
    }

    //注意：此时的number是加了volatile关键字修饰的，volatile不保证原子性
    public   void addPlusPlus(){
        number ++;
    }

    //使用原子性的数据，为了保证原子性
    AtomicInteger atomicInteger =new AtomicInteger();
    public   void addAtomic(){
        atomicInteger.getAndIncrement(); //等价于number++
    }
}

/**
 * 验证volatile不保证原子性
 *  原子性是指：不可分割,完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者分割，
 *            需要整体完整，要么同时成功，要么同时失败，与mysql的原子性有点类似（但不完全相同）。
 */
public class VolatileDemo {

     public static void main(String[] args) {
         //seeableByVolatile();
         //notAtomicByVolatile();
         solveAtomicByAtomic();


     }

    /**
     * 使用Atomic解决volatile不保证原子性的问题
     */
    private static void solveAtomicByAtomic() {
        //资源类
        MyData myData = new MyData();

        for (int i=1;i<=20;i++){//20个线程
            new Thread(()->{
                for (int j=1;j<=1000;j++){//每个线程执行1000次
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        //当线程数大于2时，表明上面开辟的20个线程没有全部结束掉
        while (Thread.activeCount() > 2){
            //yield线程让步,当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，
            //让自己或者其它的线程运行
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"线程操作之后的number值："+myData.atomicInteger);
    }
    /**
     * 验证volatile不保证原子性
     *  原子性是指：不可分割,完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者分割，
     *            需要整体完整，要么同时成功，要么同时失败，与mysql的原子性有点类似（但不完全相同）。
     *
     */
    private static void notAtomicByVolatile() {
        //资源类
        MyData myData = new MyData();

        for (int i=1;i<=20;i++){//20个线程
            new Thread(()->{
                for (int j=1;j<=1000;j++){//每个线程执行1000次
                    myData.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }
        //当线程数大于2时，表明上面开辟的20个线程没有全部结束掉
        while (Thread.activeCount() > 2){
            //yield线程让步,当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，
            //让自己或者其它的线程运行
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"线程操作之后的number值："+myData.number);
    }

    /**
     * 验证Volatile的可见性
     *  1.当number变量之前没有添加volatile关键字修饰
     *  2.当number变量之前添加volatile关键字修饰
     */
    private static void seeableByVolatile() {
        //资源类
        MyData myData = new MyData();

        //线程A
        new  Thread(() ->{
           System.out.println(Thread.currentThread().getName()+"\t come in");

           //让线程暂停一会儿
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //操作内存中的数据
            myData.addTo60();

            System.out.println(Thread.currentThread().getName()+"\t update number value:"+myData.number);
        },"A").start();

        //main线程
        while (myData.number == 0){
            //只要number等于0,main线程一直在此等待循环，直到number不等于0
        }
        System.out.println(Thread.currentThread().getName()+"\t 任务完成");
    }
}
