package season_two.count_down_latch;

import java.util.concurrent.CountDownLatch;

/**
 * 演示CountDownLatch
 */
public class CountDownLatchDemo {
     public static void main(String[] args) throws InterruptedException {
         //closeDoor();

         finishSixCountry();

     }

    /**
     * 秦灭六国举例使用CountDownLatch
     * @throws InterruptedException
     */
    private static void finishSixCountry() throws InterruptedException {
        CountDownLatch countDownLatch =new CountDownLatch(6);
        for (int i=1;i<=6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"国被灭!");
                countDownLatch.countDown();//减一,减少上面初始化CountDownLatch的数量（初始化值是6）
            }, CountryEnum.forEach_CountryEnum(i).getCountryName()).start();
        }
        countDownLatch.await();//直到上面countDownLatch.countDown()将6减到0时，才会向下执行
        System.out.println(" ******秦统一六国！");
    }

    /**
     * 使用关门的案例要理解CountDownLatch
     * @throws InterruptedException
     */
    private static void closeDoor() throws InterruptedException {
        /**
         * 指定6个线程，下面会基于这6个线程执行完之后，main线程才会做其他操作，否则一直等待
         */
        CountDownLatch countDownLatch =new CountDownLatch(6);

        for (int i=1;i<=6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 上完自习，离开教室");
                countDownLatch.countDown();//减一,减少上面初始化CountDownLatch的数量（初始化值是6）
            },String.valueOf(i)).start();

        }

        countDownLatch.await();//直到上面countDownLatch.countDown()将6减到0时，才会向下执行
        System.out.println(Thread.currentThread().getName()+"\t ******班长最后关门走人");
    }
}
