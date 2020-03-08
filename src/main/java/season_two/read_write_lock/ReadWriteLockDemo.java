package season_two.read_write_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{//资源类
    //此处加volatile是为了保证可见性，即做缓存必须加volatile
    private  volatile Map<String,Object> map = new HashMap<>();
    //为了满足读数据多个线程可以同时进行，那么ReentrantLock就不行了，因为它只能允许一个线程进行操作
//    private Lock lock =new ReentrantLock();
    private ReentrantReadWriteLock rwLock =new ReentrantReadWriteLock();

    public void put(String key,Object value){
        //写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入"+key);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key){

        //读锁
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取");
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成 "+result);
        }finally {
            rwLock.readLock().unlock();
        }
    }
}

/**
 * 读写锁演示：
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源可以同时进行。
 * 如果一个线程想去写共享资源类，就不应该再有其他线程可以对该资源进行读或写。
 * 小总结：
 *  读-读：能共享
 *  读-写：不能共享
 *  写-写：不能共享
 *
 *  注：写操作：必须是原子+独占，即整个过程必须是一个完整的统一体，中间不允许被分割，不允许被打断.
 */
public class ReadWriteLockDemo {


     public static void main(String[] args) {

         MyCache myCache = new MyCache();

         for (int i=1;i<5;i++){
             final  int tempInt =i;
            new Thread(()->{
                myCache.put(tempInt+"", tempInt+"");
            },String.valueOf(i)).start();
         }
         for (int i=1;i<5;i++){
             final  int tempInt =i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
         }

     }
}
