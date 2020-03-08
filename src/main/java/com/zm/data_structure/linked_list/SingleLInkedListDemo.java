package com.zm.data_structure.linked_list;

import java.util.Stack;

//定义HeroNode,每一个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方便，我们重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\''+"}";
    }
}

//定义一个类SingleLinkedList作为单向链表，来管理英雄
class SingleLinkedList {
    //先初始化一个头节点,头节点不要动,主要用来表示是头节点，并不存放任何数据
    private static HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点到单向链表（不考虑编号顺序）
     * 1) 找到当前链表的最后节点
     * 2) 将最后这个节点的next指向新的节点即可
     */
    public void addNode(HeroNode heroNode) {

        //因为头节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;

        //遍历单向链表，找到最后一个节点
        while (true) {
            if (temp.next == null) {//链表最后一个节点
                break;
            }
            //如果不是最后节点，将temp继续后移
            temp = temp.next;
        }
        //当退出while循环时,temp就指向了链表的最后节点
        temp.next = heroNode;//添加新的节点
    }

    /**
     * 添加节点到单向链表（考虑编号顺序）
     * 按英雄排名插入到指定位置，如果该排名已经在链表存在，则添加失败，并给出提示
     */
    public void addNodeByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们需要一个辅助变量temp
        //因为是单链表，我们找到temp是位于添加位置的前一个节点，否则插入不进去
        HeroNode temp=head;

        boolean flag = false;//flag表示添加的编号是否存在，默认为false

        while (true){
            if (temp.next == null){//说明temp已经指向了链表的最后
                break;
            }

            if (temp.next.no > heroNode.no){//要插入的位置找到，就在temp的后面且temp.next的前面
                break;
            }else if (temp.next.no == heroNode.no){//表示要添加的节点已存在
                flag=true;
                break;
            }

            //以上条件均不满足，后移，继续遍历
            temp=temp.next;
        }

        //判断flag的值，是否可以进行添加节点（true不可以添加，false可以添加）
        if (flag){
            System.out.printf("准备插入的英雄编号%d已经存在，不能再次加入\n",heroNode.no);
        }else{//添加新节点
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    /**
     * 修改节点
     * 根据节点的no进行修改（不修改no）
     */
    public  void updateNode(HeroNode heroNode){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空~");
            return;
        }

        //根据no编号，找到需要修改的节点
        //先定义一个辅助变量temp
        HeroNode temp=head;
        boolean flag =false;//当为true时表示找到了要修改的节点
        while (true){
            if (temp.next == null){//说明temp已经指向了链表的最后
                break;
            }
            if (temp.next.no == heroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;//条件不满足，后移，继续遍历
        }
        if (flag){
            temp.next.name=heroNode.name;
            temp.next.nickName=heroNode.nickName;
        }else{
            System.out.println("没有找到相应的节点~");
        }
    }

    /**
     * 删除节点
     * 根据节点的no进行删除
     */
    public void delNode(HeroNode heroNode){
        //定义辅助变量temp用于遍历
        HeroNode temp=head;

        if (temp.next ==null){
            System.out.println("链表为空");
            return;
        }

        boolean flag=false;//表示是否找到要删除的节点，true时表示找到
        HeroNode delNodePre=null;//记录找到要删除节点的上一个节点
        HeroNode delNodeSuffix=null;//记录找到要删除节点的下一个节点
        while (true){
            if (head == null){//说明temp已经指向了链表的最后
                break;
            }
            if (temp.next.no == heroNode.no){
                flag=true;
                delNodePre=temp;
                delNodeSuffix=temp.next.next;
                break;
            }
            temp=temp.next;//条件不满足，后移，继续遍历
        }
        if (flag){
            delNodePre.next=delNodeSuffix;//将要删除节点的上一个节点和下一个节点连接起来
        }else{
            System.out.println("没有找到相应的节点~");
        }
    }


    //显示链表（遍历链表）
    public void listLinkedList() {
        if (head.next == null) {//判断链表是否为空
            System.out.println("链表为空");
            return;
        }

        //因为头节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {//判断是否到链表最后
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //一定要小心，将temp后移，为了输出下一个节点，否则会死循环
            temp = temp.next;
        }
    }

    /**
     * 统计单链表的有效节点数（即如果链表带头节点，则不统计头节点）
     */
    public static int getLinkedListNodeCount(SingleLinkedList singleLinkedList){
        //定义辅助变量temp
        HeroNode temp =head;
        if (temp.next == null){//链表只有头节点时，则有效节点数是0
            return 0;
        }

        int count =0;//用于记录单向链表的有效节点数
        while (temp.next!=null){
           count++;
           temp=temp.next;//后移
        }
        return count;
    }

    /**
     * 查找单链表中倒数第k个节点
     */

    public static HeroNode getNode(SingleLinkedList singleLinkedList,int k){
        //定义一个辅助变量temp
        HeroNode temp=head;
        if (temp.next==null){
            System.out.println("该单链表无有效节点(只有head节点)");
            return null;
        }

        //获取单链表的有效节点数
        int count = SingleLinkedList.getLinkedListNodeCount(singleLinkedList);

        if (k>count){
            System.out.println("该链表没有倒数第"+k+"个节点");
            return null;
        }

        for (int i=1;i<=count-k+1;i++){
            temp=temp.next;
        }
        return temp;
    }

    /**
     * 单链表的反转
     */
    public static  void reverseLinkedList(SingleLinkedList singleLinkedList){
        //定义一个辅助变量
        HeroNode cur =head.next;
        //当只有头节点或者只有一个有效节点时，直接返回不需要进行反转
        if (head.next==null || head.next.next==null){
            return;
        }

        //定义一个新的反转链表头节点
        HeroNode reverseHead = new HeroNode(-1,"","");

        //定义一个原链表当前节点的下一个节点nextNode
        HeroNode nextNode=null;

        //遍历原链表
        while (cur!=null){
            nextNode=cur.next;
            cur.next=reverseHead.next;//将cur的下一个节点指向新链表的最前端
            reverseHead.next=cur;
            cur=nextNode;
        }
        //原链表的头节点指向反转链表的头节点.next
        head.next=reverseHead.next;
    }

    /**
     * 从尾到头打印单链表 方法1
     */
    public static void printLinkedListFromTailToHead(SingleLinkedList singleLinkedList){
        //定义一个辅助变量temp
        HeroNode temp = head.next;

        if (temp==null){
            System.out.println("该链表无有效节点~");
            return;
        }
        //不建议先反转单链表，因为这从根本上改变了原链表的结构
//        SingleLinkedList.reverseLinkedList(singleLinkedList);

        int count = getLinkedListNodeCount(singleLinkedList);
        System.out.println("从尾到头打印单链表");
        for (int i=1;i<=count;i++){
            HeroNode heroNode = getNode(singleLinkedList, i);
            System.out.println(heroNode);
        }
    }

    /**
     * 从尾到头打印单链表 方法2
     */
    public static void printLinkedListFromTailToHead2(SingleLinkedList singleLinkedList){
        //定义一个辅助变量temp
        HeroNode temp =head;

        //定义一个栈用来存放数据
        Stack<Object> stack =new Stack<>();

        while (temp.next!=null){
            stack.add(temp.next);
            temp=temp.next;
        }

        //从栈中取出数据：
        System.out.println("使用栈从尾到头打印链表~");
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }



}

/**
 * 单向链表
 */
public class SingleLInkedListDemo {
     public static void main(String[] args) {
        //测试
         //先创建节点
         HeroNode hero1 =new HeroNode(1,"宋江","及时雨");
         HeroNode hero2 =new HeroNode(2,"卢俊义","玉麒麟");
         HeroNode hero3 =new HeroNode(3,"吴用","智多星");
         HeroNode hero4 =new HeroNode(4,"林冲","豹子头");

         //创建链表
         SingleLinkedList singleLinkedList =new SingleLinkedList();

         //添加节点（不考虑顺序加入）
//         singleLinkedList.addNode(hero1);
//         singleLinkedList.addNode(hero2);
//         singleLinkedList.addNode(hero3);
//         singleLinkedList.addNode(hero4);

         //添加节点（考虑顺序加入）
         singleLinkedList.addNodeByOrder(hero1);
         singleLinkedList.addNodeByOrder(hero3);
         singleLinkedList.addNodeByOrder(hero2);
         singleLinkedList.addNodeByOrder(hero4);

         System.out.println("按顺序添加接到到链表~");
         singleLinkedList.listLinkedList();


         //从尾到头打印单链表 方法2
         SingleLinkedList.printLinkedListFromTailToHead2(singleLinkedList);


         //从尾到头打印单链表 方法1
         SingleLinkedList.printLinkedListFromTailToHead(singleLinkedList);


         //反转链表
         singleLinkedList.reverseLinkedList(singleLinkedList);
         System.out.println("反转后的链表~");
         singleLinkedList.listLinkedList();


         //获取单向链表的有效节点数
         int count = singleLinkedList.getLinkedListNodeCount(singleLinkedList);
         System.out.printf("单向链表的有效节点数是：%d",count);
         System.out.println();

         //获取单链表倒数第k个节点
         int k=6;
         HeroNode node = SingleLinkedList.getNode(singleLinkedList, k);
         System.out.println("单链表"+singleLinkedList+"的倒数第"+k+"个节点是\n:"+node);

         //修改节点
         hero1=new HeroNode(1,"宋江","孝义黑三郎");
         singleLinkedList.updateNode(hero1);

         //删除节点
         System.out.println("删除相应节点前的链表");
         singleLinkedList.listLinkedList();

         singleLinkedList.delNode(new HeroNode(1,"宋江","孝义黑三郎"));

         System.out.println("删除相应节点后的链表");
         singleLinkedList.listLinkedList();

         //遍历链表
         //singleLinkedList.listLinkedList();
      }
}
