package season_two.again_in_lock;

class Phone { //资源类
    public synchronized  void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getId()+"\t 发送短信.....");
        sendEmail();
    }

    public synchronized  void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getId()+"\t 发送邮件.....");
    }
}

/**
 * 可重入锁（递归锁）演示:synchronized是可重入锁
 */
public class SynchronizedDemo {
     public static void main(String[] args) {
         Phone phone = new Phone();

         new  Thread(()->{
             try {
                 phone.sendSMS();
             } catch (Exception e) {
                 e.printStackTrace();
             }
         },"A").start();

         new  Thread(()->{
             try {
                 phone.sendSMS();
             } catch (Exception e) {
                 e.printStackTrace();
             }
         },"B").start();
     }
}
