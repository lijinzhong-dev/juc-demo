package com.zm.data_structure.demo;

/**
 * @Auther: lijinzhong
 * @Date: 2020/3/6
 * @Description: com.zm.data_structure.demo
 * @version: 1.0
 */
public class lesson {
    int schoolid;
    int studentCount; //学生数量
     public static void main(String[] args) {
         double sum=0;
         int i=0;
         double x=1;
         while(x>0){
             sum=sum+Math.pow(2,i);
             x=31-sum;
             i++;
         }
         System.out.println(Math.pow(2,i-1));
     }

}
