package season_two.containerNotSafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全的问题
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        //listNotSafe();
        //setNotSafe();
        mapNotSafe();


    }

    /**
     * Map集合不安全案例
     */
    private static void mapNotSafe() {
        /**
         * HashMap线程不安全
         */
//        Map<String,String> map =new HashMap<>();
        //解决HashMap线程不安全问题方案一：
//        Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
        //解决HashMap线程不安全问题方案二：
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);//java.util.ConcurrentModificationException
            },String.valueOf(i)).start();
        }
    }

    /**
     * Set集合不安全案例
     */
    private static void setNotSafe() {
        /**
         * Set线程不安全
         * HashSet的底层结构是HashMap,默认创建的长度为16，负载因子为0.75
         * HashSet的add方法添加的元素是其底层结构HashMap的key,value是一个常量，源码如下：
         *  public boolean add(E e) {
                return map.put(e, PRESENT)==null;
            }
            其中PRESENT 是 private static final Object PRESENT = new Object();
         */
//        Set<String> set =new HashSet<>();

        //解决HashSet线程不安全问题方案一：
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        //解决HashSet线程不安全问题方案二：
        Set<String> set = new CopyOnWriteArraySet();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);//java.util.ConcurrentModificationException
            },String.valueOf(i)).start();
        }
    }

    /**
     * List集合不安全案例
     */
    private static void listNotSafe() {
        /**
         * new ArrayList() 底层就是创建了一个长度为10的Object类型的数组
         * 如果长度不够用,会自动扩充原始长度的一半（10 --> 15...）
         */
        //List list = new ArrayList();
        //ArrayList有线程不安全问题
        //List<String> list = new ArrayList();

        //解决ArrayList线程不安全问题方案一：
//      List<String> list = new Vector<>();

        //解决ArrayList线程不安全问题方案二：
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        //解决ArrayList线程不安全问题方案三：
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);//java.util.ConcurrentModificationException
            },String.valueOf(i)).start();
        }
        /**
         * 1 ArrayList线程不安全故障现象
         *      java.util.ConcurrentModificationException
         * 2 导致原因
         *      并发争抢修改导致，参考我们的花名册签名情况：
         *      一个人正在写，另外一个同学过来抢夺，导致数据不一致异常，即并发修改异常
         *
         * 3 解决方案
         *      3.1 使用Vector
         *      3.2 使用Collections.synchronizedList(new ArrayList<>())
         *      3.3 使用CopyOnWriteArrayList(内部使用ReentrantLock),CopyOnWriteArrayList适合读多写少
         *      CopyOnWrite容器，即写时复制容器，往该容器中添加元素的时候，不直接往当前容器Object[]中添加，
         *      而是先将当前容器Object[]进行Copy,复制出一个新的容器Object[] new Elements,然后往新的容器
         *      Object[] new Elements里添加元素，添加完元素之后，再将原容器的引用指向新容器 setArray(newElements)
         *      这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素，
         *      所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
         *
         * 4 优化建议（即同样的错误不犯第2次）
         *
         */}
}
