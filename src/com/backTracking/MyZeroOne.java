package com.backTracking;

/**
 * @author czy
 * @description
 * @date 2021-09-02 22:03
 */
public class MyZeroOne {
    static int[] weight = {2, 2, 4, 6, 3};
    static int max = 9;
    static int num = 5;
    static boolean[][] states;
    static int ME = 0;
    /**
     * 回溯算法实现
     * 递归思想-->添加备忘录
     * @param b 当前背包内重量
     * @param n 第几个
     * cache 备忘录
     */
    static boolean[][] cache = new boolean[num][max+1];

    public static void main(String[] args) {
        //System.out.println(getMax(weight,max,num));
//        System.out.println(getMax2(weight,max,num));
        f2(0, 0);
        System.out.println(ME);
    }

    /**
     * 回溯算法实现
     * 递归思想
     * @param b 当前背包内重量
     * @param n 第几个
     */
    public static void f1(int b, int n) {
        if (b == max || n == num) {
            if (b <= max) {
                ME = max;
            }
            return;
        }
        f1(b, n+1);
        if (max >= b + weight[n]) {
            f1(b + weight[n], n+1);
        }
    }

    public static void f2(int b, int n){
        if (b==max||n==num){
            if (ME<b){
                ME=b;
            }
            return;
        }
        if (cache[n][b]){
            return;
        }
        cache[n][b]=true;
        f2(b,n+1);
        if (weight[n]+b<=max){
            f2(weight[n]+b,n+1 );
        }

    }

    public static int getMax(int[] weigth, int max, int num) {
        //1,初始化第一行
        states = new boolean[num][max + 1];
        states[0][0] = true;
        if (weigth[0] <= max) {
            states[0][weigth[0]] = true;
        }
        //重量为9，加上0状态的之后就会变成10位的数组
        for (int i = 1; i < num; i++) {
            //不计算当前重量
            for (int j = 0; j <= max; j++) {
                if (states[i - 1][j]) {
                    states[i][j] = states[i - 1][j];
                }
//                System.out.print(states[i][j]1:0+" ");

            }
            //因为从上一行移下来的，所以并不会有重复使用的问题
            // 1, 0, 1，0，1  ：a
            //      👇-->2
            // 0, 0, 1, 0, 1 :b   a首位的1 确定b第三个数字的状态
            // 0, 0, 1, 0, 1 :c   a第三位的1，确定c第五位的状态
            for (int j = 0; j <= max - weight[i]; j++) {
                if (states[i - 1][j]) {
                    states[i][j + weigth[i]] = true;
                }
                System.out.print(states[i][j] ? 1 : 0 + " ");
            }
            System.out.println("-----------------------------");
            System.out.println();

        }
        printArr(states);
        int a = 0;
        for (int i = 0; i < num; i++) {
            for (int j = max; j > 0; j--) {
                if (states[i][j] && j > a) {
                    a = j;
                }
            }
        }
        return a;
    }

    public static void printArr(boolean[][] a) {
        int i = a.length;
        int j = a[0].length;
        for (int i1 = 0; i1 < i; i1++) {
            System.out.print("[");
            for (int j1 = 0; j1 < j; j1++) {
                if (a[i1][j1]) {
                    System.out.print(1);
                } else {
                    System.out.print(0);
                }
                if (j1 == j - 1) {
                    continue;
                }
                System.out.print(",");
            }
            System.out.println("]");
        }
    }

    /**
     * 一维数组求解
     *
     * @param weight
     * @param m
     * @param n
     * @return
     */
    public static int getMax2(int[] weight, int m, int n) {
        boolean[] arr = new boolean[m + 1];
        //处理第一步
        arr[0] = true;
        if (weight[0] <= m) {
            arr[weight[0]] = true;
        }
        for (int i = 0; i < n; i++) {
            //不选择时数组不变
            //选择时数组要从后往前变化，防止前面赋值被后面使用
            for (int j = m - weight[i]; j >= 0; j--) {
                if (arr[j]) {
                    arr[j + weight[i]] = true;
                }
            }
        }
        int a = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i]) {
                return i;
            }
        }
        return -1;

    }

}
