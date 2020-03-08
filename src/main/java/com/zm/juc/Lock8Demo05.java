package com.zm.juc;


import java.util.concurrent.TimeUnit;

class  Phone{

    public  static   synchronized  void sendEmail() throws  Exception{

        /**
         * Thread.sleep(4000);//暂停4秒钟
         * JDK1.5后，可以使用如下暂停方法
         * TimeUnit.SECONDS.sleep(4);
         */
        TimeUnit.SECONDS.sleep(4);
        System.out.println("*********sendEmail**********");
    }

    public static synchronized  void sendSMS() throws  Exception{
        System.out.println("*********sendSMS**********");
    }

    public    void sayHello() throws  Exception{
        System.out.println("*********sayHello**********");
    }
}

/**
 *  8锁（8锁是为了学习概括的，实际交流中不要这样讲）
 *   1.同一部手机,标准访问，请问先打印邮件还是短信，注：邮件和短信方法均是synchronized,且sendEmail没有暂停方法
 *      先打印邮件
 *
 *   2.同一部手机,在发邮件方法中暂停4秒钟，请问先打印邮件还是短信，注：邮件和短信方法均是synchronized,且sendEmail有暂停方法
 *      先打印邮件
 *
 *   3.同一部手机，新增普通sayHello方法，请问先打印邮件还是sayHello，注：sayHello方法没有synchronized，sendEmail有synchronized，且有暂停方法
 *      先打印sayHello方法
 *
 *   4.两部手机，先打印邮件还是短信  注：邮件和短信方法均是synchronized,且sendEmail有暂停方法
 *      先打印短信
 *
 *   5.同一部手机，两个静态并同步的方法，先打印邮件还是短信 注：邮件和短信方法均是 static synchronized,且sendEmail有暂停方法
 *      先打印邮件
 *
 *   6.两部手机，两个静态并同步的方法，先打印邮件还是短信  注：邮件和短信方法均是 static synchronized,且sendEmail有暂停方法
 *      先打印邮件
 *
 *   7.同一部手机，1个静态并同步的sendEmail方法，1个普通并同步的sendSMS方法，且sendEmail有暂停方法，先打印邮件还是短信
 *      先打印短信
 *
 *   8.两部手机，1个静态并同步的sendEmail方法，1个普通并同步的sendSMS方法，且sendEmail有暂停方法  先打印邮件还是短信
 *      先打印短信
 *
 *   小结1 --> 针对上面1和2案例：
 *       一个对象里面如果有多个synchronized方法，某一个时刻，只要一个线程去调用其中的synchronized方法，
 *       那么其他的线程只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
 *       此处的锁是当前对象this，被锁定后，其他的线程都不能进入到当前对象的其他的synchronized方法中
 *
 *   小结2 --> 针对上面3案例：
 *       一个对象中添加一个普通方法后，发现和同步锁无关
 *
 *   小结3 --> 针对上面4案例：
 *       换成两个对象后，不是同一把锁了，情况立刻变化，不存在抢夺同一资源类的问题。
 *
 *   小结4 --> 针对上面5和6案例：
 *      所有的非静态synchronized方法用的都是同一把锁，也就是实例对象本身
 *      synchronized实现同步的基础：Java中的每个对象都可以作为锁，具体体现为以下3中形式：
 *          1.对于普通synchronized方法，锁是当前实例对象(this)
 *          2.对于同步方法块，即synchronized(锁){...},锁是synchronized小括号里配置的对象
 *          3.对于静态的synchronized方法，锁是当前类的class对象
 *   大总结：
 *      当一个线程试图访问同步代码块时，它首先必须得到锁，退出或者抛出异常时必须释放锁。
 *      也就是说如果一个实例对象的非静态synchronized方法获取锁后，该实例对象的其他非静态synchronized方法
 *      必须等待获取锁的方法释放锁后才能获取锁。
 *      可是别的实例对象的非静态synchronized方法因为跟该实例对象的非静态synchronized方法使用的是不同的锁，
 *      所以无需等待，就可以获取它们自己的锁。
 *
 *      所有静态synchronized方法用的是同一把锁，即类对象本身（class字节码）
 *      静态synchronized方法和非静态synchronized方法使用的锁是两个不同的对象，所以它们之间不会有竞争。
 *      一旦一个静态synchronized方法获取锁后，其他的静态synchronized方法都必须等待该方法释放锁后才能获取锁。
 *
 *      基于同一个类的两个不同实例，如果一个实例中的静态synchronized方法获取到锁后，另一个实例中静态synchronized方法
 *      必须等该实例中静态synchronized方法释放锁后，才能获取到锁，因为虽然它们是不同的实例，但是它们中的静态synchronized方法
 *      使用的锁是同一个class对象！
 */
public class Lock8Demo05 {
    public static void main(String[] args) throws InterruptedException {

        //资源类
        Phone phone = new Phone();

        Phone phone2 = new Phone();

        //线程A
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"线程A").start();


        Thread.sleep(100);

        //线程B
        new Thread(()->{
            try {
                //phone.sendSMS();
                //phone.sayHello();
                phone2.sendSMS();

            } catch (Exception e) {
                e.printStackTrace();
            }
        },"线程B").start();

    }
}
