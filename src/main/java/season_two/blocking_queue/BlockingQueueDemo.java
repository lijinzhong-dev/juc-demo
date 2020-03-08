package season_two.blocking_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列演示：
 * ArrayBlockingQueue:是一个基于数组结构的有界阻塞队列,此队列按FIFO(先进先出)原则对元素进行排序.
 * LinkedBlockingQueue:一个基于链表结构的阻塞队列,此队列按FIFO排序元素,吞吐量通常高于ArrayBlockingQueue
 * SynchronousQueue:一个不存储元素的阻塞队列,每一个插入操作必须等到另一个线程调用移除操作之后才能进行,
 *                  否则插入操作一直处于阻塞状态.
 */
public class BlockingQueueDemo {

     public static void main(String[] args) throws InterruptedException {
//        method01();
//         method02();

//         method03();

         method04();
         return;

     }

    private static void method04() throws InterruptedException {
        BlockingQueue<String> blockingQueue =new ArrayBlockingQueue<>(3);

        /**
         * offer(e,time,unit)
         * 当阻塞队列满时,队列会阻塞生产者线程一定时间,超过限时后，生产者线程就会退出,并返回false
         */
        System.out.println(blockingQueue.offer("a", 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d", 1,TimeUnit.SECONDS));

        /**
         * poll(time,unit)移除元素,指定时间超时后会自动退出，并返回null
         */
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(1,TimeUnit.SECONDS));
    }

    private static void method03() throws InterruptedException {
        BlockingQueue<String> blockingQueue =new ArrayBlockingQueue<>(3);
        /**
         * 使用put方法添加元素，
         * 当阻塞队列满时,生产者继续往队列里面put元素，队列会一直阻塞直到put数据或响应中断退出。
         */
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("**********************");
        //blockingQueue.put("d");//一直阻塞

        /**
         * take移除元素,
         * 当阻塞队列空时,消费者试图从队列take元素,队列会一直阻塞消费者线程，直到队列可用.
         */
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        // System.out.println(blockingQueue.take());//一直阻塞
    }

    private static void method02() {
        BlockingQueue<String> blockingQueue =new ArrayBlockingQueue<>(3);
        /**
         * offer方法添加元素，添加成功返回true，失败返回false
         */
        System.out.println(blockingQueue.offer("a"));//true
        System.out.println(blockingQueue.offer("b"));//true
        System.out.println(blockingQueue.offer("c"));//true
        System.out.println(blockingQueue.offer("d"));//false

        /**
         * peek检查阻塞队列是否存在元素,如果存在默认返回第一个元素
         */
        System.out.println("&&&&&  "+blockingQueue.peek());

        /**
         * 移除元素,返回移除的元素，如果阻塞队列已为空，再次移除则返回null
         */
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void method01() {
        //无需指定大小，默认是10
//    List list =new ArrayList();

        //ArrayBlockingQueue需要手动指定其有界大小，此处我们指定为3，即该队列只能存放3个元素
        BlockingQueue<String> blockingQueue =new ArrayBlockingQueue<>(3);
        //使用add方法添加元素,当添加元素的数量大于阻塞队列的大小时，会报java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("d"));

        /**
         * element()检查阻塞队列中是否存在元素,默认返回阻塞队列中的第一个元素
         * 如果不存在元素则，报：java.util.NoSuchElementException
         */
        System.out.println("***"+blockingQueue.element());

        /**
         * 移除元素,remove()默认移除阻塞队列中第一个元素,remove(Object obj)根据指定的元素移除。
         * 当将阻塞队列中的元素移空后,再次移除元素，会报：java.util.NoSuchElementException
         */
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());

        //System.out.println("%%%%"+blockingQueue.element());
    }
}
