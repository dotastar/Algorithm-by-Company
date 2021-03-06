package com.leetcode.array;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * Find the minimum element.
 *
 * You may assume no duplicate exists in the array.
 *
 * Created by Xiaomeng on 10/22/2014.
 */
public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] num) {
        int start = 0;
        int end = num.length - 1;

        while(start < end && num[start] > num[end]){
            int mid = start + (end - start)/2;
            if(num[mid] > num[end]){
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        return num[start];
    }

    public static void main(String[] args){
        int[] num = {4, 5, 6, 7, 8, 1, 2, 3};
        FindMinimumInRotatedSortedArray test = new FindMinimumInRotatedSortedArray();
        System.out.println(test.findMin(num));
    }
}
