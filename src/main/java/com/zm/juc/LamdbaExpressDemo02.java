package com.zm.juc;

@FunctionalInterface
interface  Foo{

    //public  void sayHello();
    public  int  add(int x, int y);

    public default  int mul(int x, int y){

        return x * y;
    }
    public static   int div(int x, int y){

        return x / y;
    }

}

/**
 * LamdbaExpress 简单学习
 *
 * 使用Lamdba表达式的前提：定义的接口是函数式接口。
 * 函数式接口:就是在接口中只能声明一个方法体的方法，可以有多个default或static修饰的带有方法体的方法。
 *
 *
 * 当一个接口中只定义了唯一一个无方法体的方法，那么jdk8会自动在该接口上添加注解@FunctionalInterface
 * 即标识该接口为函数式接口
 *
 * LamdbaExpress使用小口诀：拷贝小括号，写死右箭头，落地大括号
 *
 *
 *
 *
 */
public class LamdbaExpressDemo02 {

    public static void main(String[] args) {
       /* Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("大家好");
            }

            @Override
            public int add(int x, int y) {
                return x +y;
            }
        };
        foo.sayHello();
        System.out.println(foo.add(1, 2));*/

       Foo foo = (int x, int y) -> {
           System.out.println("我是add方法");
           return x+y;
       };
       System.out.println(foo.add(1,2));
       System.out.println(foo.mul(4, 2));
       System.out.println(Foo.div(4, 2));
    }
}
