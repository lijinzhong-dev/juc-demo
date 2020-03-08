package com.zm.data_structure.linked_list;

import java.util.Stack;

/**
 * 演示栈的基本使用
 */
public class TestStack {

    public static void main(String[] args) {

        Stack<String> stack =new Stack<>();

        //向栈中添加数据
        stack.add("jack");
        stack.add("tom");
        stack.add("Smith");

        //从栈中取出数据
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
