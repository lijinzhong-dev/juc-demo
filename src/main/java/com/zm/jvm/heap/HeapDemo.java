package com.zm.jvm.heap;

/**
 * 堆内存的相关代码演示
 */
public class HeapDemo {
     public static void main(String[] args) {
        //返回java虚拟机试图使用的最大内存量
         long maxMemory = Runtime.getRuntime().maxMemory();
         //返回java虚拟机的内存总量
         long totalMemory = Runtime.getRuntime().totalMemory();

         System.out.println("本机JVM最大内存量（即-Xmx）："+maxMemory+"(字节)  "+(maxMemory/(double)1024/1024)+"MB");
         System.out.println("本机JVM总内存量（即-Xms）："+totalMemory+"(字节)  "+(totalMemory/(double)1024/1024)+"MB");
     }
}
