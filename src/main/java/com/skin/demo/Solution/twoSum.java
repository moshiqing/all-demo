package com.skin.demo.Solution;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * @description: 给定一个数组，给定一个数字。返回数组中可以相加得到指定数字的两个索引
 * @author: moshiqing
 * @time: 2020/4/16 15:38
 */
public class twoSum {


    public static int[] twoSumCount(int[] nums, int target) {
        HashMap<Integer, Integer> objectObjectHashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer complement = target - nums[i];
            if (objectObjectHashMap.containsKey(complement)) {
                return new int[]{objectObjectHashMap.get(complement), i};
            }
            objectObjectHashMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSum.twoSumCount(nums, target);
        System.out.println(ints);
    }
}
