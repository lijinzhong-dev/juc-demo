package com.zm.data_structure.sparse_array;

/**
 * 稀梳数组
 */
public class SparseArray {

    public static void main(String[] args) {
        //创建一个原始的二维数组（棋盘） 11*11
        int chessArr1[][] = new int[11][11];

        //0表示没有棋子、1表示黑色棋子、2表示蓝色棋子
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;

        //输出原始二维数组（棋盘）
        System.out.println("原始的二维数组:");
        for (int[] row : chessArr1){
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将原始二维数组（棋盘）转化为稀梳数组
        //1.先遍历原始二维数组（棋盘）中非0的数据的个数
        int sum =0;//记录原始二维数组中非0数据的个数
        for (int i=0;i<chessArr1.length;i++){
            for (int j=0;j<chessArr1[i].length;j++){
                if(chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);

        //2.创建对应的稀梳数组
        int sparseArr[][] = new int[sum+1][3];

        //3.给稀梳数组赋值
        sparseArr[0][0]=chessArr1.length;
        sparseArr[0][1]=chessArr1.length;
        sparseArr[0][2]=sum;

        //4.遍历原始二维数组（棋盘），将非0数据存储到稀梳数组中
        int count =0;//计数器，记录第几个非0数据
        for (int i=0;i<chessArr1.length;i++){
            for (int j=0;j<chessArr1[i].length;j++){
                if(chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀梳数组
        System.out.println("稀疏数组:");
        for (int[] row : sparseArr){
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将稀梳数组恢复成原始的二维数组（棋盘）
        int chessArr2[][] =new int[sparseArr[0][0]][sparseArr[0][1]];

        //遍历原稀梳数组，并将稀梳数组中的元素赋值到原始二维数组（棋盘）中
        int count2=0;//记录稀梳数组有多少个非0数据(从第二行开始)
        for (int i=0;i<sparseArr[0][2];i++){
            count2++;
            chessArr2[sparseArr[count2][0]][sparseArr[count2][1]]=sparseArr[count2][2];
        }

        //输出由稀疏数组转成的原始二维数组（棋盘）
        System.out.println("由稀疏数组转成的原始二维数组:");
        for (int[] row : chessArr2){
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
