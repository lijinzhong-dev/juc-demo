package com.zm.jvm;

/**
 * @Auther: lijinzhong
 * @Date: 2019/11/8
 * @Description: com.zm.jvm
 * @version: 1.0
 */
public class MyObject {
    /**
     * 实例变量名.getClass().getClassLoader()表示该实例是由哪个类加载器加载进来的，举例如下：
     * MyObject myObject = new MyObject();
     * myObject.getClass().getClassLoader()；表示获取myObject是由哪个实例加载进来的
     */
    public static void main(String[] args) {
        MyObject myObject = new MyObject();

        //结果：null
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());

        //结果：sun.misc.Launcher$ExtClassLoader@1b6d3586
        System.out.println(myObject.getClass().getClassLoader().getParent());

        //结果：sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(myObject.getClass().getClassLoader());

        System.out.println("----------------------");

        Object object =new Object();
        /**
         * object.getClass().getClassLoader() 其实是由Bootstrap(启动类加载器)加载进行来的
         * 只不过Bootstrap是由C++编写的，故此处打印出null
         * 总结：只要是java自带的实例或对象，那么它的类加载器就是Bootstrap，打印出来就是null
         *      说白了，Bootstrap启动类加载器加载/jre/lib/rt.jar
         */
        System.out.println(object.getClass().getClassLoader());//结果：null

    }
}
