package com.zm.data_structure.linked_list;

/**
 * 定义双向链表的节点
 */
class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向上一个节点

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    //为了显示方便，我们重写toString
    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
/**
 * 定义双向链表
 */
class DoubleLinkedList{
    //先初始化一个头节点,头节点不要动,主要用来表示是头节点，并不存放任何数据
    private  HeroNode2 head = new HeroNode2(0, "", "");

    /**
     * 返回头节点
     */
    public  HeroNode2 getHead(){
        return head;
    }

    /**
     * 遍历双向链表的（从头节点向后开始遍历）
     */
    public void listLinkedList() {
        if (head.next == null) {//判断双向链表是否为空
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量temp
        HeroNode2 temp = head.next;
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
     * 添加节点到双向链表的最后（不考虑编号顺序）
     */
    public void addNode(HeroNode2 heroNode) {

        //因为头节点不能动，因此我们需要一个辅助变量temp
        HeroNode2 temp = head;

        //遍历双向链表，找到最后一个节点
        while (true) {
            if (temp.next == null) {//链表最后一个节点
                break;
            }
            //如果不是最后节点，将temp继续后移
            temp = temp.next;
        }
        //形成双向链表
        temp.next = heroNode;//原双向链表的尾节点的next指向新添加的节点
        heroNode.pre=temp;//新添加的节点的pre指向原双向链表的尾节点
    }

    /**
     * 添加节点到双向链表（考虑编号顺序）
     * 按英雄排名插入到指定位置，如果该排名已经在链表存在，则添加失败，并给出提示
     */
    public void addNodeByOrder(HeroNode2 heroNode) {
        //因为头节点不能动，因此我们需要一个辅助变量temp
        HeroNode2 temp=head;

        boolean flag = false;//flag表示添加的编号是否存在，默认为false
        while (true){
            if (temp.next == null){//说明temp已经指向了链表的最后
                break;
            }

            if (temp.next.no > heroNode.no){//要插入的位置找到
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
    public  void updateNode(HeroNode2 heroNode){
        //判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空~");
            return;
        }

        //根据no编号，找到需要修改的节点
        //先定义一个辅助变量temp
        HeroNode2 temp=head;
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
    public void delNode(HeroNode2 heroNode){
        //定义辅助变量temp用于遍历
        HeroNode2 temp=head.next;

        if (temp.next ==null){
            System.out.println("链表为空");
            return;
        }

        boolean flag=false;//表示是否找到要删除的节点，true时表示找到
        while (true){
            if (temp == null){//说明temp已经指向了链表的最后
                break;
            }
            if (temp.no == heroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;//条件不满足，后移，继续遍历
        }
        if (flag){
            temp.pre.next=temp.next;
            //注意：当temp指向最后一个节点时，如果直接执行如下代码 temp.next.pre=temp.pre;
            // 会有空指针异常,所以需要进行判断，即在temp指向的节点不是最后一个节点时，才会执行如下代码
            if(temp.next!=null){
                temp.next.pre=temp.pre;
            }
        }else{
            System.out.println("没有找到相应的节点~");
        }
    }
}

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        System.out.println("测试双向链表~");

        //先创建节点
        HeroNode2 hero1 =new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 =new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 =new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 =new HeroNode2(4,"林冲","豹子头");

        //创建链表
        DoubleLinkedList doubleLinkedList =new DoubleLinkedList();
        //无顺序添加节点
//        doubleLinkedList.addNode(hero1);
//        doubleLinkedList.addNode(hero2);
//        doubleLinkedList.addNode(hero4);
//        doubleLinkedList.addNode(hero3);

        //按英雄编号的顺序从小到大添加节点
        doubleLinkedList.addNodeByOrder(hero1);
        doubleLinkedList.addNodeByOrder(hero2);
        doubleLinkedList.addNodeByOrder(hero4);
        doubleLinkedList.addNodeByOrder(hero3);
        //遍历双向链表
        doubleLinkedList.listLinkedList();

        //修改双向链表
        hero2 =new HeroNode2(2,"小卢","玉麒麟");
      //  doubleLinkedList.updateNode(hero2);
        System.out.println("修改后的双向链表~");
        //遍历双向链表
        doubleLinkedList.listLinkedList();

        //删除双向链表的指定节点
       // doubleLinkedList.delNode(hero2);
        System.out.println("删除相应节点后的双向链表~");
        //遍历双向链表
        doubleLinkedList.listLinkedList();

    }
}

