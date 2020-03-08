package season_two.thread_pool;

import java.util.concurrent.*;

/**
 * 线程池
 */
public class MyThreadPoolDemo {
     public static void main(String[] args) {
       //查看CPU核数
       System.out.println(Runtime.getRuntime().availableProcessors());
        // fixedThreadPool();
         //singleThreadExecutor();
         //cachedThreadPool();
         nyDefineThreadPool();


     }

    /**
     * 自定义线程池
     */
    private static void nyDefineThreadPool() {
        //自定义线程池
        int corePoolSize = 2;//核心线程数
        int maxPoolSize = 5;//最大线程数
        long keepAliveTime =1L;//当运行线程数小于核心线程数时,多余线程存活时间
        //任务队列，注意要指定初始化大小，否则默认大小是Integer.MAX_VALUE
        LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(3);
        //线程创建工厂
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //拒绝策略
        //ThreadPoolExecutor.AbortPolicy rejectedPolicy = new ThreadPoolExecutor.AbortPolicy();
//        ThreadPoolExecutor.CallerRunsPolicy rejectedPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
//        ThreadPoolExecutor.DiscardOldestPolicy rejectedPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        ThreadPoolExecutor.DiscardPolicy rejectedPolicy = new ThreadPoolExecutor.DiscardPolicy();

        ExecutorService threadPool = new ThreadPoolExecutor(corePoolSize,
                                                            maxPoolSize,
                                                            keepAliveTime,
                                                            TimeUnit.SECONDS,
                                                            blockingQueue,
                                                            threadFactory,
                                                            rejectedPolicy);

        /**
         *模拟9个用户来办理业务，每个用户就是一个来自外部的请求线程
         */
        try {
            for(int i=1;i<=9;i++){//模拟9个用来办理业务
                //使用线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();//关闭线程池
        }
    }

    /**
     * 一池N个线程案例
     */
    private static void cachedThreadPool() {
        //指定一池N个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();
        /**
         *模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
         */
        try {
            for(int i=1;i<=10;i++){//模拟10个用来办理业务
                //使用线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();//关闭线程池
        }
    }

    /**
     * 一池一个线程
     */
    private static void singleThreadExecutor() {
        //指定一池一个线程
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        /**
         *模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
         */
        try {
            for(int i=1;i<=10;i++){//模拟10个用来办理业务
                //使用线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();//关闭线程池
        }
    }

    /**
     * 一池固定线程数的案例
     */
    private static void fixedThreadPool() {
        //指定一池5个处理线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        /**
         *模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
         */
        try {
            for(int i=1;i<=10;i++){//模拟10个用来办理业务
                //使用线程
                threadPool.execute(()->{
                   System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();//关闭线程池
        }
    }
}
