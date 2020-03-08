package com.zm.data_structure.queue;

import java.util.Scanner;

//使用数组模拟队列---该队列只能使用一次待改进
class ArrayQueue{
    private int maxSize;//队列的最大容量
    private int front;//队列头部下标
    private int rear;//队列尾部下标

    private int[] arr;//该数组用于存储数据,模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        this.maxSize=arrMaxSize;
        arr=new int[maxSize];
        this.front=-1;//指向队列头部（并不包含头部，也就是指向队列头部的前一个位置）
        this.rear=-1;//指向队列尾部（包含尾部，也就是指向队列尾的数据）
    }

    //判断队列是否满
    public boolean isFull(){
        return this.rear == this.maxSize-1;
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

        rear++;//因为向队列中加入数据rear的下标要向后移动，故rear++

        arr[rear]=element;//添加数据
    }

    //获取队列中的数据
    public int getQueue(){
        //首先判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空,不能移除数据");
        }

        front++;//因为每次移除数据front下标会向后移动，故front++

        return  arr[front];
    }

    //显示队列的所有数据
    public  void  showQueue(){
        //首先判断队列是否为空
        if(isEmpty()){
            System.out.println("队列为空");
            return;
        }

        for (int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        //首先判断队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空,不能移除数据");
        }
        return arr[front +1];
    }
}

public class ArrayQueueDemo {
     public static void main(String[] args) {
        //创建一个队列
         ArrayQueue arrayQueue = new ArrayQueue(3);

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

