package com.zm.jvm.stack;

/**
 * JVM栈的相关代码演示
 */
public class StackDemo {

    public  static  void m1(){
        m1();
    }

    public static void main(String[] args) {
        m1();
    }
}
