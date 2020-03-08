package season_two.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 比较并交换
 **/
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current:" + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 2014) + "\t current:" + atomicInteger.get());
    }
}
