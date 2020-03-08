package season_two.entities;

/**
 * @Auther: lijinzhong
 * @Date: 2019/12/20
 * @Description: season_two.entities
 * @version: 1.0
 */
public class TestTransferValue {

    public  void changeValue1(int age){
        age=30;
    }

    public  void changeValue2(Person person){
        person.setPersonName("xxx");
    }

    public  void changeValue3(String str){
       str="xxx";
    }

     public static void main(String[] args) {
         TestTransferValue test = new TestTransferValue();

         int age=20;
         test.changeValue1(age);//基本数据类型传递的是副本，即传值
         System.out.println("age---"+age);//20

         Person person = new Person("abc");
         test.changeValue2(person);//传的是引用
         System.out.println("personName-----"+person.getPersonName());//xxx

         String str="abc";
         test.changeValue3(str);
         System.out.println("str----"+str);//abc


     }


}
