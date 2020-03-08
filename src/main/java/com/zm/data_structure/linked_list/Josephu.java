package com.zm.data_structure.linked_list;

import lombok.Getter;
import lombok.Setter;

/**
 * 约瑟夫问题，即单向环形链表
 */
public class Josephu {
     public static void main(String[] args) {

      }
}

/**
 * 创建一个单向环形链表
 */
class SingleCircleLinkedList{
    //先创建一个first节点，随便指定该节点的编号，后面在添加其他节点时会将该节点的编号进行修改
    private Boy first =null;

    /**
     * 添加其他节点，为了构建一个单向环形链表
     * 参数nums表示该单向环形链表中一共有多少个节点
     */
    public void addBoy(int nums){
        if (nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        //辅助变量，帮助我们构建环形链表
        Boy curBoy =null;

        //用for循环创建单向环形链表
        for (int i=1;i<=nums;i++){
            //根据编号创建节点
            Boy boy =new Boy(i);
            //第一个节点时，需要判断，将节点编号修改为1
            if (i==1){
                first=boy;
                first.setNext(boy);
            }
        }
    }
}

/**
 * 该类表示单向环形链表中的一个节点
 */
@Setter
@Getter
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点

    public Boy(int no) {
        this.no = no;
    }
}
