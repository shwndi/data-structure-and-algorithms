package com.backTracking;

/**
 * @author czy
 * @description
 * @date 2021-09-02 22:03
 */
public class MyZeroOne{
    static int[] weight = {2,2,4,6,3};
    static int max = 9;
    static int num = 5;
    public static int getMax(int[] weigth, int max,int num){
    //1,初始化第一行
    boolean[][] states =new boolean[num][max];
    states[0][0] = true;
    if (weigth[0]<=max){
        states[0][2] = true;
    }

    }

}
