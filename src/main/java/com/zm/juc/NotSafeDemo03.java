package com.zm.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *  集合类不安全
 *
 *  复习：
 *  ArrayList底层是一个Object类型的数组
 *  通过 new ArrayList();就在底层创建了一个长度为10的Object数组，该数组会自动扩容，
 *  第一次扩容原始长度(10)的一半，即15，扩容后，会通过Arrays.copyOf方法进行数据复制完成"搬家"
 *  第二次扩容的长度是第一次扩容后长度（15）的一半，取整后是7，即第二次扩容后的长度是15+7=22
 *  即ArrayList每次扩容是上一次长度的一半，举例：10 -> 15 -> 22 -> 33 ...
 *  HashMap初始长度是16,每次扩容是原值的一倍
 *
 *  案例：
 *  List list = new ArrayList();
 *  多线程下list.add(e);
 *  1.故障异常
 *   java.util.ConcurrentModificationException
 *
 *  2.导致原因
 *      多个线程在没有加锁的情况下，并发争夺同一个资源类
 *
 *  3.解决办法
 *     3.1使用 Vector，可以解决多线程问题，但是Vector是在add方法上加synchronized，效率低，不建议用
 *     3.2使用Collections.synchronizedList(new ArrayList()),局部代码块加synchronized
 *     3.3使用new CopyOnWriteArrayList()；即写时复制，读写分离的思想体现，即在进行写操作时copy一份，
 *        内部使用ReentrantLock
 *
 *  4.优化建议（同样的错误不犯第2次）
 *      在高并发环境下，使用juc包下的new CopyOnWriteArrayList();
 *
 *   注：CopyOnWrite容器是写时复制的容器，往一个容器中添加元素的时候，不是直接往当前容器Object[]中添加，
 *      而是先将当前容器Object[]进行copy，复制出一个新的容器Object[] newElements,
 *      然后向新的容器Object[] newElements里添加元素，添加完元素之后，
 *      再将原容器的引用指向新的容器setArray(newElements);这样做的好处是
 *      可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素，所以CopyOnWrite容器
 *      也是一种读写分离的思想，读和写不同的容器。
 */
public class NotSafeDemo03 {

    public static void main(String[] args) {

        /**
         * HashMap在多线程下不安全，解决方案：
         *  1.Collections.synchronizedMap(new HashMap<>());
         *  2.new ConcurrentHashMap<>();
         */
        Map<String,String> map =new ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());//new HashMap<>();

        for(int i=1; i<=30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
    /**
     * set集合不安全案例
     */
    private static void setNotSafe() {
        /**
         * HashSet底层数据结构是HashMap
         * 虽然set添加方法add是一个参数，而HashMap是k-v键值对，但是从底层代码看出
         *   public boolean add(E e) {
                return map.put(e, PRESENT)==null;
            }
           即其value是一个常量

         new HashSet 不安全解决方案：
            1.new CopyOnWriteArraySet();
            2.Collections.synchronizedSet(new HashSet<>());
         */
        Set set = new CopyOnWriteArraySet();//Collections.synchronizedSet(new HashSet<>());//new HashSet();;

        //java.util.ConcurrentModificationException
        for(int i=1; i<=30;i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }


    /**
     * list集合不安全案例
     */
    private static void listNotSafe() {
        List list = new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList());//new Vector();//new ArrayList();

        //java.util.ConcurrentModificationException
        for(int i=1; i<=30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }


        //list.add("a");
        //list.add("a");
        //list.add("a");

        /**
         * System.out::println 方法引用,println是PrintStream类中的一个非静态方法
         * 函数式接口 变量名 = 类实例::方法名
         * System.out的作用肯定就是来获取PrintStream类的一个类实例
         */
        //list.forEach(System.out::println);
    }
}
