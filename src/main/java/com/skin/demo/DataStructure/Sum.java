package com.skin.demo.DataStructure;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/1 15:24
 */
public class Sum {

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int l) {
        if (l == arr.length) {
            return 0;
        }
        return arr[l] + sum(arr, l + 1);
    }

    public static void main(String[] args) {
//        int[] temp ={1,2,3,4,5,6,7,8,9};
//        int sum = Sum.sum(temp);
//        System.out.println(sum);
        String str = "gin";
        System.out.println(str.length());
        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i) - 'a');
        }
        System.out.println('a');
    }

}
