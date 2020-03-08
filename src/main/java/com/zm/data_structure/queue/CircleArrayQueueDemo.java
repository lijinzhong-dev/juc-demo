package com.zm.data_structure.queue;

import java.util.Scanner;

//使用数组模拟环形队列
class CircleArrayQueue{
    private int maxSize;//队列的最大容量
    /**
     * front变量含义调整:front指向队列的第一个元素,
     * 也就是说arr[front]就是队列的第一个元素，front的初始值为0
     */
    private int front;//队列头部下标

    /**
     * rear变量含义调整:rear指向队列的最后一个元素的后一个位置,
     * 因为希望空出一个空间作为约定，rear的初始值为0
     */
    private int rear;//队列尾部下标

    private int[] arr;//该数组用于存储数据,模拟环形队列

    //创建队列的构造器
    public CircleArrayQueue(int arrMaxSize){
        this.maxSize=arrMaxSize;
        arr=new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return this.rear == this.front ;
    }

    //添加数据到队列
    public void addQueue(int element){
        //首先判断队列是否满了
        if(isFull()){
            System.out.println("队列已满,不能加入数据");
            return;
        }
       //直接将数据加入
        arr[rear]=element;

        //将rear后移，这里必须考虑取模，因为rear可能已经移到了队列的最后
        rear = (rear + 1) % maxSize;
    }

    //获取队列中的数据
    public int getQueue(){
        //首先判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空,不能移除数据");
        }
        //这里需要分析出front指向队列的第一个元素
        //1.先把front指向的元素保存到一个临时变量
        int value = arr[front];
        //2.将front后移,然后取模
        front = (front + 1) % maxSize;
        //3.将临时保存的变量返回
        return value;
    }

    //显示队列的所有数据
    public  void  showQueue(){
        //首先判断队列是否为空
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }
        //从front开始遍历
        for (int i= front;i< front+size(); i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        //首先判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空,不能移除数据");
        }
        return arr[front];
    }
}

public class CircleArrayQueueDemo {
     public static void main(String[] args) {
        //创建一个队列 注意：初始化构造函数指定的长度并不是队列的长度，队列实际长度是该长度减1
         CircleArrayQueue arrayQueue = new CircleArrayQueue(4);

         char key=' ';//接收用户输入

         Scanner scanner = new Scanner(System.in);

         boolean loop=true;

         while (loop){
             System.out.println("s(show):输入s表示显示队列");
             System.out.println("e(exit):输入e表示退出程序");
             System.out.println("a(add):输入a表示添加数据到队列");
             System.out.println("g(get):输入g表示从队列取出数据");
             System.out.println("h(head):输入h表示查看队列头部数据");

             key = scanner.next().charAt(0);//接收一个字符

             switch (key){
                 case 's':
                     arrayQueue.showQueue();
                     break;
                 case 'a':
                     System.out.println("请输入一个数：");
                     int value=scanner.nextInt();
                     arrayQueue.addQueue(value);
                     break;
                 case 'g':
                     try {
                         int result = arrayQueue.getQueue();
                         System.out.printf("取出的数据是%d\n",result);
                     }catch (Exception e){
                         System.out.println(e.getMessage());
                     }
                     break;
                 case 'h': //查看队列头的数据
                     try {
                         int result =  arrayQueue.headQueue();;
                         System.out.printf("队列头部的数据是%d\n",result);
                     }catch (Exception e){
                         System.out.println(e.getMessage());
                     }
                     break;
                 case 'e':
                     scanner.close();
                     loop=false;
                     System.out.println("程序退出");
                     break;
                 default:
                     break;
             }

         }
     }
}

